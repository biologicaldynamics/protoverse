package compiler.symbol.tables.runtime.agent.actions;

import compiler.symbol.symbols.MemberSymbol;
import compiler.symbol.tables.ClassSymbolTable;
import compiler.symbol.tables.ListSymbolTable;
import compiler.symbol.tables.MapSymbolTable;
import compiler.symbol.tables.primitive.DoubleClassSymbolTable;
import compiler.symbol.tables.primitive.PrimitiveDoubleSymbolTable;
import runtime.agent.Behavior;
import runtime.agent.actions.Action;

import java.util.HashMap;

/**
 * Created by dbborens on 4/23/15.
 */
public class BehaviorInstanceSymbolTable extends MapSymbolTable<Behavior> {

    @Override
    protected HashMap<String, MemberSymbol> resolveMembers() {
        HashMap<String, MemberSymbol> members = new HashMap<>(2);
        actions(members);
        every(members);
        return members;
    }

    private void every(HashMap<String, MemberSymbol> members) {
        DoubleClassSymbolTable cst = new DoubleClassSymbolTable();
        MemberSymbol ms = new MemberSymbol(cst, "The period with which to " +
                "perform the repeated action.");

        members.put("every", ms);
    }

    private void actions(HashMap<String, MemberSymbol> members) {
        ActionClassSymbolTable cst = new ActionClassSymbolTable();
        ListSymbolTable lst = new ListSymbolTable(cst);
        MemberSymbol ms = new MemberSymbol(lst, "The list of actions to perform.");

        members.put("actions", ms);
    }
}
