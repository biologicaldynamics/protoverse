package runtime.schedule;

import org.junit.*;
import org.mockito.InOrder;
import runtime.control.Entity;
import runtime.schedule.event.DeterministicEvent;
import runtime.util.IdentityMultimap;
import test.TestBase;

import java.util.IdentityHashMap;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ScheduleContentTest extends TestBase {

    private IdentityMultimap<Entity, DeterministicEvent> multimap;
    private EventQueue queue;
    private ScheduleContent query;
    private DeterministicEvent event;
    private Entity entity;
    @Before
    public void before() throws Exception {
        multimap = new IdentityMultimap<>();
        queue = mock(EventQueue.class);

        entity = mock(Entity.class);
        event = mock(DeterministicEvent.class);
        when(event.getEntity()).thenReturn(entity);
        query = new ScheduleContent(multimap, queue);
    }

    @Test(expected = IllegalStateException.class)
    public void doubleAddThrows() throws Exception {
        query.add(event);
        query.add(event);
    }

    @Test
    public void addTellsMap() throws Exception {
        query.add(event);
        Stream<DeterministicEvent> expected = Stream.of(event);
        Stream<DeterministicEvent> actual = multimap.get(entity);
        assertStreamsEqual(expected, actual);
    }

    @Test
    public void addTellsQueue() throws Exception {
        query.add(event);
        verify(queue).add(event);
    }

    @Test
    public void removeTellsMap() throws Exception {
        multimap.put(entity, event);
        query.remove(event);
        assertFalse(multimap.has(entity));
    }

    @Test
    public void removeTellsQueue() throws Exception {
        multimap.put(entity, event);
        query.remove(event);
        verify(queue).remove(event);
    }

    @Test(expected = IllegalStateException.class)
    public void removeAbsentThrows() throws Exception {
        query.remove(event);
    }

    @Test
    public void nextRemovesElements() throws Exception {
        multimap.put(entity, event);
        EventBlock block = mock(EventBlock.class);
        when(queue.pop()).thenReturn(block);
        when(block.get()).thenReturn(Stream.of(event));
        query.next();
        assertFalse(multimap.has(entity));
    }

    @Test
    public void nextAsksQueue() throws Exception {
        multimap.put(entity, event);
        EventBlock block = mock(EventBlock.class);
        when(queue.pop()).thenReturn(block);
        when(block.get()).thenReturn(Stream.of(event));
        assertSame(block, query.next());
    }

    @Test
    public void unlinkHasTellsQueue() throws Exception {
        multimap.put(entity, event);
        query.unlink(entity);
        verify(queue).remove(event);
        assertFalse(multimap.has(entity));
    }

    @Test
    public void unlinkHasTellsMap() throws Exception {
        multimap.put(entity, event);
    }

    @Test
    public void unlinkNotHas() throws Exception {
        multimap = mock(IdentityMultimap.class);
        query.unlink(entity);
        verifyNoMoreInteractions(multimap);
    }

    @Test
    public void update() throws Exception {
        multimap.put(entity, event);
        query.update(entity);
        InOrder inOrder = inOrder(queue);
        inOrder.verify(queue).remove(event);
        inOrder.verify(queue).add(event);
    }
}