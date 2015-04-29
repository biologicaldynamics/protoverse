package compiler.symbol.tables.runtime.agent.actions;

import compiler.pipeline.translate.nodes.MapObjectNode;
import compiler.symbol.tables.MapSymbolTable;
import runtime.agent.actions.Action;
import runtime.agent.actions.ActionDescriptor;
import runtime.agent.actions.AgentEventDescriptor;
import runtime.layer.agent.AgentLayer;
import runtime.schedule.EventSchedule;
import runtime.util.Global;

/**
 * Created by dbborens on 4/29/15.
 */
public abstract class ActionInstanceSymbolTable<T extends Action> extends MapSymbolTable<T> {
    public abstract ActionDescriptor instantiate(MapObjectNode node, Global global, AgentLayer layer);
}
