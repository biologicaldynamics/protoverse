package runtime.layer.agent;

import org.junit.*;
import runtime.agent.Agent;
import runtime.layer.agent.graph.AgentLayerGraphManager;
import runtime.topology.Topology;
import runtime.topology.coordinate.Coordinate;

import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AgentLayerTest {

    private Agent agent;
    private Coordinate c;
    private AgentSwapHelper swapHelper;
    private Topology topology;
    private AgentLayerGraphManager graphManager;
    private AgentIdIndex idIndex;
    private AgentLayer query;

    @Before
    public void before() throws Exception {
        agent = mock(Agent.class);
        c = mock(Coordinate.class);
        swapHelper = mock(AgentSwapHelper.class);
        topology = mock(Topology.class);
        idIndex = mock(AgentIdIndex.class);
        graphManager = mock(AgentLayerGraphManager.class);
        when(topology.getGraphManager()).thenReturn(graphManager);
        query = new AgentLayer(topology, swapHelper, idIndex);
    }

    @Test
    public void locateAsksGraphManager() throws Exception {
        when(graphManager.locate(agent)).thenReturn(c);
        assertSame(c, query.locate(agent));
    }

    @Test
    public void getTopology() throws Exception {
        assertSame(topology, query.getTopology());
    }

    @Test
    public void putAsksGraphManager() throws Exception {
        query.put(agent, c);
        verify(graphManager).put(agent, c);
    }

    @Test
    public void removeAsksGraphManager() throws Exception {
        query.remove(agent);
        verify(graphManager).remove(agent);
    }

    @Test
    public void swapAsksSwapHelper() throws Exception {
        Coordinate d = mock(Coordinate.class);
        query.swap(c, d);
        verify(swapHelper).swap(c, d);
    }

    @Test
    public void getVacancies() throws Exception {
        Stream<Coordinate> stream = mock(Stream.class);
        when(graphManager.getVacancies()).thenReturn(stream);
        assertSame(stream, query.getVacancies());
    }

    @Test
    public void getAgentIdIndex() throws Exception {
        assertSame(idIndex, query.getAgentIdIndex());
    }

    @Test
    public void isVacantAsksGraphManager() throws Exception {
        when(graphManager.isVacant(c)).thenReturn(true);
        assertTrue(query.isVacant(c));
    }

    @Test
    public void cleanUpAsksGraphManager() throws Exception {
        query.cleanUp();
        verify(graphManager).cleanUp();
    }
}