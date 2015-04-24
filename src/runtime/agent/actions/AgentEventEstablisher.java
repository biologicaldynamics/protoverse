/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.agent.actions;

import runtime.agent.Agent;
import runtime.schedule.event.DeterministicEvent;

import java.util.Set;
import java.util.function.Consumer;

/**
 * Created by dbborens on 3/12/15.
 */
public class AgentEventEstablisher implements Consumer<Agent> {

    private Set<AgentEventProducer> eventSet;
    private Consumer<DeterministicEvent> eventScheduler;

    public AgentEventEstablisher(Set<AgentEventProducer> eventSet,
                                 Consumer<DeterministicEvent> eventScheduler) {

        this.eventSet = eventSet;
        this.eventScheduler = eventScheduler;
    }

    @Override
    public void accept(Agent agent) {
        // Build and schedule the agent's events
        eventSet.stream()
                .map(producer -> producer.apply(agent))
                .forEach(eventScheduler::accept);
    }

}
