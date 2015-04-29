package runtime.agent.actions.time;

/**
 * Created by dbborens on 4/27/15.
 */
public interface FixedEventTimer {

    public boolean hasNext();

    public double next();
}
