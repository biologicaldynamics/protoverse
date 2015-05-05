/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package compiler.pipeline.interpret.nodes;

import org.junit.Before;
import org.junit.Test;
import test.TestBase;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ASTContainerNodeTest extends TestBase {

    private ASTNode child1, child2;
    private ASTContainerNode query;

    @Before
    public void init() throws Exception {
        child1 = mock(ASTNode.class);
        child2 = mock(ASTNode.class);
        Stream<ASTNode> stream = Stream.of(child1, child2);
        query = new ASTContainerNode("id", stream);
    }

    @Test
    public void size() throws Exception {
        assertEquals(2, query.size());
    }

    @Test
    public void getChildren() throws Exception {
        Stream<ASTNode> expected = Stream.of(child1, child2);
        Stream<ASTNode> actual = query.getChildren();
        assertStreamsEqual(expected, actual);
    }

    @Test
    public void getIdentifier() throws Exception {
        assertEquals("id", query.getIdentifier());
    }

    @Test
    public void equals() throws Exception {
        Stream<ASTNode> stream = Stream.of(child1, child2);
        ASTContainerNode other = new ASTContainerNode("id", stream);
        assertEquals(query, other);
    }

    @Test
    public void notEqDiffId() throws Exception {
        Stream<ASTNode> stream = Stream.of(child1, child2);
        ASTContainerNode other = new ASTContainerNode("something else", stream);
        assertNotEquals(query, other);
    }

    @Test
    public void notEqDiffChildren() throws Exception {
        Stream<ASTNode> stream = Stream.empty();
        ASTContainerNode other = new ASTContainerNode("id", stream);
        assertNotEquals(query, other);
    }
}