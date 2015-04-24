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
public class NanoPrimitiveVisitor extends AbstractNanoNodeVisitor {
    private final Logger logger;
    private final NanoPrimitiveDoubleVisitor doubleVisitor;
    private final NanoPrimitiveIntVisitor intVisitor;
    private final NanoPrimitiveStringVisitor stringVisitor;

    public NanoPrimitiveVisitor() {
        this(new NanoPrimitiveDoubleVisitor(),
             new NanoPrimitiveIntVisitor(),
             new NanoPrimitiveStringVisitor());
    }

    public NanoPrimitiveVisitor(NanoPrimitiveDoubleVisitor doubleVisitor,
                                NanoPrimitiveIntVisitor intVisitor,
                                NanoPrimitiveStringVisitor stringVisitor) {

        logger = LoggerFactory.getLogger(NanoPrimitiveVisitor.class);
        this.doubleVisitor = doubleVisitor;
        this.intVisitor = intVisitor;
        this.stringVisitor = stringVisitor;
    }

    @Override
    public ASTNode visitPrimitive(@NotNull PrimitiveContext ctx) {
        logger.debug("Visiting primitive: {}", ctx.getText());
        ParseTree child = ctx.getChild(0);
        if (child instanceof IntPrimitiveContext) {
            return child.accept(intVisitor);
        } else if (child instanceof StringPrimitiveContext) {
            return child.accept(stringVisitor);
        } else if (child instanceof FloatPrimitiveContext) {
            return child.accept(doubleVisitor);
        } else {
            throw new IllegalStateException("Unexpected narrow primitive " +
                    "class " + child.getClass().getSimpleName());
        }
    }
}
