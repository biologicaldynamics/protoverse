/*
 * Copyright (c) 2015 David Bruce Borenstein and the 
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import runtime.schedule.EventSchedule;
import runtime.util.Global;
import runtime.util.halt.HaltCondition;

/**
 * Created by dbborens on 3/11/15.
 */
public class Simulation implements Runnable, Entity {
   
    private final EventSchedule schedule;
    private final Logger logger;
    public Simulation(EventSchedule schedule) {
        this.schedule = schedule;
        logger = LoggerFactory.getLogger(Simulation.class);
    }

    @Override
    public void run() {
        logger.debug("Beginning simulation runtime.");
        try {
            while(true) { schedule.advance(); }
        } catch (HaltCondition ex) {
            logger.info("Simulation complete: {}", ex.toString());
        }
    }

    public Double getTime() {
        return schedule.getTime();
    }
}
