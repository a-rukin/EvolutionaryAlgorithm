package com.rukin.laboratory4.entity.operator.unary;

import java.util.List;

public class Exp extends UnaryOperator {

    @Override
    public double eval(List<Double> values) {
        return Math.exp(values.get(0));
    }

    @Override
    public String toString(List<String> args) {
        return String.format("exp(%s)", args.get(0));
    }
}
