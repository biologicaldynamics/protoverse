package runtime.topology.boundary;

import runtime.layer.agent.graph.AgentLayerGraphManager;
import runtime.layer.agent.graph.InfiniteAgentLayerGraphManager;
import runtime.topology.Topology;
import runtime.topology.coordinate.Coordinate;
import runtime.topology.shape.Shape;

/**
 * Created by dbborens on 5/6/15.
 */
public abstract class InfiniteAgentBoundary<C extends Coordinate> extends AgentBoundary<C>{

    public InfiniteAgentBoundary(Shape<C> shape) {
        super(shape);
    }

    @Override
    public AgentLayerGraphManager getGraphManager(Topology callback) {
        return new InfiniteAgentLayerGraphManager(callback);
    }
}
