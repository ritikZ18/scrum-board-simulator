package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import java.io.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import java.nio.charset.StandardCharsets;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class SimulationStore {
    
    private ArrayList<String> simulationIds = new ArrayList<>();
    private static final String JSON_FILE_PATH = "./src/main/resources/simulation.JSON";
    private int sprintLength = 0;
    private int numberOfSprints = 0;
    private int currentNumberOfSprints = 0;
    private JSONArray runningSimulationSprints;

    private static SimulationStore simulationStore;

    private JSONArray simulations;
    //private JSONObject obj;

    public SimulationStore() {
        loadSimulationsFromFile();
    }

    public static SimulationStore getInstance() {
        if (simulationStore == null) {
            simulationStore = new SimulationStore();
        }
        return simulationStore;
    }

    private JSONObject getSimulationData() {
        try (FileInputStream fis = new FileInputStream(JSON_FILE_PATH);){
            JSONTokener tokener = new JSONTokener(fis);
            return new JSONObject(tokener);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }   
    }



    public ArrayList<String> getSimulationsIDs() {
        JSONObject obj = getSimulationData();
        JSONArray simulations = obj.getJSONArray("Simulations");
        simulationIds.clear();
        if (simulations != null) {
            
            for (int i = 0; i < simulations.length(); i++) {
                JSONObject simulation = simulations.getJSONObject(i);
                simulationIds.add(simulation.getString("ID"));
            }
        }
        return simulationIds;
    }

    public void addSprint(Sprint sprint, String simulationID) {
        JSONObject obj = getSimulationData();
        JSONArray simulations = obj.getJSONArray("Simulations");
        if (simulations != null) {
            for(int i = 0; i < simulations.length(); i++) {
                if (simulations.getJSONObject(i).getString("ID").equals(simulationID)) {
                    simulations.getJSONObject(i).getJSONArray("Sprints").put(sprint.getName());
                }
            }
        }


        saveSimulationsToFile(obj);
    }


    public int getSprintLengthBySimId(String simulationID) {
        JSONObject obj = getSimulationData();
        simulations = obj.getJSONArray("Simulations");
        if (simulations != null) {
            for(int i = 0; i < simulations.length(); i++) {
                if (simulations.getJSONObject(i).getString("ID").equals(simulationID)) {
                    sprintLength = simulations.getJSONObject(i).getInt("SprintLength");
                    break;
                }
            }
        }
        return sprintLength;
    }

    public int getNumberofSprintsById(String simulationID) {
        JSONObject obj = getSimulationData();
        simulations = obj.getJSONArray("Simulations");
        if (simulations != null) {
            for(int i = 0; i < simulations.length(); i++) {
                if (simulations.getJSONObject(i).getString("ID").equals(simulationID)) {
                    numberOfSprints = simulations.getJSONObject(i).getInt("NumberOfSprints");
                    break;
                }
            }
        }
        return numberOfSprints;
    }

    public int getCurrentSprintSize(String simulationId) {
        JSONObject obj = getSimulationData();
        simulations = obj.getJSONArray("Simulations");
        if (simulations != null) {
            for(int i = 0; i < simulations.length(); i++) {
                if (simulations.getJSONObject(i).getString("ID").equals(simulationId)) {
                    currentNumberOfSprints = simulations.getJSONObject(i).getJSONArray("Sprints").length();
                    break;
                }
            }
        }
        return currentNumberOfSprints;
    }

    public void updateSimulationStatus(String simulationId, String newStatus) {
        JSONObject obj = getSimulationData();
        simulations = obj.getJSONArray("Simulations");
        for (int i = 0; i < simulations.length(); i++) {
            JSONObject simulation = simulations.getJSONObject(i);
            if (simulation.getString("ID").equals(simulationId)) {
                simulation.put("Status", newStatus);
                break;
            }
        }
        saveSimulationsToFile(obj);
    }

    private void saveSimulationsToFile(JSONObject obj) {
        try (OutputStreamWriter writer =
                        new OutputStreamWriter(
                                new FileOutputStream(JSON_FILE_PATH), StandardCharsets.UTF_8)) {
                    writer.write(obj.toString(4));
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Error writing to simulation.JSON");
                }
    }

    private void loadSimulationsFromFile() {
        JSONObject obj = getSimulationData();
        if (obj != null && obj.has("Simulations")) {
            simulations = obj.getJSONArray("Simulations");
        } else {
            simulations = new JSONArray();
        }
    }

    public JSONArray getRunningSimulationSprints() {
        // JSONArray runningSimulationSprints = new JSONArray();
        JSONObject obj = getSimulationData();
        simulations = obj.getJSONArray("Simulations");
        if (simulations != null) {
            for(int i = 0; i < simulations.length(); i++) {
                if (simulations.getJSONObject(i).getString("Status").equals("In-Progress")) {
                    if(simulations.getJSONObject(i).getJSONArray("Sprints") != null){
                    runningSimulationSprints = simulations.getJSONObject(i).getJSONArray("Sprints");
                    break;
                }
                }
            }
        }
        return runningSimulationSprints;
    }

    /*public String getSimulationStatus(String simulationId) {
        for (int i = 0; i < simulations.length(); i++) {
            JSONObject simulation = simulations.getJSONObject(i);
            if (simulation.getString("ID").equals(simulationId)) {
                return simulation.getString("Status");
            }
        }
        return null; // or throw an exception if the simulation is not found
    }
    public void addSimulation(JSONObject simulation) {
        JSONObject obj = getSimulationData();
        simulations = obj.getJSONArray("Simulations");
        if (simulations == null) {
            simulations = new JSONArray();
        }
        simulations.put(simulation);
        saveSimulationsToFile(obj);
    }*/

    public void clearSimulationData(){
        JSONObject obj = getSimulationData();
        simulations = obj.getJSONArray("Simulations");
        simulations.clear();
    }
}

