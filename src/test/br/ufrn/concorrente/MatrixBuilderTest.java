package br.ufrn.concorrente;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MatrixBuilderTest {

    private MatrixBuilder builder;

    @Before
    public void setUp() {
        builder = new MatrixBuilder();
    }

    @Test
    public void shouldReadAHeight() {
        builder.withHeight(5);
        assertThat(builder.getHeight(), CoreMatchers.is(CoreMatchers.equalTo(5)));
    }

    @Test
    public void shouldReadAnWidth() {
        builder.withWidth(5);
        assertThat(builder.getWidth(), CoreMatchers.is(CoreMatchers.equalTo(5)));
    }

    @Test
    public void shouldReadAnElement() {
        String elementString = "5";
        int currentElement = builder.readElement(elementString);
        assertThat(currentElement, CoreMatchers.is(CoreMatchers.equalTo(5)));
    }

    @Test
    public void shouldReadALine() {
        String lineString = "3 5 1";
        int elements[] = builder.readLine(lineString);
        assertThat(elements[0], CoreMatchers.is(CoreMatchers.equalTo(3)));
        //assertThat(elements[0], CoreMatchers.allOf(CoreMatchers.hasItems(3, 5, 1)));
    }
}