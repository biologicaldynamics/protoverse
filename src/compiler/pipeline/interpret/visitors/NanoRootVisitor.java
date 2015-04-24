package compiler.pipeline.interpret.visitors;

import compiler.pipeline.interpret.nanosyntax.NanosyntaxBaseVisitor;
import compiler.pipeline.interpret.nanosyntax.NanosyntaxParser;
import compiler.pipeline.interpret.nodes.ASTContainerNode;
import compiler.pipeline.interpret.nodes.ASTNode;
import org.antlr.v4.runtime.misc.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

/**
 * Created by dbborens on 4/21/15.
 */
public class NanoRootVisitor extends AbstractNanoBlockVisitor {

    private final Logger logger;

    public NanoRootVisitor() {
        this(new NanoStatementVisitor());
    }

    public NanoRootVisitor(NanoStatementVisitor statementVisitor) {
        super(statementVisitor);
        logger = LoggerFactory.getLogger(NanoRootVisitor.class);
    }

    @Override
    public ASTNode visitRoot(@NotNull NanosyntaxParser.RootContext ctx) {
        logger.debug("Visiting root with {} children", ctx.getChildCount());
        Stream<ASTNode> children = doVisit(ctx, 0, ctx.getChildCount());
        ASTContainerNode ret = new ASTContainerNode("root", children);
        return ret;
    }
}
