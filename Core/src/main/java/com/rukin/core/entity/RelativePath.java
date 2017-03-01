package com.rukin.core.entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RelativePath extends ArrayList<Integer> {

    public RelativePath(List<Integer> path) {
        super(path);
    }

    public RelativePath(AbsolutePath path) {
        List<Integer> cities = IntStream.range(0, path.size()).boxed().collect(Collectors.toList());
        addAll(path.stream().map(i -> {
            int index = cities.indexOf(i);
            cities.remove(index);
            return index;
        }).collect(Collectors.toList()));
    }

    public static Comparator<RelativePath> getComparator(Function<RelativePath, Double> objectiveFunction) {
        return (p1, p2) -> Double.compare(objectiveFunction.apply(p1), objectiveFunction.apply(p2));
    }

    public void swap(RelativePath path, int from) {
        for (int i = from; i < size(); i++) {
            int tmp = path.get(i);
            path.set(i, get(i));
            set(i, tmp);
        }
    }

    @Override
    public RelativePath clone() {
        return new RelativePath(new ArrayList<>(this));
    }
}
