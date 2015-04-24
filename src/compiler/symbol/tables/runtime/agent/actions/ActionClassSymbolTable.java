/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol.tables.runtime.agent.actions;

import compiler.symbol.symbols.ClassSymbol;
import compiler.symbol.tables.ClassSymbolTable;
import runtime.agent.actions.Action;

import java.util.HashMap;
import java.util.function.Supplier;

/**
 * Created by dbborens on 3/18/15.
 */
public class ActionClassSymbolTable extends ClassSymbolTable<Action> {

    @Override
    protected HashMap<String, ClassSymbol> resolveSubclasses() {
        HashMap<String, ClassSymbol> subclasses = new HashMap<>(1);
        wander(subclasses);
        return subclasses;
    }

    private void wander(HashMap<String, ClassSymbol> subclasses) {
        Supplier<WanderSymbolTable> supplier =
                () -> new WanderSymbolTable();

        ClassSymbol cs = new ClassSymbol(supplier, "The agent moves by one " +
                "unit toward a random adjacent vacancy. If no adjacent site " +
                "is vacant, agent does nothing.");

        subclasses.put("Wander", cs);
    }
}
