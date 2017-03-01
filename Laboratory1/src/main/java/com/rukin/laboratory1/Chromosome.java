package com.rukin.laboratory1;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Chromosome {

    private final int[] genes;

    public Chromosome(int[] genes) {
        this.genes = genes;
    }

    public int[] genes() {
        return genes;
    }

    @Override
    public String toString() {
        return Arrays.stream(genes)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining());
    }
}
