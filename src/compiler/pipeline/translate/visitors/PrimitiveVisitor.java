package compiler.pipeline.translate.visitors;

import compiler.pipeline.interpret.nodes.ASTContainerNode;
import compiler.pipeline.interpret.nodes.ASTNode;
import compiler.pipeline.interpret.nodes.ASTPrimitiveNode;
import compiler.pipeline.translate.helpers.TranslationCallback;
import compiler.pipeline.translate.nodes.ObjectNode;
import compiler.pipeline.translate.nodes.PrimitiveNode;
import compiler.symbol.tables.MapSymbolTable;
import compiler.symbol.tables.primitive.PrimitiveSymbolTable;
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
        PrimitiveNode ret =  symbolTable.convert(toTranslate);
        logger.debug("Translated primitive {} {}",
                ret.getInstantiatingClass().getSimpleName(), ret.report());
        return ret;
    }
}
