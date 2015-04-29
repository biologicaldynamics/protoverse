/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.agent.actions;

import runtime.agent.Agent;
import runtime.layer.agent.AgentLayer;
import runtime.topology.coordinate.Coordinate;
import runtime.util.RandomChooser;

import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Created by dbborens on 3/5/15.
 */
public class Wander implements Action {

    private final RandomChooser<Coordinate> chooser;
    private final Supplier<Stream<Coordinate>> candidateSupplier;
    private final Consumer<Coordinate> mover;

    /**
     * @param chooser A function that chooses a random coordinate from a stream
     *                of coordinates.
     */
    public Wander(RandomChooser<Coordinate> chooser,
                  Agent agent,
                  AgentLayer layer) {

        candidateSupplier = () -> {
            Coordinate location = layer.locate(agent);
            Stream<Coordinate> stream = layer
                    .getTopology()
                    .getNeighbors(location)
                    .filter(coord -> layer.isVacant((Coordinate) coord));
            return stream;
        };

        mover = destination -> {
            Coordinate location = layer.locate(agent);
            layer.swap(location, destination);
        };

        this.chooser = chooser;
    }

    @Override
    public void run() {
        Stream<Coordinate> candidates = candidateSupplier.get();
        Coordinate target = chooser.apply(candidates);
        mover.accept(target);
    }
}
