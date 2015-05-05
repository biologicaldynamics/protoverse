package test;/*
 * Copyright (c) 2015 David Bruce Borenstein and the
 * Trustees of Princeton University. All rights reserved.
 */

import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by dbborens on 2/15/15.
 */
public abstract class TestBase {
    private final static double epsilon = calcEpsilon();

    private static double calcEpsilon() {
        double eps = 1.0D;

        while ((1.0 + (eps / 2.0)) != 1.0) {
            eps /= 2.0;
        }

        return eps;
    }

    protected static double epsilon() {
        return epsilon;
    }

    protected static boolean epsilonEquals(double p, double q) {
        double delta = p - q;
        double magnitude = Math.abs(delta);
        return (magnitude < epsilon());
    }

    protected static <T> List<T> listOf(T... contents) {
        return Stream
                .of(contents)
                .collect(Collectors.toList());
    }

    protected static <T> void assertSetsEqual(Set<T> expected, Set<T> actual) {
        Set<T> difference = Sets.symmetricDifference(expected, actual);
        String differenceString = difference.stream().map(Object::toString).collect(Collectors.joining(", "));
        String errorString = "Unexpected difference between sets: " + differenceString;
        assertEquals(errorString, 0, difference.size());
    }

    protected static <T> void assertStreamsEqual(Stream<T> expected, Stream<T> actual) {
        List<T> expList = expected.collect(Collectors.toList());
        List<T> actList = actual.collect(Collectors.toList());
        assertEquals(expList, actList);
    }
}
