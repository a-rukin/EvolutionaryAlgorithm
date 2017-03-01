package com.rukin.laboratory4.entity.operator;

import java.util.List;

public class Variable extends Operator {

    private final int index;

    public Variable(int index) {
        super();
        this.index = index;
    }

    @Override
    public int arity() {
        return 0;
    }

    @Override
    public double eval(List<Double> values) {
        return values.get(index);
    }

    @Override
    public String toString(List<String> args) {
        return String.format("x%d", index);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Variable variable = (Variable) o;

        return index == variable.index;

    }

    @Override
    public int hashCode() {
        return index;
    }
}
