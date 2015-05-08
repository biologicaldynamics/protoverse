/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package runtime.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import runtime.schedule.event.DeterministicEvent;

/**
 * Created by dbborens on 3/8/15.
 */
public class EventBlockRunner {
    private final Logger logger;
    private final EventRunner runner;

    public EventBlockRunner(Runnable cleanup, ScheduleContent content) {
        runner = new EventRunner(cleanup, content);
        logger = LoggerFactory.getLogger(EventBlockRunner.class);
    }

    public EventBlockRunner(EventRunner runner) {
        this.runner = runner;
        logger = LoggerFactory.getLogger(EventBlockRunner.class);
    }

    public void run(EventBlock block) {
        block.get().forEach(event -> {
            logger.trace("Running {}", event.toString());
            runner.doRun(event);
        });
    }

}
