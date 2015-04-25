package compiler.symbol.tables.runtime.topology.lattice;

import compiler.symbol.symbols.MemberSymbol;
import compiler.symbol.tables.MapSymbolTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import runtime.topology.lattice.Lattice;
import runtime.topology.lattice.RectangularLattice;

import java.util.HashMap;

/**
 * Created by dbborens on 4/23/15.
 */
public class RectangularLatticeSymbolTable extends LatticeInstanceSymbolTable {

    private Logger logger;

    public RectangularLatticeSymbolTable() {
        this.logger = LoggerFactory.getLogger(RectangularLatticeSymbolTable.class);
    }

    protected HashMap<String, MemberSymbol> resolveMembers() {
        return new HashMap<>(0);
    }

    @Override
    public RectangularLattice instantiate() {
        logger.debug("Instantiating node as RectangularLattice.");
        return new RectangularLattice();
    }
}
