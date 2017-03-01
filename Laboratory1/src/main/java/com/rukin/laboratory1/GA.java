package com.rukin.laboratory1;

import com.rukin.core.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;

public class GA {
    private final int chromosomeNumber;
    private final double probabilityOfCrossover;
    private final double probabilityOfMutation;
    private final double leftBound;
    private final double rightBound;
    private final int binLength;
    private final double precision;

    public int iteration;
    public List<Double> iterationValue;
    private static final Random random = new Random();

    public GA(int chromosomeNumber, double probabilityOfCrossover, double probabilityOfMutation,
              double leftBound, double rightBound, double precision) {
        this.chromosomeNumber = chromosomeNumber;
        this.probabilityOfCrossover = probabilityOfCrossover;
        this.probabilityOfMutation = probabilityOfMutation;
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.binLength = (int) Math.ceil(Math.log((rightBound - leftBound) / precision) / Math.log(2));
        this.precision = (rightBound - leftBound) / Math.pow(2, binLength);
    }

    public double findMin(Function<Double, Double> objectiveFunction) {
        iteration = 0;
        iterationValue = new ArrayList<>();

        Chromosome[] generation = IntStream.range(0, chromosomeNumber)
                .mapToDouble(i -> random.nextDouble() * (rightBound - leftBound) + leftBound)
                .mapToObj(this::decimalToBinary)
                .map(Chromosome::new)
                .toArray(Chromosome[]::new);
        double minY = minGenerationY(objectiveFunction, generation);
        iterationValue.add(minGenerationX(objectiveFunction, generation));

        while (true) {
            Chromosome[] intermediatePopulation = reproduction(objectiveFunction, generation);
            intermediatePopulation = crossover(intermediatePopulation);
            intermediatePopulation = mutation(intermediatePopulation);

            double minLocalY = minGenerationY(objectiveFunction, generation);
            if ( minLocalY >= minY) {
                break;
            }
            generation = intermediatePopulation;
            iteration++;
            iterationValue.add(minGenerationX(objectiveFunction, generation));
        }

        return minGenerationX(objectiveFunction, generation);
    }

    private Chromosome[] reproduction(Function<Double, Double> objectiveFunction, Chromosome[] population) {
        double[] functionValues = Arrays.stream(population)
                .map(Chromosome::genes)
                .mapToDouble(this::binaryToDecimal)
                .map(objectiveFunction::apply)
                .toArray();
        double[] normalizedValues = ArrayUtil.normalize(functionValues);
        double[] modifiedFunctionValues = Arrays.stream(normalizedValues)
                .map(value -> 1 - value)
                .toArray();
        double scalar = population.length / Arrays.stream(modifiedFunctionValues).sum();
        int[] numberOfCopies = Arrays.stream(modifiedFunctionValues)
                .mapToInt(value -> (int) (value * scalar + 0.5))
                .toArray();
        return IntStream.range(0, population.length)
                .boxed()
                .flatMap(i -> IntStream.range(0, numberOfCopies[i])
                        .mapToObj(j -> population[i]))
                .toArray(Chromosome[]::new);
    }

    private Chromosome[] crossover(Chromosome[] intermediatePopulation) {
        ArrayUtil.shuffle(intermediatePopulation);
        for (int i = 0; i < intermediatePopulation.length - 1; i += 2) {
            if (random.nextDouble() <= probabilityOfCrossover) {
                int k = random.nextInt(binLength - 1);
                for (int j = k; j < binLength; j++) {
                    int gene = intermediatePopulation[i].genes()[j];
                    intermediatePopulation[i].genes()[j] = intermediatePopulation[i + 1].genes()[j];
                    intermediatePopulation[i + 1].genes()[j] = gene;
                }
            }
        }
        return intermediatePopulation;
    }

    private Chromosome[] mutation(Chromosome[] intermediatePopulation) {
        return Arrays.stream(intermediatePopulation)
                .map(chromosome -> {
                    if (random.nextDouble() <= probabilityOfMutation) {
                        int geneIndex = random.nextInt(binLength);
                        chromosome.genes()[geneIndex] ^= 1;
                    }
                    return chromosome;
                })
                .toArray(Chromosome[]::new);
    }

    private double minGenerationY(Function<Double, Double> objectiveFunction, Chromosome[] generation) {
        return Arrays.stream(generation)
                .map(Chromosome::genes)
                .mapToDouble(this::binaryToDecimal)
                .map(objectiveFunction::apply)
                .min()
                .orElse(Double.NaN);
    }

    private double minGenerationX(Function<Double, Double> objectiveFunction, Chromosome[] generation) {
        double minY = Double.MAX_VALUE;
        double minX = Double.NaN;
        for (Chromosome chromosome : generation) {
            double x = binaryToDecimal(chromosome.genes());
            double y = objectiveFunction.apply(x);
            if (y < minY) {
                minY = y;
                minX = x;
            }
        }
        return minX;
    }

    private int[] decimalToBinary(double value) {
        int[] bin = new int[binLength];
        String binString = Integer.toBinaryString((int) ((value - leftBound) / precision));
        int shift = binLength - binString.length();
        for (int i = binString.length() - 1; i >= 0; i--) {
            bin[i + shift] = binString.charAt(i) - '0';
        }
        return bin;
    }

    private double binaryToDecimal(int[] bin) {
        int value = IntStream.range(0, bin.length)
                .filter(i -> bin[bin.length - i - 1] == 1)
                .map(i -> 1 << i)
                .sum();
        return leftBound + value * precision;
    }
}
