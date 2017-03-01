package com.rukin.laboratory1;

import com.rukin.core.ui.Plot2DBuilder;

import java.util.function.Function;
import java.util.stream.IntStream;

import static java.lang.Math.abs;
import static java.lang.Math.cos;

public class Task_2 {

    private static final Function<Double, Double> FUNCTION = x -> x == 0 ? Double.MAX_VALUE : cos(x - 0.5) / abs(x);
    private static final double FUNCTION_MIN = -2.21803198727480;
    private static final int N = 100;
    private static final double CROSSOVER_PROBABILITY = 0.5;
    private static final double MUTATION_PROBABILITY = 0.001;
    private static final int K = 10000;

    public static void main(String[] args) {
        double[] populationSizes = IntStream.rangeClosed(1, 100).mapToDouble(i -> i).toArray();
        double[][] populationSizesPlots = new double[3][populationSizes.length];
        double[] crossoverProbabilities = IntStream.rangeClosed(0, 100).mapToDouble(i -> i * 0.01).toArray();
        double[][] crossoverProbabilityPlots = new double[3][crossoverProbabilities.length];
        double[] mutationProbabilities = IntStream.rangeClosed(50, 150).mapToDouble(i -> i * 0.00001).toArray();
        double[][] mutationProbabilityPlots = new double[3][mutationProbabilities.length];

        for (int i = 0; i < K; i++) {
            GA ga = new GA(N, CROSSOVER_PROBABILITY, MUTATION_PROBABILITY, -10, 10, 0.000001);
            ga.findMin(FUNCTION);
        }

        for (int k = 0; k < K; k++) {
            for (int i = 0; i < populationSizes.length; i++) {
                int populationSize = (int) populationSizes[i];
                iteration(populationSize, CROSSOVER_PROBABILITY, MUTATION_PROBABILITY, populationSizesPlots, i);
            }
        }
        regularize(populationSizesPlots);

        Plot2DBuilder builder = new Plot2DBuilder();
        builder.addPlot("population time", populationSizes, populationSizesPlots[0]);
        builder.addPlot("population iteration", populationSizes, populationSizesPlots[1]);
        builder.addPlot("population solution accuracy", populationSizes, populationSizesPlots[2]);

        for (int k = 0; k < K; k++) {
            for (int i = 0; i < crossoverProbabilities.length; i++) {
                double crossoverProbability = crossoverProbabilities[i];
                iteration(N, crossoverProbability, MUTATION_PROBABILITY, crossoverProbabilityPlots, i);
            }
        }
        regularize(crossoverProbabilityPlots);
        builder.addPlot("P_c time", crossoverProbabilities, crossoverProbabilityPlots[0]);
        builder.addPlot("P_c iteration", crossoverProbabilities, crossoverProbabilityPlots[1]);
        builder.addPlot("P_c solution accuracy", crossoverProbabilities, crossoverProbabilityPlots[2]);

        for (int k = 0; k < K; k++) {
            for (int i = 0; i < mutationProbabilities.length; i++) {
                double mutationProbability = mutationProbabilities[i];
                iteration(N, CROSSOVER_PROBABILITY, mutationProbability, mutationProbabilityPlots, i);
            }
        }
        regularize(mutationProbabilityPlots);
        builder.addPlot("P_m time", mutationProbabilities, mutationProbabilityPlots[0]);
        builder.addPlot("P_m iteration", mutationProbabilities, mutationProbabilityPlots[1]);
        builder.addPlot("P_m solution accuracy", mutationProbabilities, mutationProbabilityPlots[2]);

        builder.show();
    }

    private static void iteration(int populationSize, double crossoverProbability, double mutationProbability, double[][] matrix, int i) {
        GA ga = new GA(populationSize, crossoverProbability, mutationProbability, -10, 10, 0.000001);
        long time = System.nanoTime();
        double value = ga.findMin(FUNCTION);
        matrix[0][i] += System.nanoTime() - time;
        matrix[1][i] += ga.iteration;
        matrix[2][i] += abs(FUNCTION_MIN - value);
    }

    private static void regularize(double[][] matrix) {
        for (int i = 0; i < matrix[0].length; i++) {
            matrix[0][i] /= K;
            matrix[1][i] /= K;
            matrix[2][i] /= K;
        }
    }
}
