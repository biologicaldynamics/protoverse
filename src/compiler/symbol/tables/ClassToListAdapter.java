/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package compiler.symbol.tables;


import java.util.function.Supplier;

/**
 * Created by dbborens on 3/13/15.
 */
public class ClassToListAdapter implements ResolvingSymbolTable {

    private final Supplier<ListSymbolTable> contents;

    public ClassToListAdapter(Supplier<ListSymbolTable> contents) {
        this.contents = contents;
    }

    @Override
    public InstantiableSymbolTable getSymbolTable(String identifier) {
        return contents.get();
    }
}