/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package compiler.pipeline.interpret.visitors;

import compiler.pipeline.interpret.nanosyntax.NanosyntaxParser;
import compiler.pipeline.interpret.nanosyntax.NanosyntaxParser.*;
import compiler.pipeline.interpret.nodes.ASTContainerNode;
import compiler.pipeline.interpret.nodes.ASTNode;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class NanoAssignmentVisitorTest {

    private final String identifier = "id";

    private NanoSingletonVisitor singletonVisitor;
    private NanoBlockVisitor blockVisitor;
    private ParseTree child0, child2, grandchild;
    private BlockContext child1;
    private AssignmentContext ctx;
    private CommonToken idPayload;
    private NanoAssignmentVisitor query;

    @Before
    public void init() throws Exception {
        singletonVisitor = mock(NanoSingletonVisitor.class);
        blockVisitor = mock(NanoBlockVisitor.class);

        ctx = mock(AssignmentContext.class);
        grandchild = mock(ParseTree.class);
        child0 = mock(ParseTree.class);
        child1 = mock(BlockContext.class);
        child2 = mock(ParseTree.class);
        when(ctx.getChild(0)).thenReturn(child0);
        when(ctx.getChild(1)).thenReturn(child1);
        when(ctx.getChild(2)).thenReturn(child2);

        idPayload = mock(CommonToken.class);
        when(child0.getChild(0)).thenReturn(grandchild);
        when(grandchild.getPayload()).thenReturn(idPayload);
        when(grandchild.getText()).thenReturn(identifier);

        query = new NanoAssignmentVisitor(singletonVisitor, blockVisitor);
    }

    @Test
    public void singletonCase() throws Exception {
        when(ctx.getChildCount()).thenReturn(3);
        ASTNode value = mock(ASTNode.class);
        when(child2.accept(singletonVisitor)).thenReturn(value);

        ASTNode expected = new ASTContainerNode(identifier,
                Stream.of(value));
        ASTNode actual = query.visitAssignment(ctx);
        assertEquals(expected, actual);
    }

    @Test
    public void blockCase() throws Exception {
        when(ctx.getChildCount()).thenReturn(2);
        ASTNode dummy = mock(ASTNode.class);
        when(blockVisitor.getChildrenAsNodes(child1))
                .thenReturn(Stream.of(dummy));
        ASTNode expected = new ASTContainerNode(identifier,
                Stream.of(dummy));
        ASTNode actual = query.visitAssignment(ctx);
        assertEquals(expected, actual);
    }
}