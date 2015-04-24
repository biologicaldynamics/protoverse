package compiler.symbol.tables.runtime.topology.boundary;

import compiler.symbol.symbols.ClassSymbol;
import compiler.symbol.tables.ClassSymbolTable;
import runtime.topology.boundary.AgentBoundary;

import java.util.HashMap;
import java.util.function.Supplier;

/**
 * Created by dbborens on 4/23/15.
 */
public class BoundaryClassSymbolTable extends ClassSymbolTable<AgentBoundary> {

    @Override
    protected HashMap<String, ClassSymbol> resolveSubclasses() {
        HashMap<String, ClassSymbol> members = new HashMap<>(1);
        absorbing(members);
        return members;
    }

    private void absorbing(HashMap<String, ClassSymbol> members) {
        Supplier<AbsorbingBoundarySymbolTable> supplier =
                () -> new AbsorbingBoundarySymbolTable();

        ClassSymbol cs = new ClassSymbol(supplier, "A homogeneous boundary " +
                "condition in which anything that crosses the boundary is " +
                "removed from the system.");

        members.put("Absorbing", cs);
    }

}
