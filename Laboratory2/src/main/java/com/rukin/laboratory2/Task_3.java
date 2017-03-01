package com.rukin.laboratory2;

import com.rukin.core.entity.Point;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static java.lang.Math.*;

public class Task_3 {
    private static final Function<Point, Double> FUNCTION = (p) ->
            -cos(p.get(0)) * cos(p.get(1)) * exp(-(pow(p.get(0) - PI, 2) + pow(p.get(1) - PI, 2)));
    private static final Point LOWER_BOUND = new Point(-100, -100);
    private static final Point UPPER_BOUND = new Point(100, 100);
    private static final int N = 100;
    private static final int EPOCH_NUMBER = 1000000;
    private static final double CROSSOVER_PROBABILITY = 0.5;
    private static final double MUTATION_PROBABILITY = 0.001;
    private static final int ITER = 10;

    public static void main(String[] args) throws FileNotFoundException {
        List<Double> nTime = new ArrayList<>();
        List<Double> nIter = new ArrayList<>();
        List<Double> nPrec = new ArrayList<>();

        for (int i = 1; i <= 101; i += 10) {
            double time = 0;
            double iter = 0;
            double prec = 0;

            RealGA ga = new RealGA(i, EPOCH_NUMBER, CROSSOVER_PROBABILITY, MUTATION_PROBABILITY);

            for (int k = 0; k < ITER; k++) {
                long timeValue = System.currentTimeMillis();
                Point min = ga.findMin(FUNCTION, LOWER_BOUND, UPPER_BOUND);
                time += System.currentTimeMillis() - timeValue;
                iter += ga.getIteration();
                prec += Math.abs(-1 - FUNCTION.apply(min));
            }

            nTime.add(time / ITER);
            nIter.add(iter / ITER);
            nPrec.add(prec / ITER);

            System.out.println("N = " + i);
        }

        printValues(nTime, "nTime");
        printValues(nIter, "nIter");
        printValues(nPrec, "nPrec");

        // ---------------------------------------------

        List<Double> pcTime = new ArrayList<>();
        List<Double> pcIter = new ArrayList<>();
        List<Double> pcPrec = new ArrayList<>();

        for (double i = 0.0; i <= 1.0; i += 0.1) {
            double time = 0;
            double iter = 0;
            double prec = 0;

            RealGA ga = new RealGA(N, EPOCH_NUMBER, i, MUTATION_PROBABILITY);

            for (int k = 0; k < ITER; k++) {
                long timeValue = System.currentTimeMillis();
                Point min = ga.findMin(FUNCTION, LOWER_BOUND, UPPER_BOUND);
                time += System.currentTimeMillis() - timeValue;
                iter += ga.getIteration();
                prec += Math.abs(-1 - FUNCTION.apply(min));
            }

            pcTime.add(time / ITER);
            pcIter.add(iter / ITER);
            pcPrec.add(prec / ITER);

            System.out.println("Pc = " + i);
        }

        printValues(pcTime, "pcTime");
        printValues(pcIter, "pcIter");
        printValues(pcPrec, "pcPrec");

        // ---------------------------------------------

        List<Double> pmTime = new ArrayList<>();
        List<Double> pmIter = new ArrayList<>();
        List<Double> pmPrec = new ArrayList<>();

        for (double i = 0.0005; i <= 0.0015; i += 0.0001) {
            double time = 0;
            double iter = 0;
            double prec = 0;

            RealGA ga = new RealGA(N, EPOCH_NUMBER, CROSSOVER_PROBABILITY, i);

            for (int k = 0; k < ITER; k++) {
                long timeValue = System.currentTimeMillis();
                Point min = ga.findMin(FUNCTION, LOWER_BOUND, UPPER_BOUND);
                time += System.currentTimeMillis() - timeValue;
                iter += ga.getIteration();
                prec += Math.abs(-1 - FUNCTION.apply(min));
            }

            pmTime.add(time / ITER);
            pmIter.add(iter / ITER);
            pmPrec.add(prec / ITER);

            System.out.println("Pm = " + i);
        }

        printValues(pmTime, "pmTime");
        printValues(pmIter, "pmIter");
        printValues(pmPrec, "pmPrec");
    }

    private static void printValues(List<Double> list, String filename) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(filename)) {
            list.stream().forEach(writer::println);
        }
    }
}
