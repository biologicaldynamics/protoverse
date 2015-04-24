package compiler.symbol.tables.runtime.agent.actions;

import compiler.symbol.symbols.ClassSymbol;
import compiler.symbol.tables.ClassSymbolTable;
import runtime.agent.Behavior;

import java.util.HashMap;
import java.util.function.Supplier;

/**
 * Created by dbborens on 4/23/15.
 */
public class BehaviorClassSymbolTable extends ClassSymbolTable<Behavior> {

    @Override
    protected HashMap<String, ClassSymbol> resolveSubclasses() {
        HashMap<String, ClassSymbol> subclasses = new HashMap<>(1);
        basic(subclasses);
        return subclasses;
    }

    private void basic(HashMap<String, ClassSymbol> subclasses) {
        Supplier<BehaviorInstanceSymbolTable> supplier =
                () -> new BehaviorInstanceSymbolTable();

        ClassSymbol cst = new ClassSymbol(supplier, "A behavior is a collection of one or more actions to be performed" +
                " under particular circumstances.");

        subclasses.put("Behavior", cst);
    }
}
