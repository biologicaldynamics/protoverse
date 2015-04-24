/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.schedule;

import runtime.agent.Agent;
import runtime.schedule.event.DeterministicEvent;
import runtime.util.IdentityMultimap;

import java.util.stream.Stream;

/**
 *
 * Created by dbborens on 3/8/15.
 */
public class ScheduleContent {

    private final IdentityMultimap<Agent, DeterministicEvent> multimap;
    private final EventQueue queue;

    public ScheduleContent() {
        this(new IdentityMultimap<>(), new EventQueue());
    }

    public ScheduleContent(IdentityMultimap<Agent, DeterministicEvent> multimap,
                           EventQueue queue) {
        this.multimap = multimap;
        this.queue = queue;
    }

    public void add(DeterministicEvent event) {
        Agent agent = event.getAgent();
        if (multimap.has(agent, event)) {
            throw new IllegalArgumentException("Attempting double scheduling of agent-event pair.");
        }
        queue.add(event);

        multimap.put(agent, event);
    }

    public void remove(DeterministicEvent event) {
        Agent agent = event.getAgent();

        if (!multimap.has(agent, event)) {
            throw new IllegalStateException("Attempting to remove non-existing agent-event pair from index");
        }

        multimap.remove(agent, event);
        queue.remove(event);
    }

    public EventBlock next() {
        return queue.pop();
    }

    public void unlink(Agent agent) {
        Stream<DeterministicEvent> events = multimap.get(agent);
        events.forEach(queue::remove);
        multimap.remove(agent);
    }

    public void update(Agent agent) {
        Stream<DeterministicEvent> events = multimap.get(agent);
        events.forEach(event -> {
            queue.remove(event);
            queue.add(event);
        });
    }
}
