/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.util.halt;

/**
 * Created by dbborens on 3/8/15.
 */
public class NoMoreEvents extends HaltCondition {

    @Override
    public String toString() {
        return "No more events scheduled.";
    }

}
