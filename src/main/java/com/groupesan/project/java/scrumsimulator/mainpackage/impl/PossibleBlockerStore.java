package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import java.util.ArrayList;
import java.util.List;

public class PossibleBlockerStore {

    private static PossibleBlockerStore possibleBlockerStore;

    /**
     * returns the shared instance of the UserStoryStore which contains all user stories in the
     * system.
     *
     * @return
     */
    public static PossibleBlockerStore getInstance() {
        if (possibleBlockerStore == null) {
            possibleBlockerStore = new PossibleBlockerStore();
        }
        return possibleBlockerStore;
    }

    private List<PossibleBlocker> possibleBlockers;

    private PossibleBlockerStore() {
        possibleBlockers = new ArrayList<PossibleBlocker>();
    }

    public void addPossibleBlocker(PossibleBlocker possibleBlocker) {
        possibleBlockers.add(possibleBlocker);
    }

    public List<PossibleBlocker> getPossibleBlockers() {
        return new ArrayList<>(possibleBlockers);
    }
}
