package compiler.symbol.tables.runtime.topology.shape;

import compiler.pipeline.translate.nodes.MapObjectNode;
import compiler.pipeline.translate.nodes.ObjectNode;
import compiler.symbol.tables.MapSymbolTable;
import runtime.topology.lattice.Lattice;
import runtime.topology.shape.Shape;

/**
 * Created by dbborens on 4/25/15.
 */
public abstract class ShapeInstanceSymbolTable extends MapSymbolTable<Shape> {

    public abstract Shape instantiate(ObjectNode node, Lattice lattice);
}
