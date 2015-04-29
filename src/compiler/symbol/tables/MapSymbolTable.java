/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.symbol.tables;

import com.google.common.reflect.TypeToken;
import compiler.pipeline.translate.nodes.MapObjectNode;
import compiler.pipeline.translate.nodes.ObjectNode;
import compiler.symbol.symbols.MemberSymbol;
import compiler.symbol.tables.runtime.primitive.doubles.DoubleInstanceSymbolTable;
import compiler.symbol.tables.runtime.primitive.integers.IntegerInstanceSymbolTable;
import compiler.util.UnrecognizedIdentifierError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import runtime.topology.Topology;

import java.util.HashMap;
import java.util.function.Supplier;

/** * Created by dbborens on 3/3/15.
 */
public abstract class MapSymbolTable<T> implements InstantiableSymbolTable {
    protected final HashMap<String, MemberSymbol> requiredMembers;
    protected final Logger logger;
    private final TypeToken<T> type = new TypeToken<T>(getClass()) {};

    public MapSymbolTable() {
        requiredMembers = resolveMembers();
        logger = LoggerFactory.getLogger(MapSymbolTable.class);
    }

    protected abstract HashMap<String, MemberSymbol> resolveMembers();

    public ResolvingSymbolTable getSymbolTable(String identifier) {
        logger.debug("Resolving {}::{}",
                getInstanceClass().getSimpleName(), identifier);

        if (requiredMembers.containsKey(identifier)) {
            return requiredMembers.get(identifier).getSymbolTable();
        }

        throw new UnrecognizedIdentifierError(identifier,
                getInstanceClass());
    }

    public Class getInstanceClass() {
        return type.getRawType();
    }

    protected static Supplier<Integer> intProperty(ObjectNode node, String property) {
        ObjectNode child = ((MapObjectNode) node).getMember(property);
        IntegerInstanceSymbolTable symbolTable = (IntegerInstanceSymbolTable) child.getSymbolTable();
        Supplier<Integer> supplier = symbolTable.instantiate(child);
        return supplier;
    }

    protected static Supplier<Double> doubleProperty(ObjectNode node, String property) {
        ObjectNode child = ((MapObjectNode) node).getMember(property);
        DoubleInstanceSymbolTable symbolTable = (DoubleInstanceSymbolTable) child.getSymbolTable();
        Supplier<Double> supplier = symbolTable.instantiate(child);
        return supplier;
    }

//    protected static void verifyNodeClass(ObjectNode node, Class expected) {
//        Class actual = node.getInstanceClass(); //        if (!expected.equals(actual)) {
//            throw new IllegalArgumentException("Expected a node with class "
//                    + expected.getCanonicalName() + ", but got " +
//                    actual.getCanonicalName());
//        }
//    }
}
