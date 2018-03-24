package br.ufrn.concorrente;

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

    public static void main(String[] args) throws FileNotFoundException {

        if (args.length < 2) {
            System.out.println("ERROR: Not enough arguments to continue. Please insert two arguments.");
            return;
        }

        matrixSide = Integer.parseInt(args[0]);
        multiplicationAlgorithm = args[1];

        // Impressao das informacoes sobre os argumentos passados por linha de comando
        System.out.println("Matrix side: " + matrixSide);

        Matrix matrixA = readMatrixWithSide("A", matrixSide);
        Matrix matrixB = readMatrixWithSide("B", matrixSide);

        if (multiplicationAlgorithm.equalsIgnoreCase("S")) {
            System.out.println("Algorithm to be used: sequential");
        } else if (multiplicationAlgorithm.equalsIgnoreCase("C")){
            System.out.println("Algorithm to be used: concurrent");
        } else {
            System.out.println("ERROR: The input for the algorithm is invalid. Please input 'S' for sequential or 'C' for concurrent.");
            return;
        }

        // FIXME: trocar para nome de arquivo de saída
        PrintStream printStream = new PrintStream("out/Matrizes"+matrixSide+multiplicationAlgorithm+".txt");
        printStream.println(matrixA);
        printStream.println(matrixB);
        printStream.close();

        threadTesting();
    }

    /**
     * Realiza a leitura do arquivo de matriz da dimensão fornecida.
     * @param side A dimensão da matriz.
     * @return
     * @throws FileNotFoundException
     */
    private static Matrix readMatrixWithSide(String name, int side) {
        MatrixBuilder builder = new MatrixBuilder();
        Scanner scanner;

        try {
            scanner = new Scanner(new FileInputStream(filePathName(name, side)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        if (scanner.hasNextLine())
            builder.withHeightAndWidth(scanner.nextLine());

        while(scanner.hasNextLine()) {
            builder.readLine(scanner.nextLine());
        }

        scanner.close();
        try {
            return builder.build();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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

    private static void threadTesting() {
        Thread thread = new Thread(() -> System.out.println("Thread imprimindo!"));

        // inicializa as threads
        thread.start();

        // Thread da classe MAIN espera as threads para a execução.
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.err.println("Chorou, a interrupção foi interrompida??");
        }
    }
}
