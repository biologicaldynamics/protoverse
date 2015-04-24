/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol.tables.primitive;

import compiler.pipeline.interpret.nodes.ASTPrimitiveInteger;
import compiler.symbol.symbols.ClassSymbol;
import compiler.symbol.tables.ClassSymbolTable;
import compiler.symbol.tables.InstantiableSymbolTable;

import java.util.HashMap;
import java.util.function.Supplier;

/**
 * Created by dbborens on 3/18/15.
 */
public class IntegerClassSymbolTable extends ClassSymbolTable<Supplier<Integer>> {

    @Override
    protected HashMap<String, ClassSymbol> resolveSubclasses() {
        HashMap<String, ClassSymbol> ret = new HashMap<>(1);
        primitive(ret);
        return ret;
    }

    private void primitive(HashMap<String, ClassSymbol> ret) {
        Supplier<InstantiableSymbolTable> supplier = () -> new PrimitiveIntegerSymbolTable();
        ClassSymbol cs = new ClassSymbol(supplier, "An integer constant.");
        ret.put(ASTPrimitiveInteger.IDENTIFIER, cs);
    }

}
