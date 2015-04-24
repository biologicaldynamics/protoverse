/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.topology.lattice;

import runtime.topology.coordinate.Coordinate2D;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by dbborens on 3/10/15.
 */
public class RectangularLattice extends Lattice<Coordinate2D> {

    @Override
    public Stream<Coordinate2D> getAnnulus(Coordinate2D coordinate, int r) {
        // r=0 case (a point)
        if (r == 0) {
            return Stream.of(coordinate);
        }

        // All other cases
        int x0 = coordinate.x();
        int y0 = coordinate.y();

        return IntStream.range(0, r).mapToObj(i -> {
            int j = r - i;
            return Stream.of(
                new Coordinate2D(x0 + i, y0 + j),
                new Coordinate2D(x0 + j, y0 - i),
                new Coordinate2D(x0 - i, y0 - j),
                new Coordinate2D(x0 - j, y0 + i)
            );
        }).flatMap(subStream -> subStream.map(coord -> coord));
    }

    @Override
    public Coordinate2D invAdjust(Coordinate2D d) {
        return d;
    }

    @Override
    public Coordinate2D adjust(Coordinate2D d) {
        return d;
    }
}
