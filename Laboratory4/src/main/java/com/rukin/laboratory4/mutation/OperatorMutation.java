package com.rukin.laboratory4.mutation;

import com.rukin.laboratory4.entity.Node;
import com.rukin.laboratory4.entity.operator.Operator;
import com.rukin.laboratory4.entity.operator.Variable;
import com.rukin.laboratory4.entity.operator.binary.BinaryOperator;
import com.rukin.laboratory4.entity.operator.unary.UnaryOperator;

import java.util.List;
import java.util.Random;

public class OperatorMutation implements Mutation {

    private static final Random RANDOM = new Random();

    private final List<Variable> variables;
    private final List<UnaryOperator> unaryOperators;
    private final List<BinaryOperator> binaryOperators;

    public OperatorMutation(List<Variable> variables, List<UnaryOperator> unaryOperators, List<BinaryOperator> binaryOperators) {
        this.variables = variables;
        this.unaryOperators = unaryOperators;
        this.binaryOperators = binaryOperators;
    }

    @Override
    public void mutate(Node node) {
        Node child = node.getRandomChild();

        switch (child.arity()) {
            case 1:
                child.setOperator(getRandomOperator(unaryOperators));
                break;
            case 2:
                child.setOperator(getRandomOperator(binaryOperators));
                break;
            default:
                child.setOperator(getRandomOperator(variables));
        }
    }

    private <T extends Operator> T getRandomOperator(List<T> operators) {
        return operators.get(RANDOM.nextInt(operators.size()));
    }
}
