package br.ufrn.concorrente;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner;
        scanner = new Scanner(new FileInputStream("arquivo.txt"));

        // FIXME: trocar para nome de arquivo de saída
        PrintStream printStream = new PrintStream(System.out);

        while(scanner.hasNextLine()) {
            // TODO: realiza leitura e trata os dados
        }
        scanner.close();
        printStream.close();

        threadTesting();
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
