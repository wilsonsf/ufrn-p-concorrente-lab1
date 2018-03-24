package br.ufrn.concorrente;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class MatrixBuilderTest {

    private MatrixBuilder builder;

    @Before
    public void setUp() {
        builder = new MatrixBuilder();
    }

    @Test
    public void shouldReadAHeight() {
        String dimensionString = "5";
        int height = builder.withHeight(dimensionString);
        assertThat(height, is(equalTo(5)));
        assertThat(builder.getHeight(), is(equalTo(5)));
    }

    @Test
    public void shouldReadAnWidth() {
        String dimensionString = "5";
        int width = builder.withWidth(dimensionString);
        assertThat(width, is(equalTo(5)));
        assertThat(builder.getWidth(), is(equalTo(5)));
    }

    @Test
    public void shouldReadHeightAndWidth() {
    }

    @Test
    public void shouldReadAnElement() {
        String elementString = "5";
        int currentElement = builder.readElement(elementString);
        assertThat(currentElement, is(equalTo(5)));
    }

    /**
     * Tem que garantir a presença dos elementos e a ordem correta
     */
    @Test
    public void shouldReadALine() {
        String lineString = "3 5 1";
        ArrayList<Integer> elements = builder.readLine(lineString);
        assertThat(elements, hasItems(3, 5, 1));
        assertThat(elements.get(0), is(equalTo(3)));
        assertThat(elements.get(1), is(equalTo(5)));
        assertThat(elements.get(2), is(equalTo(1)));
    }
}