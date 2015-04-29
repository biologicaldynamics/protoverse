package compiler.symbol.tables.runtime.primitive.doubles;

import compiler.pipeline.translate.nodes.ObjectNode;

import java.util.function.Supplier;

/**
 * Created by dbborens on 4/26/15.
 */
public interface DoubleInstanceSymbolTable {

    public Supplier<Double> instantiate(ObjectNode node);
}
