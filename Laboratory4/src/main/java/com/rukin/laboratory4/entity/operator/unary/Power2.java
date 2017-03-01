package com.rukin.laboratory4.entity.operator.unary;

import java.util.List;

public class Power2 extends UnaryOperator {

    @Override
    public double eval(List<Double> values) {
        return Math.pow(values.get(0), 2);
    }

    @Override
    public String toString(List<String> args) {
        return String.format("((%s)^2)", args.get(0));
    }
}
