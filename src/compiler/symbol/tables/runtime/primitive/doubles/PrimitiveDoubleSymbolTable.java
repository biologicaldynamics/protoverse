/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package compiler.symbol.tables.runtime.primitive.doubles;

import compiler.pipeline.interpret.nodes.ASTPrimitiveDouble;
import compiler.pipeline.interpret.nodes.ASTPrimitiveNode;
import compiler.pipeline.translate.nodes.ObjectNode;
import compiler.pipeline.translate.nodes.PrimitiveDoubleNode;
import compiler.pipeline.translate.nodes.PrimitiveObjectNode;
import compiler.symbol.symbols.MemberSymbol;
import compiler.symbol.tables.runtime.primitive.PrimitiveSymbolTable;

import java.util.HashMap;
import java.util.function.Supplier;

/**
 * Created by dbborens on 3/5/15.
 */
public class PrimitiveDoubleSymbolTable extends PrimitiveSymbolTable<Double> implements DoubleInstanceSymbolTable {

    @Override
    public PrimitiveObjectNode<Double> getObjectNode(ASTPrimitiveNode<Double> astNode) {
        return new PrimitiveDoubleNode(this, astNode.getContent());
    }
}
