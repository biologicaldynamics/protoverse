/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package compiler.pipeline.interpret.nodes;

import com.google.common.base.Strings;

import java.util.stream.Stream;

/**
 * Created by dbborens on 3/5/15.
 */
public class ASTPrimitiveInteger extends ASTPrimitiveNode<Integer> {
    public static final String IDENTIFIER = "AST_PRIMITIVE_INTEGER";
    public ASTPrimitiveInteger(Integer content) {
        super(content);
    }

    @Override
    public void append(StringBuilder builder, int indentLevel) {
        builder.append(Strings.repeat(" ", indentLevel));
        builder.append("integer: " + getContent() + "\n");
    }

    @Override
    public String getIdentifier() {
        return IDENTIFIER;
    }
}
