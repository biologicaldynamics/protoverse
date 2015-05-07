/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package runtime.layer.agent;

import runtime.agent.Agent;
import runtime.layer.agent.graph.AgentLayerGraphManager;
import runtime.topology.coordinate.Coordinate;

/**
 * Created by dbborens on 3/6/15.
 */
public class AgentSwapHelper {

    private final AgentLayerGraphManager graphManager;

    public AgentSwapHelper(AgentLayerGraphManager graphManager) {
        this.graphManager = graphManager;
    }

    public void swap(Coordinate p, Coordinate q) {
        Agent pAgent = graphManager.get(p);
        Agent qAgent = graphManager.get(q);

        removeIfNotNull(pAgent);
        removeIfNotNull(qAgent);

        placeIfNotNull(pAgent, q);
        placeIfNotNull(qAgent, p);
    }

    private void placeIfNotNull(Agent agent, Coordinate coord) {
        if (agent == null) {
            return;
        }

        graphManager.put(agent, coord);
    }

    private void removeIfNotNull(Agent agent) {
        if (agent == null) {
            return;
        }
        graphManager.remove(agent);
    }
}
