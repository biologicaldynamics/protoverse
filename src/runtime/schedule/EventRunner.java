package runtime.schedule;

import runtime.schedule.event.DeterministicEvent;

/**
 * Created by dbborens on 5/7/2015.
 */
public class EventRunner {
    private final ScheduleContent content;
    private final Runnable cleanup;

    public EventRunner(Runnable cleanup, ScheduleContent content) {
        this.content = content;
        this.cleanup = cleanup;
    }

    public void doRun(DeterministicEvent event) {
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
