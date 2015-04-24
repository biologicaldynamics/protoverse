/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.agent.actions.time;

import runtime.util.EpsilonEnabled;

/**
 * Created by dbborens on 3/9/15.
 */
public class FixedIntervalTimeRule extends EpsilonEnabled {

    private final double start;       // inclusive
    private final double interval;
    private final double end;         // exclusive
    private double current;


    // This is messed up. Think about your first, intermediate
    // and last cases, then rewrite.
    public FixedIntervalTimeRule(double start, double interval, double end) {
        if (start < 0 || interval < 0 || end < start) {
            throw new IllegalArgumentException("Malformed time rule");
        }

        this.start = start;
        this.interval = interval;
        this.end = end;
        current = -1.0;
    }

    public boolean hasNext() {
        if (epsilonEquals(current, -1.0)) {
            return start < end;
        }

        return current + interval < end;
    }

    public double next() {
        if (!hasNext()) {
            throw new IllegalStateException("Time rule advanced past last interval");
        }

        if (current == -1.0) {
            current = start;
        } else {
            current += interval;
        }

        return current;
    }
}
