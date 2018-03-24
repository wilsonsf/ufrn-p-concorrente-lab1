package br.ufrn.concorrente;

public class MatrixBuilder {

    private int height = 0;
    private int width = 0;

    public int withHeight(String height) {
        this.height = Integer.parseInt(height);
        return this.height;
    }

    public int getHeight() {
        return height;
    }

    public int withWidth(String width) {
        this.width = Integer.parseInt(width);;
        return this.width;
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
