/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.layer.agent;

import runtime.agent.Agent;
import runtime.topology.coordinate.Coordinate;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by dbborens on 3/6/15.
 */
public class AgentLayerContent {

    private final Map<Coordinate, Agent> coordToAgentMap;
    private final IdentityHashMap<Agent, Coordinate> agentToCoordMap;
    private final AgentIdIndex agentIdIndex;

    public AgentLayerContent(Stream<Coordinate> canonicalSites) {
        coordToAgentMap = new HashMap<>();
        canonicalSites.forEach(c -> coordToAgentMap.put(c, null));
        agentIdIndex = new AgentIdIndex();
        agentToCoordMap = new IdentityHashMap<>();
    }

    public void put(Agent agent, Coordinate coordinate) {
        if (!coordToAgentMap.containsKey(coordinate)) {
            throw new IllegalArgumentException("Attempting placement to non-canonical coordinate");
        }


        if (agentToCoordMap.containsKey(agent)) {
            throw new IllegalArgumentException("Attempting double placement of agent");
        }

        if (coordToAgentMap.get(coordinate) != null) {
            throw new IllegalArgumentException("Attempting double coordinate occupancy");
        }

        coordToAgentMap.put(coordinate, agent);
        agentToCoordMap.put(agent, coordinate);
    }

    public Agent get(Coordinate coordinate) {
        if (!coordToAgentMap.containsKey(coordinate)) {
            throw new IllegalArgumentException("Attempting to access contents of non-canonical coordinate");
        }

        return coordToAgentMap.get(coordinate);
    }

    public void remove(Agent agent) {
        if (!agentToCoordMap.containsKey(agent)) {
            throw new IllegalArgumentException("Attempting to remove agent that does not exist on this layer");
        }

        Coordinate c = agentToCoordMap.get(agent);
        coordToAgentMap.put(c, null);
        agentToCoordMap.remove(agent);
    }

    public Coordinate locate(Agent agent) {
        return agentToCoordMap.get(agent);
    }

    public Stream<Coordinate> getVacancies() {
        throw new NotImplementedException();
    }

    public boolean isVacant() {
        throw new NotImplementedException();
    }

    public AgentIdIndex getAgentIdIndex() {
        return agentIdIndex;
    }
}
