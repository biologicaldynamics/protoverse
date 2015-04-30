/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package compiler.symbol.tables.runtime.primitive;

import compiler.pipeline.interpret.nodes.ASTPrimitiveNode;
import compiler.pipeline.translate.nodes.ObjectNode;
import compiler.pipeline.translate.nodes.PrimitiveDoubleNode;
import compiler.pipeline.translate.nodes.PrimitiveObjectNode;
import compiler.symbol.symbols.MemberSymbol;
import compiler.symbol.tables.InstantiableSymbolTable;
import compiler.symbol.tables.MapSymbolTable;

import java.util.HashMap;
import java.util.function.Supplier;

/**
 * Created by dbborens on 4/26/15.
 */
public abstract class PrimitiveSymbolTable<T> implements InstantiableSymbolTable {

    public Supplier<T> instantiate(ObjectNode node) {
        T value = ((PrimitiveObjectNode<T>) node).getValue();
        return () -> value;
    }

    public abstract PrimitiveObjectNode<T> getObjectNode(ASTPrimitiveNode<T> astNode);

}
