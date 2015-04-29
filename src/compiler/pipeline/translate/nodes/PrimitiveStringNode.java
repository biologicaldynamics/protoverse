package compiler.pipeline.translate.nodes;

import compiler.symbol.tables.runtime.primitive.PrimitiveSymbolTable;

/**
 * Created by dbborens on 4/26/15.
 */
public class PrimitiveStringNode extends PrimitiveObjectNode<String> {

    public PrimitiveStringNode(PrimitiveSymbolTable<String> symbolTable, String value) {
        super(symbolTable, value);
    }
}
