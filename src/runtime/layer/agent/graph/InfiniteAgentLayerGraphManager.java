package runtime.layer.agent.graph;

import runtime.agent.Agent;
import runtime.topology.Topology;
import runtime.topology.coordinate.Coordinate;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by dbborens on 5/5/15.
 */
public class InfiniteAgentLayerGraphManager extends AgentLayerGraphManager {

    private final Map<Coordinate, Agent> coordToAgentMap;
    private final IdentityHashMap<Agent, Coordinate> agentToCoordMap;
    private final Topology topology;
    private final Map<Coordinate, Agent> outOfBounds;

    public InfiniteAgentLayerGraphManager(Map<Coordinate, Agent> coordToAgentMap,
                                          IdentityHashMap<Agent, Coordinate> agentToCoordMap,
                                          Map<Coordinate, Agent> outOfBounds,
                                          Topology topology) {
        this.coordToAgentMap = coordToAgentMap;
        this.agentToCoordMap = agentToCoordMap;
        this.topology = topology;
        this.outOfBounds = outOfBounds;
    }

    public InfiniteAgentLayerGraphManager(Topology topology) {
        this.topology = topology;
        coordToAgentMap = new HashMap<>();
        topology.getCanonicalSites()
                .forEach(c -> coordToAgentMap
                        .put((Coordinate) c, null));
        agentToCoordMap = new IdentityHashMap<>();
        outOfBounds = new HashMap<>();
    }

    @Override
    public Coordinate locate(Agent agent) {
        if (!agentToCoordMap.containsKey(agent)) {
            return null;
        }
        return agentToCoordMap.get(agent);
    }

    @Override
    public void put(Agent agent, Coordinate coordinate) {
        Coordinate canonical = canonicalize(coordinate);
        if (agentToCoordMap.containsKey(agent)) {
            throw new IllegalStateException("Double assignment of agent to " +
                    "graph");
        }
        if (canonical.isOverbounds()) {
            outOfBounds.put(coordinate, agent);
        } else {
            coordToAgentMap.put(coordinate, agent);
        }
        agentToCoordMap.put(agent, canonical);
    }

    public Agent get(Coordinate coordinate) {
        if (coordinate.isOverbounds()) {
            if (outOfBounds.containsKey(coordinate)) {
                return outOfBounds.get(coordinate);
            } else {
                return null;
            }
        } else {
            return coordToAgentMap.get(coordinate);
        }
    }

    @Override
    public void remove(Agent agent) {
        Coordinate coordinate = locate(agent);
        if (coordinate.isOverbounds()) {
            outOfBounds.remove(coordinate);
        } else {
            coordToAgentMap.put(coordinate, null);
        }
        agentToCoordMap.remove(agent);
    }

    @Override
    public Stream<Coordinate> getVacancies() {
        return coordToAgentMap
                .keySet()
                .stream()
                .filter(coordinate -> isVacant(coordinate));
    }

    @Override
    public boolean isVacant(Coordinate coordinate) {
        if (coordinate.isOverbounds()) {
            return !outOfBounds.containsKey(coordinate);
        } else {
            return coordToAgentMap.get(coordinate) == null;
        }
    }

    @Override
    public void cleanUp() {
        Consumer<Agent> consumer = topology.getOverboundsConsumer();

        // Convert to a new list to avoid concurrent modification exception
        List<Agent> toRemove = outOfBounds
                .values()
                .stream()
                .collect(Collectors.toList());

        // Now, remove from the map using elements from the separate list
        toRemove.stream().forEach(consumer::accept);
    }

    @Override
    public Coordinate canonicalize(Coordinate coordinate) {
        return topology.canonicalize(coordinate);
    }
}
