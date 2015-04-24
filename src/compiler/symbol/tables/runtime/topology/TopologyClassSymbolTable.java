package compiler.symbol.tables.runtime.topology;

import compiler.symbol.symbols.ClassSymbol;
import compiler.symbol.tables.ClassSymbolTable;
import compiler.symbol.tables.InstantiableSymbolTable;
import runtime.topology.Topology;

import java.util.HashMap;
import java.util.function.Supplier;

/**
 * Created by dbborens on 4/21/15.
 */
public class TopologyClassSymbolTable extends ClassSymbolTable<Topology> {

    @Override
    protected HashMap<String, ClassSymbol> resolveSubclasses() {
        HashMap<String, ClassSymbol> ret = new HashMap<>(1);
        basic(ret);
        return ret;
    }

    private void basic(HashMap<String, ClassSymbol> ret) {
        Supplier<InstantiableSymbolTable> supplier = TopologyInstanceSymbolTable::new;
        ClassSymbol cs = new ClassSymbol(supplier, "A Nanoverse topology.");
        ret.put("Topology", cs);
    }
}
