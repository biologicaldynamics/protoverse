/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.agent;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by dbborens on 3/12/15.
 */
public class AgentDeathSupplier implements Function<Agent, Runnable> {

    private Consumer<Agent> layerAgentRemover;
    private Consumer<Agent> scheduleAgentRemover;

    public AgentDeathSupplier(Consumer<Agent> layerAgentRemover,
                              Consumer<Agent> scheduleAgentRemover) {

        this.layerAgentRemover = layerAgentRemover;
        this.scheduleAgentRemover = scheduleAgentRemover;
    }

    @Override
    public Runnable apply(Agent agent) {
        return () -> {
            layerAgentRemover.accept(agent);
            scheduleAgentRemover.accept(agent);
        };
    }
}
