/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package compiler.pipeline.interpret;

import compiler.pipeline.interpret.nodes.ASTNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Created by dbborens on 2/13/15.
 */
public class Interpreter {

    private final AntlrBinding antlr;
    private final Logger logger;

    public Interpreter() {
        this(new AntlrBinding());
    }

    public Interpreter(AntlrBinding antlr) {
        logger = LoggerFactory.getLogger(Interpreter.class);
        this.antlr = antlr;
    }

    public ASTNode interpret(File file) {
        verify(file);
        logger.info("Interpreting {}", file.getAbsoluteFile());
        return antlr.interpret(file);
    }

    private void verify(File file) {
        if (file == null) {
            throw new IllegalArgumentException("No project file specified.");
        }

        if (!file.exists()) {
            throw new IllegalArgumentException("Project file not found: " + file.getAbsolutePath());
        }
    }
}
