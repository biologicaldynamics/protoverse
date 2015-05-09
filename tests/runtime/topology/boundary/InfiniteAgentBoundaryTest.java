package runtime.topology.boundary;

import org.junit.*;
import org.mockito.Mockito;
import runtime.layer.agent.graph.*;
import runtime.topology.Topology;

import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class InfiniteAgentBoundaryTest {

    private InfiniteAgentBoundary query;

    @Before
    public void before() throws Exception {
        query = mock(InfiniteAgentBoundary.class, Mockito.CALLS_REAL_METHODS);

    }
    @Test
    public void getReturnsInfinite() throws Exception {
        Topology topology = mock(Topology.class);
        when (topology.getCanonicalSites()).thenReturn(Stream.empty());
        AgentLayerGraphManager ret = query.getGraphManager(topology);
        assertTrue(ret instanceof InfiniteAgentLayerGraphManager);
    }
}