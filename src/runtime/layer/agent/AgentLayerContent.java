/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package runtime.layer.agent;

import runtime.agent.Agent;
import runtime.topology.Topology;
import runtime.topology.coordinate.Coordinate;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;
import java.util.stream.Stream;

/**
 * Created by dbborens on 3/6/15.
 */
public class AgentLayerContent {

    private final Map<Coordinate, Agent> coordToAgentMap;
    private final IdentityHashMap<Agent, Coordinate> agentToCoordMap;
    private final AgentIdIndex agentIdIndex;
    private final Topology topology;
    private final Map<Coordinate, Agent> outOfBounds;

    public AgentLayerContent(Topology topology) {
        this.topology = topology;
        coordToAgentMap = new HashMap<>();
        topology.getCanonicalSites()
                .forEach(c -> coordToAgentMap
                        .put((Coordinate) c, null));
        agentIdIndex = new AgentIdIndex();
        agentToCoordMap = new IdentityHashMap<>();
        outOfBounds = new HashMap<>();
    }

    public void put(Agent agent, Coordinate coordinate) {
        if (!coordToAgentMap.containsKey(coordinate)) {
            throw new IllegalArgumentException("Attempting placement to " +
                    "non-canonical coordinate");
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
        if (coordToAgentMap.containsKey(coordinate)) {
            return coordToAgentMap.get(coordinate);
        }

        // Now handle out of bounds case
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
        return coordToAgentMap
                .keySet()
                .stream()
                .filter(coordinate -> isVacant(coordinate));
    }

    public boolean isVacant(Coordinate coordinate) {
        return coordToAgentMap.get(coordinate) == null;
    }

    public AgentIdIndex getAgentIdIndex() {
        return agentIdIndex;
    }
}
