/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.schedule.event;

import runtime.agent.Agent;

/**
 * Created by dbborens on 3/8/15.
 */
public interface Event {

    /**
     * Report the agent associated with this object.
     * @return
     */
    public Agent getAgent();

    /**
     * Run the event represented by this object.
     *
     * @return whether this event can ever happen again.
     */
    public boolean run();
}
