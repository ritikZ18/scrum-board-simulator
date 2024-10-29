package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumIdentifier;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumObject;
import com.groupesan.project.java.scrumsimulator.mainpackage.state.UserStoryUnselectedState;

public class PossibleBlocker extends ScrumObject {

    private PossibleBlockerIdentifier id;

    private String name;

    private String description;

    private String userStoryId;

    private String status;


    private void validateMandatoryField(String value, String fieldName){
        if(value==null || value.trim().isEmpty()){
            throw new IllegalArgumentException("Possible Blocker " + fieldName +" value cannot be empty");
        }
    }

    /**
     * Creates a user story. Leaves the description as an empty string.
     *
     * @param name the name for the user story
     * @throws IllegalArgumentException if name is empty
     */
    public PossibleBlocker(String name) {
        validateMandatoryField(name,"Name");
        this.name = name;
        this.description = "";
    }

    /**
     * Creates a user story.
     *
     * @param name the name for the user story
     * @param description the description for the user story for better understanding of the
     *     requirements.
     * @throws IllegalArgumentException if name or description is empty
     */
    public PossibleBlocker(String name, String description) {
        validateMandatoryField(name,"Name");
        validateMandatoryField(description,"Description");
        this.name = name;
        this.description = description;
    }

    /**
     *
     * @param name the name for the user story
     * @param description the description for the user story for better understanding of the
     *     requirements.
     * @param userStoryId the user story ID for the user story
     * @throws IllegalArgumentException if name or description or userStoryId is empty
     */
    public PossibleBlocker(String name, String description, String userStoryId,String status) {
        validateMandatoryField(name, "Name");
        validateMandatoryField(description, "Description");
        validateMandatoryField(userStoryId, "User Story ID");
        validateMandatoryField(status, "Status");
        this.name = name;
        this.description = description;
        this.userStoryId = userStoryId;
        this.status = status;
    }

    public String getUserStoryId() {
        return userStoryId;
    }

    public void setUserStoryId(String userStoryId) {
        this.userStoryId = userStoryId;
    }

    protected void register() {
        this.id = new PossibleBlockerIdentifier(ScrumIdentifierStoreSingleton.get().getNextId());
    }

    /**
     * Gets the identifier object for this UserStory. **This will throw an exception if register()
     * has not been called yet.**
     *
     * @return The ScrumIdentifier for this user story
     */
    public ScrumIdentifier getId() {
        if (!isRegistered()) {
            throw new IllegalStateException(
                    "This UserStory has not been registered and does not have an ID yet!");
        }
        return id;
    }

    /**
     * Get the name for this UserStory
     *
     * @return the name of this UserStory as a string
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Sets the name of the User Story to the specified string
     *
     * @param name the string to set the name to
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the description text of this UserStory
     *
     * @return the description of this UserStory as a string.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the Description of the User Story to the specified string
     *
     * @param description the string to set the description to
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
