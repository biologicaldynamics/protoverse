/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package compiler;

import compiler.pipeline.interpret.Interpreter;
import compiler.pipeline.interpret.nodes.ASTNode;
import compiler.pipeline.translate.nodes.MapObjectNode;
import compiler.pipeline.translate.visitors.MasterTranslationVisitor;
import compiler.symbol.tables.runtime.control.SimulationSymbolTable;
import org.junit.Before;
import org.junit.Test;
import runtime.control.Simulation;

import java.io.File;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class NanoverseCompilerTest {

    private Interpreter interpreter;
    private MasterTranslationVisitor visitor;
    private File file;
    private ASTNode ast;
    private MapObjectNode obj;
    private SimulationSymbolTable rootST;
    private Simulation simulation;

    private NanoverseCompiler query;

    @Before
    public void init() {
        interpreter = mock(Interpreter.class);
        visitor = mock(MasterTranslationVisitor.class);
        file = mock(File.class);
        ast = mock(ASTNode.class);
        obj = mock(MapObjectNode.class);
        rootST = mock(SimulationSymbolTable.class);
        simulation = mock(Simulation.class);

        query = new NanoverseCompiler(interpreter, visitor);
        when(interpreter.interpret(file)).thenReturn(ast);
        when(visitor.translate(eq(ast), anyObject())).thenReturn(obj);
        when(obj.getSymbolTable()).thenReturn(rootST);
        when(rootST.instantiate(obj)).thenReturn(simulation);
    }

    @Test
    public void compile() throws Exception {
        Simulation actual = query.compile(file);
        assertSame(simulation, actual);
    }
}