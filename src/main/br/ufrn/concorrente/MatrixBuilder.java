package br.ufrn.concorrente;

import java.util.ArrayList;

/**
 * @author Wilson Farias, Jonathan Rocha
 */
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

    protected ArrayList<Integer> readLine(String lineString) {
        ArrayList<Integer> line = new ArrayList<>();

        String[] elements = lineString.split(" ");
        for (String element : elements) {
            int value = readElement(element);
            line.add(value);
        }

        return line;
    }

    public Matrix build() {
        return new Matrix();
    }
}
