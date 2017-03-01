package com.rukin.laboratory4.entity.operator;

import java.util.List;

public abstract class Operator {

    public abstract int arity();

    public abstract double eval(List<Double> values);

    public abstract String toString(List<String> args);
}
