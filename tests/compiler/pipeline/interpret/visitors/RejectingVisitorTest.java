/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package compiler.pipeline.interpret.visitors;

import static compiler.pipeline.interpret.nanosyntax.NanosyntaxParser.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.cglib.core.Block;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RejectingVisitorTest {

    private RejectingVisitor query;

    @Before
    public void init() throws Exception {
        query = new RejectingVisitor();
    }

    @Test(expected = NotImplementedException.class)
    public void visitRoot() throws Exception {
        RootContext ctx = mock(RootContext.class);
        query.visitRoot(ctx);
    }

    @Test(expected = NotImplementedException.class)
    public void visitStatement() throws Exception {
        StatementContext ctx = mock(StatementContext.class);
        query.visitStatement(ctx);
    }

    @Test(expected = NotImplementedException.class)
    public void visitAssignment() throws Exception {
        AssignmentContext ctx = mock(AssignmentContext.class);
        query.visitAssignment(ctx);
    }

    @Test(expected = NotImplementedException.class)
    public void visitBlock() throws Exception {
        BlockContext ctx = mock(BlockContext.class);
        query.visitBlock(ctx);
    }

    @Test(expected = NotImplementedException.class)
    public void visitSingleton() throws Exception {
        SingletonContext ctx = mock(SingletonContext.class);
        query.visitSingleton(ctx);
    }

    @Test(expected = NotImplementedException.class)
    public void visitId() throws Exception {
        IdContext ctx = mock(IdContext.class);
        query.visitId(ctx);
    }

    @Test(expected = NotImplementedException.class)
    public void visitPrimitive() throws Exception {
        PrimitiveContext ctx = mock(PrimitiveContext.class);
        query.visitPrimitive(ctx);
    }

    @Test(expected = NotImplementedException.class)
    public void visitStringPrimitive() throws Exception {
        StringPrimitiveContext ctx = mock(StringPrimitiveContext.class);
        query.visitStringPrimitive(ctx);
    }

    @Test(expected = NotImplementedException.class)
    public void visitFloatPrimitive() throws Exception {
        FloatPrimitiveContext ctx = mock(FloatPrimitiveContext.class);
        query.visitFloatPrimitive(ctx);
    }

    @Test(expected = NotImplementedException.class)
    public void visitIntPrimitive() throws Exception {
        IntPrimitiveContext ctx = mock(IntPrimitiveContext.class);
        query.visitIntPrimitive(ctx);
    }
}