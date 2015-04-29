package runtime.agent.actions;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by dbborens on 4/29/15.
 */
public class CompoundAction implements Action {

    private final List<Action> actionList;

    public CompoundAction(Stream<Action> actions) {
        actionList = actions.collect(Collectors.toList());
    }

    @Override
    public void run() {
        actionList.stream().forEach(Action::run);
    }
}
