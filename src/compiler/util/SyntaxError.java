/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.util;

/**
 * Created by dbborens on 2/22/15.
 */
public class SyntaxError extends UserError {
    public SyntaxError() {
        super();
    }

    public SyntaxError(String message) {
        super(message);
    }
}
