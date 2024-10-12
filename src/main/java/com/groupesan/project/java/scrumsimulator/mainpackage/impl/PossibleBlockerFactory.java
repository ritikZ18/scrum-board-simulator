package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

public class PossibleBlockerFactory {

    private static PossibleBlockerFactory possibleBlockerFactory;

    public static PossibleBlockerFactory getInstance() {
        if (possibleBlockerFactory == null) {
            possibleBlockerFactory = new PossibleBlockerFactory();
        }
        return possibleBlockerFactory;
    }

    private PossibleBlockerFactory() {}

    public PossibleBlocker createNewPossibleBlocker(String name, String description) {
        PossibleBlocker newPB = new PossibleBlocker(name, description);
        return newPB;
    }
}
