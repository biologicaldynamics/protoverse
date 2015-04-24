/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.interpret.nodes;

import java.util.stream.Stream;

/**
 * Created by dbborens on 2/14/15.
 */
public abstract class ASTPrimitiveNode<T> implements ASTNode {

    private final T content;

    public ASTPrimitiveNode(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ASTPrimitiveNode that = (ASTPrimitiveNode) o;

        if (!content.equals(that.content)) return false;

        return true;
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public Stream<ASTNode> getChildren() {
        return Stream.of(this);
    }
}
