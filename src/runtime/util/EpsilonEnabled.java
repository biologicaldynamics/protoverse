/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.util;

/**
 * Created by dbborens on 3/8/15.
 */
public abstract class EpsilonEnabled {

    protected static final double epsilon = calcEpsilon();

    protected boolean epsilonEquals(double p, double q) {
        double delta = p - q;
        double magnitude = Math.abs(delta);
        return (magnitude < epsilon);
    }

    private static double calcEpsilon() {
        double eps = 1.0D;

        while ((1.0 + (eps / 2.0)) != 1.0) {
            eps /= 2.0;
        }

        return eps;
    }
}
