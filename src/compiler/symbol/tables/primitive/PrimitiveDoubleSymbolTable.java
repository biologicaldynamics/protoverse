/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol.tables.primitive;

import compiler.pipeline.interpret.nodes.ASTPrimitiveDouble;
import compiler.pipeline.translate.nodes.ConstantDouble;

/**
 * Created by dbborens on 3/5/15.
 */
public class PrimitiveDoubleSymbolTable implements PrimitiveSymbolTable<ConstantDouble, ASTPrimitiveDouble>  {
    @Override
    public ConstantDouble convert(ASTPrimitiveDouble toInterpret) {
        Double value = toInterpret.getContent();
        return new ConstantDouble(value);
    }

//    @Override
//    public Class getInstanceClass() {
//        return Supplier.class;
//    }
}
