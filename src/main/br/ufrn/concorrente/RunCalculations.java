package br.ufrn.concorrente;

public class RunCalculations {

    public static void main (String[] args) {

        for (int i = 4; i <= 2048; i *= 2) {

            System.out.printf("Dimension: %d%n%n", i);

            String[] sequential = {String.valueOf(i), "S"};
            Main.main(sequential);

            System.out.println();

            String[] concurrent = {String.valueOf(i), "C"};
            Main.main(concurrent);

            System.out.println();
            System.out.println();
        }
    }
}
