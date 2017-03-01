package com.rukin.laboratory3;

import com.rukin.core.entity.AbsolutePath;
import com.rukin.core.entity.Country;
import com.rukin.core.entity.RelativePath;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.function.Function;


public class PerformanceTask {
    private static final int N = 100;
    private static final int EPOCH_NUMBER = 10000;
    private static final double CROSSOVER_PROBABILITY = 1;
    private static final double MUTATION_PROBABILITY = 0.4;
    private static final int ITERATIONS = 100;

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = Task.class.getClassLoader().getResourceAsStream("berlin52");
        Country country = new Country(stream);
        Function<RelativePath, Double> function = ordinalPath -> country.distance(new AbsolutePath(ordinalPath));
        RelativePath minPath = new RelativePath(new AbsolutePath(Task.getBestSolution("berlin52_best_solution")));
        try (PrintWriter writer1 = new PrintWriter("pc_t");
             PrintWriter writer2 = new PrintWriter("pc_a")) {
            for (double pc = 0; pc <= 1; pc += 0.1) {
                double[] res = evaluate(function, country, minPath, N, EPOCH_NUMBER, pc, MUTATION_PROBABILITY);
                writer1.println(res[0]);
                writer2.println(res[1]);
            }
        }
        try (PrintWriter writer1 = new PrintWriter("pm_t");
             PrintWriter writer2 = new PrintWriter("pm_a")) {
            for (double pm = 0; pm <= 1; pm += 0.1) {
                double[] res = evaluate(function, country, minPath, N, EPOCH_NUMBER, CROSSOVER_PROBABILITY, pm);
                writer1.println(res[0]);
                writer2.println(res[1]);
            }
        }
    }

    private static double[] evaluate(Function<RelativePath, Double> function, Country country, RelativePath minPath,
                                     int n, int epochNumber, double probabilityOfCrossover, double probabilityOfMutation) {
        double time = 0;
        double diff = 0;
        GA ga = new GA(country, n, epochNumber, probabilityOfCrossover, probabilityOfMutation);
        for (int j = 0; j < ITERATIONS; j++) {
            long startTime = System.currentTimeMillis();
            RelativePath foundPath = ga.findMin(function);
            time += (System.currentTimeMillis() - startTime);
            diff += Math.abs((function.apply(foundPath) - function.apply(minPath)));
        }
        System.out.println(Arrays.toString(new double[]{time / ITERATIONS, diff / ITERATIONS}));
        return new double[]{time / ITERATIONS, diff / ITERATIONS};
    }
}
