/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.layer.agent;

import runtime.agent.Agent;
import runtime.topology.coordinate.Coordinate;

/**
 * Created by dbborens on 3/6/15.
 */
public class AgentSwapHelper {

    private final AgentLayerContent content;

    public AgentSwapHelper(AgentLayerContent content) {
        this.content = content;
    }

    public void swap(Coordinate p, Coordinate q) {
        Agent pAgent = content.get(p);
        Agent qAgent = content.get(q);

        removeIfNotNull(pAgent);
        removeIfNotNull(qAgent);

        placeIfNotNull(pAgent, q);
        placeIfNotNull(qAgent, p);
    }

    private void placeIfNotNull(Agent agent, Coordinate coord) {
        if (agent == null) {
            return;
        }

        content.put(agent, coord);
    }

    private void removeIfNotNull(Agent agent) {
        if (agent == null) {
            return;
        }
        content.remove(agent);
    }
}
