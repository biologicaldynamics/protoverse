/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package compiler.util;

/**
 * Created by dbborens on 2/21/15.
 */
public class UnrecognizedIdentifierError extends UserError {

    public UnrecognizedIdentifierError() {
        super();
    }

    public UnrecognizedIdentifierError(String identifier) {
        super("Unrecognized identifier" + identifier);
    }

    public UnrecognizedIdentifierError(String identifier, Class clazz) {
        super("Unrecognized identifier " + clazz.getSimpleName() + "::" +
                identifier);
    }
}
