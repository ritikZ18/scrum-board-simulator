package com.groupesan.project.java.scrumsimulator.mainpackage.ui;

import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

public class GenerateLineChart extends JFrame implements BaseComponent {

    private CategoryDataset dataset;

    public GenerateLineChart(CategoryDataset dataset) {
        this.dataset = dataset;
        init();
    }

    public void init() {
        JFreeChart chart = ChartFactory.createLineChart(
                "Burndown Chart",
                "Sprint Days",
                "Story Points",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        chart.setBackgroundPaint(java.awt.Color.white);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        setContentPane(chartPanel);

        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        saveChartToFile(chart);
    }

    private void saveChartToFile(JFreeChart chart) {
    File outputFile = new File("./stats/burndown_chart.png");
    try {
        BufferedImage image = chart.createBufferedImage(800, 600);
        ImageIO.write(image, "png", outputFile);
        System.out.println("Chart saved as " + outputFile.getAbsolutePath());
    } catch (IOException e) {
        System.err.println("Error saving chart: " + e.getMessage());
    }
}
}
