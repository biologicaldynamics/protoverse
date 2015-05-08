package runtime.process.agent;

import org.junit.*;
import runtime.agent.*;
import runtime.topology.coordinate.Coordinate;
import runtime.util.RandomChooser;

import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ScatterTest {

    private AgentDescriptor descriptor;
    private Supplier<Stream<Coordinate>> siteSupplier;
    private RandomChooser<Coordinate> chooser;
    private Supplier<Integer> count;
    private Scatter query;

    @Before
    public void before() throws Exception {
        descriptor = mock(AgentDescriptor.class);
        siteSupplier = mock(Supplier.class);
        chooser = mock(RandomChooser.class);
        count = mock(Supplier.class);
        query = new Scatter(descriptor, siteSupplier, chooser, count);
    }

    @Test
    public void run() throws Exception {
        Coordinate c = mock(Coordinate.class);
        Coordinate d = mock(Coordinate.class);
        Stream<Coordinate> candidates = Stream.of(c, d);
        when(siteSupplier.get()).thenReturn(candidates);
        when(count.get()).thenReturn(1);
        when(chooser.choose(candidates, 1))
                .thenReturn(Stream.of(d));

        query.run();

        verify(descriptor).establish(d);
        verify(descriptor, never()).establish(c);
    }
}