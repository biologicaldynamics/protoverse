/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.topology.boundary;

import runtime.agent.Agent;
import runtime.topology.coordinate.Coordinate;
import runtime.topology.shape.Shape;

import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Created by dbborens on 3/10/15.
 */
public abstract class AgentBoundary<C extends Coordinate> {

    protected final Shape<C> shape;

    public AgentBoundary(Shape<C> shape) {
        this.shape = shape;
    }

    /**
     * Reports the canonical coordinates (not wrapped, reflected, etc) that
     * exist in this topology.
     *
     * @return
     */
    public Stream<C> getCanonicalSites() {
        return shape.getCanonicalSites();
    }

    /**
     * Reports the neighbors of the specifies coordinate, given the rules of
     * the boundary condition and the underlying topology.
     *
     * @param c
     * @return
     */
    public abstract Stream<C> getNeighbors(C c);


    /**
     * Provides a consumer that performs the behavior that this boundary
     * condition expects when an agent goes over (halt the simulation,
     * delete the agent, throw an error, etc.)
     *
     */
    public abstract Consumer<Agent> getOverboundsConsumer();
}
