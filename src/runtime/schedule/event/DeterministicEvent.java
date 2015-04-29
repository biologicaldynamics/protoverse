/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.schedule.event;

import runtime.agent.actions.Action;
import runtime.agent.actions.time.FixedEventTimer;
import runtime.agent.actions.time.PeriodicEventTimer;
import runtime.control.Entity;

/**
 * Created by dbborens on 3/8/15.
 */
public class DeterministicEvent extends Event {

    private double time;
    private FixedEventTimer timer;
    private Entity entity;
    private Action action;
    private boolean ready;

    public DeterministicEvent(Entity entity, Action action, FixedEventTimer timer) {
        this.entity = entity;
        this.action = action;
        this.timer = timer;
        advance();
    }

    private void advance() {
        ready = timer.hasNext();
        if (ready) {
            time = timer.next();
        }
    }

    @Override
    public Entity getEntity() {
        return entity;
    }

    @Override
    public boolean run() {
        if (!ready) {
            return false;
        }

        action.run();
        advance();

        return ready;
    }

    public double getNextTime() {
        return time;
    }

    @Override
    public String toString() {
        return action.getClass().getSimpleName() + " @ t=" + getNextTime();
    }
}
