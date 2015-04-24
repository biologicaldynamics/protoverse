/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol.tables;


/**
 * Created by dbborens on 3/13/15.
 */
public interface ResolvingSymbolTable extends SymbolTable {
    public InstantiableSymbolTable getSymbolTable(String identifier);
}
