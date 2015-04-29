/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.nodes;

import compiler.util.IllegalAssignmentError;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by dbborens on 2/22/15.
 */
public class LocalContextMap {
    private Map<String, ObjectNode> members;

    public LocalContextMap() {
        members = new HashMap<>();
    }

    public Stream<String> getMemberIdentifiers() {
        return members.keySet().stream();
    }

    public ObjectNode getMember(String name) {
        if (!members.containsKey(name)) {
            throw new IllegalStateException("Retrieval of undefined member '" + name + "'");
        }

        return members.get(name);
    }

    public void loadMember(String identifier, ObjectNode value) {
        if (members.containsKey(identifier)) {
            throw new IllegalAssignmentError("Double assignment to member '" + identifier + "'");
        }
        members.put(identifier, value);
    }

    public boolean hasMember(String name) {
        return members.containsKey(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocalContextMap that = (LocalContextMap) o;

        if (!members.equals(that.members)) return false;

        return true;
    }
}
