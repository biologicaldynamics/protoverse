package compiler.symbol.tables.runtime.schedule.event;

import compiler.symbol.symbols.ClassSymbol;
import compiler.symbol.tables.ClassSymbolTable;
import runtime.schedule.event.Event;

import java.util.HashMap;
import java.util.function.Supplier;

/**
 * Created by dbborens on 4/23/15.
 */
public class EventClassSymbolTable extends ClassSymbolTable<Event> {

    @Override
    protected HashMap<String, ClassSymbol> resolveSubclasses() {
        HashMap<String, ClassSymbol> subclasses = new HashMap<>(1);
        basic(subclasses);
        return subclasses;
    }

    private void basic(HashMap<String, ClassSymbol> subclasses) {
        Supplier<DeterministicEventInstanceSymbolTable> supplier =
                () -> new DeterministicEventInstanceSymbolTable();

        ClassSymbol cst = new ClassSymbol(supplier, "A behavior is a collection of one or more actions to be performed" +
                " under particular circumstances.");

        subclasses.put("Behavior", cst);
    }
}
