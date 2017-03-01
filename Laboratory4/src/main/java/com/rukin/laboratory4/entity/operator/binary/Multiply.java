package com.rukin.laboratory4.entity.operator.binary;

import java.util.List;

public class Multiply extends BinaryOperator {

    @Override
    public double eval(List<Double> values) {
        return values.get(0) * values.get(1);
    }

    @Override
    public String toString(List<String> args) {
        return String.format("%s * %s", args.get(0), args.get(1));
    }
}
