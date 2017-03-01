package com.rukin.laboratory3;

import com.rukin.core.entity.AbsolutePath;
import com.rukin.core.entity.Country;
import com.rukin.core.entity.Point;
import com.rukin.core.entity.RelativePath;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Task extends Application {

    private static final int SCALE = 2;
    private static final int WIDTH = 300;
    private static final int HEIGHT = WIDTH;
    private static final int N = 100;
    private static final int EPOCH_NUMBER = 10000;
    private static final double CROSSOVER_PROBABILITY = 1;
    private static final double MUTATION_PROBABILITY = 0.4;

    public static void main(String[] args) {
        launch(args);
    }

    static List<Integer> getBestSolution(String filename) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Task.class.getClassLoader().getResourceAsStream(filename)))) {
            return reader.lines().map(line -> Integer.parseInt(line) - 1).collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void draw(Stage primaryStage, Country country, AbsolutePath foundPath, AbsolutePath minPath) {
        primaryStage.setTitle("Travelling salesman problem");
        Group root = new Group();
        Canvas canvas = new Canvas(WIDTH * SCALE, HEIGHT * SCALE);
        Point lowerBound = new Point(IntStream.range(0, country.getDimentions()).mapToDouble(country::getLowerBound).toArray());
        Point upperBound = new Point(IntStream.range(0, country.getDimentions()).mapToDouble(country::getUpperBound).toArray());
        Function<Point, Point> normalizeWithScale = point ->
                new Point(IntStream.range(0, country.getDimentions())
                        .mapToDouble(i -> (point.get(i) - lowerBound.get(i)) / (upperBound.get(i) - lowerBound.get(i)) * WIDTH * (SCALE - 0.5) + 50)
                        .toArray());

        drawPath(canvas.getGraphicsContext2D(), minPath.toPoints(country.getPoints(), normalizeWithScale), Color.BLUE, SCALE - 1, false);
        drawPath(canvas.getGraphicsContext2D(), foundPath.toPoints(country.getPoints(), normalizeWithScale), Color.RED, SCALE + 1, true);
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private static void drawPath(GraphicsContext context, List<Point> points, Color color, int width, boolean withText) {
        context.setFill(color);
        context.setStroke(color);
        context.setLineWidth(width);
        drawPoints(context, points, width, withText);
        drawLine(context, points);
    }

    private static void drawPoints(GraphicsContext context, List<Point> points, int width, boolean withText) {
        points.forEach(point -> {
            context.fillOval((int) point.get(0), (int) point.get(1), width, width);
            if (withText) {
                context.fillText(String.valueOf(points.indexOf(point)), (int) point.get(0), (int) point.get(1));
            }
        });
    }

    private static void drawLine(GraphicsContext context, List<Point> points) {
        for (int i = 0; i < points.size(); i++) {
            Point first = points.get(i);
            Point second = points.get((i + 1) % points.size());
            context.strokeLine((int) first.get(0), (int) first.get(1), (int) second.get(0), (int) second.get(1));
        }
    }

    @Override
    public void start(Stage primaryStage) {
        InputStream stream = Task.class.getClassLoader().getResourceAsStream("berlin52");
        Country country = new Country(stream);
        Function<RelativePath, Double> function = ordinalPath -> country.distance(new AbsolutePath(ordinalPath));

        List<Integer> minPath = getBestSolution("berlin52_best_solution");

        GA ga = new GA(country, N, EPOCH_NUMBER, CROSSOVER_PROBABILITY, MUTATION_PROBABILITY);
        RelativePath foundPath = ga.findMin(function);
        System.out.println("Found value = " + function.apply(foundPath));
        System.out.println("The best value = " + country.distance(minPath));

        draw(primaryStage, country, new AbsolutePath(foundPath), new AbsolutePath(minPath));
    }
}
