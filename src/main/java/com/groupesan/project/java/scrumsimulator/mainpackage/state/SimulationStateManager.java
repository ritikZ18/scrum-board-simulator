package com.groupesan.project.java.scrumsimulator.mainpackage.state;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import javax.swing.JOptionPane;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SimulationStore;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * SimulationStateManager manages the state of a simulation, including whether it is running and
 * saving its ID.
 */
public class SimulationStateManager {
    private boolean isRunning = false;
    private SimulationStore simulationStore;
    private static final String JSON_FILE_PATH = "src/main/resources/simulation.JSON";

    private String currentSimulationId;

    /** Simulation State manager. Not running by default. */
    public SimulationStateManager() {
        this.simulationStore = SimulationStore.getInstance();
    }

    /**
     * Returns the current state of the simulation.
     *
     * @return boolean running
     */
    public boolean isRunning() {
        return isRunning;
    }


    /** Method to set the simulation state to running. */
    public void startSimulation() {
        this.isRunning = true;
        // Add other logic for starting the simulation
    }

    public void startSimulation(String simulationId) {
        this.isRunning = true;
        this.currentSimulationId = simulationId;
        simulationStore.updateSimulationStatus(simulationId, "In-Progress");
    }

    /** Method to set the simulation state to not running. */
    public void stopSimulation() {
        if (this.isRunning && this.currentSimulationId != null) {
            simulationStore.updateSimulationStatus(this.currentSimulationId, "Done");
            this.isRunning = false;
            this.currentSimulationId = null;
        }
    }

    /**
     * Saves the details of a new simulation to a JSON file.
     *
     * @param simId The ID of the simulation.
     * @param simName The name of the simulation.
     * @param numberOfSprints The number of sprints in the simulation.
     */
    public static void saveNewSimulationDetails(
            String simId, String simName, String numberOfSprints, int sprintLength) {
        JSONObject simulationData = getSimulationData();
        if (simulationData == null) {
            simulationData = new JSONObject();
        }

        JSONObject newSimulation = new JSONObject();
        newSimulation.put("ID", simId);
        newSimulation.put("Name", simName);
        newSimulation.put("Status", "New");
        newSimulation.put("NumberOfSprints", numberOfSprints);
        newSimulation.put("Sprints", new JSONArray());
        newSimulation.put("Events", new JSONArray());
        newSimulation.put("Users", new JSONArray());
        newSimulation.put("SprintLength", sprintLength);

        JSONArray simulations = simulationData.optJSONArray("Simulations");
        if (simulations == null) {
            simulations = new JSONArray();
            simulationData.put("Simulations", simulations);
        }
        simulations.put(newSimulation);

        updateSimulationData(simulationData);
    }

    private static JSONObject getSimulationData() {
        try (FileInputStream fis = new FileInputStream(JSON_FILE_PATH)) {
            JSONTokener tokener = new JSONTokener(fis);
            return new JSONObject(tokener);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading from simulation.JSON");
            return null;
        }
    }

    private static void updateSimulationData(JSONObject updatedData) {
        try (OutputStreamWriter writer =
                new OutputStreamWriter(
                        new FileOutputStream(JSON_FILE_PATH), StandardCharsets.UTF_8)) {
            writer.write(updatedData.toString(4));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error writing to simulation.JSON");
        }
    }

    public JSONObject getSimulationIDs() {
        try (FileInputStream fis = new FileInputStream(JSON_FILE_PATH)) {
            JSONTokener tokener = new JSONTokener(fis);
            return new JSONObject(tokener);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading from simulation.JSON");
            return null;
        }
    }

}
