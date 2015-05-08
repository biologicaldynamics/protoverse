package runtime.layer.agent.graph;

import org.junit.*;
import runtime.agent.Agent;
import runtime.topology.Topology;
import runtime.topology.coordinate.Coordinate;
import test.TestBase;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class InfiniteAgentLayerGraphManagerTest extends TestBase {

    private Agent agent;
    private Map<Coordinate, Agent> coordToAgentMap;
    private IdentityHashMap<Agent, Coordinate> agentToCoordMap;
    private Topology topology;
    private Map<Coordinate, Agent> outOfBounds;
    private InfiniteAgentLayerGraphManager query;
    private Coordinate c;

    @Before
    public void before() throws Exception {
        c = mock(Coordinate.class);
        agent = mock(Agent.class);
        coordToAgentMap = new HashMap<>();
        agentToCoordMap = new IdentityHashMap<>();
        topology = mock(Topology.class);
        when(topology.canonicalize(c)).thenReturn(c);
        outOfBounds = new HashMap<>();
        query = new InfiniteAgentLayerGraphManager(coordToAgentMap,
                agentToCoordMap,
                outOfBounds,
                topology);
    }

    private void doLocateTest(boolean overbounds) {
        Agent agent = mock(Agent.class);
        when(c.isOverbounds()).thenReturn(overbounds);
        agentToCoordMap.put(agent, c);
        assertSame(c, query.locate(agent));
    }

    @Test
    public void locateCanonical() throws Exception {
        doLocateTest(false);
    }

    @Test
    public void locateOutOfBounds() throws Exception {
        doLocateTest(true);
    }

    @Test
    public void locateAbsent() throws Exception {
        Agent agent = mock(Agent.class);
        assertNull(query.locate(agent));
    }

    @Test
    public void putCanonical() throws Exception {
        when(c.isOverbounds()).thenReturn(false);
        query.put(agent, c);
        assertSame(c, agentToCoordMap.get(agent));
        assertFalse(outOfBounds.containsKey(agent));
        assertSame(agent, coordToAgentMap.get(c));
    }

    @Test
    public void putOutOfBounds() throws Exception {
        when(c.isOverbounds()).thenReturn(true);
        query.put(agent, c);
        assertSame(agent, outOfBounds.get(c));
        assertSame(c, agentToCoordMap.get(agent));
        assertFalse(coordToAgentMap.containsKey(c));

    }

    @Test(expected = IllegalStateException.class)
    public void putOccupiedThrows() throws Exception {
        when(c.isOverbounds()).thenReturn(false);
        query.put(agent, c);
        query.put(agent, c);

    }

    @Test
    public void removeCanonical() throws Exception {
        when(c.isOverbounds()).thenReturn(false);
        agentToCoordMap.put(agent, c);
        coordToAgentMap.put(c, agent);
        query.remove(agent);
        assertFalse(agentToCoordMap.containsKey(agent));
        assertNull(coordToAgentMap.get(c));
    }

    @Test
    public void removeOutOfBounds() throws Exception {
        when(c.isOverbounds()).thenReturn(true);
        agentToCoordMap.put(agent, c);
        outOfBounds.put(c, agent);
        query.remove(agent);
        assertFalse(agentToCoordMap.containsKey(agent));
        assertFalse(outOfBounds.containsKey(c));
    }

    @Test(expected = IllegalStateException.class)
    public void removeVacantThrows() throws Exception {
        query.remove(agent);
    }

    @Test
    public void getVacancies() throws Exception {
        Coordinate d = mock(Coordinate.class);
        when(topology.getCanonicalSites())
                .thenReturn(Stream.of(c, d));

        coordToAgentMap.put(c, agent);
        coordToAgentMap.put(d, null);

        Stream<Coordinate> actual = query.getVacancies();
        Stream<Coordinate> expected = Stream.of(d);

        assertStreamsEqual(expected, actual);
    }

    @Test
    public void isVacantCanonical() throws Exception {
        coordToAgentMap.put(c, null);
        assertTrue(query.isVacant(c));
        coordToAgentMap.put(c, agent);
        assertFalse(query.isVacant(c));
    }

    @Test
    public void isVacantOutOfBounds() throws Exception {
        when(c.isOverbounds()).thenReturn(true);
        assertTrue(query.isVacant(c));
        outOfBounds.put(c, agent);
        assertFalse(query.isVacant(c));
    }

    @Test
    public void cleanUpAsksTopoConsumer() throws Exception {
        outOfBounds.put(c, agent);
        Consumer<Agent> consumer = mock(Consumer.class);
        when(topology.getOverboundsConsumer()).thenReturn(consumer);
        query.cleanUp();
        verify(consumer).accept(agent);
    }

    @Test
    public void canonicalizeAsksTopology() throws Exception {
        Coordinate d = mock(Coordinate.class);
        when(topology.canonicalize(c)).thenReturn(d);
        assertSame(d, query.canonicalize(c));
    }

    @Test
    public void getVacantCanonical() throws Exception {
        coordToAgentMap.put(c, null);
        assertNull(query.get(c));
    }

    @Test
    public void getVacantOutOfBounds() throws Exception {
        when(c.isOverbounds()).thenReturn(true);
        assertNull(query.get(c));
    }

    @Test
    public void getOccupiedCanonical() throws Exception {
        coordToAgentMap.put(c, agent);
        assertSame(agent, query.get(c));
    }

    @Test
    public void getOccupiedOutOfBounds() throws Exception {
        when(c.isOverbounds()).thenReturn(true);
        outOfBounds.put(c, agent);
        assertSame(agent, query.get(c));
    }
}