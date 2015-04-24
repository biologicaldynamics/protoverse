/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.topology.coordinate;

/**
 * Created by dbborens on 3/10/15.
 */
public class Coordinate2D extends Coordinate<Coordinate2D> {

    private final int x;
    private final int y;

    private final boolean overbounds;

    public Coordinate2D(int x, int y, boolean overbounds) {
        this.x = x;
        this.y = y;
        this.overbounds = overbounds;
    }

    public Coordinate2D(int x, int y) {
        this(x, y, false);
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    @Override
    public boolean isOverbounds() {
        return overbounds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinate2D that = (Coordinate2D) o;

        if (overbounds != that.overbounds) return false;
        if (x != that.x) return false;
        if (y != that.y) return false;

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        if (overbounds) {
            sb.append("!");
        }
        sb.append(x);
        sb.append(", ");
        sb.append(y);
        sb.append(")");
        return sb.toString();
    }

    @Override
    public Coordinate2D asOverbounds() {
        return new Coordinate2D(x, y, true);
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + (overbounds ? 1 : 0);
        return result;
    }
}
