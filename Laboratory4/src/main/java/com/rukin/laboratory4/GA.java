package com.rukin.laboratory4;

import com.rukin.laboratory4.crossover.Crossover;
import com.rukin.laboratory4.crossover.SubtreeCrossover;
import com.rukin.laboratory4.entity.Node;
import com.rukin.laboratory4.entity.operator.Constant;
import com.rukin.laboratory4.entity.operator.Operator;
import com.rukin.laboratory4.entity.operator.Variable;
import com.rukin.laboratory4.entity.operator.binary.BinaryOperator;
import com.rukin.laboratory4.entity.operator.unary.UnaryOperator;
import com.rukin.laboratory4.mutation.Mutation;
import com.rukin.laboratory4.mutation.NodeMutation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class GA {
    private static final Random random = new Random();
    private final List<Constant> constants;
    private final List<Variable> variables;
    private final List<UnaryOperator> unaryOperators;
    private final List<BinaryOperator> binaryOperators;
    private final List<Operator> functions;
    private final List<Double> probabilities;
    private final int populationSize;
    private final int maxSize;
    private final int maxDepth;
    private final int epocheNumber;
    private final double crossoverProbability;
    private final double mutationProbability;
    private final Crossover crossover;
    private final Mutation mutation;
    private double[] lowerBound = {-2, -2};
    private double[] upperBound = {2, 2};
    private double[] step = {0.1, 0.1};
    private double survivedPart = 0.05;
    private double survivedProbability = 0.8;
    private Node[] initialElement = new Node[0];

    GA(List<Operator> terminals, List<Operator> functions,
       int populationSize, int maxSize, int maxDepth, int epochNumber,
       double crossoverProbability, double mutationProbability) {
        this.constants = terminals.stream().filter(op -> op instanceof Constant).map(op -> (Constant) op).collect(Collectors.toList());
        this.variables = terminals.stream().filter(op -> op instanceof Variable).map(op -> (Variable) op).collect(Collectors.toList());
        this.unaryOperators = functions.stream().filter(op -> op instanceof UnaryOperator).map(op -> (UnaryOperator) op).collect(Collectors.toList());
        this.binaryOperators = functions.stream().filter(op -> op instanceof BinaryOperator).map(op -> (BinaryOperator) op).collect(Collectors.toList());
        this.functions = functions;

        this.populationSize = populationSize;
        this.maxSize = maxSize;
        this.maxDepth = maxDepth;

        this.epocheNumber = epochNumber;
        this.crossoverProbability = crossoverProbability;
        this.mutationProbability = mutationProbability;

        this.crossover = new SubtreeCrossover();
        this.mutation = new NodeMutation(terminals, functions);

        this.probabilities = new ArrayList<>(populationSize);
        probabilities.add(1 - 1. / populationSize);
        for (int i = 1; i < populationSize; i++) {
            probabilities.add(probabilities.get(probabilities.size() - 1) + 1 - ((double) i) / populationSize);
        }
    }

    void setLowerBound(double... lowerBound) {
        this.lowerBound = lowerBound;
    }

    void setUpperBound(double... upperBound) {
        this.upperBound = upperBound;
    }

    void setStep(double... step) {
        this.step = step;
    }

    Node find(Function<Node, Double> objectiveFunction) {
        int iteration = 0;
        List<NodeInfo> population = Stream.concat(
                Stream.of(initialElement),
                IntStream.range(0, populationSize)
                        .parallel()
                        .mapToObj(i -> initialization()))
                .map(node -> new NodeInfo(node, objectiveFunction.apply(node)))
                .sorted()
                .collect(Collectors.toList());

        NodeInfo min = population.get(0);
        printPopulation(population, iteration);

        while (iteration < epocheNumber) {
            List<Node> intermediatePopulation = population.stream().map(nodeInfo -> nodeInfo.node).collect(Collectors.toList());
            intermediatePopulation = crossover(intermediatePopulation);
            intermediatePopulation = mutation(intermediatePopulation);
            population = selection(objectiveFunction, population, intermediatePopulation.stream()
                    .map(node -> new NodeInfo(node, objectiveFunction.apply(node)))
                    .collect(Collectors.toList()));
            if (min.value > population.get(0).value) {
                min = population.get(0);
            }
            iteration++;
            printPopulation(population, iteration);
        }

        return min.node;
    }

    private void printPopulation(List<NodeInfo> population, int iteration) {
        System.out.println("Iteration number " + iteration);
        population.stream().limit(5).forEach(nodeInfo -> System.out.println(nodeInfo.value + " " + nodeInfo.node));
        System.out.println();
    }

    private List<NodeInfo> selection(Function<Node, Double> objectiveFunction, List<NodeInfo> population, List<NodeInfo> children) {
        int survivedNumber = population.size() - children.size();
        List<NodeInfo> newPopulation = population.stream().limit(survivedNumber).collect(Collectors.toList());
        List<NodeInfo> remains = Stream.concat(population.stream().skip(survivedNumber), children.stream()).collect(Collectors.toList());
        while (population.size() != newPopulation.size()) {
            int i = random.nextInt(remains.size());
            int j = random.nextInt(remains.size());
            newPopulation.add(remains.remove(random.nextDouble() < survivedProbability ? i : j));
        }
        Collections.sort(newPopulation);
        return newPopulation;
    }

    private List<Node> crossover(List<Node> intermediatePopulation) {
        int survivedNumber = Math.max(1, (int) (survivedPart * populationSize + 0.5));
        int childrenNumber = ((populationSize - survivedNumber) / 2) * 2;
        List<Node> children = new ArrayList<>(childrenNumber);
        for (int i = 0; i < childrenNumber / 2; i++) {
            if (random.nextDouble() <= crossoverProbability) { // with crossover probability
                Node parent1 = intermediatePopulation.get(getParentIndex()).clone(); // get parents
                Node parent2 = intermediatePopulation.get(getParentIndex()).clone();
                crossover.cross(parent1, parent2);
                children.add(parent1);
                children.add(parent2);
            }
        }
        return children;
    }

    private int getParentIndex() {
        double prob = random.nextDouble() * probabilities.get(probabilities.size() - 1);
        for (int i = 0; i < probabilities.size(); i++) {
            if (prob < probabilities.get(i)) {
                return i;
            }
        }
        return probabilities.size() - 1;
    }

    private List<Node> mutation(List<Node> intermediatePopulation) {
        return intermediatePopulation.stream()
                .map(node -> {
                    if (random.nextDouble() <= mutationProbability) { // with mutation probability
                        if (node.exprSize() == 1) {
                            return initialization();
                        }
                        mutation.mutate(node.clone());
                    }
                    return node;
                })
                .collect(Collectors.toList());
    }

    private Node initialization() {
        int maxDepth = random.nextInt(this.maxDepth - variables.size()) + variables.size() + 1;
        return random.nextBoolean() ? fullInitialization(1, maxDepth) : growInitialization(1, maxDepth);
    }

    private Node fullInitialization(int deep, int maxDepth) {
        if (deep == maxDepth) {
            return new Node(getTerminal());
        } else {
            return fullInitialization(deep + 1, maxDepth, new Node(getNonTerminal()));
        }
    }

    private Node fullInitialization(int deep, int maxDepth, Node root) {
        IntStream.range(0, root.arity()).forEach(i -> {
            Node node = fullInitialization(deep, maxDepth);
            root.set(i, node);
        });
        return root;
    }

    private Node growInitialization(int deep, int maxDepth) {
        if (deep == maxDepth || random.nextBoolean()) {
            return new Node(getTerminal());
        } else {
            return growInitialization(deep + 1, maxDepth, new Node(getNonTerminal()));
        }
    }

    private Node growInitialization(int deep, int maxDepth, Node root) {
        IntStream.range(0, root.arity()).forEach(i -> {
            Node node = growInitialization(deep, maxDepth);
            root.set(i, node);
        });
        return root;
    }

    private Operator getTerminal() {
        int choose = random.nextInt(variables.size() + 1);
        if (choose < variables.size()) {
            return variables.get(choose);
        } else {
            return constants.get(random.nextInt(constants.size()));
        }
    }

    private Operator getNonTerminal() {
        return functions.get(random.nextInt(functions.size()));
    }

    static class NodeInfo implements Comparable<NodeInfo> {

        public final Node node;
        final double value;

        NodeInfo(Node node, double value) {
            this.node = node;
            this.value = value;
        }

        @Override
        public int compareTo(NodeInfo other) {
            return Double.compare(value, other.value);
        }
    }
}
