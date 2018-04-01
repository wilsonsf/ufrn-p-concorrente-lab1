package br.ufrn.concorrente;

import java.util.ArrayList;
import java.util.List;

/**
 * Uma classe utilitária que auxilia no cálculo do produto de matrizes.
 * @author Wilson Farias
 */
public final class MatrixUtil {

    /**
     * Escondendo o construtor da classe utilitária.
     */
    private MatrixUtil() { }

    /**
     * Algoritmo sequencial para multiplicação de matrizes.
     * @param matrixA uma matriz
     * @param matrixB outra matriz
     * @return a matriz resultado do produto
     */
    public static Matrix multiplySequential(final Matrix matrixA,
                                            final Matrix matrixB) {

        // Alocacao de ArrayList de ArrayList da matriz resultado
        List<List<Integer>> resultElements = new ArrayList<>();
        for (int i = 0; i < matrixA.getSide(); i++) {
            resultElements.add(new ArrayList<>());
            for (int j = 0; j < matrixA.getSide(); j++) {
                resultElements.get(i).add(0);
            }
        }

        Matrix resultMatrix = new Matrix(resultElements);

        // ================= Comeco da multiplicação da matriz =================
        for (int i = 0; i < resultMatrix.getSide(); i++) {
            for (int j = 0; j < resultMatrix.getSide(); j++) {
                int resultado = 0;

                for (int k = 0; k < resultMatrix.getSide(); k++) {
                    resultado += matrixA.getElement(i, k)
                            * matrixB.getElement(k, j);
                }

                resultMatrix.setElement(i, j, resultado);
            }
        }

        // ================= Fim da multiplicação da matriz =================

        return resultMatrix;
    }

    /**
     * Calcula o produto de duas matrizes A e B e retorna a matriz resultado.
     * @param matrixA Uma matriz
     * @param matrixB Outra matriz
     * @return A matriz resultado do produto
     */
    protected  static Matrix calculateAMatrixProduct(final Matrix matrixA,
                                                   final Matrix matrixB) {

        int side = matrixA.getSide();
        Matrix resultMatrix = new Matrix(side);
        for (int i = 0; i < side; i++) {
            calculateARow(matrixA, matrixB, i, resultMatrix);
        }

        return resultMatrix;
    }

    /**
     * Dadas as duas matrizes do produto, uma linha, calcula e atribui os
     * elementos na matriz resultado.
     * @param matrixA primeira matriz
     * @param matrixB segunda matriz
     * @param row fileira que deve ser calculada
     * @param resultMatrix a matriz com os valores da fileira atribuídos
     */
    protected  static void calculateARow(final Matrix matrixA,
                                         final Matrix matrixB,
                                         final int row,
                                         Matrix resultMatrix) {

        int side = resultMatrix.getSide();
        for (int column = 0; column < side; column++) {
            Integer element = calculateAnElement(matrixA, matrixB, row, column);
            resultMatrix.setElement(row, column, element);
        }
    }

    /**
     * Calcula o elemento na posição (row x column), no produto entre as
     * matrizes matrixA e matrixB.
     * @param matrixA primeira matriz
     * @param matrixB segunda matriz
     * @param row uma fileira.
     * @param column uma coluna.
     * @return o valor do elemento calculado
     */
    protected static Integer calculateAnElement(final Matrix matrixA,
                                                final Matrix matrixB,
                                                final int row,
                                                final int column) {
        int resultado = 0;

        int side = matrixA.getSide();
        for (int k = 0; k < side; k++) {
            resultado += matrixA.getElement(row, k)
                    * matrixB.getElement(k, column);
        }

        return resultado;
    }

    /**
     * Calcula um elemento do produto entre uma fileira e uma coluna,
     * de n elementos cada.
     * @param row uma fileira.
     * @param column uma coluna.
     * @return o produto entre a fileira e a coluna.
     * @deprecated não mais utilizado no cálculo
     */
    @Deprecated(since = "2018/04/01", forRemoval = true)
    protected static Integer calculateAnElement(final List<Integer> row,
                                                final List<Integer> column) {
        int sum = 0;
        for (int i = 0; i < row.size(); i++) {
            sum += row.get(i) * column.get(i);
        }
        return sum;
    }

    /**
     * Dada uma linha e uma matriz, calcula uma nova linha como parte do calculo
     * do produto de matrizes.
     * @param row uma fileira de uma matriz.
     * @param matrix uma matriz.
     * @return uma fileira resultado do produto de matrizes.
     * @deprecated a cópia de elementos aumenta consideravalmente o tempo de
     * execução.
     */
    @Deprecated(since = "2018/04/01", forRemoval = true)
    public static List<Integer> calculateARow(final List<Integer> row,
                                              final Matrix matrix) {
        List<Integer> newRow = new ArrayList<>();

        for (int i = 0; i < matrix.getSide(); i++) {
            Integer newElement = calculateAnElement(row, matrix.getColumn(i));
            newRow.add(newElement);
        }

        return newRow;
    }

    /**
     * Calcula o produto de duas matrizes A e B e retorna a matriz resultado.
     * @param matrixA Uma matriz
     * @param matrixB Outra matriz
     * @return A matriz resultado do produto
     * @deprecated novo método de cálculo incluído
     */
    @Deprecated(forRemoval = true)
    public static Matrix calculateMatrixProduct(final Matrix matrixA,
                                                final Matrix matrixB) {
        return calculateAMatrixProduct(matrixA, matrixB);
    }
}
