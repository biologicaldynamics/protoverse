package compiler.symbol.tables.runtime.control;

import compiler.pipeline.translate.nodes.ListObjectNode;
import compiler.pipeline.translate.nodes.MapObjectNode;
import compiler.pipeline.translate.nodes.ObjectNode;
import compiler.symbol.symbols.MemberSymbol;
import compiler.symbol.tables.ClassSymbolTable;
import compiler.symbol.tables.ListSymbolTable;
import compiler.symbol.tables.MapSymbolTable;
import compiler.symbol.tables.runtime.process.AgentProcessClassSymbolTable;
import compiler.symbol.tables.runtime.process.AgentProcessInstanceSymbolTable;
import compiler.symbol.tables.runtime.topology.TopologyClassSymbolTable;
import compiler.symbol.tables.runtime.topology.TopologyInstanceSymbolTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import runtime.agent.actions.time.SingularEventTimer;
import runtime.control.Simulation;
import runtime.layer.agent.AgentLayer;
import runtime.process.agent.AgentProcess;
import runtime.schedule.EventSchedule;
import runtime.schedule.event.DeterministicEvent;
import runtime.topology.Topology;
import runtime.util.Global;

import java.util.HashMap;

/**
 * Created by dbborens on 4/21/15.
 */
public class SimulationSymbolTable extends MapSymbolTable<Simulation> {

    private Logger logger;
    public SimulationSymbolTable() {
        super();
        logger = LoggerFactory.getLogger(SimulationSymbolTable.class);
    }

    @Override
    protected HashMap<String, MemberSymbol> resolveMembers() {
        HashMap<String, MemberSymbol> ret = new HashMap<>(2);
        topology(ret);
        initially(ret);
        return ret;
    }

    private void initially(HashMap<String, MemberSymbol> ret) {
        ClassSymbolTable cst = new AgentProcessClassSymbolTable();
        ListSymbolTable lst = new ListSymbolTable(cst);
        MemberSymbol ms = new MemberSymbol(lst, "A list of top-down processes " +
                "to perform at the outset of the simulation.");
        ret.put("initially", ms);
    }

    private void topology(HashMap<String, MemberSymbol> ret) {
        TopologyClassSymbolTable st = new TopologyClassSymbolTable();
        MemberSymbol ms = new MemberSymbol(st, "The topological" +
                " properties of the system.");

        ret.put("topology", ms);
    }

    public Simulation instantiate(MapObjectNode node) {
        logger.debug("Instantiating node as a SimulationSymbolTable.");

        // Build the simulation itself.
        EventSchedule schedule = new EventSchedule();
        Global global = new Global();
        Simulation simulation = new Simulation(schedule);

        // Build the topology.
        MapObjectNode topologyNode = (MapObjectNode) node.getMember("topology");
        TopologyInstanceSymbolTable topoST = (TopologyInstanceSymbolTable) topologyNode.getSymbolTable();
        Topology topology = topoST.instantiate(topologyNode);
        AgentLayer agentLayer = new AgentLayer(topology);

        // Build initial actions.
        ListObjectNode initially = (ListObjectNode) node.getMember("initially");
        initially.getMemberStream().forEach(childNode -> {
            DeterministicEvent event = makeProcess(childNode, simulation, global, agentLayer, schedule);
            logger.debug("Scheduling event {}.", event.toString());
        });

        // Return the simulation.
        return simulation;
    }

    private DeterministicEvent makeProcess(ObjectNode childNode,
                                           Simulation simulation,
                                           Global global,
                                           AgentLayer agentLayer,
                                           EventSchedule schedule) {
        AgentProcessInstanceSymbolTable st = (AgentProcessInstanceSymbolTable) childNode.getSymbolTable();
        AgentProcess process = st.instantiate((MapObjectNode) childNode,
                global,
                agentLayer, schedule);
        SingularEventTimer timer = new SingularEventTimer(0.0);
        DeterministicEvent event = new DeterministicEvent(simulation, process, timer);
        return event;
    }
}
