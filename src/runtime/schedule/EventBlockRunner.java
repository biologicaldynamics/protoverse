/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package runtime.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by dbborens on 3/8/15.
 */
public class EventBlockRunner {
    private final ScheduleContent content;
    private final Logger logger;

    public EventBlockRunner(ScheduleContent content) {
        this.content = content;
        logger = LoggerFactory.getLogger(EventBlockRunner.class);
    }

    public void run(EventBlock block) {
         block.get()
                 .peek(event ->
                         logger.trace("Running {}",
                                 event.toString()))

                 .forEach(event -> {
                     boolean stillActive = event.run();
                     if (stillActive) {
                         content.add(event);
                     }
                 });
    }
}
