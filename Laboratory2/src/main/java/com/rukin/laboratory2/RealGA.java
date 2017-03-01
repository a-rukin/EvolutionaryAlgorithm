package com.rukin.laboratory2;

import com.rukin.core.entity.Point;
import com.rukin.core.util.Numbers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class RealGA {
    private final int numberOfChromosome;
    private final int epochNumber;
    private final double probabilityOfCrossover;
    private final double probabilityOfMutation;
    private final List<Double> probabilities;
    private double crossoverAlpha = 0.5;
    private int iteration;
    private List<Point> epochMinPoints;
    private static final Random random = new Random();

    RealGA(int chromosomeNumber, int epochNumber,
           double crossoverProbability, double mutationProbability) {
        this.numberOfChromosome = chromosomeNumber;
        this.probabilityOfCrossover = crossoverProbability;
        this.probabilityOfMutation = mutationProbability;
        this.epochNumber = epochNumber;
        this.probabilities = new ArrayList<>(chromosomeNumber);
        probabilities.add(1 - 1. / chromosomeNumber);
        for (int i = 1; i < chromosomeNumber; i++) {
            probabilities.add(probabilities.get(probabilities.size() - 1) + 1 - ((double) i) / chromosomeNumber);
        }
    }

    List<Point> getEpochMinPoints() {
        return epochMinPoints;
    }

    int getIteration() {
        return iteration;
    }

    Point findMin(Function<Point, Double> objectiveFunction, Point lowerBound, Point upperBound) {
        epochMinPoints = new ArrayList<>();
        iteration = 1;
        List<Point> generation = IntStream.range(0, numberOfChromosome)
                .mapToObj(i -> Point.getInstance(lowerBound, upperBound))
                .collect(Collectors.toList());
        Point minPoint = findMinPoint(objectiveFunction, generation);
        epochMinPoints.add(minPoint);
        int minimumRepeats = 0;
        while (iteration <= epochNumber) {
            List<Point> prevGeneration = new ArrayList<>(generation);
            Collections.sort(generation, (o1, o2) -> -Double.compare(objectiveFunction.apply(o1), objectiveFunction.apply(o2)));
            generation = crossover(generation);
            generation = mutation(generation, lowerBound, upperBound);

            Point localMinPoint = findMinPoint(objectiveFunction, generation);
            if (localMinPoint == null || minPoint.equals(localMinPoint)) {
                minimumRepeats++;
                double stopCriteriaIteration = 100;
                if (minimumRepeats == stopCriteriaIteration) {
                    break;
                }
            } else {
                minimumRepeats = 0;
                if (objectiveFunction.apply(minPoint) > objectiveFunction.apply(localMinPoint)) {
                    minPoint = localMinPoint;
                    epochMinPoints.add(minPoint);
                }
            }

            generation = Stream.concat(prevGeneration.stream(), generation.stream())
                    .sorted((o1, o2) -> -Double.compare(objectiveFunction.apply(o1), objectiveFunction.apply(o2)))
                    .limit(numberOfChromosome)
                    .collect(Collectors.toList());
            iteration++;
        }

        return minPoint;
    }

    private Point findMinPoint(Function<Point, Double> function, List<Point> chromosomes) {
        if (chromosomes.isEmpty()) {
            return null;
        }
        Point minPoint = chromosomes.get(0);
        double minValue = function.apply(minPoint);
        for (int i = 1; i < chromosomes.size(); i++) {
            Point point = chromosomes.get(i);
            double value = function.apply(point);
            if (value < minValue) {
                minPoint = point;
                minValue = value;
            }
        }
        return minPoint;
    }

    private List<Point> crossover(List<Point> intermediatePopulation) {
        List<Point> chromosomes = new ArrayList<>();
        for (int i = 0; i < numberOfChromosome * 2; i++) {
            if (random.nextDouble() <= probabilityOfCrossover) { // with crossover probability
                Point parent1 = intermediatePopulation.get(getParentIndex()); // get parents
                Point parent2 = intermediatePopulation.get(getParentIndex());
                chromosomes.add(crossoverChild(parent1, parent2)); // and cross it
            }
        }
        return chromosomes;
    }

    private int getParentIndex() {
        double prob = random.nextDouble() * probabilities.get(probabilities.size() - 1);
        for (int i = 0; i < probabilities.size(); i++) {
            if (prob > probabilities.get(i)) {
                return i;
            }
        }
        return probabilities.size() - 1;
    }

    private Point crossoverChild(Point first, Point second) {
        return new Point(IntStream.range(0, first.size())
                .mapToDouble(i -> {
                    double cMin = Math.min(first.get(i), second.get(i));
                    double cMax = Math.max(first.get(i), second.get(i));
                    double I = cMax - cMin;
                    return Numbers.random(cMin - I * crossoverAlpha, cMax + I * crossoverAlpha);
                })
                .toArray());
    }

    private List<Point> mutation(List<Point> intermediatePopulation, Point lowerBound, Point upperBound) {
        return intermediatePopulation.stream()
                .map(chromosome -> {
                    if (random.nextDouble() <= probabilityOfMutation) {
                        int geneIndex = random.nextInt(chromosome.size());
                        chromosome.set(geneIndex, Numbers.random(lowerBound.get(geneIndex), upperBound.get(geneIndex)));
                    }
                    return chromosome;
                })
                .collect(Collectors.toList());
    }
}
