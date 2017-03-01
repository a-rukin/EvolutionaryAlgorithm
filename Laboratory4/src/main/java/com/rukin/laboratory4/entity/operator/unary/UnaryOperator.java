package com.rukin.laboratory4.entity.operator.unary;

import com.rukin.laboratory4.entity.operator.Operator;

public abstract class UnaryOperator extends Operator {

    @Override
    public int arity() {
        return 1;
    }
}
