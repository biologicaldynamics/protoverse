/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package runtime.schedule;

import runtime.control.Entity;
import runtime.schedule.event.DeterministicEvent;
import runtime.util.IdentityMultimap;

import java.util.stream.Stream;

/**
 *
 * Created by dbborens on 3/8/15.
 */
public class ScheduleContent {

    private final IdentityMultimap<Entity, DeterministicEvent> multimap;
    private final EventQueue queue;

    public ScheduleContent() {
        this(new IdentityMultimap<>(), new EventQueue());
    }

    public ScheduleContent(IdentityMultimap<Entity, DeterministicEvent> multimap,
                           EventQueue queue) {
        this.multimap = multimap;
        this.queue = queue;
    }

    public void add(DeterministicEvent event) {
        Entity entity = event.getEntity();
        if (multimap.has(entity, event)) {
            throw new IllegalStateException("Attempting double scheduling of entity-event pair.");
        }
        queue.add(event);

        multimap.put(entity, event);
    }

    public void remove(DeterministicEvent event) {
        Entity entity = event.getEntity();

        if (!multimap.has(entity, event)) {
            throw new IllegalStateException("Attempting to remove non-existing entity-event pair from index");
        }

        multimap.remove(entity, event);
        queue.remove(event);
    }

    public EventBlock next() {
        EventBlock block = queue.pop();
        block.get().forEach(event -> {
            Entity entity = event.getEntity();
            multimap.remove(entity, event);
        });
        return block;
    }

    public void unlink(Entity entity) {
        if (multimap.has(entity)) {
            Stream<DeterministicEvent> events = multimap.get(entity);
            events.forEach(queue::remove);
            multimap.remove(entity);
        }
    }

    public void update(Entity entity) {
        Stream<DeterministicEvent> events = multimap.get(entity);
        events.forEach(event -> {
            queue.remove(event);
            queue.add(event);
        });
    }
}
