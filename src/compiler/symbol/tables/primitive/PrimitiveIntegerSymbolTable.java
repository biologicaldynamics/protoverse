/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol.tables.primitive;

import compiler.pipeline.interpret.nodes.ASTPrimitiveInteger;
import compiler.pipeline.translate.nodes.ConstantInteger;

/**
 * Created by dbborens on 3/5/15.
 */
public class PrimitiveIntegerSymbolTable implements PrimitiveSymbolTable<ConstantInteger, ASTPrimitiveInteger> {
    @Override
    public ConstantInteger convert(ASTPrimitiveInteger toInterpret) {
        Integer value = toInterpret.getContent();
        return new ConstantInteger(value);
    }

//    @Override
//    public Class getInstanceClass() {
//        return Supplier.class;
//    }
}
