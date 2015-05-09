package runtime.schedule;

import org.junit.*;
import runtime.agent.Agent;
import runtime.schedule.event.DeterministicEvent;
import test.TestBase;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class EventScheduleTest extends TestBase {

    private EventBlockRunner runner;
    private ScheduleContent content;
    private EventSchedule query;

    @Before
    public void before() throws Exception {
        runner = mock(EventBlockRunner.class);
        content = mock(ScheduleContent.class);
        query = new EventSchedule(runner, content);
    }

    @Test
    public void getTime() throws Exception {
        assertEquals(0.0, query.getTime(), epsilon());
    }

    private EventBlock setUpAdvanceTest(double time) throws Exception {
        EventBlock block = mock(EventBlock.class);
        when(content.next()).thenReturn(block);
        when(block.getTime()).thenReturn(time);
        query.advance();
        return block;
    }
    @Test
    public void advanceRunsNextBlock() throws Exception {
        EventBlock block = setUpAdvanceTest(5.0);
        verify(runner).run(block);
    }

    @Test
    public void advanceAdvancesClock() throws Exception {
        setUpAdvanceTest(5.0);
        assertEquals(5.0, query.getTime(), epsilon());
    }

    @Test(expected = IllegalStateException.class)
    public void advanceThrowsOnIllegalTime() throws Exception {
        setUpAdvanceTest(5.0);
        setUpAdvanceTest(4.0);
    }

    @Test
    public void scheduleAsksContent() throws Exception {
        DeterministicEvent event = mock(DeterministicEvent.class);
        query.schedule(event);
        verify(content).add(event);
    }

    @Test
    public void unlink() throws Exception {
        Agent agent = mock(Agent.class);
        query.unlink(agent);
        verify(content).unlink(agent);
    }

    @Test
    public void update() throws Exception {
        Agent agent = mock(Agent.class);
        query.update(agent);
        verify(content).update(agent);
    }
}