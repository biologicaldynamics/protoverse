/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package compiler.symbol.tables.runtime.topology.shape;

import compiler.pipeline.translate.nodes.MapObjectNode;
import compiler.pipeline.translate.nodes.ObjectNode;
import compiler.pipeline.translate.nodes.PrimitiveIntegerNode;
import compiler.symbol.symbols.MemberSymbol;
import compiler.symbol.tables.runtime.primitive.integers.IntegerClassSymbolTable;
import compiler.symbol.tables.runtime.primitive.integers.IntegerInstanceSymbolTable;
import runtime.topology.lattice.Lattice;
import runtime.topology.shape.Rectangle;

import java.util.HashMap;
import java.util.function.Supplier;

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
    public Rectangle instantiate(ObjectNode node, Lattice lattice) {
        Integer width = intProperty(node, "width").get();
        Integer height = intProperty(node, "height").get();
        return new Rectangle(lattice, width, height);
    }

}
