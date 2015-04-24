package runtime.util;

import compiler.NanoverseCompiler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Created by dbborens on 4/20/15.
 */
public class Sandbox {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Sandbox.class);
        NanoverseCompiler compiler = new NanoverseCompiler();
        File file = new File("./samples/preamble.nano");
        logger.info("Starting Nanoverse with project file {}", file.getAbsolutePath());
        compiler.compile(file);
    }
}
