/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.nodes;

import compiler.symbol.tables.MapSymbolTable;
import compiler.symbol.tables.ResolvingSymbolTable;
import compiler.util.IllegalAssignmentError;

import java.util.stream.Stream;


/**
 * MapObjectNode represents a Java object whose members
 * have definite names. This is generally everything except
 * a Collection.
 *
 * Created by dbborens on 2/22/15.
 */
public class MapObjectNode implements ObjectNode {

    private final LocalContextMap local;
    private final MapSymbolTable symbolTable;

    public MapObjectNode(MapSymbolTable symbolTable) {
        this(symbolTable, new LocalContextMap());
    }

    public MapObjectNode(MapSymbolTable symbolTable, LocalContextMap local) {
        this.symbolTable = symbolTable;
        this.local = local;
    }

    public void loadMember(String identifier, Resolvable value) {
        local.loadMember(identifier, value);
    }

    public Stream<String> getMemberIdentifiers() {
        return local.getMemberIdentifiers();
    }

    public Resolvable getMember(String identifier) {
        return local.getMember(identifier);
    }

    public boolean hasMember(String identifier) {
        return local.hasMember(identifier);
    }

    public ResolvingSymbolTable getSymbolTableFor(String identifier) {
        return symbolTable.getSymbolTable(identifier);
    }

    public MapSymbolTable getSymbolTable() {
        return symbolTable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MapObjectNode that = (MapObjectNode) o;

        if (!local.equals(that.local)) return false;
        if (!symbolTable.equals(that.symbolTable)) return false;

        return true;
    }

    @Override
    public Class getInstantiatingClass() {
        return symbolTable.getInstanceClass();
    }

}
