package com.rukin.core.ui;

import org.math.plot.Plot3DPanel;

import javax.swing.*;

public class Plot3DBuilder {

    private Plot3DPanel plot;

    public Plot3DBuilder() {
        this.plot = new Plot3DPanel("SOUTH");
    }

    public void addPlot(String plotName, double[] x, double[] y, double[][] z) {
        plot.addGridPlot(plotName, x, y, z);
    }

    public void addPlot(String plotName, double[] x, double[] y, double[] z) {
        plot.addLinePlot(plotName, x, y, z);
    }

    public void show() {
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setContentPane(plot);
        frame.setVisible(true);
    }
}
