/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package runtime.agent.actions;

import runtime.agent.Agent;
import runtime.schedule.EventSchedule;

import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by dbborens on 3/12/15.
 */
public class AgentEventScheduler implements Consumer<Agent> {

    private final Set<AgentEventDescriptor> eventSet;
    private final EventSchedule schedule;

    public AgentEventScheduler(Stream<AgentEventDescriptor> eventStream,
                               EventSchedule schedule) {

        eventSet = eventStream.collect(Collectors.toSet());
        this.schedule = schedule;
    }

    @Override
    public void accept(Agent agent) {
        // Build and schedule the agent's events
        eventSet.stream()
                .map(descriptor -> descriptor.apply(agent))
                .forEach(schedule::schedule);
    }

}
