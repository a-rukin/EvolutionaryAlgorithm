package com.rukin.laboratory4.crossover;

import com.rukin.laboratory4.entity.Node;
import com.rukin.laboratory4.entity.operator.Operator;

import java.util.Random;

public class NodeCrossover implements Crossover {

    private static final Random RANDOM = new Random();
    private static final int ITERATION_NUMBER = 10;

    @Override
    public void cross(Node node1, Node node2) {
        int firstSize = node1.exprSize();
        int secondSize = node2.exprSize();

        Node firstNode;
        Node secondNode = null;
        do {
            firstNode = node1.getNode(RANDOM.nextInt(firstSize));
            for (int i = 0; i < ITERATION_NUMBER; i++) {
                secondNode = node2.getNode(RANDOM.nextInt(secondSize));
                if (firstNode.arity() == secondNode.arity()) {
                    break;
                }
            }
        } while (firstNode.arity() != secondNode.arity());

        Operator operator = firstNode.getOperator();
        firstNode.setOperator(secondNode.getOperator());
        secondNode.setOperator(operator);
    }
}
