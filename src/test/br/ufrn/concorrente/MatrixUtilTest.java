package br.ufrn.concorrente;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class MatrixUtilTest {

    private Matrix matrixA;

    /**
     * Configura a matriz A e B para manipulação nos testes.
     *
     * Matriz A:
     * 1 2 3
     * 4 5 6
     * 7 8 9
     *
     * //TODO: matriz B
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
    }

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
}