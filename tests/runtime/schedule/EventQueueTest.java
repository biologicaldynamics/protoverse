package runtime.schedule;

import org.junit.*;
import runtime.schedule.event.DeterministicEvent;
import runtime.util.halt.*;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class EventQueueTest {

    private TreeMap<Double, EventBlock> nodeTree;
    private IdentityHashMap<DeterministicEvent, EventBlock> eventMap;
    private EventQueuePruner pruner;
    private EventQueue query;
    private DeterministicEvent event;
    private EventBlock block;
    @Before
    public void before() throws Exception {
        nodeTree = new TreeMap<>();
        eventMap = mock(IdentityHashMap.class);
        pruner = mock(EventQueuePruner.class);
        block = mock(EventBlock.class);
        event = mock(DeterministicEvent.class);
        when(block.get()).thenReturn(Stream.of(event));
        query = new EventQueue(nodeTree, eventMap, pruner);
    }

    @Test(expected = NoMoreEvents.class)
    public void popEmptyHalts() throws Exception {
        query.pop();
    }

    @Test
    public void popRemovesEvents() throws Exception {
        nodeTree.put(3.0, block);
        query.pop();
        verify(eventMap).remove(event);
    }

    @Test
    public void popReturnsFirstNode() throws Exception {
        nodeTree.put(3.0, block);
        assertSame(block, query.pop());
    }

    @Test
    public void removeTellsMap() throws Exception {
        when(eventMap.get(event)).thenReturn(block);
        when(eventMap.containsKey(event)).thenReturn(true);
        query.remove(event);
        verify(eventMap).remove(event);
    }

    @Test
    public void removeTellsPruner() throws Exception {
        when(eventMap.get(event)).thenReturn(block);
        when(eventMap.containsKey(event)).thenReturn(true);
        query.remove(event);
        verify(pruner).remove(event, block);
    }

    @Test(expected = IllegalStateException.class)
    public void removeAbsentThrows() throws Exception {
        query.remove(event);
    }

    @Test
    public void add() throws Exception {
        when(pruner.add(event)).thenReturn(block);
        query.add(event);
        verify(eventMap).put(event, block);
    }

    @Test
    public void sizeAsksMap() throws Exception {
        when(eventMap.size()).thenReturn(17);
        assertEquals(17, query.size());
    }

    @Test
    public void getMomentCount() throws Exception {
        nodeTree.put(3.0, block);
        assertEquals(1, query.getMomentCount());
    }
}