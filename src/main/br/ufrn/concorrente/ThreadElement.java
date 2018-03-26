package br.ufrn.concorrente;

/**
 * Thread que calcula um elemento da matriz.
 */
public class ThreadElement extends Thread {


    private final Matrix matrixB;
    private final Matrix matrixA;
    private final Matrix result;
    private final int i;
    private final int j;

    public ThreadElement(String name, Matrix matrixA, Matrix matrixB, Matrix result, int i, int j) {
        super(name);
        this.matrixA = matrixA;
        this.matrixB = matrixB;
        this.result = result;
        this.i = i;
        this.j = j;
    }

    @Override
    public void run() {
        result.setElement(i, j, MatrixUtil.calculateAnElement(matrixA.getRow(i), matrixB.getColumn(j)));
    }
}
