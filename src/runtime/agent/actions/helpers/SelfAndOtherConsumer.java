/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package runtime.agent.actions.helpers;

import runtime.topology.coordinate.Coordinate;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Given a locator for a particular agent, and a coordinate
 * bi-consumer (coordinate content swap, etc), resolve the location of this
 * agent and consume both coordinates.
 *
 * Created by dbborens on 3/6/15.
 */
public class SelfAndOtherConsumer implements Consumer<Coordinate> {

    private final Supplier<Coordinate> locator;
    private final BiConsumer<Coordinate, Coordinate> consumer;

    public SelfAndOtherConsumer(Supplier<Coordinate> locator,
                                BiConsumer<Coordinate, Coordinate> consumer) {

        this.locator = locator;
        this.consumer = consumer;
    }

    @Override
    public void accept(Coordinate q) {
        Coordinate p = locator.get();
        consumer.accept(p, q);
    }
}
