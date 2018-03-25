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

        PrintStream printStream = null;
        try {
            printStream = new PrintStream("out/Matrizes" + matrixSide + multiplicationAlgorithm + ".txt");
            printStream.println(matrixA);
            printStream.println(matrixB);

            Matrix matrixC = calculateElapsedTimeUsingReferenceAccess(matrixA, matrixB);
            printStream.println(matrixC);

            printStream.println();
            printStream.println();

            matrixC = calculateElapsedTimeUsingDirectAccess(matrixA, matrixB);
            printStream.println(matrixC);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        } finally {
            printStream.close();
        }
    }

    private static Matrix calculateElapsedTimeUsingDirectAccess(Matrix matrixA, Matrix matrixB) {
        long timeInit = System.nanoTime();
        Matrix matrixC = MatrixUtil.multiplySequential(matrixA, matrixB);
        long timeFinal = System.nanoTime();
        System.out.println("Sequencial - Using direct access:\t" + (timeFinal-timeInit) + "us");
        return matrixC;
    }

    private static Matrix calculateElapsedTimeUsingReferenceAccess(Matrix matrixA, Matrix matrixB) {
        long timeInit = System.nanoTime();
        Matrix matrixC = MatrixUtil.calculateMatrixProduct(matrixA, matrixB);
        long timeFinal = System.nanoTime();
        System.out.println("Sequencial - Using reference access:\t" + (timeFinal-timeInit) + "us");
        return matrixC;
    }

    /**
     * Realiza a leitura do arquivo de matriz da dimensão fornecida.
     * @param side A dimensão da matriz.
     * @return
     * @throws FileNotFoundException
     */
    private static Matrix readMatrixWithSide(String name, int side) throws MatrixBuildException, FileNotFoundException {
        MatrixBuilder builder = new MatrixBuilder();
        Scanner scanner;

        scanner = new Scanner(new FileInputStream(filePathName(name, side)));

        if (scanner.hasNextLine())
            builder.withHeightAndWidth(scanner.nextLine());

        while(scanner.hasNextLine()) {
            builder.readLine(scanner.nextLine());
        }

        scanner.close();
        return builder.build();
    }

    /**
     * Retorna o caminho para o arquivo baseado no nome e dimensão da matriz fornecidos.
     * @param matrix o nome da matriz, A ou B
     * @param side a dimensão da matriz.
     * @return o caminho do arquivo para leitura
     */
    private static String filePathName(String matrix, int side) {
        return "resources/"+matrix + side + "x" + side + ".txt";
    }

//    @Deprecated
//    private static void threadTesting() {
//        Thread thread = new Thread(() -> System.out.println("Thread imprimindo!"));
//
//        // inicializa as threads
//        thread.start();
//
//        // Thread da classe MAIN espera as threads para a execução.
//        try {
//            thread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            System.err.println("Chorou, a interrupção foi interrompida??");
//        }
//    }
}
