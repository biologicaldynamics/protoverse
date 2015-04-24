/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol.tables.primitive;

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
        return new HashMap<>(0);
    }

    @Override
    public InstantiableSymbolTable getSymbolTable(String identifier) {
        if (!identifier.equals(ASTPrimitiveDouble.IDENTIFIER)) {
            throw new IllegalStateException("Consistency failure");
        }

        return new PrimitiveDoubleSymbolTable();
    }

//    public static Supplier<Double> instantiate(ObjectNode node) {
//        if (node instanceof ConstantFloat) {
//            return ((ConstantFloat) node).instantiate();
//        } else {
//            throw new IllegalArgumentException("Unrecognized node");
//        }
//    }
}
