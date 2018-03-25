package br.ufrn.concorrente;

import br.ufrn.concorrente.exceptions.MatrixBuildException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wilson Farias
 */
public class MatrixBuilder {

    private List<List<Integer>> lines = new ArrayList<>();
    private int height = 0;
    private int width = 0;

    public MatrixBuilder withHeightAndWidth(String dimensionsString) {
        String[] dimensions = dimensionsString.split(" ");
        if (dimensions.length == 2){
            withHeight(dimensions[0]);
            withWidth(dimensions[1]);
        }
        return this;
    }

    protected int withHeight(String height) {
        this.height = Integer.parseInt(height);
        return this.height;
    }

    public int getHeight() {
        return height;
    }

    protected int withWidth(String width) {
        this.width = Integer.parseInt(width);
        return this.width;
    }

    public int getWidth() {
        return width;
    }

    protected int readElement(String elementString) {
        return Integer.parseInt(elementString);
    }

    public List<Integer> readLine(String lineString) {
        ArrayList<Integer> line = new ArrayList<>();

        String[] elements = lineString.split(" ");
        for (String element : elements) {
            int value = readElement(element);
            line.add(value);
        }

        lines.add(line);
        return line;
    }

    public Matrix build() throws MatrixBuildException {
        String validationErrors = "";
        if (width==0)
            validationErrors += "\nNão foi informada a largura.";
        if (height == 0)
            validationErrors += "\nNão foi informada a altura.";

        if (lines.size() != height)
            validationErrors += "\nNão foram lidas todas as linhas da matriz.";


        if (validationErrors.isEmpty()) {
            return new Matrix(lines);
        } else {
            throw new MatrixBuildException(validationErrors);
        }
    }
}
