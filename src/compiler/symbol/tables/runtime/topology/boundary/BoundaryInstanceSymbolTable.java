package compiler.symbol.tables.runtime.topology.boundary;

import compiler.pipeline.translate.nodes.ObjectNode;
import compiler.symbol.tables.MapSymbolTable;
import runtime.topology.boundary.AgentBoundary;
import runtime.topology.lattice.Lattice;
import runtime.topology.shape.Shape;

/**
 * Created by dbborens on 4/27/15.
 */
public abstract class BoundaryInstanceSymbolTable<T extends AgentBoundary> extends MapSymbolTable<T> {

    public abstract AgentBoundary instantiate(Shape shape);
}
