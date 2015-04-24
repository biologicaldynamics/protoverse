/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.nodes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by dbborens on 2/22/15.
 */
public class LocalContextList {

    private List<ObjectNode> members;

    public LocalContextList() {
        members = new ArrayList<>();
    }

    public ObjectNode get(int i) {
        if (i >= members.size()) {
            throw new IllegalArgumentException("Retrieval of undefined indexed member");
        }

        return members.get(i);
    }

    public void loadMember(ObjectNode toAdd) {
        members.add(toAdd);
    }

    public int size() {
        return members.size();
    }

    public Stream<ObjectNode> getMembers() {
        return members.stream();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocalContextList that = (LocalContextList) o;

        if (!members.equals(that.members)) return false;

        return true;
    }
}
