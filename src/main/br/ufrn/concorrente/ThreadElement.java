package br.ufrn.concorrente;

/**
 * Thread que calcula um elemento da matriz.
 */
public class ThreadElement extends Thread {


    private final Matrix matrixB;
    private final Matrix matrixA;
    private final Matrix result;
    private final int row;
    private final int column;

    public ThreadElement(String name, Matrix matrixA, Matrix matrixB, Matrix result, int i, int j) {
        super(name);
        this.matrixA = matrixA;
        this.matrixB = matrixB;
        this.result = result;
        this.row = i;
        this.column = j;
    }

    @Override
    public void run() {
        result.setElement(row, column, MatrixUtil.calculateAnElement(matrixA, matrixB, row, column));
    }
}
