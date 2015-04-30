/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package compiler.pipeline.translate.nodes;

import compiler.symbol.tables.InstantiableSymbolTable;

/**
 * Created by dbborens on 2/22/15.
 */
public interface ObjectNode {

    /**
     * Reports the class of the object that will be instantiated by this class.
     * @return
     */
    public Class getInstantiatingClass();

    public InstantiableSymbolTable getSymbolTable();
}
