package compiler.pipeline.interpret.visitors;

import static compiler.pipeline.interpret.nanosyntax.NanosyntaxParser.*;

import compiler.pipeline.interpret.nanosyntax.NanosyntaxBaseVisitor;
import compiler.pipeline.interpret.nodes.ASTNode;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.log4j.lf5.viewer.LogFactor5Dialog;
import org.apache.log4j.spi.LoggerFactory;
import org.slf4j.Logger;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by dbborens on 4/21/15.
 */
public abstract class AbstractNanoBlockVisitor extends AbstractNanoNodeVisitor {

    private final NanoStatementVisitor statementVisitor;
    private final Logger logger;

    public AbstractNanoBlockVisitor(NanoStatementVisitor statementVisitor) {
        this.statementVisitor = statementVisitor;
        logger = org.slf4j.LoggerFactory.getLogger(AbstractNanoBlockVisitor.class);
    }

    private Class[] validPayloadContexts = new Class[] {
            StatementContext.class,
    };

    protected Stream<ASTNode> doVisit(ParserRuleContext ctx, int start, int end) {
        logger.debug("Visiting block: {}", ctx.getText());
        Stream<ASTNode> children = IntStream.range(start, end)
                .mapToObj(ctx::getChild)
                .map(this::verifyAndAccept);

        return children;
    }

    private ASTNode verifyAndAccept(ParseTree child) {
        verifyPayload(child, validPayloadContexts);
        ASTNode ret = child.accept(statementVisitor);
        return ret;
    }
}
