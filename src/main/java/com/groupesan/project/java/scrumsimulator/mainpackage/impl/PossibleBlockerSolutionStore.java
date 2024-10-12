package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import java.util.ArrayList;
import java.util.List;

public class PossibleBlockerSolutionStore {

    private static PossibleBlockerSolutionStore possibleBlockerSolutionStore;
    public static PossibleBlockerSolutionStore getInstance() {
        if (possibleBlockerSolutionStore == null) {
            possibleBlockerSolutionStore = new PossibleBlockerSolutionStore();
        }
        return possibleBlockerSolutionStore;
    }

    private List<PossibleBlockerSolution> possibleBlockerSolutions;

    private PossibleBlockerSolutionStore() {
        possibleBlockerSolutions = new ArrayList<PossibleBlockerSolution>();
    }

    public void addPossibleBlockerSolution(PossibleBlockerSolution PossibleBlockerSolution) {
        possibleBlockerSolutions.add(PossibleBlockerSolution);
    }

    public List<PossibleBlockerSolution> getPossibleBlockerSolutions() {
        return new ArrayList<>(possibleBlockerSolutions);
    }
}
