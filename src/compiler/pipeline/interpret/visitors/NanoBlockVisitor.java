package compiler.pipeline.interpret.visitors;

import compiler.pipeline.interpret.nanosyntax.NanosyntaxParser;
import compiler.pipeline.interpret.nodes.ASTContainerNode;
import compiler.pipeline.interpret.nodes.ASTNode;
import org.antlr.v4.runtime.misc.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

/**
 * Created by dbborens on 4/22/15.
 */
public class NanoBlockVisitor extends AbstractNanoBlockVisitor {

    private final Logger logger;

    public NanoBlockVisitor(NanoStatementVisitor statementVisitor) {
        super(statementVisitor);
        logger = LoggerFactory.getLogger(NanoBlockVisitor.class);
    }

    public Stream<ASTNode> getChildrenAsNodes(@NotNull NanosyntaxParser.BlockContext ctx) {
        logger.debug("Visiting block with {} children", ctx.getChildCount());
        Stream<ASTNode> children = doVisit(ctx, 1, ctx.getChildCount()-1);
        return children;
    }
}
