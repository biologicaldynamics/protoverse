/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package runtime.topology.coordinate;

/**
 * Created by dbborens on 3/5/15.
 */
public abstract class Coordinate<C extends Coordinate> {

    public abstract boolean isOverbounds();

    public abstract C asOverbounds();

    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract String toString();
}
