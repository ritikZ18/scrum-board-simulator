package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumIdentifier;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumObject;

public class PossibleBlockerSolution extends ScrumObject {

    private PossibleBlockerSolutionIdentifier id;


    private String solution;
    private String blockerId;

    private int probability;

    private void validateMandatoryField(String value, String fieldName){
        if(value==null || value.trim().isEmpty()){
            throw new IllegalArgumentException("Possible Blocker" + fieldName +" value cannot be empty");
        }
    }

    public PossibleBlockerSolution( String solution) {
        validateMandatoryField(solution,"solution");
        this.solution = solution;
    }

    public PossibleBlockerSolution( String blockerId, String solution) {
        validateMandatoryField(solution,"solution");
        this.solution = solution;
        this.blockerId = blockerId;
    }

    protected void register() {
        this.id = new PossibleBlockerSolutionIdentifier(ScrumIdentifierStoreSingleton.get().getNextId());
    }

    public ScrumIdentifier getId() {
        if (!isRegistered()) {
            throw new IllegalStateException(
                    "This UserStory has not been registered and does not have an ID yet!");
        }
        return id;
    }

    public String getBlockerId() {
        return blockerId;
    }

    @Override
    public String toString() {
        return solution;
    }

    public String getSolution() {
        return solution;
    }
    @Override
    public String getName() {
        return "";
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public int getSolutionProbability(){
        return probability;
    }

    public void setProbability(int probability){
        this.probability = Integer.parseInt(String.valueOf(probability));
    }


}
