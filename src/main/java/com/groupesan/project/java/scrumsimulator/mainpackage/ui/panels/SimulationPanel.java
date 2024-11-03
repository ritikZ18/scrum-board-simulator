package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SimulationStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.state.SimulationStateManager;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class SimulationPanel extends JPanel implements BaseComponent {

    private SimulationStateManager simulationStateManager;
    private JButton startSimulationButton;
    private JButton stopSimulationButton;

    /** Simulation Panel Initialization. */
    protected SimulationPanel(SimulationStateManager simulationStateManager) {
        this.simulationStateManager = simulationStateManager;
        this.init();
    }

    @Override
    public void init(){
        startSimulationButton = new JButton("Start a Simulation");
        stopSimulationButton = new JButton("Stop Simulation");
        stopSimulationButton.setVisible(false);

        startSimulationButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        startSimulationByID();
                        JOptionPane.showMessageDialog(null, "Simulation started!");
                        updateButtonVisibility();
                    }
                });

        stopSimulationButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        simulationStateManager.stopSimulation();
                        JOptionPane.showMessageDialog(null, "Simulation stopped!");
                        updateButtonVisibility();
                    }
                });

        add(startSimulationButton);
        add(stopSimulationButton);
    }

    private void updateButtonVisibility() {
        // Show/hide buttons based on the simulation state
        if (simulationStateManager.isRunning()) {
            stopSimulationButton.setVisible(true);
            startSimulationButton.setVisible(false);
        } else {
            stopSimulationButton.setVisible(false);
            startSimulationButton.setVisible(true);
        }
    }

    private void startSimulationByID() {
        SimulationStore simulationStore = SimulationStore.getInstance();
        ArrayList<String> simulationIds = simulationStore.getSimulationsIDs();  // Retrieve the IDs from the store
        if (simulationIds != null && !simulationIds.isEmpty()) {
            JComboBox<String> simulationIdDropdown = new JComboBox<>(simulationIds.toArray(new String[0]));
            int response = JOptionPane.showConfirmDialog(this, simulationIdDropdown, "Select a Simulation ID:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (response == JOptionPane.OK_OPTION) {
                String selectedSimulationId = (String) simulationIdDropdown.getSelectedItem();
                simulationStateManager.startSimulation(selectedSimulationId);
                JOptionPane.showMessageDialog(this, "Selected Simulation ID: " + selectedSimulationId);
                updateButtonVisibility();
                this.revalidate();
                this.repaint();
            }
        } else {
            JOptionPane.showMessageDialog(this, "No simulations available.", "No Data", JOptionPane.ERROR_MESSAGE);
        }
    }
}
