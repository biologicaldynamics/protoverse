/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.topology;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import runtime.topology.boundary.AgentBoundary;
import runtime.topology.coordinate.Coordinate;

import java.util.stream.Stream;

/**
 * Created by dbborens on 3/5/15.
 */
public class Topology<C extends Coordinate> {

    private final AgentBoundary boundary;
    private final Logger logger;

    public Topology(AgentBoundary boundary) {
        this.boundary = boundary;
        logger = LoggerFactory.getLogger(Topology.class);
        logger.debug("Constructing new Topology with boundary {}.",
                boundary.getClass().getSimpleName() );
    }

    public Stream<C> getCanonicalSites() {
        return boundary.getCanonicalSites();
    }

    public Stream<C> getNeighbors(C c) {
        return boundary.getNeighbors(c);
    }
}
