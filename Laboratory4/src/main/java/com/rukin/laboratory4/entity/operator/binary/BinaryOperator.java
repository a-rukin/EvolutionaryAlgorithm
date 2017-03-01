package com.rukin.laboratory4.entity.operator.binary;

import com.rukin.laboratory4.entity.operator.Operator;

public abstract class BinaryOperator extends Operator {

    @Override
    public int arity() {
        return 2;
    }
}
