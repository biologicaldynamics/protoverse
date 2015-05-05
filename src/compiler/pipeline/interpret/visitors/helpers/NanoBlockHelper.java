/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package compiler.pipeline.interpret.visitors.helpers;

import compiler.pipeline.interpret.nanosyntax.NanosyntaxParser.StatementContext;
import compiler.pipeline.interpret.nodes.ASTNode;
import compiler.pipeline.interpret.visitors.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.slf4j.Logger;

import java.util.stream.*;

/**
 * Created by dbborens on 4/30/15.
 */
public class NanoBlockHelper extends AbstractNanoNodeVisitor {

    private final NanoStatementVisitor statementVisitor;
    private final Logger logger;

    public NanoBlockHelper(NanoStatementVisitor statementVisitor) {
        this.statementVisitor = statementVisitor;
        logger = org.slf4j.LoggerFactory.getLogger(AbstractNanoBlockVisitor.class);
    }

    private Class[] validPayloadContexts = new Class[] {
            StatementContext.class,
    };

    public Stream<ASTNode> doVisit(ParserRuleContext ctx, int start, int end) {
        logger.debug("Visiting block: {}", ctx.getText());
        Stream<ASTNode> children = IntStream.range(start, end)
                .mapToObj(ctx::getChild)
                .map(this::verifyAndAccept);

        return children;
    }

    private ASTNode verifyAndAccept(ParseTree child) {
        verifyPayload(child, validPayloadContexts);
        ASTNode ret = child.accept(statementVisitor);
        return ret;
    }
}
