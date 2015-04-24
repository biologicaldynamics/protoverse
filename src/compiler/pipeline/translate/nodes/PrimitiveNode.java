/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.nodes;

import com.google.common.reflect.TypeToken;

import java.util.function.Supplier;

/**
 * Created by dbborens on 2/22/15.
 */
public abstract class PrimitiveNode<T> implements ObjectNode {

    private final TypeToken<T> type = new TypeToken<T>(getClass()) {};
    private T value;

    public PrimitiveNode(T value) {
        this.value = value;
    }

    public T report() {
        return value;
    }

    public Supplier<T> instantiate() {
        return () -> report();
    }

//    @Override
//    public Class getInstanceClass() {
//        return type.getRawType();
//    }
}
