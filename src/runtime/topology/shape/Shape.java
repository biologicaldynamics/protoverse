/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.topology.shape;

import runtime.topology.coordinate.Coordinate;
import runtime.topology.lattice.Lattice;

import java.util.stream.Stream;

/**
 * Created by dbborens on 3/10/15.
 */
public abstract class Shape<C extends Coordinate> {
    public abstract Stream<C> getCanonicalSites();

    protected final Lattice<C> lattice;
   
    public Shape(Lattice<C> lattice) {
        this.lattice = lattice;
    }

    public abstract C getOverbounds(C c);

    /**
     * Reports whether the coordinate is out of bounds. Should ideally
     * be faster than getOverbounds(...) because it can be implemented
     * as a lookup to a pre-calculated list of canonical sites.
     *
     * @param c
     * @return
     */
    public abstract boolean isOverbounds(C c);
    /**
     * Reports the neighbors of the specified coordinate, taking into
     * consideration only the coordinate system (ie, neighbors may be
     * out of bounds)
     *
     * @return
     */
    public Stream<C> getRawNeighbors(C coordinate) {
        return lattice.getAnnulus(coordinate, 1);
    }
}
