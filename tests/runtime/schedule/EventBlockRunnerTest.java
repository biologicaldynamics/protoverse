package runtime.schedule;

import org.junit.*;
import runtime.schedule.event.*;

import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class EventBlockRunnerTest {

    private EventRunner runner;
    private EventBlockRunner query;
    @Before
    public void before() throws Exception {
        runner = mock(EventRunner.class);
        query = new EventBlockRunner(runner);
    }

    @Test
    public void run() throws Exception {
        DeterministicEvent event = mock(DeterministicEvent.class);
        EventBlock block = mock(EventBlock.class);
        Stream<DeterministicEvent> eventStream = Stream.of(event);
        when(block.get()).thenReturn(eventStream);
        query.run(block);
        verify(runner).doRun(event);
    }
}