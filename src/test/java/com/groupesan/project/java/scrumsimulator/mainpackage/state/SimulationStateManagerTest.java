package com.groupesan.project.java.scrumsimulator.mainpackage.state;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONTokener;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import org.json.JSONObject;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SimulationStore;

public class SimulationStateManagerTest {
    private static final String JSON_FILE_PATH = ".src/main/resources/simulation.json";
    private SimulationManager simulationManager = new SimulationManager();
    private SimulationStateManager simulationStateManager = new SimulationStateManager();

    @Test
    public void testCreateSimulation() {
        simulationManager.createSimulation("testID123","testSimulation", "3", 5);
        assertEquals(true, SimulationStore.getInstance().getSimulationsIDs().contains("testID123"));
    }

    @Test
    public void testStartSimulation() {
        
        Boolean statusUpdate = false;
        simulationStateManager.startSimulation("testID123");

        try (FileInputStream fis = new FileInputStream(JSON_FILE_PATH)) {
            JSONTokener tokener = new JSONTokener(fis);
            JSONObject simObject = new JSONObject(tokener);

            for (int i = 0; i < simObject.getJSONArray("Simulations").length(); i++) {
                JSONObject simulation = simObject.getJSONArray("Simulations").getJSONObject(i);

                if (simulation.getString("Status").equals("In-Progress")) {
                    statusUpdate = true;
                    break;
                }
            }
            assertEquals(true, statusUpdate);
            
        } catch (IOException e) {
            System.out.println("testStartSimulation Failed");
        }

    }

    @Test
    public void testStopSimulation(){
        Boolean statusUpdate = false;
        simulationStateManager.stopSimulation();

        try (FileInputStream fis = new FileInputStream(JSON_FILE_PATH)) {
            JSONTokener tokener = new JSONTokener(fis);
            JSONObject simObject = new JSONObject(tokener);

            for (int i = 0; i < simObject.getJSONArray("Simulations").length(); i++) {
                JSONObject simulation = simObject.getJSONArray("Simulations").getJSONObject(i);
                System.out.println(simulation.getString("Status"));
                if (simulation.getString("Status").equals("Done")) {
                    statusUpdate = true;
                    break;
                }
            }
            assertEquals(true, statusUpdate);
            
        } catch (IOException e) {
            System.out.println("testStartSimulation Failed");
        }
    }

    @Test
    public void testclearSimulationData() {
        SimulationStore.getInstance().clearSimulationData();
        try (FileInputStream fis = new FileInputStream(JSON_FILE_PATH)) {
            JSONTokener tokener = new JSONTokener(fis);
            JSONObject simObject = new JSONObject(tokener);

            assertEquals(true, simObject.getJSONArray("Simulations").length() == 0);
            
        } catch (IOException e) {
            System.out.println("testStartSimulation Failed");
        }
    }


}
