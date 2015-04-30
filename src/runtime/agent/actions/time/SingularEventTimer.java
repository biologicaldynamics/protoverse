/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package runtime.agent.actions.time;

/**
 * Created by dbborens on 4/27/15.
 */
public class SingularEventTimer implements FixedEventTimer {

    private final double time;
    boolean happened;

    public SingularEventTimer(double time) {
        this.time = time;
        happened = false;
    }

    @Override
    public boolean hasNext() {
        return !happened;
    }

    @Override
    public double next() {
        if (happened) {
            throw new IllegalStateException("Attempting to fire singular event twice.");
        }
        happened = true;
        return time;
    }
}
