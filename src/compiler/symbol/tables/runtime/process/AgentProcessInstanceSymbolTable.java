package compiler.symbol.tables.runtime.process;

import compiler.pipeline.translate.nodes.MapObjectNode;
import compiler.symbol.tables.MapSymbolTable;
import runtime.layer.agent.AgentLayer;
import runtime.process.agent.AgentProcess;
import runtime.schedule.EventSchedule;
import runtime.topology.Topology;
import runtime.util.Global;

/**
 * Created by dbborens on 4/27/15.
 */
public abstract class AgentProcessInstanceSymbolTable<T extends AgentProcess> extends MapSymbolTable<T> {

    public abstract AgentProcess instantiate(MapObjectNode node, Global global, AgentLayer agentLayer, EventSchedule schedule);

}
