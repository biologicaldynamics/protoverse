package runtime.schedule;

import org.junit.*;
import runtime.control.Entity;
import runtime.schedule.event.DeterministicEvent;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class EventRunnerTest {

    private ScheduleContent content;
    private Runnable cleanup;
    private EventRunner query;
    private DeterministicEvent event;
    private Entity entity;

    @Before
    public void before() throws Exception {
        content = mock(ScheduleContent.class);
        cleanup = mock(Runnable.class);
        event = mock(DeterministicEvent.class);
        entity = mock(Entity.class);
        when(event.getEntity()).thenReturn(entity);
        query = new EventRunner(cleanup, content);
    }

    private void doTestStart() throws Exception {
        query.doRun(event);
        verify(event).run();
        verify(cleanup).run();
    }

    @Test
    public void runNotActive() throws Exception {
        when(entity.exists()).thenReturn(true);
        doTestStart();
        verify(content, never()).add(event);
    }

    @Test
    public void runNotPresent() throws Exception {
        when(event.run()).thenReturn(true);
        doTestStart();
        verify(content, never()).add(event);
    }

    @Test
    public void runActiveAndPresent() throws Exception {
        when(entity.exists()).thenReturn(true);
        when(event.run()).thenReturn(true);
        doTestStart();
        verify(content).add(event);
    }
}