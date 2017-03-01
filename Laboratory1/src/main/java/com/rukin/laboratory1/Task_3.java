package com.rukin.laboratory1;

import com.rukin.core.ui.Plot2DBuilder;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.lang.Math.abs;
import static java.lang.Math.cos;

public class Task_3 {

    private static final Function<Double, Double> FUNCTION = x -> x == 0 ? Double.MAX_VALUE : cos(x - 0.5) / abs(x);

    private static final int N = 100;
    private static final double CROSSOVER_PROBABILITY = 0.5;
    private static final double MUTATION_PROBABILITY = 0.001;

    private static final int SCALE = 10;

    public static void main(String[] args) {
        GA ga = new GA(N, CROSSOVER_PROBABILITY, MUTATION_PROBABILITY, -10, 10, 0.0001);
        ga.findMin(FUNCTION);

        Plot2DBuilder builder = new Plot2DBuilder();

        double[] x = IntStream.range(-10 * SCALE, 10 * SCALE).mapToDouble(v -> ((double) v) / SCALE).toArray();
        double[] y = Arrays.stream(x).map(FUNCTION::apply).toArray();
        int index = 10 * SCALE;
        y[index] = (y[index - 1] + y[index + 1]) / 2;

        double[] xExtremums = ga.iterationValue.stream().mapToDouble(v -> v).toArray();
        double[] yExtremums = Arrays.stream(xExtremums).map(FUNCTION::apply).toArray();

        builder.addPlot("cos(x - 0.5) / |x|", x, y);
        builder.addScatterPlot("extremums", xExtremums, yExtremums);

        builder.show();
    }
}
