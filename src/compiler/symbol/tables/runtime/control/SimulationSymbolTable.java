package compiler.symbol.tables.runtime.control;

import compiler.pipeline.translate.nodes.MapObjectNode;
import compiler.symbol.symbols.MemberSymbol;
import compiler.symbol.tables.ClassSymbolTable;
import compiler.symbol.tables.ListSymbolTable;
import compiler.symbol.tables.MapSymbolTable;
import compiler.symbol.tables.runtime.process.AgentProcessClassSymbolTable;
import compiler.symbol.tables.runtime.topology.TopologyClassSymbolTable;
import compiler.symbol.tables.runtime.topology.TopologyInstanceSymbolTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import runtime.control.Simulation;
import runtime.schedule.EventSchedule;
import runtime.topology.Topology;

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
        Simulation simulation = new Simulation(schedule);

        // Build the topology.
        MapObjectNode topologyNode = (MapObjectNode) node.getMember("topology");
        TopologyInstanceSymbolTable topoST = (TopologyInstanceSymbolTable) topologyNode.getSymbolTable();
        Topology topology = topoST.instantiate(topologyNode);

        // Build initial actions.
        logger.warn("*** Initial actions not implemented.");

        // Return the simulation.
        return simulation;
    }
}
