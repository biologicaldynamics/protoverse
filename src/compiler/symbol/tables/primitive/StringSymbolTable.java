/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol.tables.primitive;

import compiler.pipeline.interpret.nodes.ASTPrimitiveString;
import compiler.pipeline.translate.nodes.ConstantString;

/**
 * Created by dbborens on 3/5/15.
 */
public class StringSymbolTable implements PrimitiveSymbolTable<ConstantString, ASTPrimitiveString> {

    @Override
    public ConstantString convert(ASTPrimitiveString toInterpret) {
        String value = toInterpret.getContent();
        return new ConstantString(value);
    }

//    @Override
//    public Class getInstanceClass() {
//        return null;
//    }
}
