package com.rukin.laboratory4.mutation;

import com.rukin.laboratory4.entity.operator.Variable;
import com.rukin.laboratory4.entity.Node;
import com.rukin.laboratory4.entity.operator.Constant;
import com.rukin.laboratory4.entity.operator.Operator;

import java.util.List;
import java.util.Random;

public class TrancatedMutation implements Mutation {

    private static final Random RANDOM = new Random();

    private final List<Constant> constants;
    private final List<Variable> variables;

    public TrancatedMutation(List<Constant> constants, List<Variable> variables) {
        this.constants = constants;
        this.variables = variables;
    }

    @Override
    public void mutate(Node node) {
        int size = node.exprSize();
        Node child = node.getNode(RANDOM.nextInt(size));

        child.setOperator(getTerminal());
        child.clear();
    }

    private Operator getTerminal() {
        int choose = RANDOM.nextInt(variables.size() + 1);
        if (choose < variables.size()) {
            return variables.get(choose);
        } else {
            return constants.get(RANDOM.nextInt(constants.size()));
        }
    }
}
