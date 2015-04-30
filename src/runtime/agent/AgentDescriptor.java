/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package runtime.agent;

import runtime.agent.actions.AgentEventScheduler;
import runtime.layer.agent.AgentLayer;
import runtime.topology.coordinate.Coordinate;
import java.util.function.Supplier;

/**
 * Created by dbborens on 3/12/15.
 */
public class AgentDescriptor {

    private final AgentEventScheduler eventEstablisher;
    private final AgentDeathMaker deathSupplier;
    private final AgentLayer layer;

    public AgentDescriptor(AgentLayer layer,
                           AgentEventScheduler eventEstablisher,
                           AgentDeathMaker deathSupplier) {

        this.eventEstablisher = eventEstablisher;
        this.deathSupplier = deathSupplier;
        this.layer = layer;
    }

    public void establish(Coordinate c) {
        // Get the ID number
        int id = layer.getAgentIdIndex().nextId();

        // Build the agent
        Agent agent = new Agent(id);

        // Place the agent
        layer.put(agent, c);

        // Build and schedule the agent's events
        eventEstablisher.accept(agent);

        // Get the agent locator
        Supplier<Coordinate> locator = () -> layer.locate(agent);

        // Get the agent's death event
        Runnable death = deathSupplier.apply(agent);

        // Initialize agent
        agent.init(locator, death);
    }
}
