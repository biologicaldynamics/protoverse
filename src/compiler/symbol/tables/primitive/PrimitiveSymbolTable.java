/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol.tables.primitive;

import compiler.pipeline.interpret.nodes.ASTPrimitiveNode;
import compiler.pipeline.translate.nodes.PrimitiveNode;
import compiler.symbol.tables.InstantiableSymbolTable;

/**
 * Created by dbborens on 3/5/15.
 */
public interface PrimitiveSymbolTable<T extends PrimitiveNode, S extends ASTPrimitiveNode> extends InstantiableSymbolTable {

    public T convert(S toInterpret);
}
