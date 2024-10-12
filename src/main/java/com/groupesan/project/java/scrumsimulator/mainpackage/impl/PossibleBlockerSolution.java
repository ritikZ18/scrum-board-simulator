package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumIdentifier;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumObject;
import com.groupesan.project.java.scrumsimulator.mainpackage.state.UserStoryUnselectedState;

public class PossibleBlockerSolution extends ScrumObject {

    private PossibleBlockerSolutionIdentifier id;

    private String name;

    private String solution;


    private void validateMandatoryField(String value, String fieldName){
        if(value==null || value.trim().isEmpty()){
            throw new IllegalArgumentException("Possible Blocker " + fieldName +" value cannot be empty");
        }
    }

    public PossibleBlockerSolution(String name) {
        validateMandatoryField(name,"Name");
        this.name = name;
        this.solution = "";
    }

    public PossibleBlockerSolution(String name, String solution) {
        validateMandatoryField(name,"Name");
        validateMandatoryField(solution,"solution");
        this.name = name;
        this.solution = solution;
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
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

}
