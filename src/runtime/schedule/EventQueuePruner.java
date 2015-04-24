/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.schedule;

import runtime.schedule.event.DeterministicEvent;

import java.util.TreeMap;

/**
 * Created by dbborens on 3/9/15.
 */
public class EventQueuePruner {

    private final TreeMap<Double, EventBlock> tree;

    public EventQueuePruner(TreeMap<Double, EventBlock> tree) {
        this.tree = tree;
    }

    /**
     * Remove the event from the block. If it's the last event in the block,
     * remove the block from the tree. Blow up if the event is missing from
     * the block, or the block is missing from the tree.
     *
     * @param event
     * @param block
     */
    public void remove(DeterministicEvent event, EventBlock block) {
        if (block.size() == 1) {
            double time = event.getNextTime();
            tree.remove(time);
        } else {
            block.remove(event);
        }
    }

    /**
     * Add the event to the tree. If the tree has an event block with the
     * event's time, add it to that block. Otherwise, create a new block and
     * insert it.
     *
     * @param event
     */
    public EventBlock add(DeterministicEvent event) {
        double time = event.getNextTime();
        if (!tree.containsKey(time)) {
            EventBlock block = new EventBlock(time);
            tree.put(time, block);
        }

        EventBlock block = tree.get(time);
        block.add(event);
        return block;
    }
}
