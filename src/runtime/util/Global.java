package runtime.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * Created by dbborens on 4/27/15.
 */
public class Global extends EpsilonEnabled {

    private final Logger logger;
    private final Random random;

    public Global() {
        long seed = System.currentTimeMillis();
        random = new Random(seed);
        logger = LoggerFactory.getLogger(Global.class);
        logger.debug("Instantiating global properties.");
        logger.info("Random number seed {}.", seed);
    }

    public double epsilon() {
        return epsilon;
    }

    public Random getRandom() {
        return random;
    }

    @Override
    public boolean epsilonEquals(double p, double q) {
        return super.epsilonEquals(p, q);
    }
}
