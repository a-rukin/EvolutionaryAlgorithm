package com.rukin.laboratory4.entity.operator.unary;

import java.util.List;

public class Cos extends UnaryOperator {

    @Override
    public double eval(List<Double> values) {
        return Math.cos(values.get(0));
    }

    @Override
    public String toString(List<String> args) {
        return String.format("cos(%s)", args.get(0));
    }
}
