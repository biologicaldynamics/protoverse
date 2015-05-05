/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package compiler.pipeline.interpret.nodes;

import org.junit.Before;
import org.junit.Test;
import test.TestBase;

import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ASTPrimitiveIntegerTest extends TestBase {

    private ASTPrimitiveInteger query;
    private Integer value;

    @Before
    public void init() throws Exception {
        value = 7;
        query = new ASTPrimitiveInteger(value);
    }

    @Test
    public void getIdentifier() throws Exception {
        assertEquals(ASTPrimitiveInteger.IDENTIFIER, query.getIdentifier());
    }

    @Test
    public void getContent() throws Exception {
        assertEquals(value, query.getContent());
    }

    @Test
    public void equals() throws Exception {
        ASTPrimitiveInteger other = new ASTPrimitiveInteger(value);
        assertEquals(query, other);
    }

    @Test
    public void notEquals() throws Exception {
        ASTPrimitiveInteger other = new ASTPrimitiveInteger(value - 1);
        assertNotEquals(query, other);

    }

    @Test
    public void size() throws Exception {
        assertEquals(1, query.size());
    }

    @Test
    public void getChildren() throws Exception {
        Stream<ASTNode> expected = Stream.of(query);
        Stream<ASTNode> actual = query.getChildren();
        assertStreamsEqual(expected, actual);
    }
}