package br.ufrn.concorrente;

import org.hamcrest.BaseMatcher;
import org.hamcrest.CoreMatchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StatisticsTest {

    private Statistics statistics;

    @Before
    public void setUp() {
        statistics = new Statistics();
        statistics.addData(99.0);
        statistics.addData(10.0);
        statistics.addData(0.125);
        statistics.addData(200.0);
    }

    @Test
    public void shouldSaveAData() {
        statistics = new Statistics();
        statistics.addData(5.0);

        assertThat(statistics.getData(), hasItems(5.0));
    }

    @Test
    public void shouldSaveABundleOfData() {
        statistics.addData(1.0);
        statistics.addData(5.0);
        statistics.addData(9.3);

        assertThat(statistics.getData(), hasItems(1.0, 5.0, 9.3));
    }

    @Test
    public void shouldCalculateMaximumFromData() {
        assertThat(statistics.getMaximum(), is(equalTo(200.0)));
    }

    @Test
    public void shouldCalculateMinimumFromData() {
        assertThat(statistics.getMinimum(), is(equalTo(0.125)));
    }

    /**
     * Garante a corretude do cálculo da média.
     * (99 + 10 + 0,125 + 200) / 4 = 77,28125
     */
    @Test
    public void shouldCalculateAverage() {
        assertThat(statistics.getAverage(), is(equalTo(77.28125)));
    }

    /**
     * Garante a corretude do cálculo do desvio padrão.
     * std = sqrt ( 1/n sum[1,n] ( (i - avg)^2 ) )
     */
    @Test
    public void shuldCalculateStandardDeviation() {
        org.junit.Assert.assertEquals(80.64032679, statistics.getStandardDeviation(), 0.001);
    }
}