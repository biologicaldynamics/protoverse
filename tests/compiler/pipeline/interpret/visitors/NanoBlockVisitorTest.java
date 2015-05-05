/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package compiler.pipeline.interpret.visitors;

import static compiler.pipeline.interpret.nanosyntax.NanosyntaxParser.BlockContext;

import compiler.pipeline.interpret.nodes.ASTNode;
import compiler.pipeline.interpret.visitors.helpers.NanoBlockHelper;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class NanoBlockVisitorTest {

    private NanoBlockHelper helper;
    private NanoBlockVisitor query;
    private BlockContext ctx;

    @Before
    public void init() throws Exception {
        helper = mock(NanoBlockHelper.class);
        ctx = mock(BlockContext.class);
        query = new NanoBlockVisitor(helper);
    }

    @Test
    public void getChildrenAsNodes() throws Exception {
        when(ctx.getChildCount()).thenReturn(3);
        Stream<ASTNode> expected = mock(Stream.class);
        when(helper.doVisit(ctx, 1, 2)).thenReturn(expected);
        Stream<ASTNode> actual = query.getChildrenAsNodes(ctx);
        assertSame(expected, actual);
    }
}