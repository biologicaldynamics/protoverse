package compiler.pipeline.interpret.visitors;

import static compiler.pipeline.interpret.nanosyntax.NanosyntaxParser.*;
import compiler.pipeline.interpret.nodes.ASTNode;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by dbborens on 4/22/15.
 */
public class NanoStatementVisitor extends AbstractNanoNodeVisitor {
    private final Logger logger;
    private final NanoAssignmentVisitor assignmentVisitor;

    private final Class[] legalChildContexts = new Class[] {
            AssignmentContext.class,
    };

    public NanoStatementVisitor() {
        logger = LoggerFactory.getLogger(NanoStatementVisitor.class);
        NanoBlockVisitor blockVisitor = new NanoBlockVisitor(this);
        assignmentVisitor = new NanoAssignmentVisitor(blockVisitor);
    }

    public NanoStatementVisitor(NanoAssignmentVisitor assignmentVisitor) {
        logger = LoggerFactory.getLogger(NanoStatementVisitor.class);
        this.assignmentVisitor = assignmentVisitor;
    }

    @Override
    public ASTNode visitStatement(@NotNull StatementContext ctx) {
        logger.debug("Visiting statement: {}", ctx.getText());

        // Second child is a semicolon (checked by ANTLR -- ignored)
        ParseTree child = ctx.getChild(0);
        verifyPayload(child, legalChildContexts);

        return child.accept(assignmentVisitor);
    }
}
