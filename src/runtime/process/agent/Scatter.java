/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package runtime.process.agent;

import runtime.agent.AgentDescriptor;
import runtime.topology.coordinate.Coordinate;
import runtime.util.RandomChooser;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Scatter creates a population of agents, based on a specified
 * description, placed randomly within a specified region of space.
 *
 * Created by dbborens on 3/12/15.
 */
public class Scatter extends AgentProcess {

    private AgentDescriptor establisher;
    private Supplier<Stream<Coordinate>> siteSupplier;
    private RandomChooser<Coordinate> chooser;
    private Supplier<Integer> count;

    /**
     *
     * @param establisher AgentProducer for the type of agent
     *                 to be scattered.
     *
     * @param siteSupplier Provides a stream of coordinates
     *                     representing valid sites for this
     *                     scatter operation.
     *
     * @param chooser Random coordinate selector.
     *
     * @param count Supplier of number of agents to scatter.
     *              (Can be different each time.)
     */
    public Scatter(AgentDescriptor establisher,
                   Supplier<Stream<Coordinate>> siteSupplier,
                   RandomChooser<Coordinate> chooser,
                   Supplier<Integer> count) {

        this.establisher = establisher;
        this.siteSupplier = siteSupplier;
        this.chooser = chooser;
        this.count = count;
    }

    @Override
    public void run() {
        Stream<Coordinate> candidates = siteSupplier.get();
        Stream<Coordinate> targets = chooser.choose(candidates, count.get());
        targets.forEach(establisher::establish);
    }
}
