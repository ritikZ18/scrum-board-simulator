package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.groupesan.project.java.scrumsimulator.mainpackage.state.SimulationStateManager;

class SimulationPanelTest {

    private SimulationStateManager simulationStateManager;
    private SimulationStore simulationStore;
    private String testSimulationId;

    @BeforeEach
    void setUp() {
        simulationStateManager = new SimulationStateManager();
        simulationStore = SimulationStore.getInstance();
        simulationStore.setUseInMemoryStore(true);
        simulationStore.clearSimulations();
        testSimulationId = "test-simulation-id";

        JSONObject testSimulation = new JSONObject();
        testSimulation.put("ID", testSimulationId);
        testSimulation.put("Name", "Test Simulation");
        testSimulation.put("Status", "New");
        simulationStore.addSimulation(testSimulation);

    }

    @Test
    void testSimulationStatusChangeToInProgressWhenStarted() {
        System.out.println("Initial status: " + simulationStore.getSimulationStatus(testSimulationId));
        // Start the simulation
        simulationStateManager.startSimulation(testSimulationId);
        System.out.println("After starting simulation");
        // Verify the status has changed to "In-Progress"
        String status = simulationStore.getSimulationStatus(testSimulationId);
        System.out.println("Final status: " + status);
        assertEquals("In-Progress", status, "Simulation status should be 'In-Progress' after starting");
    }

    @Test
    void testSimulationStatusChangeToDoneWhenStopped() {
        String initialStatus = simulationStore.getSimulationStatus(testSimulationId);
        System.out.println("Initial status: " + initialStatus);
        assertEquals("New", initialStatus, "Initial simulation status should be 'New'");

        simulationStateManager.startSimulation(testSimulationId);
        String runningStatus = simulationStore.getSimulationStatus(testSimulationId);
        System.out.println("Running status: " + runningStatus);
        assertEquals("In-Progress", runningStatus, "Simulation status should be 'In-Progress' after starting");

        simulationStateManager.stopSimulation();
        String finalStatus = simulationStore.getSimulationStatus(testSimulationId);
        System.out.println("Final status: " + finalStatus);
        assertEquals("Done", finalStatus, "Simulation status should be 'Done' after stopping");
    }

    @Test
    void testSimulationStatusRemainsSameIfNotStarted() {
        String initialStatus = simulationStore.getSimulationStatus(testSimulationId);
        assertEquals("New", initialStatus, "Initial simulation status should be 'New'");
        simulationStateManager.stopSimulation();
        String finalStatus = simulationStore.getSimulationStatus(testSimulationId);
        assertEquals("New", finalStatus, "Simulation status should remain 'New' if not started");
    }
}
