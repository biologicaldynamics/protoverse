/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.visitors;

import compiler.pipeline.interpret.nodes.ASTContainerNode;
import compiler.pipeline.interpret.nodes.ASTNode;
import compiler.pipeline.translate.helpers.TranslationCallback;
import compiler.pipeline.translate.nodes.ListObjectNode;
import compiler.pipeline.translate.nodes.ObjectNode;
import compiler.symbol.tables.InstantiableSymbolTable;
import compiler.symbol.tables.ListSymbolTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Takes a container node and a list symbol table, and returns
 * a list object node.
 *
 * Created by dbborens on 4/22/15.
 */
public class ListContainerVisitor {

    private final TranslationCallback walker;
    private final Logger logger;

    public ListContainerVisitor(TranslationCallback walker) {
        logger = LoggerFactory.getLogger(ListContainerVisitor.class);
        this.walker = walker;
    }

    public ObjectNode translate(ASTContainerNode toTranslate, ListSymbolTable symbolTable) {
        logger.debug("Translating {} using LST for class {}", toTranslate.getIdentifier(),
                symbolTable.getMemberClass().getSimpleName());

        ListObjectNode node = new ListObjectNode(symbolTable);
        toTranslate.getChildren()
                .forEach(child -> {

                    // The child's identifier is an instantiable subclass of
                    // the list class.
                    String identifier = child.getIdentifier();

                    InstantiableSymbolTable childIST =
                            symbolTable.getSymbolTable(identifier);

                    ObjectNode childNode = walker.walk(child, childIST);

                    logger.debug("Loading new {} to list of {}",
                            childNode.getInstantiatingClass().getSimpleName(),
                            symbolTable.getMemberClass().getSimpleName());

                    node.loadMember(childNode);
                });

        return node;
    }
}
