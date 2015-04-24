/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.agent.actions.coordinate;

import runtime.topology.coordinate.Coordinate;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Provides the current neighborhood around a particular agent. Since the
 * output depends on a supplier provided at construction, the method is
 * agnostic to the radius of the neighborhood (or even how "neighborhood" is
 * defined.)
 *
 * Created by dbborens on 3/6/15.
 */
public class NeighborhoodSupplier implements Supplier<Stream<Coordinate>> {

    private final Function<Coordinate, Stream<Coordinate>> coordToNeighborhood;
    private final Supplier<Coordinate> locator;

    public NeighborhoodSupplier(Function<Coordinate, Stream<Coordinate>> coordToNeighborhood,
                                Supplier<Coordinate> locator) {

        this.coordToNeighborhood = coordToNeighborhood;
        this.locator = locator;
    }

    @Override
    public Stream<Coordinate> get() {
        Coordinate myLocation = locator.get();
        Stream<Coordinate> neighborhood = coordToNeighborhood.apply(myLocation);
        return neighborhood;
    }
}
