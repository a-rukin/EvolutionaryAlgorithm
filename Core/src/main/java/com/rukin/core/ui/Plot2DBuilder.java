package com.rukin.core.ui;

import org.math.plot.Plot2DPanel;

import javax.swing.*;

public class Plot2DBuilder {

    private Plot2DPanel plot;

    public Plot2DBuilder() {
        this.plot = new Plot2DPanel("SOUTH");
    }

    public void addPlot(String plotName, double[] x, double[] y) {
        plot.addLinePlot(plotName, x, y);
    }

    public void addScatterPlot(String plotName, double[] x, double[] y) {
        plot.addScatterPlot(plotName, x, y);
    }

    public void show() {
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setContentPane(plot);
        frame.setVisible(true);
    }
}
