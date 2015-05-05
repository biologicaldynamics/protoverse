/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package compiler.pipeline.interpret;

import compiler.pipeline.interpret.nodes.ASTNode;
import compiler.util.IllegalAssignmentError;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class InterpreterTest {

    private AntlrBinding antlr;
    private Interpreter query;
    private File file;

    @Before
    public void init() throws Exception {
        antlr = mock(AntlrBinding.class);
        file = mock(File.class);
        when(file.exists()).thenReturn(true);
        query = new Interpreter(antlr);
    }

    @Test
    public void interpretCallsBinding() throws Exception {
        ASTNode expected = mock(ASTNode.class);
        when(antlr.interpret(file)).thenReturn(expected);

        ASTNode actual = query.interpret(file);
        assertSame(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullFileThrows() throws Exception {
        file = null;
        query.interpret(file);
    }

    @Test(expected = IllegalArgumentException.class)
    public void absentFileThrows() throws Exception {
        when(file.exists()).thenReturn(false);
        query.interpret(file);
    }
}