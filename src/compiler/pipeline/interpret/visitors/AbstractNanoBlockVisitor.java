/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package compiler.pipeline.interpret.visitors;

import compiler.pipeline.interpret.nodes.ASTNode;
import compiler.pipeline.interpret.visitors.helpers.NanoBlockHelper;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.stream.Stream;

/**
 * Created by dbborens on 4/21/15.
 */
public abstract class AbstractNanoBlockVisitor extends AbstractNanoNodeVisitor {

    private final NanoBlockHelper helper;

    public AbstractNanoBlockVisitor(NanoStatementVisitor statementVisitor) {
        this(new NanoBlockHelper(statementVisitor));
    }

    public AbstractNanoBlockVisitor(NanoBlockHelper helper) {
        this.helper = helper;
    }

    protected Stream<ASTNode> doVisit(ParserRuleContext ctx, int start, int end) {
        return helper.doVisit(ctx, start, end);
    }

}
