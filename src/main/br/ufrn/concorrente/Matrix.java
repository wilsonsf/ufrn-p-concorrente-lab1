package br.ufrn.concorrente;

import java.util.ArrayList;

/**
 * @author Jonathan Rocha
 */
public class Matrix {

    /**
     * Atributos
     */
    private ArrayList<ArrayList<Integer>> elements;

    /**
     * Construtor
     * @param elements
     */
    public Matrix(ArrayList<ArrayList<Integer>> elements) {
        this.elements = elements;
    }

    /**
     * Retorna o lado da matriz
     * @return
     */
    public int getSide() {
        return elements.size();
    }

    /**
     * Retorna um elemento da matriz
     * @param column
     * @param line
     * @return
     */
    public int getElement(int line, int column) {
        return elements.get(line).get(column);
    }

    /**
     * Modifica um elemento da matriz
     * @param line
     * @param column
     * @param value
     */
    public void setElement(int line, int column, int value) {
        elements.get(line).set(column, value);
    }

    /**
     * Retorna uma linha da matriz
     * @param line
     * @return
     */
    public ArrayList<Integer> getLine(int line) {
        return elements.get(line);
    }

    /**
     * Retorna uma coluna da matriz
     * @param column
     * @return
     */
    public ArrayList<Integer> getColumn(int column) {

        ArrayList<Integer> columnToBeReturned = new ArrayList<Integer>();

        for (int i = 0; i < elements.size(); i++) {
            columnToBeReturned.add(elements.get(i).get(column));
        }

        return columnToBeReturned;
    }

    /**
     * Retorna toda a matriz
     * @return
     */
    public ArrayList<ArrayList<Integer>> getAllElements() {
        return elements;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        //TODO: Implement this
        return elements.toString();
    }
}
