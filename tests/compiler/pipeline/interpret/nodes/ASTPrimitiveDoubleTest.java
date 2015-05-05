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

public class ASTPrimitiveDoubleTest extends TestBase {

    private ASTPrimitiveDouble query;
    private Double value;

    @Before
    public void init() throws Exception {
        value = 7.3;
        query = new ASTPrimitiveDouble(value);
    }

    @Test
    public void getIdentifier() throws Exception {
        ASTPrimitiveDouble query = new ASTPrimitiveDouble(null);
        assertEquals(ASTPrimitiveDouble.IDENTIFIER, query.getIdentifier());
    }

    @Test
    public void getContent() throws Exception {
        assertEquals(value, query.getContent(), epsilon());
    }

    @Test
    public void equals() throws Exception {
        ASTPrimitiveDouble other = new ASTPrimitiveDouble(value);
        assertEquals(query, other);
    }

    @Test
    public void notEquals() throws Exception {
        ASTPrimitiveDouble other = new ASTPrimitiveDouble(value - 1.0);
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