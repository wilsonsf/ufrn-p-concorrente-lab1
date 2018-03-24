package br.ufrn.concorrente;

import java.util.ArrayList;

/**
 * @author Wilson Farias
 */
public class MatrixBuilder {

    private int lineCount = 0;
    private int height = 0;
    private int width = 0;

    public void withHeightAndWidth(String dimensionsString) {
        String[] dimensions = dimensionsString.split(" ");
        if (dimensions.length == 2){
            withHeight(dimensions[0]);
            withWidth(dimensions[1]);
        }
    }

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

        lineCount++;
        return line;
    }

    public Matrix build() throws Exception {
        String validationErrors = "";
        if (width==0)
            validationErrors += "\nNão foi informada a largura.";
        if (height == 0)
            validationErrors += "\nNão foi informada a altura.";

        if (lineCount != height)
            validationErrors += "\nNão foram lidas todas as linhas da matriz.";


        if (validationErrors.isEmpty()) {
            return new Matrix();
        } else {
            throw new Exception(validationErrors);
        }
    }


}
