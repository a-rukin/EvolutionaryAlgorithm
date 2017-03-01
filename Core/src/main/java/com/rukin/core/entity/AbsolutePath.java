package com.rukin.core.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AbsolutePath extends ArrayList<Integer> {

    private static final Random random = new Random();

    public AbsolutePath(List<Integer> path) {
        super(path);
    }

    public AbsolutePath(RelativePath path) {
        List<Integer> cities = IntStream.range(0, path.size()).boxed().collect(Collectors.toList());
        addAll(path.stream().mapToInt(i -> i).mapToObj(cities::remove).collect(Collectors.toList()));
    }

    public void mutation() {
        int i = random.nextInt(size());
        int j = random.nextInt(size());

        int tmp = get(i);
        set(i, get(j));
        set(j, tmp);
    }

    public List<Point> toPoints(List<Point> points) {
        return toPoints(points, Function.identity());
    }

    public List<Point> toPoints(List<Point> points, Function<Point, Point> pointModify) {
        return stream().map(points::get).map(pointModify).collect(Collectors.toList());
    }
}
