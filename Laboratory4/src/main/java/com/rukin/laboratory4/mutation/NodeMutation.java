package com.rukin.laboratory4.mutation;

import com.rukin.laboratory4.entity.Node;
import com.rukin.laboratory4.entity.operator.Operator;

import java.util.*;

public class NodeMutation implements Mutation {

    private static final Random RANDOM = new Random();

    private final Map<Integer, List<Operator>> arityToOperator = new HashMap<>();

    public NodeMutation(List<Operator> terminals, List<Operator> functions) {
        this.arityToOperator.put(0, terminals);
        functions.forEach(operator -> {
            List<Operator> operators = arityToOperator.getOrDefault(operator.arity(), new ArrayList<>());
            operators.add(operator);
            arityToOperator.put(operator.arity(), operators);
        });
    }

    @Override
    public void mutate(Node node) {
        Node child = node.getRandomChild();

        List<Operator> operators = arityToOperator.get(child.getOperator().arity());

        Operator randomOperator = operators.get(RANDOM.nextInt(operators.size()));
        while (!randomOperator.equals(child.getOperator())) {
            randomOperator = operators.get(RANDOM.nextInt(operators.size()));
        }
        child.setOperator(randomOperator);
    }
}
