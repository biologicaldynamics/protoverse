/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.schedule.event;

import runtime.agent.Agent;
import runtime.agent.actions.Action;
import runtime.agent.actions.time.FixedIntervalTimeRule;

/**
 * Created by dbborens on 3/8/15.
 */
public class DeterministicEvent implements Event, Comparable<DeterministicEvent> {

    private double time;
    private FixedIntervalTimeRule timeRule;
    private Agent agent;
    private Action action;
    private boolean ready;

    public DeterministicEvent(Agent agent, Action action, FixedIntervalTimeRule timeRule) {
        this.agent = agent;
        this.action = action;
        this.timeRule = timeRule;
        advance();
    }

    private void advance() {
        ready = timeRule.hasNext();
        if (ready) {
            time = timeRule.next();
        }
    }
    public Agent getAgent() {
        return agent;
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
    public int compareTo(DeterministicEvent o) {
        return Double.compare(this.getNextTime(), o.getNextTime());
    }
}
