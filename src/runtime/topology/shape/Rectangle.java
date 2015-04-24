/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.topology.shape;

import runtime.topology.coordinate.Coordinate2D;
import runtime.topology.lattice.Lattice;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by dbborens on 3/10/15.
 */
public class Rectangle extends Shape<Coordinate2D> {

    private final int height, width;
    private final Set<Coordinate2D> canonicalSites;

    public Rectangle(Lattice<Coordinate2D> lattice, int width, int height) {
        super(lattice);
        this.height = height;
        this.width = width;
        canonicalSites = calcCanonicalSites();
    }

    private Set<Coordinate2D> calcCanonicalSites() {
        // Is this *really* better than a nested for loop?
        return IntStream.range(0, width)
                .mapToObj(x -> IntStream.range(0, height)
                        .mapToObj(y -> new Coordinate2D(x, y)))
                .flatMap(subStream -> subStream.map(c -> c))
                .collect(Collectors.toSet());
    }

    @Override
    public Stream<Coordinate2D> getCanonicalSites() {
        return canonicalSites.stream();
    }

    @Override
    public Coordinate2D getOverbounds(Coordinate2D c) {
        Coordinate2D d = lattice.invAdjust(c);
        int dx = overbounds(d.x(), width);
        int dy = overbounds(d.y(), height);

        boolean overbounds = dx != 0 || dy != 0;

        return new Coordinate2D(dx, dy, overbounds);
    }

    private int overbounds(int c, int extent) {
        if (c < 0) {
            return c;
        } else if (c >= extent) {
            return c - extent + 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean isOverbounds(Coordinate2D coordinate) {
        return !canonicalSites.contains(coordinate);
    }

}
