/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package compiler.symbol.tables.runtime.topology.lattice;

import compiler.pipeline.translate.nodes.MapObjectNode;
import compiler.symbol.tables.MapSymbolTable;
import runtime.topology.lattice.Lattice;

/**
 * Created by dbborens on 4/25/15.
 */
public abstract class LatticeInstanceSymbolTable extends MapSymbolTable {

    public abstract Lattice instantiate();
}
