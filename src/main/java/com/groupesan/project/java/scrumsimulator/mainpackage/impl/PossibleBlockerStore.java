package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import java.util.*;

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

    public boolean hasSpike(String userStoryId) {
        return getSpikedUserStories().contains(userStoryId);
    }

    private List<PossibleBlocker> possibleBlockers;
    private List<String> spikedUserStories;
    private Map<String, String> spikedUserStoryStatus;

    private Set<String> successfulSpikes = new HashSet<>();//changes

    private PossibleBlockerStore() {
        possibleBlockers = new ArrayList<PossibleBlocker>();
        spikedUserStories = new ArrayList<>();
        spikedUserStoryStatus = new HashMap<>();
    }

    public void addPossibleBlocker(PossibleBlocker possibleBlocker) {
        possibleBlockers.add(possibleBlocker);
    }

    public List<PossibleBlocker> getPossibleBlockers() {
        return new ArrayList<>(possibleBlockers);
    }
    public void addSpikedUserStory(String userStoryId) {
        if (!spikedUserStories.contains(userStoryId)) {
            spikedUserStories.add(userStoryId);
            spikedUserStoryStatus.put(userStoryId, "Spike Added");
        }
    }

    public List<String> getSpikedUserStories() {
        return new ArrayList<>(spikedUserStories);
    }

    public String getSpikedStatus(String userStoryId) {
        return spikedUserStoryStatus.getOrDefault(userStoryId, "Spike Added");
    }

    public void updateSpikedStatus(String userStoryId, String status) {
        if (spikedUserStoryStatus.containsKey(userStoryId)) {
            spikedUserStoryStatus.put(userStoryId, status);
        }
    }


    public void markSpikeAsSuccessful(String userStoryId) {
        successfulSpikes.add(userStoryId);
    }

    public boolean isUserStoryEditable(String userStoryId) {
        return !spikedUserStories.contains(userStoryId) || successfulSpikes.contains(userStoryId);
    }

}
