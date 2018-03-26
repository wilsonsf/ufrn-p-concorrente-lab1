package br.ufrn.concorrente;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Statistics {

    private Double sum = Double.valueOf(0.0);
    private Collection<Double> data = new ArrayList<>();
    private Double maximum = Double.MIN_VALUE;
    private Double minimum = Double.MAX_VALUE;

    public void addData(Double data) {
        maximum = Double.max(data, maximum);
        minimum = Double.min(data, minimum);
        sum += data;

        this.data.add(data);
    }

    public Collection<Double> getData() {
        return data;
    }

    public Double getMaximum() {
        return maximum;
    }

    public Double getMinimum() {
        return minimum;
    }

    public Double getAverage() {
        return data.size() > 0 ?
                data.stream()
                .mapToDouble(p -> p)
                .average()
                .getAsDouble()
                : 0.0;
    }

    public Double getStandardDeviation() {
        if (data.size() == 0)
            return 0.0;

        Double std = Double.valueOf(0);
        Double average = getAverage();

        for (Double element : data ) {
            std += Math.pow(element - average, 2);
        }

        std /= data.size();

        return Math.sqrt(std);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Dados: ")
                .append(dataToString())
                .append('\n')
                .append("Média: ")
                .append(getAverage())
                .append('\n')
                .append("Desvio: ")
                .append(getStandardDeviation());


        return builder.toString();
    }

    protected String dataToString() {
        StringBuilder builder = new StringBuilder();

        Iterator<Double> iterator = data.iterator();

        while(iterator.hasNext()) {
            builder.append(iterator.next());

            if (iterator.hasNext()) {
                builder.append("; ");
            }
        }
        return builder.toString();
    }
}
