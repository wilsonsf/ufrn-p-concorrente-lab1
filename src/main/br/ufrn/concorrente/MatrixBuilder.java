package br.ufrn.concorrente;

import br.ufrn.concorrente.exceptions.MatrixBuildException;

import java.util.ArrayList;
import java.util.List;

/**
 * Uma classe que segue o padrão Builder para construção de matrizes a partir de
 * String - da leitura do arquivo.
 * @author Wilson Farias
 */
public final class MatrixBuilder {

    private List<List<Integer>> lines = new ArrayList<>();
    private int height = 0;
    private int width = 0;

    /**
     * Recebe uma string com os parâmetros de altura e largura no formato: "A L"
     * , onde A é altura e L largura.
     * @param dimensionsString string com parâmetros
     * @return o próprio objeto builder
     */
    public MatrixBuilder withHeightAndWidth(final String dimensionsString) {
        String[] dimensions = dimensionsString.split(" ");
        if (dimensions.length == 2) {
            withHeight(dimensions[0]);
            withWidth(dimensions[1]);
        }
        return this;
    }

    protected int withHeight(final String newHeight) {
        this.height = Integer.parseInt(newHeight);
        return this.height;
    }

    public int getHeight() {
        return height;
    }

    protected int withWidth(final String newWidth) {
        this.width = Integer.parseInt(newWidth);
        return this.width;
    }

    public int getWidth() {
        return width;
    }

    protected int readElement(final String elementString) {
        return Integer.parseInt(elementString);
    }

    public List<Integer> readLine(final String lineString) {
        ArrayList<Integer> line = new ArrayList<>();

        String[] elements = lineString.split(" ");
        for (String element : elements) {
            int value = readElement(element);
            line.add(value);
        }

        lines.add(line);
        return line;
    }

    /**
     * Constrói a Matrix a partir dos dados armazenados na classe, fornecidos
     * pelos outros métodos.
     * @return uma Matrix
     * @throws MatrixBuildException quando algum dado não foi fornecido
     * corretamente
     */
    public Matrix build() throws MatrixBuildException {
        String validationErrors = "";
        if (width == 0) {
            validationErrors += "\nNão foi informada a largura.";
        }
        if (height == 0) {
            validationErrors += "\nNão foi informada a altura.";
        }

        if (lines.size() != height) {
            validationErrors += "\nNão foram lidas todas as linhas da matriz.";
        }


        if (validationErrors.isEmpty()) {
            return new Matrix(lines);
        } else {
            throw new MatrixBuildException(validationErrors);
        }
    }
}
