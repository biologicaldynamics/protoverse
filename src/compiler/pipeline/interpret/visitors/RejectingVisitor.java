/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package compiler.pipeline.interpret.visitors;

import compiler.pipeline.interpret.nanosyntax.NanosyntaxBaseVisitor;
import compiler.pipeline.interpret.nanosyntax.NanosyntaxParser;
import compiler.pipeline.interpret.nodes.ASTNode;
import org.antlr.v4.runtime.misc.NotNull;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Rejects any visit that is not explicitly overridden. This prevents my custom
 * visitor from quietly using default behavior.
 *
 * Created by dbborens on 4/22/15.
 */
public class RejectingVisitor extends NanosyntaxBaseVisitor<ASTNode> {
    @Override
    public ASTNode visitRoot(@NotNull NanosyntaxParser.RootContext ctx) {
        throw new NotImplementedException();
    }

    @Override
    public ASTNode visitStatement(@NotNull NanosyntaxParser.StatementContext ctx) {
        throw new NotImplementedException();
    }

    @Override
    public ASTNode visitAssignment(@NotNull NanosyntaxParser.AssignmentContext ctx) {
        throw new NotImplementedException();
    }

    @Override
    public ASTNode visitBlock(@NotNull NanosyntaxParser.BlockContext ctx) {
        throw new NotImplementedException();
    }

    @Override
    public ASTNode visitSingleton(@NotNull NanosyntaxParser.SingletonContext ctx) {
        throw new NotImplementedException();
    }

    @Override
    public ASTNode visitId(@NotNull NanosyntaxParser.IdContext ctx) {
        throw new NotImplementedException();
    }

    @Override
    public ASTNode visitPrimitive(@NotNull NanosyntaxParser.PrimitiveContext ctx) {
        throw new NotImplementedException();
    }

    @Override
    public ASTNode visitStringPrimitive(@NotNull NanosyntaxParser.StringPrimitiveContext ctx) {
        throw new NotImplementedException();
    }

    @Override
    public ASTNode visitFloatPrimitive(@NotNull NanosyntaxParser.FloatPrimitiveContext ctx) {
        throw new NotImplementedException();
    }

    @Override
    public ASTNode visitIntPrimitive(@NotNull NanosyntaxParser.IntPrimitiveContext ctx) {
        throw new NotImplementedException();
    }
}
