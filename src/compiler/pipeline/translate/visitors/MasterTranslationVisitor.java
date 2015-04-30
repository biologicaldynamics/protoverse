/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.visitors;

import compiler.pipeline.interpret.nodes.ASTContainerNode;
import compiler.pipeline.interpret.nodes.ASTNode;
import compiler.pipeline.interpret.nodes.ASTPrimitiveNode;
import compiler.pipeline.translate.helpers.TranslationCallback;
import compiler.pipeline.translate.nodes.ObjectNode;
import compiler.symbol.tables.ListSymbolTable;
import compiler.symbol.tables.MapSymbolTable;
import compiler.symbol.tables.SymbolTable;
import compiler.symbol.tables.runtime.primitive.PrimitiveSymbolTable; import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Depth-first search of the abstract syntax tree, which translates
 * each node into a roughly one-to-one correspondence with the
 * prototype for the Java objects into which the simulation will be
 * compiled. These translations are not necessarily complete; default
 * values will be inferred, if possible, in the next stage of the pipeline.
 *
 * Process, starting with the root symbol table and node:
 *
 *    (0) Before starting, each symbol in the symbol table corresponding to
 *        a node in the abstract syntax tree will have been resolved to the
 *        narrowest possible symbol table.
 *
 *    (1) For each child node, retrieve its symbol table, and then recursively
 *        translate it into an ObjectNode.
 *
 *    (2) Load each returned ObjectNode as members of the local ObjectNode.
 *
 * Created by dbborens on 2/22/15.
 */
public class MasterTranslationVisitor {

    private final MapContainerVisitor mapVisitor;
    private final ListContainerVisitor listVisitor;
    private final PrimitiveVisitor primitiveVisitor;
    private final Logger logger;

    public MasterTranslationVisitor() {
        TranslationCallback walker = (node, st) -> translate(node, st);
        listVisitor = new ListContainerVisitor(walker);
        mapVisitor = new MapContainerVisitor(walker);
        primitiveVisitor = new PrimitiveVisitor();
        logger = LoggerFactory.getLogger(MasterTranslationVisitor.class);
        logger.info("Loading semantic information.");
    }

    public MasterTranslationVisitor(MapContainerVisitor mapVisitor,
                                    ListContainerVisitor listVisitor,
                                    PrimitiveVisitor primitiveVisitor) {


        this.mapVisitor = mapVisitor;
        this.listVisitor = listVisitor;
        this.primitiveVisitor = primitiveVisitor;
        logger = LoggerFactory.getLogger(MasterTranslationVisitor.class);
        logger.info("Loading semantic information.");
    }

    public ObjectNode translate(ASTNode toTranslate, SymbolTable symbolTable) {
        if (symbolTable instanceof ListSymbolTable) {
            return listVisitor.translate((ASTContainerNode) toTranslate, (ListSymbolTable) symbolTable);
        } else if (symbolTable instanceof MapSymbolTable) {
            return mapVisitor.translate((ASTContainerNode) toTranslate, (MapSymbolTable) symbolTable);
        } else if (symbolTable instanceof PrimitiveSymbolTable) {
            return primitiveVisitor.translate((ASTPrimitiveNode) toTranslate, (PrimitiveSymbolTable) symbolTable);
        } else {
            throw new IllegalArgumentException("Unexpected symbol table class");
        }
    }
}
