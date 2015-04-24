/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.schedule;

import runtime.schedule.event.DeterministicEvent;

import java.util.IdentityHashMap;
import java.util.TreeMap;

/**
 * Created by dbborens on 3/9/15.
 */
public class EventQueue {
    private TreeMap<Double, EventBlock> nodeTree;
    private IdentityHashMap<DeterministicEvent, EventBlock> eventMap;
    private EventQueuePruner pruner;

    public EventQueue() {
        nodeTree = new TreeMap<>();
        eventMap = new IdentityHashMap<>();
        pruner = new EventQueuePruner(nodeTree);
    }

    public EventQueue(TreeMap<Double, EventBlock> nodeTree, IdentityHashMap<DeterministicEvent, EventBlock> eventMap, EventQueuePruner pruner) {
        this.nodeTree = nodeTree;
        this.eventMap = eventMap;
        this.pruner = pruner;
    }

    /**
     * Retrieve the first element from the node tree. Remove each of its
     * member events from the event map. Remove the element from the tree.
     * Return the block.
     *
     * @return
     */
    public EventBlock pop() {
        Double time = nodeTree.firstKey();
        EventBlock block = nodeTree.get(time);
        nodeTree.remove(time);
        block.get().forEach(eventMap::remove);
        return block;
    }

    /**
     * Find the node for the reported event in the event map. Blow up if the
     * event doesn't exist. Hand the node and the event to the tree pruner.
     *
     * @param event
     */
    public void remove(DeterministicEvent event) {
        if (!eventMap.containsKey(event)) {
            throw new IllegalArgumentException("Trying to remove unrecognized event");
        }
        EventBlock block = eventMap.get(event);
        pruner.remove(event, block);
        eventMap.remove(event);
    }

    /**
     * Hand the event to the tree pruner for adding. The tree pruner returns
     * the node to which it was added. Add the event and the node to the event
     * map.
     *
     * @param event
     */
    public void add(DeterministicEvent event) {
        EventBlock block = pruner.add(event);
        eventMap.put(event, block);
    }

    /**
     * Reports the number of events scheduled.
     * @return
     */
    public int getNumEvents() {
        return eventMap.size();
    }

    /**
     * Reports the number of unique times at which events are
     * currently scheduled.
     * @return
     */
    public int getMoments() {
        return nodeTree.size();
    }
}
