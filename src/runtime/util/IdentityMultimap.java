/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.util;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Set;
import java.util.stream.Stream;

/**
 * A simple multimap backed by an identity hash map. Unfortunately, this is
 * not provided by Guava.
 *
 * Created by dbborens on 3/8/15.
 */
public class IdentityMultimap<K, V> {

    private final IdentityHashMap<K, Set<V>> contents;

    public IdentityMultimap() {
        this(new IdentityHashMap<>());
    }

    public IdentityMultimap(IdentityHashMap<K, Set<V>> contents) {
        this.contents = contents;
    }

    public Stream<V> get(K key) {
        return contents.get(key).stream();
    }

    /**
     * Remove the key. Internally, this should remove the node entirely iff
     * this was the last key in the node.
     *
     * @param key
     */
    public void remove(K key) {
        if (!contents.containsKey(key)) {
            throw new IllegalArgumentException("Attempting to remove unrecognized key from identity multimap");
        }
        contents.remove(key);
    }

    public void remove(K key, V value) {
        if (!contents.containsKey(key)) {
            throw new IllegalArgumentException("Attempting to remove unrecognized key from identity multimap");
        }

        Set<V> values = contents.get(key);
        if (values.size() == 1) {
            contents.remove(key);
        } else if (values.contains(value)) {
            values.remove(value);
        } else {
            throw new IllegalArgumentException("Attempting to remove unrecognized key-value pair from identity multimap");
        }
    }

    /**
     * Add the key, creating an internal node if necessary.
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        if (!contents.containsKey(key)) {
            contents.put(key, new HashSet<>());
        }
        Set<V> values = contents.get(key);
        if (values.contains(value)) {
            throw new IllegalArgumentException("Repeat assignment of key to identity multimap");
        }
        values.add(value);
    }

    public boolean has(K key) {
        return contents.containsKey(key);
    }

    public boolean has(K key, V value) {
        if (!contents.containsKey(key)) {
            return false;
        }

        Set<V> values = contents.get(key);
        return values.contains(value);
    }
}
