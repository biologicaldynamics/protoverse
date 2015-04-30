/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package compiler.symbol.tables.runtime.process;

import compiler.symbol.symbols.ClassSymbol;
import compiler.symbol.tables.ClassSymbolTable;
import runtime.process.agent.AgentProcess;

import java.util.HashMap;
import java.util.function.Supplier;

/**
 * Created by dbborens on 3/18/15.
 */
public class AgentProcessClassSymbolTable extends ClassSymbolTable<AgentProcess> {

    @Override
    protected HashMap<String, ClassSymbol> resolveSubclasses() {
        HashMap<String, ClassSymbol> subclasses = new HashMap<>(1);
        scatter(subclasses);
        return subclasses;
    }

    private void scatter(HashMap<String, ClassSymbol> subclasses) {
        Supplier<ScatterSymbolTable> supplier = () -> new ScatterSymbolTable();
        ClassSymbol cs = new ClassSymbol(supplier, "Scatter a specified " +
                "number of agents within a specified region of the topology.");

        subclasses.put("Scatter", cs);
    }
}
