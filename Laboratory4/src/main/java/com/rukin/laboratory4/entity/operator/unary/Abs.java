package com.rukin.laboratory4.entity.operator.unary;

import java.util.List;

public class Abs extends UnaryOperator {

    @Override
    public double eval(List<Double> values) {
        return Math.abs(values.get(0));
    }

    @Override
    public String toString(List<String> args) {
        return String.format("abs(%s)", args.get(0));
    }
}
