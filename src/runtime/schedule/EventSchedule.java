/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.schedule;

import runtime.agent.Agent;
import runtime.schedule.event.DeterministicEvent;

/**
 * Created by dbborens on 3/8/15.
 */
public class EventSchedule {

    private final EventBlockRunner runner;
    private final ScheduleContent schedulerContent;
    private double time;

    public EventSchedule() {
        schedulerContent = new ScheduleContent();
        runner = new EventBlockRunner(schedulerContent);
        time = 0.0;
    }

    public EventSchedule(EventBlockRunner runner, ScheduleContent schedulerContent) {
        this.runner = runner;
        this.schedulerContent = schedulerContent;
        time = 0.0;
    }
    /**
     * Report current simulation time.
     * @return
     */
    public double getTime() {
        return time;
    }

    /**
     * Advance to the next scheduled event.
     */
    public void advance() {
        EventBlock block = schedulerContent.next();

        if (block.getTime() < time) {
            throw new IllegalStateException("Current time later than scheduled events");
        }

        time = block.getTime();
        runner.run(block);
    }

    /**
     * Schedule an event and handle as appropriate.
     *
     * @param event
     */
    public void schedule(DeterministicEvent event) {
        schedulerContent.add(event);
    }

    /**
     * Unlink all events associated with a specific agent.
     *
     * @param agent
     */
    public void unlink(Agent agent) {
        schedulerContent.unlink(agent);
    }

    /**
     * Update the waiting time for events associated with
     * a specific agent.
     *
     * @param agent
     */
    public void update(Agent agent) {
        schedulerContent.update(agent);
    }
}
