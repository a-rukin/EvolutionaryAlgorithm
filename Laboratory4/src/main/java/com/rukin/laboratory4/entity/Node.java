package com.rukin.laboratory4.entity;

import com.rukin.laboratory4.entity.operator.Constant;
import com.rukin.laboratory4.entity.operator.Variable;
import com.rukin.laboratory4.entity.operator.binary.*;
import com.rukin.laboratory4.entity.operator.unary.Cos;
import com.rukin.laboratory4.entity.operator.unary.Exp;
import com.rukin.laboratory4.entity.operator.Operator;
import com.rukin.laboratory4.entity.operator.unary.Abs;
import com.rukin.laboratory4.entity.operator.unary.Sin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Node extends ArrayList<Node> {

    private static final Random RANDOM = new Random();

    private Operator operator;

    public Node(Operator operator) {
//        super(IntStream.range(0, operator.arity()).mapToObj(i -> (Node) null).collect(Collectors.toList()));
        this.operator = operator;
    }

    public static Node getNode(String sign, Node... nodes) {
        switch (nodes.length) {
            case 1:
                return getUnaryNode(sign, nodes[0]);
            case 2:
                return getBinaryNode(sign, nodes[0], nodes[1]);
            default:
                return getTerminalNode(sign);
        }
    }

    private static Node getBinaryNode(String sign, Node arg1, Node arg2) {
        Node node = null;
        switch (sign) {
            case "+":
                node = new Node(new Plus());
                break;
            case "-":
                node = new Node(new Minus());
                break;
            case "*":
                node = new Node(new Multiply());
                break;
            case "/":
                node = new Node(new Divide());
                break;
            case "^":
                node = new Node(new Power());
                break;
        }
        node.set(0, arg1);
        node.set(1, arg2);
        return node;
    }

    private static Node getUnaryNode(String sign, Node arg) {
        Node node = null;
        switch (sign) {
            case "cos":
                node = new Node(new Cos());
                break;
            case "sin":
                node = new Node(new Sin());
                break;
            case "exp":
                node = new Node(new Exp());
                break;
            case "Abs":
                node = new Node(new Abs());
                break;
        }
        node.set(0, arg);
        return node;
    }

    private static Node getTerminalNode(String sign) {
        try {
            return new Node(new Constant(Double.valueOf(sign)));
        } catch (NumberFormatException e) {
            return new Node(new Variable(Integer.parseInt(sign.substring(1))));
        }
    }

    @Override
    public Node clone() {
        Node node = new Node(operator);
        IntStream.range(0, arity()).forEach(i -> node.set(i, get(i).clone()));
        return node;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public int arity() {
        return operator.arity();
    }

    public Node getRandomChild() {
        return getNode(RANDOM.nextInt(exprSize()));
    }

    public List<Node> find(int arity) {
        List<Node> result = new ArrayList<>();
        find(arity, result);
        return result;
    }

    private void find(int arity, List<Node> result) {
        if (arity() == arity) {
            result.add(this);
        }
        stream().forEach(node -> node.find(arity, result));
    }

    public int exprSize() {
        if (isEmpty()) {
            return 1;
        }
        return stream().mapToInt(Node::exprSize).sum() + 1;
    }

    public int exprDepth() {
        if (isEmpty()) {
            return 1;
        }
        return stream().mapToInt(Node::exprSize).max().getAsInt() + 1;
    }

    public double eval(List<Double> args) {
        if (isEmpty()) {
            return operator.eval(args);
        }
        List<Double> values = stream().map(node -> node.eval(args)).collect(Collectors.toList());
        return operator.eval(values);
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return operator.toString(null);
        }
        List<String> args = stream().map(Node::toString).collect(Collectors.toList());
        return operator.toString(args);
    }

    public Node getNode(int index) {
        if (index == 0) {
            return this;
        }
        for (int i = 0; i < size(); i++) {
            int subtreeSize = get(i).exprSize();
            if (index - subtreeSize <= 0) {
                return get(i).getNode(index - 1);
            }
            index -= subtreeSize;
        }
        throw new IndexOutOfBoundsException("Couldn't find node with index=" + index);
    }
}
