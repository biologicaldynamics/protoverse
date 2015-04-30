/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package runtime.topology.lattice;

import runtime.topology.coordinate.Coordinate;

import java.util.stream.Stream;

/**
 * Created by dbborens on 3/10/15.
 */
public abstract class Lattice<C extends Coordinate> {
    public abstract Stream<C> getAnnulus(C coordinate, int r);

    /**
     * Remove any adjustments that must be made to a coordinate
     * in order to force its coordinates to be orthogonal in this
     * lattice topology. For example, in a triangular lattice,
     * the x coordinate must be adjusted to reflect the y
     * coordinate.
     *
     * @param d
     * @return
     */
    public abstract C invAdjust(C d);

    /**
     * Apply any adjustments that must be made to a coordinate
     * in order to force its coordinates to be orthogonal in this
     * lattice topology. For example, in a triangular lattice,
     * the x coordinate must be adjusted to reflect the y
     * coordinate.
     *
     * @param d
     * @return
     */
    public abstract C adjust(C d);
}
