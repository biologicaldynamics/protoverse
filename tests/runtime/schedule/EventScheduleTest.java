package runtime.schedule;

import org.junit.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class EventScheduleTest {

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
        fail();
    }

    @Test
    public void advance() throws Exception {
        fail();
    }

    @Test
    public void schedule() throws Exception {
        fail();
    }

    @Test
    public void unlink() throws Exception {
        fail();
    }

    @Test
    public void update() throws Exception {
        fail();
    }
}