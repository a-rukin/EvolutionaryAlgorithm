package com.rukin.laboratory4;

import com.rukin.laboratory4.entity.Node;
import com.rukin.laboratory4.entity.operator.Constant;
import com.rukin.laboratory4.entity.operator.Operator;
import com.rukin.laboratory4.entity.operator.Variable;
import com.rukin.laboratory4.entity.operator.binary.*;
import com.rukin.laboratory4.entity.operator.unary.Abs;
import com.rukin.laboratory4.entity.operator.unary.Cos;
import com.rukin.laboratory4.entity.operator.unary.Exp;
import com.rukin.laboratory4.entity.operator.unary.Sin;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Task {

    static final Function<List<Double>, Double> FGOLD = (x) -> (1 + Math.pow(x.get(0) + x.get(1) + 1, 2) * (19 - 14 * x.get(0) + 3 * x.get(0) * x.get(0) - 14 * x.get(1) + 6 * x.get(0) * x.get(1) + 3 * x.get(1) * x.get(1))) * (30 + Math.pow(2 * x.get(0) - 3 * x.get(1), 2) * (18 - 32 * x.get(0) + 12 * x.get(0) * x.get(0) + 48 * x.get(1) - 36 * x.get(0) * x.get(1) + 27 * x.get(1) * x.get(1)));
    private static final List<Operator> FUNCTIONS = Arrays.asList(new Plus(), new Minus(), new Divide(), new Multiply(),
            new Abs(), new Sin(), new Cos(), new Exp(), new Power());
    private static final List<Operator> TERMINALS = Stream.concat(
            IntStream.range(0, 2).mapToObj(Variable::new),
            Arrays.stream(new int[]{1, 2, 3, 6, 19, 14, 18, 32, 12, 48, 36, 27}).mapToObj(Constant::new)
    ).collect(Collectors.toList());

    private static final int N = 1000;
    private static final int MAX_SIZE = Integer.MAX_VALUE;
    private static final int MAX_DEEP = 15;
    private static final int EPOCH_NUMBER = 10000;
    private static final double CROSSOVER_PROBABILITY = 1;
    private static final double MUTATION_PROBABILITY = 0.3;
    private static double[] lowerBound = {-2, -2};
    private static double[] upperBound = {2, 2};
    private static double[] step = {1, 1};

    public static void main(String[] args) {
        GA ga = new GA(TERMINALS, FUNCTIONS, N, MAX_SIZE, MAX_DEEP,
                EPOCH_NUMBER, CROSSOVER_PROBABILITY, MUTATION_PROBABILITY);
        ga.setLowerBound(lowerBound);
        ga.setUpperBound(upperBound);
        ga.setStep(step);
        Node min = ga.find(e -> error(FGOLD, e));
        System.out.format("Found expr:\n%s\nAvr error:%f\n", min, error(FGOLD, min));
    }

    private static double error(Function<List<Double>, Double> function, Node expr) {
        double totalError = 0;
        int iter = 0;
        for (double x0 = lowerBound[0]; x0 <= upperBound[0]; x0 += step[0]) {
            for (double x1 = lowerBound[1]; x1 <= upperBound[1]; x1 += step[1]) {
                List<Double> args = Arrays.asList(x0, x1);
                totalError += Math.abs(function.apply(args) - expr.eval(args));
                iter++;
            }
        }
        if (!Double.isFinite(totalError)) {
            return Double.POSITIVE_INFINITY;
        }
        return totalError / iter;
    }
}
