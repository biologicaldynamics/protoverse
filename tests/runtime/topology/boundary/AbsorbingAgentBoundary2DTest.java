package runtime.topology.boundary;

import org.junit.*;
import runtime.agent.Agent;
import runtime.topology.coordinate.*;
import runtime.topology.shape.Shape;
import test.TestBase;

import java.util.function.Consumer;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AbsorbingAgentBoundary2DTest extends TestBase {

    private Coordinate2D outside, inside, corrected;
    private Shape shape;
    private AbsorbingAgentBoundary2D query;

    @Before
    public void before() throws Exception {
        shape = mock(Shape.class);
        outside = mock(Coordinate2D.class);
        inside = mock(Coordinate2D.class);
        corrected = mock(Coordinate2D.class);
        when(shape.isOverbounds(outside)).thenReturn(true);
        when(outside.asOverbounds()).thenReturn(corrected);
        query = new AbsorbingAgentBoundary2D(shape);
    }
    @Test
    public void getNeighbors() throws Exception {
        Coordinate2D start = mock(Coordinate2D.class);
        Stream<Coordinate2D> neighbors = Stream.of(outside, inside);
        when(shape.getRawNeighbors(start)).thenReturn(neighbors);
        Stream<Coordinate2D> actual = query.getNeighbors(start);
        Stream<Coordinate2D> expected = Stream.of(corrected, inside);
        assertStreamsEqual(expected, actual);
    }

    @Test
    public void overboundTriggersAgentDeath() throws Exception {
        Agent agent = mock(Agent.class);
        Consumer<Agent> consumer = query.getOverboundsConsumer();
        consumer.accept(agent);
        verify(agent).die();
    }

    @Test
    public void canonicalizeInsidePreserves() throws Exception {
        assertSame(inside, query.canonicalize(inside));
    }

    @Test
    public void canonicalizeOutsideSetsFlag() throws Exception {
        assertSame(corrected, query.canonicalize(outside));
    }
}