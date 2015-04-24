package compiler.symbol.tables.runtime.topology.shape;

import compiler.symbol.symbols.MemberSymbol;
import compiler.symbol.tables.MapSymbolTable;
import compiler.symbol.tables.primitive.IntegerClassSymbolTable;
import compiler.symbol.tables.primitive.PrimitiveSymbolTable;
import runtime.topology.shape.Rectangle;

import java.util.HashMap;

/**
 * Created by dbborens on 4/23/15.
 */
public class RectangleSymbolTable extends MapSymbolTable<Rectangle> {
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
}
