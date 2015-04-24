package compiler.symbol.tables.runtime.topology;

import compiler.symbol.symbols.MemberSymbol;
import compiler.symbol.tables.ClassSymbolTable;
import compiler.symbol.tables.MapSymbolTable;
import compiler.symbol.tables.runtime.topology.boundary.BoundaryClassSymbolTable;
import compiler.symbol.tables.runtime.topology.lattice.LatticeClassSymbolTable;
import compiler.symbol.tables.runtime.topology.shape.ShapeClassSymbolTable;
import runtime.topology.Topology;

import java.util.HashMap;

/**
 * Created by dbborens on 4/21/15.
 */
public class TopologyInstanceSymbolTable extends MapSymbolTable<Topology> {
    @Override
    protected HashMap<String, MemberSymbol> resolveMembers() {
        HashMap<String, MemberSymbol> ret = new HashMap<>(3);
        shape(ret);
        lattice(ret);
        boundary(ret);
        return ret;
    }

    private void boundary(HashMap<String, MemberSymbol> ret) {
        ClassSymbolTable cst = new BoundaryClassSymbolTable();
        MemberSymbol ms = new MemberSymbol(cst, "The boundary condition for the topology.");
        ret.put("boundary", ms);
    }

    private void lattice(HashMap<String, MemberSymbol> ret) {
        ClassSymbolTable cst = new LatticeClassSymbolTable();
        MemberSymbol ms = new MemberSymbol(cst, "The lattice connectivity for the topology.");
        ret.put("lattice", ms);
    }

    private void shape(HashMap<String, MemberSymbol> ret) {
        ClassSymbolTable cst = new ShapeClassSymbolTable();
        MemberSymbol ms = new MemberSymbol(cst, "The shape of the topology's boundary.");
        ret.put("shape", ms);
    }
}
