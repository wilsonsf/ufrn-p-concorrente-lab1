package br.ufrn.concorrente;

public class ThreadRow extends Thread{

    private final Matrix matrixB;
    private final Matrix matrixA;
    private final Matrix result;
    private final int row;

    public ThreadRow(String name, Matrix matrixA, Matrix matrixB, Matrix result, int row) {
        super(name);
        this.matrixA = matrixA;
        this.matrixB = matrixB;
        this.result = result;
        this.row = row;
    }

    @Override
    public void run() {
        MatrixUtil.calculateARow(matrixA, matrixB, row, result);
    }
}
