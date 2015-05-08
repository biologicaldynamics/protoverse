package runtime.layer.agent;

import org.junit.*;
import org.mockito.InOrder;
import org.mockito.verification.VerificationMode;
import runtime.agent.Agent;
import runtime.layer.agent.graph.AgentLayerGraphManager;
import runtime.topology.coordinate.Coordinate;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AgentSwapHelperTest {

    private Coordinate p, q;
    private Agent a, b;
    private AgentLayerGraphManager graphManager;
    private AgentSwapHelper query;

    @Before
    public void before() throws Exception {
        p = mock(Coordinate.class);
        q = mock(Coordinate.class);
        a = mock(Agent.class);
        b = mock(Agent.class);
        graphManager = mock(AgentLayerGraphManager.class);
        query = new AgentSwapHelper(graphManager);
    }

    @Test
    public void swapBothNull() throws Exception {
        query.swap(p, q);
        verify(graphManager, never()).put(any(), any());
        verify(graphManager, never()).remove(any());
    }

    @Test
    public void swapLeftNull() throws Exception {
        when(graphManager.get(p)).thenReturn(a);
        query.swap(p, q);
        InOrder inOrder = inOrder(graphManager);
        inOrder.verify(graphManager).remove(a);
        inOrder.verify(graphManager).put(a, q);

        verify(graphManager, never()).remove(b);
        verify(graphManager, never()).put(any(), eq(p));
    }

    @Test
    public void swapRightNull() throws Exception {
        when(graphManager.get(q)).thenReturn(b);
        query.swap(p, q);
        InOrder inOrder = inOrder(graphManager);
        inOrder.verify(graphManager).remove(b);
        inOrder.verify(graphManager).put(b, p);

        verify(graphManager, never()).remove(a);
        verify(graphManager, never()).put(any(), eq(q));
    }

    @Test
    public void swapNeitherNull() throws Exception {
        when(graphManager.get(p)).thenReturn(a);
        when(graphManager.get(q)).thenReturn(b);
        query.swap(p, q);
        InOrder inOrder = inOrder(graphManager);
        inOrder.verify(graphManager).remove(a);
        inOrder.verify(graphManager).remove(b);
        inOrder.verify(graphManager).put(a, q);
        inOrder.verify(graphManager).put(b, p);
    }

}