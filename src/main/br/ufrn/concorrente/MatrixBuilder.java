package br.ufrn.concorrente;

public class MatrixBuilder {

    private int height = 0;
    private int width = 0;

    public void withHeight(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void withWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    protected int readElement(String elementString) {
        return Integer.parseInt(elementString);
    }

    public int[] readLine(String lineString) {
        return new int[0];
    }
}
