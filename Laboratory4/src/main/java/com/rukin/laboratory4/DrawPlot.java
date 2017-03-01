package com.rukin.laboratory4;

import com.rukin.core.ui.Plot3DBuilder;
import com.rukin.laboratory4.antlr.FormulaLexer;
import com.rukin.laboratory4.antlr.FormulaParser;
import com.rukin.laboratory4.entity.Node;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class DrawPlot {

    private static double[] lowerBound = {-2, -2};
    private static double[] upperBound = {2, 2};
    private static double[] step = {0.01, 0.01};

    public static void main(String[] args) {
        String expr = "((((((((x0 - x1))^2 + (((((x1 - x0))^2 + ((x0 - x1) - ((x1 - x0) - (x1 - x1)))) - (x0 + x1)) * (x0)^2 - (x0 - x1))) - (x0 - x1)) * (x0)^2 - (((x1 - x0) - (x1 - x1)) - ((x0 - x1) * (x1)^2 - (x0 - x1)))) + (((((((x1 - x1) - (((x0 - x1) - (x1 - x1)) - (x0 - x0))) - (((x0 - x1) * (x0)^2 - (x0 - x1)) - ((x1 - x1) - (((x1 - x0) - (x0 - x1)) - (x0 - x0))))) - (((x0 - x1) * (x1)^2 - (x0 - x1)) - (x0 - x1))) - (((x1 - x0) - (x0 - x1)) - (x0 - x1))) + ((x0 - x0) - ((((x0)^2 + ((x1 - x0) - (x0 - x0))) - (((((x1 - x1))^2 + (((((x0 - x1) * (x0)^2)^2 + ((x1 - x0) - (((x0 - x1))^2 + ((((x0 - x1) - (((x1 - x1) * (x0)^2 - (x0 - x1)) - ((x1 - x1) - ((((((((x1 - x1) - (((x1 - x0) - (x0 - x1)) - (x0 - x0))) - (((x0 - x1) * (x0)^2 - (x0 - x1)) - ((((x0 - x1) * (x1)^2 - (x0 - x1)) - (x0 - x1)) - (((x1 - x0) - (x0 - x1)) - (x0 - x1) * (x1)^2)))) * (x0)^2 - (x0 - x1)) - (x0 - x1)) - ((x0 - x1) - (((x1 - x0) - (x0 - x1)) - (x0 - x1)))) - (x0 - x1)) - (x0 - x1))))) - (((x0 - x1) * (x0)^2 - (x0 - x1)) - ((x0 - x1) - (((x1 - x0) - (x0 - x1)) - (x0 - x1))))) - (((x1 - x0) - (x0 - x1)) - (x0 - x1)))))) - (x0 - x1)) - (x0 - x1))) - (x0 - x1)) * ((((x0 + x1) - (x1 - x1)) + (x1 - x1)))^2 - (x1 - x1))) - (((((x1 - 48) * (x1)^2 - ((x0)^2 + ((((x1 - x0) - ((x0 - x0) - (((((((x1 - x1) - ((x0 - x1) - (x0 - x0))) - (((x0 - x1) * (x0)^2 - (x0 - x1)) - ((x1 - x1) - (((x1 - x0) - (x0 - x1)) - (x0 - x0))))) - (((x0 - x1) * (x1)^2 - (x0 - x1)) - (x0 - x1))) - (((x1 - x0) - (x0 - x1)) - (x0 - x1))))^2 + ((x1 - x0) - (x1 - x0))))) - (x1 - x1)) - (x0 - x1)))) + ((((x1 - x1) - (((x1 - x0) - (x0 - x1)) - (x0 - x0))) - (((x0 - x1) * (x0)^2 - (x1 - x1)) - ((x1 - x1) - (((x1 - x0) - (x0 - x1)) - (x0 - x1))))) - (((((x1 + x0) - (x1 - x1)) + (x1 - x1)))^2 + (x0 - x1)))) - (((x0 - x1) * (x1)^2 - (x0 - x1)) - (((((x1 - x1) - (((x1 - x1) - (x0 - x1)) - (x0 - x0))) - (((x1 - x1) - (x0 - x1)) - ((x0 - x1) - (((x1 - x0) - (x0 - x1)) - (x0 - x1))))) - ((((x0 - x1) * (x1)^2 - (x0 - x0)) - (x0 - x1)) - (x0 - x1))) - (((x1 - x0) - (x0 - x1)) - (x0 - x1))))) - ((((x0 - x1) - (((x0 - x1) * (x1)^2 - (x0 - x1)) - (x0 - x1))))^2 + ((x0)^2 + ((x1 - x0) - ((((x0 - x1) - (((x0 - x1) * (x1)^2 - (x0 - x1)) - (((((x1 - x1) - (((x1 - x0) - (x0 - x1)) - (x0 - x0))) - (((x0 - x1) * (x0)^2 - (x1 - x1)) - ((x1 - x1) - (((x1 - x0) - (x0 - x1)) - (x0 - x1))))) - ((((x0 - x1) * (x1)^2 - (x0 - x1)) - (x0 - x1)) - (x0 - x1))) - (((x1 - x0) - (x0 - x1)) - (x0 - x1))))) - (((x0 - x1) * (x1)^2 - (x0 - x1)) - (x0 - x1))) - (((x1 - x0) - (((x0 - x1) - (((x0 - x1) * (x1)^2 - (x0 - x1)) - ((x0 - x1) - (((x1 - x0) - (x0 - x1)) - (x0 - x1))))) - (((x0 - x1) * (x0)^2 - (x0 - x1)) - ((x0 - x1) - (((x0 - x1) * (x0)^2 - (x0 - x1)) - (((((x1 - x0) - (((x1 - x0) - (x0 - x1)) - (x0 - x0))) - (((x0 - x1) * (x0)^2 - (x0 - x1)) - ((x1 - x1) - (((x1 - x0) - (x0 - x1)) - (x0 - x1))))) - (((x0 - x1) * (x1)^2 - (x0 - x1)) - (x0 - x1))) - (((x1 - x0) - (x0 - x1)) - (x0 - x1)))))))) - (x0 - x1))) * (x1)^2))))))) - (x0 - x1))))^2 + (((x0 - x1))^2 + ((x1 - x0) - (x0 - x1))) * ((x0 - 48) * (x1)^2)^2)";
        CharStream stream = new ANTLRInputStream(expr);
        FormulaLexer lexer = new FormulaLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        FormulaParser parser = new FormulaParser(tokens);
        Node root = parser.expression().expr;

        double[] x1 = IntStream.range(0, (int) ((upperBound[0] - lowerBound[0]) / step[0]))
                .mapToDouble(i -> lowerBound[0] + i * step[0])
                .toArray();
        double[] x2 = IntStream.range(0, (int) ((upperBound[1] - lowerBound[1]) / step[1]))
                .mapToDouble(i -> lowerBound[1] + i * step[1])
                .toArray();
        double[][] y1 = new double[x1.length][x2.length];
        double[][] y2 = new double[x1.length][x2.length];
        for (int i = 0; i < x1.length; i++) {
            for (int j = 0; j < x2.length; j++) {
                List<Double> values = Arrays.asList(x1[i], x2[j]);
                y1[i][j] = Task.FGOLD.apply(values);
                y2[i][j] = root.eval(values);
            }
        }
        Plot3DBuilder plotBuilder = new Plot3DBuilder();
        plotBuilder.addPlot("Real", x1, x2, y1);
        plotBuilder.addPlot("Found", x1, x2, y2);
        plotBuilder.show();
    }
}
