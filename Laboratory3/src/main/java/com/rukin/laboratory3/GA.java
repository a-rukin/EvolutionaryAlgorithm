package com.rukin.laboratory3;

import com.rukin.core.entity.AbsolutePath;
import com.rukin.core.entity.Country;
import com.rukin.core.entity.Point;
import com.rukin.core.entity.RelativePath;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class GA {
    private final Country country;
    private final Point lowerBound;
    private final Point upperBound;
    private final int populationSize;
    private final int epochNumber;
    private final double probabilityOfCrossover;
    private final double probabilityOfMutation;
    private static final Random random = new Random();

    GA(Country country, int populationSize, int epochNumber,
       double probabilityOfCrossover, double probabilityOfMutation) {
        this.country = country;
        this.lowerBound = new Point(IntStream.range(0, country.getDimentions()).mapToDouble(country::getLowerBound).toArray());
        this.upperBound = new Point(IntStream.range(0, country.getDimentions()).mapToDouble(country::getUpperBound).toArray());
        this.populationSize = populationSize;
        this.probabilityOfCrossover = probabilityOfCrossover;
        this.probabilityOfMutation = probabilityOfMutation;
        this.epochNumber = epochNumber;
    }

    RelativePath findMin(Function<RelativePath, Double> objectiveFunction) {
        int iteration = 0;
        List<RelativePath> population = IntStream.range(0, populationSize)
                .mapToObj(i -> randomChromosome())
                .sorted(RelativePath.getComparator(objectiveFunction))
                .collect(Collectors.toList());
        RelativePath min = population.get(0);
        while (iteration <= epochNumber) {
            List<RelativePath> prevPopulation = new ArrayList<>(population);
            population = crossover(population);
            population = mutation(population);
            population = selection(objectiveFunction, prevPopulation, population);
            if (objectiveFunction.apply(min) > objectiveFunction.apply(population.get(0))) {
                min = population.get(0);
            }
            iteration++;
        }
        return min;
    }

    private List<RelativePath> selection(Function<RelativePath, Double> objectiveFunction,
                                         List<RelativePath> population, List<RelativePath> children) {
        int populationSize = population.size();
        int survivedNumber = populationSize - children.size();
        List<RelativePath> newPopulation = population.stream().limit(survivedNumber).collect(Collectors.toList());
        List<RelativePath> remains = Stream.concat(population.stream().skip(survivedNumber), children.stream()).collect(Collectors.toList());
        while (populationSize != newPopulation.size()) {
            int i = random.nextInt(remains.size());
            int j = random.nextInt(remains.size());
            double survivedProbability = 0.7;
            newPopulation.add(remains.remove(random.nextDouble() < survivedProbability ? i : j));
        }
        Collections.sort(newPopulation, RelativePath.getComparator(objectiveFunction));
        return newPopulation;
    }

    private List<RelativePath> crossover(List<RelativePath> intermediatePopulation) {
        double survivedPart = 0.2;
        int childrenNumber = (Math.min(populationSize - 1, (int) (survivedPart * populationSize + 0.5)) / 2) * 2;
        List<RelativePath> children = new ArrayList<>(childrenNumber);
        for (int i = 0; i < childrenNumber / 2; i++) {
            if (random.nextDouble() <= probabilityOfCrossover) {
                RelativePath parent1 = intermediatePopulation.get(random.nextInt(intermediatePopulation.size())).clone();
                RelativePath parent2 = intermediatePopulation.get(random.nextInt(intermediatePopulation.size())).clone();
                parent1.swap(parent2, random.nextInt(parent1.size()));
                children.add(parent1);
                children.add(parent2);
            }
        }
        return children;
    }

    private List<RelativePath> mutation(List<RelativePath> intermediatePopulation) {
        return intermediatePopulation.stream()
                .map(chromosome -> {
                    if (random.nextDouble() <= probabilityOfMutation) {
                        AbsolutePath path = new AbsolutePath(chromosome);
                        path.mutation();
                        chromosome = new RelativePath(path);
                    }
                    return chromosome;
                })
                .collect(Collectors.toList());
    }

    private RelativePath randomChromosome() {
        Point point = Point.getInstance(lowerBound, upperBound);
        Comparator<Point> comparator = Point.getComparator(point);
        List<Point> points = country.getPoints();
        return new RelativePath(new AbsolutePath(
                Stream.concat(
                        IntStream.range(0, points.size())
                                .filter(i -> points.get(i).get(0) < point.get(0))
                                .boxed()
                                .sorted((i1, i2) -> comparator.compare(points.get(i1), points.get(i2))),
                        IntStream.range(0, points.size())
                                .filter(i -> points.get(i).get(0) >= point.get(0))
                                .boxed()
                                .sorted((i1, i2) -> -comparator.compare(points.get(i1), points.get(i2)))
                ).collect(Collectors.toList())
        ));
    }
}
