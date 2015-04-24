package compiler.symbol.tables.runtime.agent.actions;

import compiler.symbol.symbols.MemberSymbol;
import compiler.symbol.tables.MapSymbolTable;
import runtime.agent.actions.Wander;

import java.util.HashMap;

/**
 * Created by dbborens on 4/23/15.
 */
public class WanderSymbolTable extends MapSymbolTable<Wander> {
    @Override
    protected HashMap<String, MemberSymbol> resolveMembers() {
        return new HashMap<>(0);
    }
}
