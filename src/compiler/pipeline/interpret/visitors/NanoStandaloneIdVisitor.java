/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package compiler.pipeline.interpret.visitors;

import compiler.pipeline.interpret.nanosyntax.NanosyntaxParser;
import compiler.pipeline.interpret.nodes.ASTContainerNode;
import compiler.pipeline.interpret.nodes.ASTNode;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

/**
 * Visits an identifier that represents an object with
 * no defined properties. This is not always the case
 * with identifier contexts; they are also used to
 * represent the key in an assignment context, in which
 * case they are turned into strings.
 *
 * Created by dbborens on 4/22/15.
 */
public class NanoStandaloneIdVisitor extends AbstractNanoNodeVisitor {

    private final Logger logger;

    public NanoStandaloneIdVisitor() {
        logger = LoggerFactory.getLogger(NanoStandaloneIdVisitor.class);
    }

    @Override
    public ASTNode visitId(@NotNull NanosyntaxParser.IdContext ctx) {
        logger.debug("Visiting stand-alone ID: {}", ctx.getText());
        ParseTree idTree = ctx.getChild(0);
        verifyPayload(idTree, CommonToken.class);
        String identifier = idTree.getText();
        return new ASTContainerNode(identifier, Stream.empty());
    }
}
