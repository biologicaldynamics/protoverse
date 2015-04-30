/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package compiler.symbol.tables.runtime.agent;

import compiler.symbol.symbols.ClassSymbol;
import compiler.symbol.tables.ClassSymbolTable;
import runtime.agent.Agent;

import java.util.HashMap;
import java.util.function.Supplier;

/**
 * Created by dbborens on 3/18/15.
 */
public class AgentClassSymbolTable extends ClassSymbolTable<Supplier<Agent>> {
    @Override
    protected HashMap<String, ClassSymbol> resolveSubclasses() {
        HashMap<String, ClassSymbol> subclasses = new HashMap<>(1);
        basic(subclasses);
        return subclasses;
    }

    private void basic(HashMap<String, ClassSymbol> subclasses) {
        Supplier<AgentInstanceSymbolTable> supplier =
                () -> new AgentInstanceSymbolTable();

        ClassSymbol cs = new ClassSymbol(supplier, "Description of a Nanoverse agent.");
        subclasses.put("Agent", cs);
    }
}
