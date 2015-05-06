package runtime.layer.agent.graph;

import runtime.agent.Agent;
import runtime.topology.coordinate.Coordinate;

import java.util.stream.Stream;

/**
 * Created by dbborens on 5/5/15.
 */
public abstract class AgentLayerGraphManager {

    /**
     * Provide the coordinate of the supplied agent, or throw an exception if
     * it does not have a canonical or out-of-bounds location.
     *
     * @param agent
     * @return
     */
    public abstract Coordinate locate(Agent agent);

    /**
     * Place an agent in the specified location. If the location is out of
     * bounds, handle that in the manner appropriate to the topology.
     *
     * @param agent
     * @param coordinate
     */
    public abstract void put(Agent agent, Coordinate coordinate);

    /**
     * Remove the agent from the graph. Throw an exception if it does not have
     * a canonical or out-of-bounds location.
     *
     * @param agent
     */
    public abstract void remove(Agent agent);

    /**
     * Provide a list of canonical vacant locations.
     */
    public abstract Stream<Coordinate> getVacancies();

    /**
     * Returns true iff there is no agent in the specified location. Out of
     * bounds coordinates handled as per boundary condition.
     *
     * @param coordinate
     * @return
     */
    public abstract boolean isVacant(Coordinate coordinate);

    /**
     * Perform boundary-specified OOB cleanup process, if applicable.
     */
    public abstract void cleanUp();

    /**
     * Put the coordinate in a canonical form. If in bounds, this does nothing.
     * If out of bounds, it either gets mapped to a new location, or flagged as
     * out of bounds (as specified by the BC).
     *
     * @param coordinate
     * @return
     */
    public abstract Coordinate canonicalize(Coordinate coordinate);
}
