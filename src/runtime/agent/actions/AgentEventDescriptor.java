/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.agent.actions;

import runtime.agent.Agent;
import runtime.agent.AgentDescriptor;
import runtime.agent.actions.time.FixedEventTimer;
import runtime.agent.actions.time.PeriodicEventTimer;
import runtime.schedule.event.DeterministicEvent;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by dbborens on 3/12/15.
 */
public class AgentEventDescriptor implements Function<Agent, DeterministicEvent> {

    private ActionDescriptor actionProducer;
    private Supplier<FixedEventTimer> timeRuleSupplier;

    public AgentEventDescriptor(ActionDescriptor actionProducer,
                                Supplier<FixedEventTimer> timeRuleSupplier) {

        this.actionProducer = actionProducer;
        this.timeRuleSupplier = timeRuleSupplier;
    }

    @Override
    public DeterministicEvent apply(Agent agent) {
        FixedEventTimer timeRule = timeRuleSupplier.get();
        Action action = actionProducer.apply(agent);
        DeterministicEvent event = new DeterministicEvent(agent, action, timeRule);
        return event;
    }
}
