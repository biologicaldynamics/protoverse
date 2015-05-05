/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package compiler.pipeline.interpret.nodes;

import com.google.common.base.Strings;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** * Created by dbborens on 4/21/15. */ public class ASTContainerNode implements ASTNode {
    private final String identifier;
    private final List<ASTNode> children;

    public ASTContainerNode(String identifier, Stream<ASTNode> children) {
        this.identifier = identifier;
        this.children = children.collect(Collectors.toList());
    }

    @Override
    public int size() {
        return children.size();
    }

    public Stream<ASTNode> getChildren() {
        return children.stream();
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ASTContainerNode blockNode = (ASTContainerNode) o;

        if (!identifier.equals(blockNode.identifier)) return false;
        if (!children.equals(blockNode.children)) return false;

        return true;
    }

    @Override
    public void astReport(StringBuilder builder, int indentLevel) {
        builder.append(Strings.repeat(" ", indentLevel));
        builder.append("container: " + identifier + "(" + size() + ")\n");
        if (children == null) {
            throw new IllegalStateException("Container " + identifier + " has null child list");
        }
        children.stream()
                .forEach(child -> {
                            if (child == null) {
                                throw new IllegalStateException("Container " + identifier + " has a null child");
                            }
                            child.astReport(builder, indentLevel + 1);
                        });
    }
}
