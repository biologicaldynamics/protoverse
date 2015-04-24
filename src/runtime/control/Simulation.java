/*
 * Copyright (c) 2015 David Bruce Borenstein and the 
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.control;

import runtime.schedule.EventSchedule;
import runtime.util.halt.HaltCondition;

/**
 * Created by dbborens on 3/11/15.
 */
public class Simulation implements Runnable {
   
    private EventSchedule scheduler;

    public Simulation(EventSchedule scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public void run() {
        try {
            while(true) { scheduler.advance(); }
        } catch (HaltCondition ex) {
            System.out.println("Simulation complete: " + ex);
            System.err.println("This should now hand control to a 'finally()' handler for resolving output, etc.");
            System.err.println("Messages should be handled by a mockable logger like log4j.");
        }
    }

    public Double getTime() {
        return scheduler.getTime();
    }
}
