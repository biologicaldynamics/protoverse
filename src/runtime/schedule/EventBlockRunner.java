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
    private final ScheduleContent content;
    private final Logger logger;
    private final Runnable cleanup;

    public EventBlockRunner(Runnable cleanup, ScheduleContent content) {
        this.cleanup = cleanup;
        this.content = content;
        logger = LoggerFactory.getLogger(EventBlockRunner.class);
    }

    public void run(EventBlock block) {
         block.get()
                 .forEach(event -> {
                     logger.trace("Running {}",
                             event.toString());

                     doRun(event);

                 });
    }

    private void doRun(DeterministicEvent event) {
        boolean stillActive = event.run();
        cleanup.run();
        if (stillPresent(event) && stillActive) {
            content.add(event);
        }
    }

    private boolean stillPresent(DeterministicEvent event) {
        return event.getEntity().exists();
    }
}
