/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.layer.agent;

import runtime.agent.Agent;

import java.util.HashMap;

/**
 * Created by dbborens on 3/6/15.
 */
public class AgentIdIndex {

    private final HashMap<Integer, Agent> idToAgentMap;
    private int nextId;

    public AgentIdIndex() {
        idToAgentMap = new HashMap<>();
        nextId = 0;
    }

    public int nextId() {
        int curId = nextId;
        nextId++;
        return curId;
    }

    public Agent resolve(Integer agentId) {
        if (!idToAgentMap.containsKey(agentId)) {
            throw new IllegalArgumentException("Agent with id=" + agentId + " not found.");
        }
        return idToAgentMap.get(agentId);
    }

    public void add(Agent agent) {
        Integer agentId = agent.getAgentId();
        if (idToAgentMap.containsKey(agentId)) {
            throw new IllegalStateException("Duplicate assignment of agentId=" + agentId + " to agent map.");
        }

        idToAgentMap.put(agentId, agent);
    }

    public void remove(Agent agent) {
        Integer agentId = agent.getAgentId();
        if (!idToAgentMap.containsKey(agentId)) {
            throw new IllegalArgumentException("Agent with agentId " + agentId + " cannot be removed from agent map because it is not contained there.");
        }

        Agent contained = idToAgentMap.get(agentId);

        if (agent != contained) {
            throw new IllegalStateException("The agent with id=" + agentId + " is not the same as the one to be removed!");
        }

        idToAgentMap.remove(agentId);
    }
}
