package br.ufrn.concorrente;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class MatrixUtilTest {

    private Matrix matrixA;
    private Matrix matrixB;

    /**
     * Configura a matriz A e B para manipulação nos testes.
     *
     * Matriz A:
     * 1 2 3
     * 4 5 6
     * 7 8 9
     *
     * Matriz B:
     * 1 1 1
     * 2 2 2
     * 3 3 3
     */
    @Before
    public void setUp() {
        int value = 1;
        List<List<Integer>> matrixGrid = new ArrayList<>();

        ArrayList<Integer> firstRow = new ArrayList<>();
        firstRow.add(value++);
        firstRow.add(value++);
        firstRow.add(value++);
        matrixGrid.add(firstRow);

        ArrayList<Integer> secondRow = new ArrayList<>();
        secondRow.add(value++);
        secondRow.add(value++);
        secondRow.add(value++);
        matrixGrid.add(secondRow);

        ArrayList<Integer> thirdRow = new ArrayList<>();
        thirdRow.add(value++);
        thirdRow.add(value++);
        thirdRow.add(value++);
        matrixGrid.add(thirdRow);

        matrixA = new Matrix(matrixGrid);

        matrixGrid = new ArrayList<>();

        firstRow = new ArrayList<>();
        firstRow.add(1);
        firstRow.add(1);
        firstRow.add(1);
        matrixGrid.add(firstRow);

        secondRow = new ArrayList<>();
        secondRow.add(2);
        secondRow.add(2);
        secondRow.add(2);
        matrixGrid.add(secondRow);

        thirdRow = new ArrayList<>();
        thirdRow.add(3);
        thirdRow.add(3);
        thirdRow.add(3);
        matrixGrid.add(thirdRow);

        matrixB = new Matrix(matrixGrid);

    }

    /**
     * Garante a corretude no cálculo de um produto de uma fileira (1x3) por uma
     * coluna (3x1), resultado em uma matriz de um elemento (1x1).
     *              1
     * 1 2 3    x   4   =   30
     *              7
     */
    @Test
    public void shouldCalculateAnElement() {
        List<Integer> row = new ArrayList<>();
        row.add(1);
        row.add(2);
        row.add(3);

        List<Integer> column = new ArrayList<>();
        column.add(1);
        column.add(4);
        column.add(7);
        Integer element = MatrixUtil.calculateAnElement(row, column);

        assertThat(element, is(equalTo(30)));
    }

    /**
     * Garante a corretude no calculo do produto de uma fileira de matriz (1x3)
     * por uma matriz (3x3).
     *
     * (1x3) * (3x3)
     *  row     x      A
     *              1 2 3
     * 1 1 1    x   4 5 6   =   12 15 18
     *              7 8 9
     */
    @Test
    public void shouldCalculateARow() {
        List<Integer> row = new ArrayList<>();
        row.add(1);
        row.add(1);
        row.add(1);

        List<Integer> newRow = MatrixUtil.calculateARow(row, matrixA);

        assertThat(newRow, hasItems(12, 15, 18));
        assertThat(newRow.get(0), is(equalTo(12)));
        assertThat(newRow.get(1), is(equalTo(15)));
        assertThat(newRow.get(2), is(equalTo(18)));
    }

    /**
     * Garante a corretude no produto da matriz B (3x3) pela matriz A (3x3),
     * resultando em uma nova matriz (3x3).
     *   B            A
     * 1 1 1        1 2 3
     * 2 2 2    x   4 5 6
     * 3 3 3        7 8 9
     *
     * 1 + 4 + 7 = 12
     * 2 + 5 + 8 = 15
     * 3 + 6 + 9 = 18
     *
     *  2 +  8 + 14 = 24
     *  4 + 10 + 16 = 30
     *  6 + 12 + 18 = 36
     *
     *  3 + 12 + 21 = 36
     *  6 + 15 + 24 = 45
     *  9 + 18 + 27 = 54
     *
     *  12 15 18
     *  24 30 36
     *  36 45 54
     */
    @Test
    public void shouldCalculateAMatrixProduct() {
        Matrix matrix = MatrixUtil.calculateMatrixProduct(matrixB, matrixA);

        assertThat(matrix.getRow(0), hasItems(12, 15, 18));
        assertThat(matrix.getRow(1), hasItems(24, 30, 36));
        assertThat(matrix.getRow(2), hasItems(36, 45, 54));
    }
}