/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.nodes;

import compiler.symbol.tables.ListSymbolTable;
import compiler.symbol.tables.ResolvingSymbolTable;

import java.util.stream.Stream;

/**
 * Created by dbborens on 2/22/15.
 */
public class ListObjectNode implements ObjectNode {

    private ListSymbolTable symbolTable;
    private final LocalContextList local;

    public ListObjectNode(ListSymbolTable symbolTable) {
        this(symbolTable, new LocalContextList());
    }

    public ListObjectNode(ListSymbolTable symbolTable, LocalContextList local) {
        this.symbolTable = symbolTable;
        this.local = local;
    }

    public Stream<ObjectNode> getMemberStream() {
        return local.getMembers();
    }

    public ObjectNode getMember(int index) {
        if (index >= size()) {
            throw new IllegalArgumentException("List context member index out of bounds.");
        }
        return local.get(index);
    }

    public void loadMember(ObjectNode value) {
        local.loadMember(value);
    }

    public int size() {
        return local.size();
    }

    public ResolvingSymbolTable getSymbolTable() {
        return symbolTable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListObjectNode that = (ListObjectNode) o;

        if (!local.equals(that.local)) return false;
        if (!symbolTable.equals(that.symbolTable)) return false;

        return true;
    }

    @Override
    public Class getInstantiatingClass() {
        return symbolTable.getMemberClass();
    }
}
