/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package compiler.symbol.tables.runtime.process;

import compiler.pipeline.translate.nodes.MapObjectNode;
import compiler.symbol.symbols.MemberSymbol;
import compiler.symbol.tables.runtime.agent.AgentInstanceSymbolTable;
import compiler.symbol.tables.runtime.primitive.integers.IntegerClassSymbolTable;
import compiler.symbol.tables.runtime.agent.AgentClassSymbolTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import runtime.agent.AgentDescriptor;
import runtime.layer.agent.AgentLayer;
import runtime.process.agent.AgentProcess;
import runtime.process.agent.Scatter;
import runtime.schedule.EventSchedule;
import runtime.topology.coordinate.Coordinate;
import runtime.util.Global;
import runtime.util.RandomChooser;

import java.util.HashMap;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Created by dbborens on 4/23/15.
 */
public class ScatterSymbolTable extends AgentProcessInstanceSymbolTable<Scatter> {

    private final Logger logger;

    public ScatterSymbolTable() {
        super();
        logger = LoggerFactory.getLogger(ScatterSymbolTable.class);
    }
    @Override
    protected HashMap<String, MemberSymbol> resolveMembers() {
        HashMap<String, MemberSymbol> members = new HashMap<>(2);
        count(members);
        description(members);
        return members;
    }

    private void description(HashMap<String, MemberSymbol> members) {
        AgentClassSymbolTable cst = new AgentClassSymbolTable();
        MemberSymbol ms = new MemberSymbol(cst, "The properties of the agents to be scattered.");
        members.put("description", ms);
    }

    private void count(HashMap<String, MemberSymbol> members) {
        IntegerClassSymbolTable cst = new IntegerClassSymbolTable();
        MemberSymbol ms = new MemberSymbol(cst, "The number of agents to scatter.");
        members.put("count", ms);
    }

    @Override
    public AgentProcess instantiate(MapObjectNode node, Global global, AgentLayer agentLayer, EventSchedule schedule) {
        Supplier<Integer> count = intProperty(node, "count");
        AgentDescriptor agent = getAgentDescriptor(node, global, agentLayer, schedule);
        RandomChooser<Coordinate> chooser = new RandomChooser<>(global.getRandom());
        Supplier<Stream<Coordinate>> siteSupplier = () -> agentLayer
                .getTopology()
                .getCanonicalSites();

        return new Scatter(agent, siteSupplier, chooser, count);
    }

    private AgentDescriptor getAgentDescriptor(MapObjectNode node, Global global, AgentLayer agentLayer, EventSchedule schedule) {
        MapObjectNode agentNode = (MapObjectNode) node.getMember("description");
        AgentInstanceSymbolTable st = (AgentInstanceSymbolTable) agentNode.getSymbolTable();
        return st.instantiate(agentNode, global, agentLayer, schedule);
    }
}
