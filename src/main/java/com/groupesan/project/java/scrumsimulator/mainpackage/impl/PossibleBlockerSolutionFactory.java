package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

public class PossibleBlockerSolutionFactory {

    private static PossibleBlockerSolutionFactory possibleBlockerSolutionFactory;

    public static PossibleBlockerSolutionFactory getInstance() {
        if (possibleBlockerSolutionFactory == null) {
            possibleBlockerSolutionFactory = new PossibleBlockerSolutionFactory();
        }
        return possibleBlockerSolutionFactory;
    }

    private PossibleBlockerSolutionFactory() {}

    public PossibleBlockerSolution createNewPossibleBlockerSolution(String blockerId, String solution) {
        PossibleBlockerSolution newPB = new PossibleBlockerSolution(blockerId,solution);
        return newPB;
    }
}
