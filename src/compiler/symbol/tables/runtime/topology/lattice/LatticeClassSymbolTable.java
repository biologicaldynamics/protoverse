package compiler.symbol.tables.runtime.topology.lattice;

import compiler.symbol.symbols.ClassSymbol;
import compiler.symbol.tables.ClassSymbolTable;
import runtime.topology.lattice.Lattice;

import java.util.HashMap;
import java.util.function.Supplier;

/**
 * Created by dbborens on 4/23/15.
 */
public class LatticeClassSymbolTable extends ClassSymbolTable<Lattice> {
    @Override
    protected HashMap<String, ClassSymbol> resolveSubclasses() {
        HashMap<String, ClassSymbol> members = new HashMap<>(1);
        rectangular(members);
        return members;
    }

    private void rectangular(HashMap<String, ClassSymbol> members) {
        Supplier<RectangularLatticeSymbolTable> supplier = () -> new RectangularLatticeSymbolTable();
        ClassSymbol cs = new ClassSymbol(supplier, "A two-dimensional regular lattice with rectangular connectivity " +
                "(n=2 neighbors).");
        members.put("Rectangular", cs);
    }
}
