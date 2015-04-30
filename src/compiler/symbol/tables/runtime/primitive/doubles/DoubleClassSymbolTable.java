/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package compiler.symbol.tables.runtime.primitive.doubles;

import compiler.pipeline.interpret.nodes.ASTPrimitiveDouble;
import compiler.symbol.symbols.ClassSymbol;
import compiler.symbol.tables.ClassSymbolTable;
import compiler.symbol.tables.InstantiableSymbolTable;

import java.util.HashMap;
import java.util.function.Supplier;

/**
 * Created by dbborens on 3/18/15.
 */
public class DoubleClassSymbolTable extends ClassSymbolTable<Supplier<Double>> {

    @Override
    protected HashMap<String, ClassSymbol> resolveSubclasses() {
        HashMap<String, ClassSymbol> ret = new HashMap<>(1);
        primitive(ret);
        return ret;
    }

    private void primitive(HashMap<String, ClassSymbol> ret) {
        Supplier<InstantiableSymbolTable> supplier = () -> new PrimitiveDoubleSymbolTable();
        ClassSymbol cs = new ClassSymbol(supplier, "An integer constant.");
        ret.put(ASTPrimitiveDouble.IDENTIFIER, cs);
    }
}
