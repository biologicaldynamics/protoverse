package compiler.symbol.tables.runtime.topology.shape;

import compiler.pipeline.translate.nodes.MapObjectNode;
import compiler.symbol.symbols.MemberSymbol;
import compiler.symbol.tables.MapSymbolTable;
import compiler.symbol.tables.primitive.IntegerClassSymbolTable;
import compiler.symbol.tables.primitive.PrimitiveSymbolTable;
import runtime.topology.lattice.Lattice;
import runtime.topology.shape.Rectangle;
import runtime.topology.shape.Shape;

import java.util.HashMap;

/**
 * Created by dbborens on 4/23/15.
 */
public class RectangleSymbolTable extends ShapeInstanceSymbolTable {
    @Override
    protected HashMap<String, MemberSymbol> resolveMembers() {
        HashMap<String, MemberSymbol> ret = new HashMap<>(2);
        height(ret);
        width(ret);
        return ret;
    }

    private void width(HashMap<String, MemberSymbol> members) {
        IntegerClassSymbolTable cst = new IntegerClassSymbolTable();
        MemberSymbol ms = new MemberSymbol(cst, "The width of the rectangle that bounds the topology.");
        members.put("width", ms);
    }

    private void height(HashMap<String, MemberSymbol> members) {
        IntegerClassSymbolTable cst = new IntegerClassSymbolTable();
        MemberSymbol ms = new MemberSymbol(cst, "The height of the rectangle that bounds the topology.");
        members.put("height", ms);
    }

    @Override
    public Rectangle instantiate(MapObjectNode node, Lattice lattice) {

        return null;
    }
}
