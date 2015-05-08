package runtime.control;

import org.junit.*;
import runtime.schedule.EventSchedule;
import runtime.util.halt.HaltCondition;
import test.TestBase;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SimulationTest extends TestBase {

    private EventSchedule schedule;
    private Simulation simulation;

    @Before
    public void before() throws Exception {
        schedule = mock(EventSchedule.class);
        simulation = new Simulation(schedule);
        doThrow(mock(HaltCondition.class)).when(schedule).advance();
    }

    @Test
    public void run() throws Exception {
        simulation.run();
        verify(schedule, times(1)).advance();
    }

    @Test
    public void getTime() throws Exception {
        when(schedule.getTime()).thenReturn(7.2);
        assertEquals(7.2, simulation.getTime(), epsilon());
    }

    @Test
    public void exists() throws Exception {
        assertTrue(simulation.exists());
    }
}