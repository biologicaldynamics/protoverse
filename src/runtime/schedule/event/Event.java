/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package runtime.schedule.event;

import runtime.agent.Agent;
import runtime.control.Entity;

/**
 * Created by dbborens on 3/8/15.
 */
public abstract class Event implements Comparable<Event> {

    /**
     * Report the entity that owns this event.
     * @return
     */
    public abstract Entity getEntity();

    /**
     * Run the event represented by this object.
     *
     * @return whether this event can ever happen again.
     */
    public abstract boolean run();

    public abstract double getNextTime();

    @Override
    public int compareTo(Event o) {
        return Double.compare(this.getNextTime(), o.getNextTime());
    }
}
