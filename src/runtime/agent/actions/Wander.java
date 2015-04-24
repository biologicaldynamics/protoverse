/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.agent.actions;

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
     *
     * @param candidateSupplier Supplies the neighborhood into which the agent
     *                          may wander. It is assumed that the neighborhood
     *                          is compatible with the mover.
     *
     * @param mover Moves the agent associated with this action to the specified
     *              coordinate. It is assumed that the mover is capable of
     *              moving the agent to any coordinate that might be supplied.
     */
    public Wander(RandomChooser<Coordinate> chooser,
                  Supplier<Stream<Coordinate>> candidateSupplier,
                  Consumer<Coordinate> mover) {

        this.chooser = chooser;
        this.candidateSupplier = candidateSupplier;
        this.mover = mover;
    }

    @Override
    public void run() {
        Stream<Coordinate> candidates = candidateSupplier.get();
        Coordinate target = chooser.apply(candidates);
        mover.accept(target);
    }
}
