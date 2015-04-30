/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package compiler.symbol.tables.runtime.primitive.integers;

import compiler.pipeline.translate.nodes.ObjectNode;

import java.util.function.Supplier;

/**
 * Created by dbborens on 4/26/15.
 */
public interface IntegerInstanceSymbolTable {

    public Supplier<Integer> instantiate(ObjectNode node);
}
