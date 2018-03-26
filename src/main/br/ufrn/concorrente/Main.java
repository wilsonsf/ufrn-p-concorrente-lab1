package br.ufrn.concorrente;

import br.ufrn.concorrente.exceptions.MatrixBuildException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Jonathan Rocha, Wilson Farias
 */
public class Main {

    private static int matrixSide;
    private static String multiplicationAlgorithm;
    private static PrintStream statisticsDataStreamStream;
    private static PrintStream matrixOutputStream;

    public static void main(String[] args) {

        if (args.length < 2) {
            System.err.println("ERROR: Not enough arguments to continue. Please insert two arguments.");
            return;
        }

        try {
            matrixSide = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.err.println("ERROR: Invalid parameter for matrix size.");
            return;
        }
        multiplicationAlgorithm = args[1];

        // Impressao das informacoes sobre os argumentos passados por linha de comando
        System.out.println("Matrix side: " + matrixSide);

        if (multiplicationAlgorithm.equalsIgnoreCase("S")) {
            System.out.println("Algorithm to be used: sequential");
        } else if (multiplicationAlgorithm.equalsIgnoreCase("C")) {
            System.out.println("Algorithm to be used: concurrent");
        } else {
            System.err.println("ERROR: The input for the algorithm is invalid. Please input 'S' for sequential or 'C' for concurrent.");
            return;
        }

        Matrix matrixA;
        Matrix matrixB;
        try {
            matrixA = readMatrixWithSide("A", matrixSide);
            matrixB = readMatrixWithSide("B", matrixSide);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println(e.getLocalizedMessage());
            return;
        } catch (MatrixBuildException e) {
            System.err.println(e.getLocalizedMessage());
            return;
        }

        calculateAndWriteMatrix(matrixA, matrixB);

        generateStatisticsDataWithMatrix(matrixA, matrixB);
    }

    private static void generateStatisticsDataWithMatrix(Matrix matrixA, Matrix matrixB) {

        Statistics statistics = new Statistics();
        for (int i = 0; i < 20; i++) {
            long timeElapsed = calculateElapsedTimeUsingReferenceAccess(matrixA, matrixB);
            statistics.addData(Double.valueOf(timeElapsed));
        }

        statisticsDataStreamStream = null;
        try {
            statisticsDataStreamStream = new PrintStream("out/Statistics-" + multiplicationAlgorithm + "-"
                    + fileNameWith("C", matrixSide));

            statisticsDataStreamStream.println(statistics);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        } finally {
            statisticsDataStreamStream.close();
        }
    }

    /**
     * Calcula o resultado da multiplicação e escreve no arquivo de saída
     * Cixi.txt, onde i é a dimensão da matriz.
     * @param matrixA uma matriz
     * @param matrixB outra matriz
     */
    private static void calculateAndWriteMatrix(Matrix matrixA, Matrix matrixB) {
        matrixOutputStream = null;
        try {
            matrixOutputStream = new PrintStream("out/" + fileNameWith("C", matrixSide));
            Matrix matrixC = MatrixUtil.multiplySequential(matrixA, matrixB);
            matrixOutputStream.println(matrixC);
        } catch (FileNotFoundException e) {
            // Nunca vai acontecer, vai criar o arquivo
            e.printStackTrace();
        } finally {
            matrixOutputStream.close();
        }
    }

    /**
     * Calcula o tempo de execução da multiplicação de duas matrizes fornecidas.
     * @param matrixA uma matriz
     * @param matrixB outra matriz
     * @return o tempo de execução em nanosegundos
     */
    private static long calculateElapsedTimeUsingReferenceAccess(Matrix matrixA, Matrix matrixB) {
        long initTime = System.nanoTime();
        MatrixUtil.calculateMatrixProduct(matrixA, matrixB);
        long endTime = System.nanoTime();
        System.out.println("Sequencial - Using reference access:\t" + (endTime-initTime) + " us\t" +
                (endTime-initTime)/1000000000 + " s");
        return endTime-initTime;
    }

    /**
     * Realiza a leitura do arquivo de matriz da dimensão fornecida.
     * @param side A dimensão da matriz.
     * @return
     * @throws FileNotFoundException
     * @throws MatrixBuildException
     */
    private static Matrix readMatrixWithSide(String name, int side) throws MatrixBuildException, FileNotFoundException {
        MatrixBuilder builder = new MatrixBuilder();
        Scanner scanner;

        scanner = new Scanner(new FileInputStream("resources/" + fileNameWith(name, side)));

        if (scanner.hasNextLine())
            builder.withHeightAndWidth(scanner.nextLine());

        while(scanner.hasNextLine()) {
            builder.readLine(scanner.nextLine());
        }

        scanner.close();
        return builder.build();
    }

    /**
     * Retorna o nome do arquivo baseado em uma letra e a dimensão da matriz
     * fornecidos.
     * @param letter o nome da matriz, A ou B
     * @param side a dimensão da matriz
     * @return o caminho do arquivo para leitura
     */
    private static String fileNameWith(String letter, int side) {
        return letter + side + "x" + side + ".txt";
    }
}
