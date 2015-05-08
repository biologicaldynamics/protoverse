package runtime.agent;

import org.junit.*;
import runtime.topology.coordinate.Coordinate;

import java.util.function.Supplier;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AgentTest {

    private final int id = 4;
    private Supplier<Coordinate> locator;
    private Runnable death;
    private Coordinate c;
    private Agent query;

    @Before
    public void before() throws Exception {
        query = new Agent(id);
        locator = mock(Supplier.class);
        c = mock(Coordinate.class);
        when(locator.get()).thenReturn(c);
        death = mock(Runnable.class);
        query.init(locator, death);
    }

    @Test
    public void getAgentId() throws Exception {
        assertEquals(id, query.getAgentId());
    }

    @Test
    public void locate() throws Exception {
        assertSame(c, query.locate());
    }

    @Test
    public void existsYes() throws Exception {
        assertTrue(query.exists());
    }

    @Test
    public void existsNo() throws Exception {
        when(locator.get()).thenReturn(null);
        assertFalse(query.exists());
    }

    @Test
    public void die() throws Exception {
        query.die();
        verify(death).run();
    }
}