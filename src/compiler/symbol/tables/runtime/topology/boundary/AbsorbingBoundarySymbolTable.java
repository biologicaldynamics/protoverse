package compiler.symbol.tables.runtime.topology.boundary;

import compiler.symbol.symbols.MemberSymbol;
import compiler.symbol.tables.MapSymbolTable;
import runtime.topology.boundary.AbsorbingAgentBoundary2D;

import java.util.HashMap;

/**
 * Created by dbborens on 4/23/15.
 */
public class AbsorbingBoundarySymbolTable extends MapSymbolTable<AbsorbingAgentBoundary2D> {

    @Override
    protected HashMap<String, MemberSymbol> resolveMembers() {
        return new HashMap<>(0);
    }
}
