package com.rukin.core.util;

import java.util.Random;

public class Numbers {

    private static final Random random = new Random();

    private Numbers() {
    }

    public static double random(double lowerBound, double upperBound) {
        return random.nextDouble() * (upperBound - lowerBound) + lowerBound;
    }
}
