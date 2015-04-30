/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package runtime;

import compiler.NanoverseCompiler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import runtime.control.Simulation;

import java.io.File;

/**
 * Created by dbborens on 4/22/15.
 */
public class Sandbox {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Sandbox.class);
        NanoverseCompiler compiler = new NanoverseCompiler();
        File file = new File("./samples/preamble.nano");
        logger.info("Starting Nanoverse with project file {}", file.getAbsolutePath());
        Simulation simulation = compiler.compile(file);

        logger.info("Running simulation.");
        simulation.run();
    }

}
