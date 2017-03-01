package com.rukin.core.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayUtil {

    private ArrayUtil() {
    }

    public static double[] normalize(double[] array) {
        double min = Arrays.stream(array).min().orElse(Double.NaN);
        double max = Arrays.stream(array).max().orElse(Double.NaN);
        double range = max - min;
        if (range == 0) {
            return array;
        } else {
            return Arrays.stream(array)
                    .map(value -> (value - min) / range)
                    .toArray();
        }
    }

    public static <T> void shuffle(T[] array) {
        List<T> list = Arrays.asList(array);
        Collections.shuffle(list);
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }
    }
}
