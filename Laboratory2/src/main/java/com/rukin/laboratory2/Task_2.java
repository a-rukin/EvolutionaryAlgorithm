package com.rukin.laboratory2;

import com.rukin.core.entity.Point;
import com.rukin.core.ui.Plot3DBuilder;

import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.lang.Math.*;

public class Task_2 {

    private static final int SCALE = 10;
    private static final Function<Point, Double> FUNCTION = (p) ->
            -cos(p.get(0)) * cos(p.get(1)) * exp(-(pow(p.get(0) - PI, 2) + pow(p.get(1) - PI, 2)));
    private static final Point LOWER_BOUND = new Point(-100, -100);
    private static final Point UPPER_BOUND = new Point(100, 100);
    private static final Point GRAPH_LOWER_BOUND = new Point(0, 0);
    private static final Point GRAPH_UPPER_BOUND = new Point(10, 10);
    private static final int N = 100;
    private static final int EPOCH_NUMBER = 100000;
    private static final double CROSSOVER_PROBABILITY = 0.5;
    private static final double MUTATION_PROBABILITY = 0.001;

    public static void main(String[] args) {
        double[] x = IntStream.range((int) GRAPH_LOWER_BOUND.get(0) * SCALE, (int) GRAPH_UPPER_BOUND.get(0) * SCALE)
                .mapToDouble(i -> ((double) i) / SCALE).toArray();
        double[] y = IntStream.range((int) GRAPH_LOWER_BOUND.get(1) * SCALE, (int) GRAPH_UPPER_BOUND.get(1) * SCALE)
                .mapToDouble(i -> ((double) i) / SCALE).toArray();
        double[][] z = new double[x.length][y.length];

        for (int i = 0; i < x.length; i++) {
            double x1 = x[i];
            for (int j = 0; j < y.length; j++) {
                double x2 = y[j];
                z[i][j] = FUNCTION.apply(new Point(x1, x2));
            }
        }

        RealGA ga = new RealGA(N, EPOCH_NUMBER, CROSSOVER_PROBABILITY, MUTATION_PROBABILITY);
        Point min = ga.findMin(FUNCTION, LOWER_BOUND, UPPER_BOUND);
        System.out.println(min + " = " + FUNCTION.apply(min));

        List<Point> minPoints = ga.getEpochMinPoints();
        minPoints.removeIf(point -> GRAPH_LOWER_BOUND.get(0) > point.get(0) || GRAPH_LOWER_BOUND.get(1) > point.get(1) ||
                GRAPH_UPPER_BOUND.get(0) < point.get(0) || GRAPH_UPPER_BOUND.get(1) < point.get(1)); // crop points
        double[] x1 = new double[minPoints.size()];
        double[] y1 = new double[minPoints.size()];
        double[] z1 = new double[minPoints.size()];
        for (int i = 0; i < minPoints.size(); i++) {
            Point point = minPoints.get(i);
            x1[i] = point.get(0);
            y1[i] = point.get(1);
            z1[i] = FUNCTION.apply(point);
        }
        Plot3DBuilder plot = new Plot3DBuilder();
        plot.addPlot("", x, y, z);
        plot.addPlot("", x1, y1, z1);
        plot.show();
    }
}
