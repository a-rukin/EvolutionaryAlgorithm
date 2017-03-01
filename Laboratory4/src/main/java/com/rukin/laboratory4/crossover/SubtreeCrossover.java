package com.rukin.laboratory4.crossover;

import com.rukin.laboratory4.entity.Node;
import com.rukin.laboratory4.entity.operator.Operator;

import java.util.List;
import java.util.Random;

public class SubtreeCrossover implements Crossover {

    private static final Random RANDOM = new Random();

    @Override
    public void cross(Node node1, Node node2) {
        while (true) {
            Node firstNode = node1.getRandomChild();
            int depth1 = node1.exprDepth();
            int firstDepth = firstNode.exprDepth();
            if (depth1 == firstDepth && depth1 > 1) {
                continue;
            }
            List<Node> sameNodes = node2.find(firstNode.arity());
            if (!sameNodes.isEmpty()) {
                Node secondNode = sameNodes.get(RANDOM.nextInt(sameNodes.size()));
                swapNodes(firstNode, secondNode);
                return;
            }
        }
    }

    private void swapNodes(Node firstNode, Node secondNode) {
        for (int i = 0; i < firstNode.size(); i++) {
            Node subtree = firstNode.get(i);
            firstNode.set(i, secondNode.get(i));
            secondNode.set(i, subtree);
        }
        Operator operator = firstNode.getOperator();
        firstNode.setOperator(secondNode.getOperator());
        secondNode.setOperator(operator);
    }
}
