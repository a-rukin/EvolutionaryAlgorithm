package com.rukin.laboratory4.entity.operator;

import java.util.List;

public class Constant extends Operator {

    private final double value;

    public Constant(double value) {
        this.value = value;
    }

    @Override
    public int arity() {
        return 0;
    }

    @Override
    public double eval(List<Double> values) {
        return value;
    }

    @Override
    public String toString(List<String> args) {
        if (Math.floor(value) == value) {
            return String.format("%.0f", value);
        }
        return String.format("%.3f", value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Constant constant = (Constant) o;

        return Double.compare(constant.value, value) == 0;

    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(value);
        return (int) (temp ^ (temp >>> 32));
    }
}
