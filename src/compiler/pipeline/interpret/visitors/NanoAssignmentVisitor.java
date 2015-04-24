package compiler.pipeline.interpret.visitors;

import static compiler.pipeline.interpret.nanosyntax.NanosyntaxParser.*;

import compiler.pipeline.interpret.nanosyntax.NanosyntaxParser;
import compiler.pipeline.interpret.nodes.ASTContainerNode;
import compiler.pipeline.interpret.nodes.ASTNode;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

/**
 * Created by dbborens on 4/22/15.
 */
public class NanoAssignmentVisitor extends AbstractNanoNodeVisitor {

    private final Logger logger;
    private final NanoSingletonVisitor singletonVisitor;
    private final NanoBlockVisitor blockVisitor;

    public NanoAssignmentVisitor(NanoBlockVisitor blockVisitor) {
        logger = LoggerFactory.getLogger(NanoAssignmentVisitor.class);
        singletonVisitor = new NanoSingletonVisitor(this);
        this.blockVisitor = blockVisitor;
    }

    @Override
    public ASTNode visitAssignment(@NotNull AssignmentContext ctx) {
        logger.debug("Visiting assignment: {}", ctx.getText());
        return byCase(ctx);
    }

    private ASTNode byCase(AssignmentContext ctx) {
        String id = getIdentifier(ctx);
        int n = ctx.getChildCount();

        // myRef {...}
        if (n == 2) {
            return blockCase(ctx, id);

            // myRef: myValue
        } else if (n == 3) {
            return singletonCase(ctx, id);

        } else {
            throw new IllegalStateException("Unexpected node count in AssignmentNode");
        }
    }

    private ASTContainerNode blockCase(AssignmentContext ctx, String id) {
        logger.debug("Resolving assignment of block to {}", id);
        BlockContext child = (BlockContext) ctx.getChild(1);
        Stream<ASTNode> children = blockVisitor.getChildrenAsNodes(child);
        return new ASTContainerNode(id, children);
    }

    private ASTContainerNode singletonCase(AssignmentContext ctx, String id) {
        logger.debug("Resolving assignment of singleton to {}", id);
        ParseTree child = ctx.getChild(2);
        ASTNode value = child.accept(singletonVisitor);
        Stream<ASTNode> children = Stream.of(value);
        return new ASTContainerNode(id, children);
    }

    private String getIdentifier(AssignmentContext ctx) {
        ParseTree idTree = ctx.getChild(0).getChild(0);
        verifyPayload(idTree, CommonToken.class);
        String identifier = idTree.getText();
        return identifier;
    }

}
