/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.schedule;

import com.google.common.collect.Sets;
import runtime.schedule.event.DeterministicEvent;
import runtime.util.EpsilonEnabled;

import java.util.Set;
import java.util.stream.Stream;

/**
 * A doubly linked list node whose contents are a set of Events.
 * @see ScheduleContent
 *
 * Created by dbborens on 3/8/15.
 */
public class EventBlock extends EpsilonEnabled implements Comparable<EventBlock>{

    private final Set<DeterministicEvent> contents;
    private final double time;

    /**
     *
     * @param time the expected time of all set members.
     */

    public EventBlock(double time) {
        this.time = time;

        contents = Sets.newIdentityHashSet();
    }

    /**
     * Add an event to the node. If the event is already contained, throw
     * an exception.
     *
     * @param event
     */
    public void add(DeterministicEvent event) {
        if (!epsilonEquals(time, event.getNextTime())) {
            throw new IllegalArgumentException("Unexpected event time for event queue node");
        }
        if (contents.contains(event)) {
            throw new IllegalArgumentException("Double assignment to event queue node");
        }

        contents.add(event);
    }

    /**
     * Removes an event from the set. If this is the last event in the set,
     * removes this node from the list.
     *
     * @param event
     */
    public void remove(DeterministicEvent event) {
        if (!contents.contains(event)) {
            throw new IllegalArgumentException("Attempting to remove non-existing event from event node");
        }

        contents.remove(event);
    }

    public Stream<DeterministicEvent> get() {
        return contents.stream();
    }

    public int size() {
        return contents.size();
    }

    public double getTime() {
        return time;
    }

    @Override
    public int compareTo(EventBlock o) {
        return Double.compare(this.time, o.time);
    }
}
