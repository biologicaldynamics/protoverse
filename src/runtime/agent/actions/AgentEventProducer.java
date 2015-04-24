/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.agent.actions;

import runtime.agent.Agent;
import runtime.agent.actions.time.FixedIntervalTimeRule;
import runtime.schedule.event.DeterministicEvent;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by dbborens on 3/12/15.
 */
public class AgentEventProducer implements Function<Agent, DeterministicEvent> {

    private Function<Agent, Action> actionProducer;
    private Supplier<FixedIntervalTimeRule> timeRuleSupplier;

    public AgentEventProducer(Function<Agent, Action> actionProducer,
                              Supplier<FixedIntervalTimeRule> timeRuleSupplier) {

        this.actionProducer = actionProducer;
        this.timeRuleSupplier = timeRuleSupplier;
    }

    @Override
    public DeterministicEvent apply(Agent agent) {
        FixedIntervalTimeRule timeRule = timeRuleSupplier.get();
        Action action = actionProducer.apply(agent);
        DeterministicEvent event = new DeterministicEvent(agent, action, timeRule);
        return event;
    }
}
