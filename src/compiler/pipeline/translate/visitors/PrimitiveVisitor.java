package compiler.pipeline.translate.visitors;

import compiler.pipeline.interpret.nodes.ASTPrimitiveNode;
import compiler.pipeline.translate.nodes.ObjectNode;
import compiler.symbol.tables.runtime.primitive.PrimitiveSymbolTable;
import compiler.symbol.tables.runtime.primitive.doubles.PrimitiveDoubleSymbolTable;
import compiler.symbol.tables.runtime.primitive.integers.PrimitiveIntegerSymbolTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Takes a primitive AST node and a primitive symbol table,
 * and returns a primitive object node.
 *
 * Created by dbborens on 4/22/15.
 */
public class PrimitiveVisitor {
    private final Logger logger;

    public PrimitiveVisitor() {
        logger = LoggerFactory.getLogger(PrimitiveVisitor.class);
    }

    public ObjectNode translate(ASTPrimitiveNode toTranslate, PrimitiveSymbolTable symbolTable) {
        logger.debug("Translating primitive {}", toTranslate.getContent());
        if (symbolTable instanceof PrimitiveIntegerSymbolTable) {
            return ((PrimitiveIntegerSymbolTable) symbolTable).getObjectNode(toTranslate);
        } else if (symbolTable instanceof PrimitiveDoubleSymbolTable) {
            return ((PrimitiveDoubleSymbolTable) symbolTable).getObjectNode(toTranslate);
        } else {
            throw new IllegalStateException("Unrecognized primitive");
        }
    }
}
