package compiler.symbol.tables.runtime.topology.shape;

import compiler.symbol.symbols.ClassSymbol;
import compiler.symbol.tables.ClassSymbolTable;
import compiler.symbol.tables.MapSymbolTable;
import runtime.topology.shape.Shape;

import java.util.HashMap;
import java.util.function.Supplier;

/**
 * Created by dbborens on 4/23/15.
 */
public class ShapeClassSymbolTable extends ClassSymbolTable<Shape> {

    @Override
    protected HashMap<String, ClassSymbol> resolveSubclasses() {
        HashMap<String, ClassSymbol> ret = new HashMap<>(1);
        rectangle(ret);
        return ret;
    }

    private void rectangle(HashMap<String, ClassSymbol> subclasses) {
        Supplier<MapSymbolTable> supplier = () -> new RectangleSymbolTable();
        ClassSymbol cs = new ClassSymbol(supplier, "A rectangular boundary.");
        subclasses.put("Rectangle", cs);
    }
}
