/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package compiler.util;

/**
 * Created by dbborens on 2/21/15.
 */
public class IllegalAssignmentError extends UserError {

    public IllegalAssignmentError() {
        super();
    }

    public IllegalAssignmentError(String message) {
        super(message);
    }
}
