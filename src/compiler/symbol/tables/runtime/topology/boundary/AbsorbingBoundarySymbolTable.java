/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package compiler.symbol.tables.runtime.topology.boundary;

import compiler.pipeline.translate.nodes.MapObjectNode;
import compiler.pipeline.translate.nodes.ObjectNode;
import compiler.symbol.symbols.MemberSymbol;
import compiler.symbol.tables.MapSymbolTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import runtime.topology.boundary.AbsorbingAgentBoundary2D;
import runtime.topology.boundary.AgentBoundary;
import runtime.topology.lattice.Lattice;
import runtime.topology.shape.Shape;

import java.util.HashMap;

/**
 * Created by dbborens on 4/23/15.
 */
public class AbsorbingBoundarySymbolTable extends BoundaryInstanceSymbolTable<AbsorbingAgentBoundary2D> {

    private Logger logger;
    public AbsorbingBoundarySymbolTable() {
        super();
        logger = LoggerFactory.getLogger(AbsorbingBoundarySymbolTable.class);
    }
    @Override
    protected HashMap<String, MemberSymbol> resolveMembers() {
        return new HashMap<>(0);
    }

    @Override
    public AgentBoundary instantiate(Shape shape) {
        logger.debug("Instantiating node as AgentBoundary with shape {}.",
                shape.getClass().getSimpleName());

        return new AbsorbingAgentBoundary2D(shape);
    }
}
