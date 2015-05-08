/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package runtime.layer.agent;

import runtime.agent.Agent;
import runtime.layer.agent.graph.AgentLayerGraphManager;
import runtime.topology.Topology;
import runtime.topology.coordinate.Coordinate;

import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Created by dbborens on 3/5/15.
 */
public class AgentLayer {

    private final AgentSwapHelper swapHelper;
    private final Topology topology;
    private final AgentLayerGraphManager graphManager;
    private final AgentIdIndex agentIdIndex;

    public AgentLayer(Topology topology,
                      AgentSwapHelper swapHelper,
                      AgentIdIndex agentIdIndex) {

        this.topology = topology;
        this.swapHelper = swapHelper;
        this.agentIdIndex = agentIdIndex;
        graphManager = topology.getGraphManager();
    }

    public AgentLayer(Topology topology) {
        graphManager = topology.getGraphManager();
        agentIdIndex = new AgentIdIndex();
        this.topology = topology;
        this.swapHelper = new AgentSwapHelper(graphManager);
    }

    public Coordinate locate(Agent agent) {
        return graphManager.locate(agent);
    }

    public Topology getTopology() {
        return topology;
    }

    public void put(Agent agent, Coordinate coordinate) {
        graphManager.put(agent, coordinate);
    }

    public void remove(Agent agent) {
        graphManager.remove(agent);
    }

    public void swap(Coordinate p, Coordinate q) {
        swapHelper.swap(p, q);
    }

    public Stream<Coordinate> getVacancies() {
        return graphManager.getVacancies();
    }

    public AgentIdIndex getAgentIdIndex() {
        return agentIdIndex;
    }

    public boolean isVacant(Coordinate location) {
        return graphManager.isVacant(location);
    }

    public void cleanUp() {
        graphManager.cleanUp();
    }
}
