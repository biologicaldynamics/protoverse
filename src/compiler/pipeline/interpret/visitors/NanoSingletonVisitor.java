/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package compiler.pipeline.interpret.visitors;

import static compiler.pipeline.interpret.nanosyntax.NanosyntaxParser.*;
import compiler.pipeline.interpret.nodes.ASTNode;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by dbborens on 4/22/15.
 */
public class NanoSingletonVisitor extends AbstractNanoNodeVisitor {

    private final Logger logger;
    private final NanoStandaloneIdVisitor idVisitor;
    private final NanoPrimitiveVisitor primitiveVisitor;
    private final NanoAssignmentVisitor assignmentVisitor;

    public NanoSingletonVisitor(NanoAssignmentVisitor assignmentVisitor) {
        this(new NanoStandaloneIdVisitor(),
             new NanoPrimitiveVisitor(), assignmentVisitor);
    }

    public NanoSingletonVisitor(NanoStandaloneIdVisitor idVisitor,
                                NanoPrimitiveVisitor primitiveVisitor,
                                NanoAssignmentVisitor assignmentVisitor) {

        logger = LoggerFactory.getLogger(NanoSingletonVisitor.class);
        this.idVisitor = idVisitor;
        this.primitiveVisitor = primitiveVisitor;
        this.assignmentVisitor = assignmentVisitor;
    }

    @Override
    public ASTNode visitSingleton(@NotNull SingletonContext ctx) {
        logger.debug("Visiting singleton: {}", ctx.getText());
        ParseTree child = ctx.getChild(0);
        if (child instanceof IdContext) {
            return child.accept(idVisitor);
        } else if (child instanceof AssignmentContext) {
            return child.accept(assignmentVisitor);
        } else if (child instanceof PrimitiveContext) {
            return child.accept(primitiveVisitor);
        } else {
            throw new IllegalStateException("Unexpected singleton child element " +
                    child.getClass().getSimpleName());
        }
    }
}
