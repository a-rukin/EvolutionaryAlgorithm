package com.rukin.core.entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Country {

    private final double[][] distances;
    private final List<Point> points;

    public Country(InputStream is) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            Map<Integer, Point> citiesCoordinates = reader.lines()
                    .map(line -> line.split(" "))
                    .collect(Collectors.toMap(
                            arr -> Integer.parseInt(arr[0]) - 1,
                            arr -> new Point(Double.parseDouble(arr[1]), Double.parseDouble(arr[2]))
                    ));

            this.points = new ArrayList<>(citiesCoordinates.values());
            this.distances = new double[citiesCoordinates.size()][citiesCoordinates.size()];
            for (int i = 0; i < this.distances.length; i++) {
                for (int j = i + 1; j < this.distances.length; j++) {
                    this.distances[i][j] = citiesCoordinates.get(i).distance(citiesCoordinates.get(j));
                    this.distances[j][i] = this.distances[i][j];
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Point> getPoints() {
        return points;
    }

    public int getDimentions() {
        return points.stream().findFirst().get().size();
    }

    public double getLowerBound(int dimen) {
        return points.stream().mapToDouble(p -> p.get(dimen)).min().orElse(-1.);
    }

    public double getUpperBound(int dimen) {
        return points.stream().mapToDouble(p -> p.get(dimen)).max().orElse(-1.);
    }

    public double distance(List<Integer> path) {
        double sum = 0.;
        for (int i = 0; i < path.size(); i++) {
            int a = path.get(i);
            int b = path.get((i + 1) % path.size());
            sum += distances[a][b];
        }
        return sum;
    }
}
