package compiler.symbol.tables.runtime.topology.lattice;

import compiler.symbol.symbols.MemberSymbol;
import compiler.symbol.tables.MapSymbolTable;
import runtime.topology.lattice.RectangularLattice;

import java.util.HashMap;

/**
 * Created by dbborens on 4/23/15.
 */
public class RectangularLatticeSymbolTable extends MapSymbolTable<RectangularLattice> {
    @Override
    protected HashMap<String, MemberSymbol> resolveMembers() {
        return new HashMap<>(0);
    }
}
