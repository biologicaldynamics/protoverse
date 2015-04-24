/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.agent;

import runtime.agent.actions.AgentEventEstablisher;
import runtime.layer.agent.AgentIdIndex;
import runtime.topology.coordinate.Coordinate;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by dbborens on 3/12/15.
 */
public class AgentEstablisher {

    private AgentIdIndex idIndex;
    private Function<Agent, Coordinate> agentLocator;
    private AgentEventEstablisher eventEstablisher;
    private BiConsumer<Agent, Coordinate> agentLayerPlacer;
    private AgentDeathSupplier deathSupplier;

    public AgentEstablisher(AgentIdIndex idIndex,
                            Function<Agent, Coordinate> agentLocator,
                            AgentEventEstablisher eventEstablisher,
                            BiConsumer<Agent, Coordinate> agentLayerPlacer,
                            AgentDeathSupplier deathSupplier) {

        this.idIndex = idIndex;
        this.agentLocator = agentLocator;
        this.eventEstablisher = eventEstablisher;
        this.agentLayerPlacer = agentLayerPlacer;
        this.deathSupplier = deathSupplier;
    }

    public void establish(Coordinate c) {
        // Get the ID number
        int id = idIndex.nextId();

        // Build the agent
        Agent agent = new Agent(id);

        // Place the agent
        agentLayerPlacer.accept(agent, c);

        // Build and schedule the agent's events
        eventEstablisher.accept(agent);

        // Get the agent locator
        Supplier<Coordinate> locator = () -> agentLocator.apply(agent);

        // Get the agent's death event
        Runnable death = deathSupplier.apply(agent);

        // Initialize agent
        agent.init(locator, death);
    }
}
