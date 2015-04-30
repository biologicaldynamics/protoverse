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
public class ASTPrimitiveString extends ASTPrimitiveNode<String> {
    public static final String IDENTIFIER = "AST_PRIMITIVE_STRING";

    public ASTPrimitiveString(String content) {
        super(content);
    }
    @Override
    public void append(StringBuilder builder, int indentLevel) {
        builder.append(Strings.repeat(" ", indentLevel));
        builder.append("string: " + getContent() + "\n");
    }

    @Override
    public String getIdentifier() {
        return IDENTIFIER;
    }

}
