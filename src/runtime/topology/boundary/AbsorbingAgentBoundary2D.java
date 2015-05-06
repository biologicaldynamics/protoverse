/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package runtime.topology.boundary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import runtime.agent.Agent;
import runtime.topology.coordinate.Coordinate2D;
import runtime.topology.shape.Shape;

import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Created by dbborens on 3/10/15.
 */
public class AbsorbingAgentBoundary2D extends AgentBoundary<Coordinate2D> {

    private final Logger logger;

    public AbsorbingAgentBoundary2D(Shape<Coordinate2D> shape) {
        super(shape);
        logger = LoggerFactory.getLogger(AbsorbingAgentBoundary2D.class);
        logger.debug("Constructing new AbsorbingAgentBoundary2D.");
    }


    @Override
    public Stream<Coordinate2D> getNeighbors(Coordinate2D c) {
        return shape.getRawNeighbors(c)
                .map(neighbor -> applyAbsorbing(neighbor));
    }

    private Coordinate2D applyAbsorbing(Coordinate2D c) {
        if (shape.isOverbounds(c)) {
            return c.asOverbounds();
        }

        return c;
    }

    @Override
    public Consumer<Agent> getOverboundsConsumer() {
        return agent -> agent.die();
    }

    @Override
    public Coordinate2D canonicalize(Coordinate2D input) {
        return input.asOverbounds();
    }

}
