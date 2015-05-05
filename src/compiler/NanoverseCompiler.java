/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package compiler;

import compiler.pipeline.interpret.Interpreter;
import compiler.pipeline.interpret.nodes.ASTNode;
import compiler.pipeline.translate.nodes.MapObjectNode;
import compiler.pipeline.translate.visitors.MasterTranslationVisitor;
import compiler.symbol.tables.SymbolTable;
import compiler.symbol.tables.runtime.control.SimulationSymbolTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import runtime.control.Simulation;

import java.io.File;

/**
 * Created by dbborens on 2/16/15.
 */
public class NanoverseCompiler {

    private final Logger logger;
    private final Interpreter interpreter;
    private final MasterTranslationVisitor visitor;

    public NanoverseCompiler() {
        this(new Interpreter(), new MasterTranslationVisitor());
    }

    public NanoverseCompiler(Interpreter interpreter, MasterTranslationVisitor visitor) {
        this.interpreter = interpreter;
        this.visitor = visitor;
        logger = LoggerFactory.getLogger(NanoverseCompiler.class);
    }

    public Simulation compile(File source) {
        ASTNode astRoot = interpreter.interpret(source);

        StringBuilder sb = new StringBuilder();
        astRoot.astReport(sb, 0);
        logger.debug("Abstract syntax tree is reported below\n" + sb.toString());
        SymbolTable rootMST = new SimulationSymbolTable();
        MapObjectNode objRoot = (MapObjectNode) visitor.translate(astRoot, rootMST);

        SimulationSymbolTable rootST = (SimulationSymbolTable) objRoot.getSymbolTable();
        logger.info("Instantiating simulation.");
        Simulation simulation = rootST.instantiate(objRoot);
        return simulation;
    }
}
