package br.ufrn.concorrente.exceptions;

public class MatrixBuildException extends Exception {

    public MatrixBuildException(String validationErrors) {
        super(validationErrors);
    }
}
