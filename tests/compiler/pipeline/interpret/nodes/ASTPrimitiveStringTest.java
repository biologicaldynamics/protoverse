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

public class ASTPrimitiveStringTest extends TestBase {

    private ASTPrimitiveString query;
    private String value;

    @Before
    public void init() throws Exception {
        value = "test";
        query = new ASTPrimitiveString(value);
    }

    @Test
    public void getIdentifier() throws Exception {
        assertEquals(ASTPrimitiveString.IDENTIFIER, query.getIdentifier());
    }

    @Test
    public void getContent() throws Exception {
        assertEquals(value, query.getContent());
    }

    @Test
    public void equals() throws Exception {
        ASTPrimitiveString other = new ASTPrimitiveString(value);
        assertEquals(query, other);
    }

    @Test
    public void notEquals() throws Exception {
        ASTPrimitiveString other = new ASTPrimitiveString("something else");
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