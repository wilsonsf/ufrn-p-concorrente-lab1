package br.ufrn.concorrente;

import java.util.ArrayList;
import java.util.List;

public class MatrixUtil {

    /**
     * Algoritmo sequencial para multiplicação de matrizes.
     * @param A uma matriz
     * @param B outra matriz
     * @return a matriz resultado do produto
     */
    public static Matrix multiplySequential(Matrix A, Matrix B) {

        // Alocacao de ArrayList de ArrayList da matriz resultado
        List<List<Integer>> resultElements = new ArrayList<>();
        for (int i = 0; i < A.getSide(); i++) {
            resultElements.add(new ArrayList<>());
            for (int j = 0; j < A.getSide(); j++) {
                resultElements.get(i).add(0);
            }
        }

        Matrix resultMatrix = new Matrix(resultElements);

        // ==================== Comeco da multiplicação da matriz ====================
        for (int i = 0; i < resultMatrix.getSide(); i++) {
            for (int j = 0; j < resultMatrix.getSide(); j++) {
                int resultado = 0;

                for (int k = 0; k < resultMatrix.getSide(); k++) {
                    resultado += A.getElement(i, k) * B.getElement(k, j);
                }

                resultMatrix.setElement(i, j, resultado);
            }
        }

        // ====================== Fim da multiplicação da matriz ======================

        return resultMatrix;
    }

    /**
     * Calcula um elemento do produto entre uma fileira e uma coluna,
     * de n elementos cada.
     * @param row uma fileira.
     * @param column uma coluna.
     * @return o produto entre a fileira e a coluna.
     */
    protected static Integer calculateAnElement(List<Integer> row, List<Integer> column) {
        int sum = 0;
        for (int i = 0; i < row.size() ; i++) {
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
     */
    public static List<Integer> calculateARow(List<Integer> row, Matrix matrix) {
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
     */
    public static Matrix calculateMatrixProduct(Matrix matrixA, Matrix matrixB) {
        List<List<Integer>> gridResult = new ArrayList<>();

        for (int i = 0; i < matrixA.getSide(); i++) {
            gridResult.add(calculateARow(matrixA.getRow(i), matrixB));
        }

        return new Matrix(gridResult);
    }
}