package compiler.symbol.tables.runtime.agent.actions;

import compiler.pipeline.translate.nodes.MapObjectNode;
import compiler.symbol.symbols.MemberSymbol;
import compiler.symbol.tables.MapSymbolTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import runtime.agent.actions.ActionDescriptor;
import runtime.agent.actions.Wander;
import runtime.layer.agent.AgentLayer;
import runtime.schedule.EventSchedule;
import runtime.topology.coordinate.Coordinate;
import runtime.util.Global;
import runtime.util.RandomChooser;

import java.util.HashMap;

/**
 * Created by dbborens on 4/23/15.
 */
public class WanderSymbolTable extends ActionInstanceSymbolTable<Wander> {

    private Logger logger;

    public WanderSymbolTable() {
        super();
        logger = LoggerFactory.getLogger(WanderSymbolTable.class);
    }

    @Override
    protected HashMap<String, MemberSymbol> resolveMembers() {
        return new HashMap<>(0);
    }

    @Override
    public ActionDescriptor instantiate(MapObjectNode node, Global global, AgentLayer layer) {
        logger.debug("Instantiating ActionDescriptor node as Wander.");
        RandomChooser<Coordinate> chooser = new RandomChooser<>(global.getRandom());

        ActionDescriptor descriptor = agent -> new Wander(chooser, agent, layer);
        return descriptor;
    }
}
