/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler;

import compiler.pipeline.interpret.Interpreter;
import compiler.pipeline.interpret.nodes.ASTNode;
import compiler.pipeline.translate.nodes.ObjectNode;
import compiler.pipeline.translate.visitors.MasterTranslationVisitor;
import compiler.symbol.tables.SymbolTable;
import compiler.symbol.tables.runtime.control.SimulationSymbolTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Created by dbborens on 2/16/15.
 */
public class NanoverseCompiler {

    private final Logger logger;

    public NanoverseCompiler() {
        logger = LoggerFactory.getLogger(NanoverseCompiler.class);
    }
    // TODO the compiler really should return a runner object.
    public void compile(File source) {
        Interpreter interpreter = new Interpreter();
        ASTNode astRoot = interpreter.interpret(source);

        StringBuilder sb = new StringBuilder();
        astRoot.append(sb, 0);
        logger.debug("Abstract syntax tree is reported below\n" + sb.toString());
        MasterTranslationVisitor visitor = new MasterTranslationVisitor();
        SymbolTable rootMST = new SimulationSymbolTable();
        ObjectNode objRoot = visitor.translate(astRoot, rootMST);
    }
}
