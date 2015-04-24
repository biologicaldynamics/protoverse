/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.control.logic;

import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * Created by dbborens on 3/18/15.
 */
public class RelationalOperator implements Supplier<Boolean> {

    private BiFunction<Double, Double, Boolean> operator;
    private Supplier<Double> leftOperand;
    private Supplier<Double> rightOperand;

    public RelationalOperator(BiFunction<Double, Double, Boolean> operator,
                              Supplier<Double> leftOperand,
                              Supplier<Double> rightOperand) {

        this.operator = operator;
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }

    @Override
    public Boolean get() {
        Double left = leftOperand.get();
        Double right = rightOperand.get();
        return operator.apply(left, right);
    }
}
