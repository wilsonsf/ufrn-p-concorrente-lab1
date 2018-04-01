package br.ufrn.concorrente;

import java.util.ArrayList;
import java.util.List;

/**
 * Essa classe representa uma abstração do objeto Matriz e possui as operações
 * elementares para criação, e leitura e atualização de elementos, bem como
 * uma formatação do objeto para String.
 * @author Jonathan Rocha
 */
public class Matrix {

    /** Armazena os elementos da matriz para manipulação. */
    private List<List<Integer>> elementsMatrix;

    /**
     * Construtor que inicializa uma matriz da ordem fornecida e inicializa seus
     * elementos com 0 (zero).
     * @param side a ordem da matriz
     */
    public Matrix(final Integer side) {
        elementsMatrix = new ArrayList<>();
        for (int i = 0; i < side; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < side; j++) {
                row.add(0);
            }
            elementsMatrix.add(row);
        }
    }

    /**
     * Construtor da Matriz.
     * @param elements matriz de elementos do tipo Integer
     */
    public Matrix(final List<List<Integer>> elements) {
        this.elementsMatrix = elements;
    }

    /**
     * Retorna o lado da matriz.
     * @return retorna a ordem da matriz.
     */
    public int getSide() {
        return elementsMatrix.size();
    }

    /**
     * Retorna um elemento da matriz.
     * @param column coluna do elemento.
     * @param line linha do elemento.
     * @return o elemento que está na posição (linha, coluna).
     */
    public int getElement(final int line, final int column) {
        return elementsMatrix.get(line).get(column);
    }

    /**
     * Modifica um elemento da matriz.
     * @param line linha do elemento a ser modificado
     * @param column coluna do elemento a ser modificado
     * @param value novo valor do elemento na coordenada (linha, coluna)
     */
    public void setElement(final int line, final int column, final int value) {
        elementsMatrix.get(line).set(column, value);
    }

    /**
     * Retorna uma linha da matriz.
     * @param line número da linha da matriz, 0 a n-1
     * @return retorna uma lista com os elementos da linha solicitada.
     */
    public List<Integer> getRow(final int line) {
        return elementsMatrix.get(line);
    }

    /**
     * Retorna uma coluna da matriz.
     * @param column número da coluna da matriz, 0 a n-1
     * @return retorna uma lista com os elementos da coluna solicitada.
     */
    public List<Integer> getColumn(final int column) {

        ArrayList<Integer> columnToBeReturned = new ArrayList<>();

        for (List<Integer> row : elementsMatrix) {
            columnToBeReturned.add(row.get(column));
        }
        return columnToBeReturned;
    }

    /**
     * Retorna uma Matriz imprimível como uma String.
     * @return uma String representando o estado atual da matriz
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (List<Integer> row : elementsMatrix) {
            for (Integer element : row) {
                builder.append(element);
                builder.append(" ");
            }
            builder.setCharAt(builder.length() - 1, '\n');
        }
        return builder.toString();
    }
}
