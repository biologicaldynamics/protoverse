package runtime.agent.actions;

import runtime.agent.Agent;

import java.util.function.Function;

/**
 * Created by dbborens on 4/29/15.
 */
@FunctionalInterface
public interface ActionDescriptor extends Function<Agent, Action> {
}
