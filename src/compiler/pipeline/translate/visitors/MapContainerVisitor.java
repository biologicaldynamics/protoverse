package compiler.pipeline.translate.visitors;

import compiler.pipeline.interpret.nodes.ASTContainerNode;
import compiler.pipeline.interpret.nodes.ASTNode;
import compiler.pipeline.translate.helpers.TranslationCallback;
import compiler.pipeline.translate.nodes.MapObjectNode;
import compiler.pipeline.translate.nodes.ObjectNode;
import compiler.symbol.tables.*;
import compiler.symbol.tables.MapSymbolTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Takes a container node and a map symbol table, and returns
 * a map object node.
 *
 * Created by dbborens on 4/22/15.
 */
public class MapContainerVisitor {
    private final TranslationCallback walker;
    private final Logger logger;

    public MapContainerVisitor(TranslationCallback walker) {
        logger = LoggerFactory.getLogger(MapContainerVisitor.class);
        this.walker = walker;
    }

    public ObjectNode translate(ASTContainerNode toTranslate, MapSymbolTable symbolTable) {
        logger.debug("Translating {} using MST for class {}", toTranslate.getIdentifier(),
                symbolTable.getInstanceClass().getSimpleName());

        MapObjectNode node = new MapObjectNode(symbolTable);

        // Visit each child.
        toTranslate.getChildren()
//                .parallel()
                .forEach(child -> {
                    // The child's identifier is a field of this object.
                    String identifier = child.getIdentifier();

                    // Get a symbol table that can resolve the field's value.
                    ResolvingSymbolTable childRST = symbolTable.getSymbolTable(identifier);

                    ObjectNode childNode;

                    // If the child is a list, pass it back to be resolved.
                    if (childRST instanceof ListSymbolTable) {
                        childNode = walker.walk(child, childRST);

                    // Otherwise, it is the granchild that will be resolved.
                    } else {

                        // The child will have exactly one child of its own: the instance.
                        ASTNode grandchild = child.getChildren().findFirst().get();

                        // The identifier of the grandchild can be resolved to an instance.
                        String gcIdentifier = grandchild.getIdentifier();
                        InstantiableSymbolTable childIST = childRST.getSymbolTable(gcIdentifier);

                        // Call back on this grandchild.
                        childNode = walker.walk(grandchild, childIST);
                    }

                    logger.debug("Loading new {} to property \"{}\"",
                            childNode.getInstantiatingClass().getSimpleName(),
                            identifier);

                    node.loadMember(identifier, childNode);
                });

        return node;
    }
}
