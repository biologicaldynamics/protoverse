package compiler.symbol.tables.runtime.process;

import compiler.symbol.symbols.MemberSymbol;
import compiler.symbol.tables.MapSymbolTable;
import compiler.symbol.tables.primitive.IntegerClassSymbolTable;
import compiler.symbol.tables.runtime.agent.AgentClassSymbolTable;
import runtime.process.agent.Scatter;

import java.util.HashMap;

/**
 * Created by dbborens on 4/23/15.
 */
public class ScatterSymbolTable extends MapSymbolTable<Scatter> {

    @Override
    protected HashMap<String, MemberSymbol> resolveMembers() {
        HashMap<String, MemberSymbol> members = new HashMap<>(2);
        count(members);
        description(members);
        return members;
    }

    private void description(HashMap<String, MemberSymbol> members) {
        AgentClassSymbolTable cst = new AgentClassSymbolTable();
        MemberSymbol ms = new MemberSymbol(cst, "The properties of the agents to be scattered.");
        members.put("description", ms);
    }

    private void count(HashMap<String, MemberSymbol> members) {
        IntegerClassSymbolTable cst = new IntegerClassSymbolTable();
        MemberSymbol ms = new MemberSymbol(cst, "The number of agents to scatter.");
        members.put("count", ms);
    }
}
