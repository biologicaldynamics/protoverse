package compiler.symbol.tables.runtime.agent;

import compiler.pipeline.translate.nodes.ListObjectNode;
import compiler.pipeline.translate.nodes.MapObjectNode;
import compiler.pipeline.translate.nodes.ObjectNode;
import compiler.symbol.symbols.MemberSymbol;
import compiler.symbol.tables.ClassSymbolTable;
import compiler.symbol.tables.ListSymbolTable;
import compiler.symbol.tables.MapSymbolTable;
import compiler.symbol.tables.SymbolTable;
import compiler.symbol.tables.runtime.schedule.event.EventClassSymbolTable;
import compiler.symbol.tables.runtime.schedule.event.EventInstanceSymbolTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import runtime.agent.Agent;
import runtime.agent.AgentDeathMaker;
import runtime.agent.AgentDescriptor;
import runtime.agent.actions.AgentEventScheduler;
import runtime.agent.actions.AgentEventDescriptor;
import runtime.layer.agent.AgentLayer;
import runtime.schedule.EventSchedule;
import runtime.util.Global;

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Created by dbborens on 4/23/15.
 */
public class AgentInstanceSymbolTable extends MapSymbolTable<Agent> {

    private Logger logger;

    public AgentInstanceSymbolTable() {
        super();
        logger = LoggerFactory.getLogger(AgentInstanceSymbolTable.class);
    }

    @Override
    protected HashMap<String, MemberSymbol> resolveMembers() {
        HashMap<String, MemberSymbol> members = new HashMap<>(1);
        propDo(members);
        return members;
    }

    private void propDo(HashMap<String, MemberSymbol> members) {
        ClassSymbolTable cst = new EventClassSymbolTable();
        ListSymbolTable lst = new ListSymbolTable(cst);
        MemberSymbol ms = new MemberSymbol(lst, "List of behaviors to perform repeatedly.");
        members.put("do", ms);
    }

    public AgentDescriptor instantiate(MapObjectNode node, Global global, AgentLayer agentLayer, EventSchedule schedule) {
        logger.debug("Instantiating node as an Agent descriptor.");
        AgentEventScheduler eventEstablisher = getEventScheduler(node, global, agentLayer, schedule);
        AgentDeathMaker deathMaker = getAgentDeathSupplier(agentLayer, schedule);
        return new AgentDescriptor(agentLayer, eventEstablisher, deathMaker);
    }

    private AgentEventScheduler getEventScheduler(MapObjectNode node,
                                                      Global global,
                                                      AgentLayer layer,
                                                      EventSchedule schedule) {
        Stream<AgentEventDescriptor> eventStream = getEventStream(node, global, layer, schedule);
        return new AgentEventScheduler(eventStream, schedule);
    }

    private Stream<AgentEventDescriptor> getEventStream(MapObjectNode node, Global global, AgentLayer layer, EventSchedule schedule) {
        ListObjectNode doList = (ListObjectNode) node.getMember("do");
        Stream<AgentEventDescriptor> eventStream = doList.getMemberStream()
                .map(eventNode -> getEventProducer((MapObjectNode) eventNode, global, layer, schedule));
        return eventStream;
    }

    private AgentEventDescriptor getEventProducer(MapObjectNode eventNode, Global global, AgentLayer layer, EventSchedule schedule) {
        EventInstanceSymbolTable st = (EventInstanceSymbolTable) eventNode.getSymbolTable();
        AgentEventDescriptor descriptor = st.instantiate(eventNode, global, layer, schedule);
        return descriptor;
    }

    public AgentDeathMaker getAgentDeathSupplier(AgentLayer layer, EventSchedule schedule) {
        Consumer<Agent> layerAgentRemover = layer::remove;
        Consumer<Agent> scheduleAgentRemover = schedule::unlink;
        return new AgentDeathMaker(layerAgentRemover, scheduleAgentRemover);
    }
}
