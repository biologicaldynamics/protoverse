package compiler.symbol.tables.runtime.agent;

import compiler.symbol.symbols.MemberSymbol;
import compiler.symbol.tables.ClassSymbolTable;
import compiler.symbol.tables.ListSymbolTable;
import compiler.symbol.tables.MapSymbolTable;
import compiler.symbol.tables.runtime.agent.actions.ActionClassSymbolTable;
import compiler.symbol.tables.runtime.agent.actions.BehaviorClassSymbolTable;
import runtime.agent.Agent;

import java.util.HashMap;

/**
 * Created by dbborens on 4/23/15.
 */
public class AgentInstanceSymbolTable extends MapSymbolTable<Agent> {

    @Override
    protected HashMap<String, MemberSymbol> resolveMembers() {
        HashMap<String, MemberSymbol> members = new HashMap<>(1);
        propDo(members);
        return members;
    }

    private void propDo(HashMap<String, MemberSymbol> members) {
        ClassSymbolTable cst = new BehaviorClassSymbolTable();
        ListSymbolTable lst = new ListSymbolTable(cst);
        MemberSymbol ms = new MemberSymbol(lst, "List of behaviors to perform repeatedly.");
        members.put("do", ms);
    }
}
