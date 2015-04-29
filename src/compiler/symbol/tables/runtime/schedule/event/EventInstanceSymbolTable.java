package compiler.symbol.tables.runtime.schedule.event;

import com.google.common.util.concurrent.AbstractScheduledService;
import compiler.pipeline.translate.nodes.MapObjectNode;
import compiler.symbol.tables.MapSymbolTable;
import runtime.agent.actions.AgentEventDescriptor;
import runtime.layer.agent.AgentLayer;
import runtime.schedule.EventSchedule;
import runtime.util.Global;

/**
 * Created by dbborens on 4/29/15.
 */
public abstract class EventInstanceSymbolTable<T extends AgentEventDescriptor> extends MapSymbolTable<T> {

    public abstract AgentEventDescriptor instantiate(MapObjectNode node, Global global, AgentLayer layer, EventSchedule schedule);
}
