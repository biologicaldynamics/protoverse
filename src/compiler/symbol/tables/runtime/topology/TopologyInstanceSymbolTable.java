package compiler.symbol.tables.runtime.topology;

import compiler.pipeline.translate.nodes.MapObjectNode;
import compiler.symbol.symbols.MemberSymbol;
import compiler.symbol.tables.ClassSymbolTable;
import compiler.symbol.tables.MapSymbolTable;
import compiler.symbol.tables.runtime.topology.boundary.BoundaryClassSymbolTable;
import compiler.symbol.tables.runtime.topology.lattice.LatticeClassSymbolTable;
import compiler.symbol.tables.runtime.topology.lattice.LatticeInstanceSymbolTable;
import compiler.symbol.tables.runtime.topology.shape.ShapeClassSymbolTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import runtime.topology.Topology;
import runtime.topology.boundary.AgentBoundary;
import runtime.topology.lattice.Lattice;
import runtime.topology.shape.Shape;

import java.util.HashMap;

/**
 * Created by dbborens on 4/21/15.
 */
public class TopologyInstanceSymbolTable extends MapSymbolTable<Topology> {

    private Logger logger;

    public TopologyInstanceSymbolTable() {
        super();
        logger = LoggerFactory.getLogger(TopologyInstanceSymbolTable.class);
    }

    @Override
    protected HashMap<String, MemberSymbol> resolveMembers() {
        HashMap<String, MemberSymbol> ret = new HashMap<>(3);
        shape(ret);
        lattice(ret);
        boundary(ret);
        return ret;
    }

    private void boundary(HashMap<String, MemberSymbol> ret) {
        ClassSymbolTable cst = new BoundaryClassSymbolTable();
        MemberSymbol ms = new MemberSymbol(cst, "The boundary condition for the topology.");
        ret.put("boundary", ms);
    }

    private void lattice(HashMap<String, MemberSymbol> ret) {
        ClassSymbolTable cst = new LatticeClassSymbolTable();
        MemberSymbol ms = new MemberSymbol(cst, "The lattice connectivity for the topology.");
        ret.put("lattice", ms);
    }

    private void shape(HashMap<String, MemberSymbol> ret) {
        ClassSymbolTable cst = new ShapeClassSymbolTable();
        MemberSymbol ms = new MemberSymbol(cst, "The shape of the topology's boundary.");
        ret.put("shape", ms);
    }

    public Topology instantiate(MapObjectNode node) {
        logger.debug("Instantiating node as a Topology.");

        Lattice lattice = getLattice(node);

        Shape shape = getShape(node, lattice);

        AgentBoundary boundary = getBoundary(node, shape, lattice);

        Topology topology = new Topology(boundary);
        return topology;
    }

    /** Instantiate boundary. */
    private AgentBoundary getBoundary(MapObjectNode node, Shape shape, Lattice lattice) {
        return null;
    }

    /** Instantiate shape. */
    private Shape getShape(MapObjectNode node, Lattice lattice) {
        return null;
    }

    /** Instantiate lattice. */
    private Lattice getLattice(MapObjectNode node) {
        MapObjectNode latticeNode = (MapObjectNode) node.getMember("lattice");
        LatticeInstanceSymbolTable latticeST = (LatticeInstanceSymbolTable) latticeNode.getSymbolTable();
        Lattice lattice = latticeST.instantiate();
        return lattice;
    }
}
