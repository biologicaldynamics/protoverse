/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol.tables.runtime.primitive.integers;

import compiler.pipeline.interpret.nodes.ASTPrimitiveNode;
import compiler.pipeline.translate.nodes.PrimitiveIntegerNode;
import compiler.pipeline.translate.nodes.PrimitiveObjectNode;
import compiler.symbol.tables.runtime.primitive.PrimitiveSymbolTable;

import java.util.function.Supplier;

/**
 * Created by dbborens on 3/5/15.
 */
public class PrimitiveIntegerSymbolTable extends PrimitiveSymbolTable<Integer> implements IntegerInstanceSymbolTable {

    @Override
    public PrimitiveObjectNode<Integer> getObjectNode(ASTPrimitiveNode<Integer> astNode) {
        return new PrimitiveIntegerNode(this, astNode.getContent());
    }

}
