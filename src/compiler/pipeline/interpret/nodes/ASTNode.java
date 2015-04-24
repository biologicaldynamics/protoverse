package compiler.pipeline.interpret.nodes;

import java.util.stream.Stream;

/**
 * Created by dbborens on 4/21/15.
 */
public interface ASTNode {

    public void append(StringBuilder builder, int indentLevel);
    public String getIdentifier();
    public int size();
    public Stream<ASTNode> getChildren();
}
