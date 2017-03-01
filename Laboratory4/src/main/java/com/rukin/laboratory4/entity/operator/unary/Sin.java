package com.rukin.laboratory4.entity.operator.unary;

import java.util.List;

public class Sin extends UnaryOperator {

    @Override
    public double eval(List<Double> values) {
        return Math.sin(values.get(0));
    }

    @Override
    public String toString(List<String> args) {
        return String.format("sin(%s)", args.get(0));
    }
}
