/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.layer.agent;

import runtime.agent.Agent;
import runtime.topology.Topology;
import runtime.topology.coordinate.Coordinate;

import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Created by dbborens on 3/5/15.
 */
public class AgentLayer {

    private final AgentLayerContent content;
    private final AgentSwapHelper swapHelper;
    private final Topology topology;

    public AgentLayer(AgentLayerContent content,
                      Topology topology,
                      AgentSwapHelper swapHelper) {

        this.content = content;
        this.topology = topology;
        this.swapHelper = swapHelper;
    }

    public AgentLayer(Topology topology) {
        this.topology = topology;
        this.content = new AgentLayerContent(topology.getCanonicalSites());
        this.swapHelper = new AgentSwapHelper(content);
    }

    public Coordinate locate(Agent agent) {
        return content.locate(agent);
    }

    public Topology getTopology() {
        return topology;
    }

    public void put(Agent agent, Coordinate coordinate) {
        content.put(agent, coordinate);
    }

    public void remove(Agent agent) {
        content.remove(agent);
    }

    public void swap(Coordinate p, Coordinate q) {
        swapHelper.swap(p, q);
    }

    public Stream<Coordinate> getVacancies() {
        return content.getVacancies();
    }

    public AgentIdIndex getAgentIdIndex() {
        return content.getAgentIdIndex();
    }

    public boolean isVacant(Coordinate location) {
        return content.isVacant();
    }
}
