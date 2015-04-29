package compiler.symbol.tables.runtime.schedule.event;

import compiler.pipeline.translate.nodes.ListObjectNode;
import compiler.pipeline.translate.nodes.MapObjectNode;
import compiler.symbol.symbols.MemberSymbol;
import compiler.symbol.tables.ListSymbolTable;
import compiler.symbol.tables.runtime.agent.actions.ActionClassSymbolTable;
import compiler.symbol.tables.runtime.agent.actions.ActionInstanceSymbolTable;
import compiler.symbol.tables.runtime.primitive.doubles.DoubleClassSymbolTable;
import runtime.agent.actions.Action;
import runtime.agent.actions.ActionDescriptor;
import runtime.agent.actions.AgentEventDescriptor;
import runtime.agent.actions.CompoundAction;
import runtime.agent.actions.time.FixedEventTimer;
import runtime.agent.actions.time.PeriodicEventTimer;
import runtime.layer.agent.AgentLayer;
import runtime.schedule.EventSchedule;
import runtime.util.Global;

import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by dbborens on 4/23/15.
 */
public class DeterministicEventInstanceSymbolTable extends EventInstanceSymbolTable<AgentEventDescriptor> {

    @Override
    protected HashMap<String, MemberSymbol> resolveMembers() {
        HashMap<String, MemberSymbol> members = new HashMap<>(2);
        actions(members);
        every(members);
        return members;
    }

    private void every(HashMap<String, MemberSymbol> members) {
        DoubleClassSymbolTable cst = new DoubleClassSymbolTable();
        MemberSymbol ms = new MemberSymbol(cst, "The period with which to " +
                "perform the repeated action.");

        members.put("every", ms);
    }

    private void actions(HashMap<String, MemberSymbol> members) {
        ActionClassSymbolTable cst = new ActionClassSymbolTable();
        ListSymbolTable lst = new ListSymbolTable(cst);
        MemberSymbol ms = new MemberSymbol(lst, "The list of actions to perform.");

        members.put("actions", ms);
    }

    public AgentEventDescriptor instantiate(MapObjectNode node, Global global, AgentLayer layer, EventSchedule schedule) {

        ListObjectNode actionNode = (ListObjectNode) node.getMember("actions");

        List<ActionDescriptor> childDescriptors = actionNode
                .getMemberStream()
                .map(childNode -> {
                    ActionInstanceSymbolTable childST = (ActionInstanceSymbolTable) childNode
                            .getSymbolTable();

                    ActionDescriptor childDescriptor = childST
                            .instantiate((MapObjectNode) childNode, global, layer);

                    return childDescriptor;
                }).collect(Collectors.toList());

        ActionDescriptor actionDescriptor = agent -> {
            Stream<Action> childActions = childDescriptors.stream().map(child -> child.apply(agent));
            CompoundAction action = new CompoundAction(childActions);
            return action;
        };

        Supplier<FixedEventTimer> timerSupplier = () -> {
            double start = schedule.getTime();
            double interval = doubleProperty(node, "every").get();
            logger.warn("*** Hard coded that event ends at t=100.0");
            double end = 100.0;
            return new PeriodicEventTimer(start, interval, end);
        };

        return new AgentEventDescriptor(actionDescriptor, timerSupplier);
    }

}
