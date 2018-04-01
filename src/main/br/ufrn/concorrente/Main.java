package br.ufrn.concorrente;

import br.ufrn.concorrente.exceptions.MatrixBuildException;

import java.io.File;
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
    private static Matrix matrixA;
    private static Matrix matrixB;
    private static final int NUMBER_OF_RUNS = 20;
    private static Matrix matrixC;

    /**
     * Método principal do projeto que espera 2 argumentos:
     * <ul>
     * <li>O primeiro representa a ordem (tamanho) da matriz, apenas potências de
     * 2 estão disponíveis a até 2048.</li>
     * <li>O segundo representa a estratégia do algoritmo, S ou C, para Sequencial
     * e Concorrente, respectivamente.</li>
     * </ul>
     * @param args ordem e algoritmo
     * @throws InterruptedException
     */
    public static void main(final String[] args) {

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

        if (!multiplicationAlgorithm.equalsIgnoreCase("S")
                && !multiplicationAlgorithm.equalsIgnoreCase("C")) {
            System.err.println("ERROR: The input for the algorithm is invalid. Please input 'S' for sequential or 'C' for concurrent.");
            return;
        }

        if (!readMatricesFromFiles()) {
            return;
        }

        if (multiplicationAlgorithm.equalsIgnoreCase("S")) {
            System.out.println("Algorithm to be used: sequential");
        } else {
            System.out.println("Algorithm to be used: concurrent");
        }
        computeAndWriteMatrix(matrixA, matrixB);
        generateStatisticsDataWithMatrix(matrixA, matrixB);

        System.out.println("Done!");
    }

    /**
     * Realiza a leitura das matrizes a partir dos arquivos no formato definido
     * ?ixi.txt, onde ? será A ou B, e i, será substituido pela ordem da matriz.
     * @return se a leitura ocorreu corretamente
     */
    private static boolean readMatricesFromFiles() {
        try {
            matrixA = readMatrixWithSide("A", matrixSide);
            matrixB = readMatrixWithSide("B", matrixSide);
        } catch (FileNotFoundException | MatrixBuildException e) {
            System.err.println(e.getLocalizedMessage());
            return false;
        }
        return true;
    }

    /**
     * Calcula o resultado da multiplicação e escreve no arquivo de saída
     * Cixi.txt, onde i é a dimensão da matriz.
     * @param matrixA uma matriz
     * @param matrixB outra matriz
     */
    private static void computeAndWriteMatrix(final Matrix matrixA, final Matrix matrixB) {
        PrintStream matrixOutputStream = null;
        try {
            File file = new File("out/"
                    + fileNameWith("C", matrixSide));
            if (file.exists()){
                return;
            } else {
                file.createNewFile();
            }

            Matrix matrixC = MatrixUtil.multiplySequential(matrixA, matrixB);

            matrixOutputStream = new PrintStream(file);
            matrixOutputStream.println(matrixC);

        } catch (java.io.IOException e) {
            e.printStackTrace();
            /*
             * Não lançará, a menos que não tenha permissão para escrever, nesse
             * caso foi triste.
             */
        } finally {
            if (matrixOutputStream != null) {
                matrixOutputStream.close();
            }
        }
    }

    /**
     * Gera os dados estatísticos para as matrizes fornecidas e armazena em
     * arquivo no formato <i>"Statistics-?-Cixi.txt"</i>, onde o <b>?</b> pode ser S ou C
     * conforme estratégia e o <b>i</b> é substituído pela ordem da matriz.
     * @param matrixA uma matriz
     * @param matrixB outra matriz
     */
    private static void generateStatisticsDataWithMatrix(Matrix matrixA, Matrix matrixB) {

        final double NANO_TO_SECONDS = 1000000000.0;

        Statistics statistics = new Statistics();

        for (int i = 0; i < NUMBER_OF_RUNS; i++) {
            long timeElapsed = computeElapsedTime(matrixA, matrixB);

            if(i==0) {
                System.out.printf("%d us\t", timeElapsed);
                System.out.printf("%.4f s%n", timeElapsed/NANO_TO_SECONDS);
            }

            statistics.addData(Double.valueOf(timeElapsed));
            System.out.print(".");
        }

        PrintStream statisticsDataStreamStream = null;
        try {
            statisticsDataStreamStream = new PrintStream("out/" + generateStatisticsFileName());

            statisticsDataStreamStream.println(statistics);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            /*
             * Não lançará, a menos que não tenha permissão para escrever, nesse
             * caso foi triste.
             */
        } finally {
            if (statisticsDataStreamStream != null) {
                statisticsDataStreamStream.close();
            }
        }
    }

    /**
     * Calcula o tempo de execução da multiplicação de duas matrizes fornecidas
     * e retorna o tempo de execução em nanossegundos.
     * @param matrixA uma matriz
     * @param matrixB outra matriz
     * @return o tempo de execução em nanosegundos
     */
    private static long computeElapsedTime(Matrix matrixA, Matrix matrixB) {
        long initTime = System.nanoTime();

        if (multiplicationAlgorithm.equalsIgnoreCase("S")) {
            MatrixUtil.calculateMatrixProduct(matrixA, matrixB);
        } else {
            try {
                concurrentExecution();
            } catch (InterruptedException e) {
                // FIXME: o que faz nessa situação?
            }
        }
        long endTime = System.nanoTime();
        return endTime - initTime;
    }

    /**
     * Realiza a rotina de cálculo de matriz com uso de concorrência.
     * @throws InterruptedException caso a thread seja interrompida durante a
     * espera pode lançar essa exceção
     */
    private static void concurrentExecution() throws InterruptedException {
//        concurrentImplementationByElement();
        concurrentImplementationByRow();
    }

    private static void concurrentImplementationByRow() throws InterruptedException {
        matrixC = new Matrix(matrixSide);
        for (int row = 0; row < matrixSide; row++) {
            ThreadRow thread = new ThreadRow(
                    String.format("Row: %d", row),
                    matrixA,
                    matrixB,
                    matrixC,
                    row);
                thread.start();

                thread.join();
        }
    }

    private static void concurrentImplementationByElement() throws InterruptedException {
        matrixC = new Matrix(matrixSide);
        for (int i = 0; i < matrixSide; i++) {
            for (int j = 0; j < matrixSide; j++) {
                ThreadElement thread = new ThreadElement(
                        String.format("(%d, %d)", i, j),
                        matrixA, matrixB, matrixC, i, j);
                thread.start();

                thread.join();
            }
        }
    }

    /**
     * Realiza a leitura do arquivo de matriz da dimensão fornecida.
     * @param side A dimensão da matriz.
     * @return uma matriz a partir do arquivo
     * @throws MatrixBuildException caso o arquivo não possua todos os dados da
     * matriz
     * @throws FileNotFoundException caso o arquivo não exista
     */
    private static Matrix readMatrixWithSide(final String name, final int side) throws MatrixBuildException, FileNotFoundException {
        MatrixBuilder builder = new MatrixBuilder();
        Scanner scanner;

        scanner = new Scanner(new FileInputStream("resources/" + fileNameWith(name, side)));

        if (scanner.hasNextLine()) {
            builder.withHeightAndWidth(scanner.nextLine());
        }

        while (scanner.hasNextLine()) {
            builder.readLine(scanner.nextLine());
        }

        scanner.close();
        return builder.build();
    }

    private static String generateStatisticsFileName() {
        return "Statistics-" + multiplicationAlgorithm + "-"
                + fileNameWith("C", matrixSide);
    }

    /**
     * Retorna o nome do arquivo baseado em uma letra e a dimensão da matriz
     * fornecidos.
     * @param letter o nome da matriz, A ou B
     * @param side a dimensão da matriz
     * @return o caminho do arquivo para leitura
     */
    private static String fileNameWith(final String letter, final int side) {
        return letter + side + "x" + side + ".txt";
    }
}
