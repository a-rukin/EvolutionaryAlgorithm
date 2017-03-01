package com.rukin.core.entity;

import com.rukin.core.util.Numbers;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Point {

    private final double[] dimensValue;

    public Point(double... dimensValue) {
        this.dimensValue = dimensValue;
    }

    public static Point getInstance(Point lowerBound, Point upperBound) {
        return new Point(IntStream.range(0, lowerBound.size())
                .mapToDouble(i -> Numbers.random(lowerBound.get(i), upperBound.get(i)))
                .toArray());
    }

    public static Comparator<Point> getComparator(Point p) {
        return (p1, p2) -> Double.compare(p1.cos(p), p2.cos(p));
    }

    public void set(int dimenIndex, double dimenValue) {
        dimensValue[dimenIndex] = dimenValue;
    }

    public double get(int dimenIndex) {
        return dimensValue[dimenIndex];
    }

    public int size() {
        return dimensValue.length;
    }

    public double distance(Point p) {
        assert size() == p.size();
        return Math.sqrt(IntStream.range(0, size())
                .mapToDouble(i -> Math.pow(get(i) - p.get(i), 2))
                .sum());
    }

    private double cos(Point p) {
        assert p.size() == 2;
        return (get(0) - p.get(0)) / distance(p);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Arrays.equals(dimensValue, point.dimensValue);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(dimensValue);
    }

    @Override
    public String toString() {
        return Arrays.stream(dimensValue).mapToObj(String::valueOf).collect(Collectors.joining(", ", "(", ")"));
    }
}
