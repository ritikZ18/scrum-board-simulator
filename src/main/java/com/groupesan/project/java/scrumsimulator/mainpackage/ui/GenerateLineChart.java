package com.groupesan.project.java.scrumsimulator.mainpackage.ui;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.GetSprintExcecutionData;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Sprint;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;

import javax.swing.*;

public class GenerateLineChart extends JFrame implements BaseComponent {

    private Sprint currentSprint;

    public GenerateLineChart(Sprint sprint) {
        this.currentSprint = sprint;
        init();
    }

    public void init() {
        GetSprintExcecutionData excecutionData = new GetSprintExcecutionData(this.currentSprint);
        CategoryDataset dataset = excecutionData.generateDataSet();

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
    }
}
