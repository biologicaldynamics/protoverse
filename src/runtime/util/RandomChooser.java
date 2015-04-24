/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

package runtime.util;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Given a collection, chooses a random member of the collection.
 *
 * Created by dbborens on 3/5/15.
 */
public class RandomChooser<T> implements Function<Stream<T>, T> {

    private Random random;

    public RandomChooser (Random random) {
        this.random = random;
    }

    @Override
    public T apply(Stream<T> stream) {
        List<T> ts = stream.collect(Collectors.toList());
        int n = ts.size();
        int i = random.nextInt(n);
        return ts.get(i);
    }

    /**
     * Choose k unique values from the supplied T domain.
     * If k > n, return all values in a random order.
     *
     * @param domain
     * @param k
     * @return
     */
    public Stream<T> choose(Stream<T> domain, int k) {
        if (k < 0) {
            throw new IllegalArgumentException("nCk expects k >= 0");
        }

        if (k == 0) {
            return Stream.empty();
        }

        List<T> ts = domain.collect(Collectors.toList());

        k = k > ts.size() ? ts.size() : k;

        Collections.shuffle(ts, random);
        return IntStream.range(0, k)
                .boxed()
                .map(i -> ts.get(i));
    }
}
