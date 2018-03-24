package br.ufrn.concorrente;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class MatrixTest {

    private final static int MATRIX_ORDER = 3;
    private Matrix matrix;

    /**
     * Configura uma matriz de ordem 3 para realização de testes.
     * 1 2 3
     * 4 5 6
     * 7 8 9
     */
    @Before
    public void setUp() {
        List<List<Integer>> matrixGrid = new ArrayList<>();

        ArrayList<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        firstRow.add(2);
        firstRow.add(3);
        matrixGrid.add(firstRow);

        ArrayList<Integer> secondRow = new ArrayList<>();
        secondRow.add(4);
        secondRow.add(5);
        secondRow.add(6);
        matrixGrid.add(secondRow);

        ArrayList<Integer> thirdRow = new ArrayList<>();
        thirdRow.add(7);
        thirdRow.add(8);
        thirdRow.add(9);
        matrixGrid.add(thirdRow);

        matrix = new Matrix(matrixGrid);
    }

    @Test
    public void shouldCreateAFirstOrderSquareMatrix() {
        List<List<Integer> > matrixGrid = new ArrayList<>();
        List<Integer> row = new ArrayList<>();

        row.add(9);
        matrixGrid.add(row);

        matrix = new Matrix(matrixGrid);

        assertThat(matrix.getSide(), is(equalTo(1)));
        assertThat(matrix.getElement(0,0), is(equalTo(9)));
    }

    @Test
    public void shouldReturnMatrixSide() {
        assertThat(matrix.getSide(), is(equalTo(3)));
    }

    @Test
    public void shouldReturnAnElementFromTheMatrix() {
        assertThat(matrix.getElement(0,0), is(equalTo(1)));
        assertThat(matrix.getElement(2,2), is(equalTo(9)));
    }

    @Test
    public void shouldSetAnElementInTheMatrix() {
        matrix.setElement(1,1,-99);
        assertThat(matrix.getElement(1,1), is(equalTo(-99)));
    }

    @Test
    public void shouldReturnALine() {
        assertThat(matrix.getLine(0), hasItems(1, 2, 3));
        assertThat(matrix.getLine(1), hasItems(4, 5, 6));
        assertThat(matrix.getLine(2), hasItems(7, 8, 9));
    }

    @Test
    public void shouldReturnAColumn() {
        assertThat(matrix.getColumn(0), hasItems(1,4,7));
        assertThat(matrix.getColumn(1), hasItems(2,5,8));
        assertThat(matrix.getColumn(2), hasItems(3,6,9));
    }

    @Test
    public void shouldFormatToString() {
        assertThat(matrix.toString(), is(equalTo("1 2 3\n4 5 6\n7 8 9\n")));
    }
}