package compiler.symbol.tables.runtime.control;

import compiler.symbol.symbols.MemberSymbol;
import compiler.symbol.tables.ClassSymbolTable;
import compiler.symbol.tables.ListSymbolTable;
import compiler.symbol.tables.MapSymbolTable;
import compiler.symbol.tables.runtime.process.AgentProcessClassSymbolTable;
import compiler.symbol.tables.runtime.topology.TopologyClassSymbolTable;
import runtime.control.Simulation;

import java.util.HashMap;

/**
 * Created by dbborens on 4/21/15.
 */
public class SimulationSymbolTable extends MapSymbolTable<Simulation> {

    @Override
    protected HashMap<String, MemberSymbol> resolveMembers() {
        HashMap<String, MemberSymbol> ret = new HashMap<>(2);
        topology(ret);
        initially(ret);
        return ret;
    }

    private void initially(HashMap<String, MemberSymbol> ret) {
        ClassSymbolTable cst = new AgentProcessClassSymbolTable();
        ListSymbolTable lst = new ListSymbolTable(cst);
        MemberSymbol ms = new MemberSymbol(lst, "A list of top-down processes " +
                "to perform at the outset of the simulation.");
        ret.put("initially", ms);
    }

    private void topology(HashMap<String, MemberSymbol> ret) {
        TopologyClassSymbolTable st = new TopologyClassSymbolTable();
        MemberSymbol ms = new MemberSymbol(st, "The topological" +
                " properties of the system.");

        ret.put("topology", ms);
    }

}
