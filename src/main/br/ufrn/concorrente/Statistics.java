package br.ufrn.concorrente;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Classe que armazena os dados e auxilia no cálculo de dados estatísticos.
 * @author Wilson Farias
 */
public class Statistics {

    /** Coleção que representa a população dos dados. */
    private Collection<Double> data = new ArrayList<>();
    /** Armazena o maior valor adicionado a coleção de dados. */
    private Double maximum = Double.MIN_VALUE;
    /** Armazena o menor valor adicionado a coleção de dados. */
    private Double minimum = Double.MAX_VALUE;

    /**
     * Adiciona um dado para a população de dados.
     * @param newData um registro
     */
    public void addData(final Double newData) {
        maximum = Double.max(newData, maximum);
        minimum = Double.min(newData, minimum);

        this.data.add(newData);
    }

    /**
     * Getter para a colação de dados.
     * @return a coleção de dados armzenados
     */
    public Collection<Double> getData() {
        return data;
    }

    /**
     * Getter para o maior valor armazenado.
     * @return o maior valor armazenado
     */
    public Double getMaximum() {
        return maximum;
    }

    /**
     * Getter para o menor valor armazenado.
     * @return o menor valor armazenado
     */
    public Double getMinimum() {
        return minimum;
    }

    /**
     * Calcula a média dos dados armazenados na amostra.
     * @return a média dos dados armazenados
     */
    public Double computeAverage() {
        if (data.isEmpty()) {
            return 0.0;
        } else {
            return data.stream()
                    .mapToDouble(p -> p)
                    .average()
                    .getAsDouble();
        }
    }

    /**
     * Calcula o desvio padrão dos dados armazenados.
     * @return o desvio padrão
     */
    public Double computeStandardDeviation() {
        if (data.isEmpty()) {
            return 0.0;
        }

        Double std = 0.0;
        Double average = computeAverage();

        for (Double element : data) {
            std += Math.pow(element - average, 2);
        }

        std /= data.size();

        return Math.sqrt(std);
    }

    /**
     * Retorna uma versão imprimível da classe Statistics.
     * @return string representando a classe
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Dados: \n")
                .append(dataToString())
                .append('\n')
                .append("Máximo: ")
                .append(maximum)
                .append('\n')
                .append("Mínimo: ")
                .append(minimum)
                .append('\n')
                .append("Média: ")
                .append(computeAverage())
                .append('\n')
                .append("Desvio: ")
                .append(computeStandardDeviation());


        return builder.toString();
    }

    /**
     * Converte os dados armzenados em uma forma legível de dados.
     * @return os dados armazenados
     */
    protected String dataToString() {
        StringBuilder builder = new StringBuilder();

        Iterator<Double> iterator = data.iterator();

        while (iterator.hasNext()) {
            builder.append(iterator.next());

            if (iterator.hasNext()) {
                builder.append("; ");
            }
        }
        return builder.toString();
    }
}
