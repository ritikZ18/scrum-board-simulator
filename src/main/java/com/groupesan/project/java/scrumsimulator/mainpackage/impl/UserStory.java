package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.Player;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumIdentifier;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumObject;
import com.groupesan.project.java.scrumsimulator.mainpackage.state.UserStoryState;
import com.groupesan.project.java.scrumsimulator.mainpackage.state.UserStoryUnselectedState;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class UserStory extends ScrumObject {
    private UserStoryIdentifier id;

    private String name;

    private String description;

    private Double pointValue;

    private Double businessValue;

    private UserStoryState state;

    private Player owner;

    private Boolean SprintBacklog;

    private String status = "New";

    private Date completionDate;

    // private ArrayList<Task> tasks;  TODO: implement tasks

    private void validateMandatoryField(String value, String fieldName){
        if(value==null || value.trim().isEmpty()){
            throw new IllegalArgumentException("User Story " + fieldName +" value cannot be empty");
        }
    }

    /**
     * Creates a user story. Leaves the description as an empty string.
     *
     * @param name the name for the user story
     * @param pointValue the point value for the story as a way of estimating required effort.
     * @throws IllegalArgumentException if name is empty
     */
    public UserStory(String name, Double pointValue, Double businessValue) {
        validateMandatoryField(name,"Name");
        this.name = name;
        this.description = "";
        this.pointValue = pointValue;
        this.businessValue = businessValue;
        this.state = new UserStoryUnselectedState(this);
        this.SprintBacklog = false;
    }

    /**
     * Creates a user story.
     *
     * @param name the name for the user story
     * @param description the description for the user story for better understanding of the
     *     requirements.
     * @param pointValue the point value for the story as a way of estimating required effort.
     * @throws IllegalArgumentException if name or description is empty
     */
    public UserStory(String name, String description, Double pointValue, Double businessValue) {
        validateMandatoryField(name,"Name");
        validateMandatoryField(description,"Description");
        this.name = name;
        this.description = description;
        this.pointValue = pointValue;
        this.businessValue = businessValue;
        this.state = new UserStoryUnselectedState(this);
        this.SprintBacklog = false;
    }

    protected void register() {
        this.id = new UserStoryIdentifier(ScrumIdentifierStoreSingleton.get().getNextId());
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

    /**
     * Get the point value of this UserStory
     *
     * @return the point value of this UserStory as a Double
     */
    public Double getPointValue() {
        return pointValue;
    }

    /**
     * Set the point value of the User Story to the specified value
     *
     * @param pointValue the point value as a Double. Usually an element of the fibonacci sequence.
     */
    public void setPointValue(Double pointValue) {
        this.pointValue = pointValue;
    }

    /**
     * Get the Business value of this UserStory
     *
     * @return the point value of this UserStory as a Double
     */

    public Double getBusinessValue() {
        return businessValue;
    }
    /**
     * Set the point value of the User Story to the specified value
     *
     * @param businessValue the point value as a Double. Usually an element of the fibonacci sequence.
     */
    public void setBusinessValue(Double businessValue) {
        this.businessValue = businessValue;
    }

    /**
     * [NOT IMPLEMENTED] return all child scrum objects of this object. Usually this would be tasks.
     *
     * @return a List containing all child ScrumObjects of this UserStory
     */
    public List<ScrumObject> getChildren() {
        return new ArrayList<>(); // TODO: implement tasks
    }

    /**
     * returns this user story's ID and name as text in the following format: US #3 - foo
     *
     * @return a string of the following format: "US #3 - foo"
     */
    @Override
    public String toString() {
        if (isRegistered()) {
            return this.getId().toString() + " - " + name;
        }
        return "(unregistered) - " + getName();
    }

    // State Management, need Player class to implement final selection logic
    /**
     * Change the state of this UserStory. Usually called when a Player picks up the task or
     * finishes it.
     *
     * @param state the new UserStoryState for this UserStory
     */
    public void changeState(UserStoryState state) {
        this.state = state;
    }

    /**
     * Get the UserStoryState of this UserStory. Unselected, completed, etc.
     *
     * @return a UserStoryState object containing the state for this UserStory
     */
    public UserStoryState getUserStoryState() {
        return state;
    }

    /**
     * Sets the owner of this UserStory to the specified player. This should be called whenever a
     * Player picks up this task and assigns themselves to it.
     *
     * @param player the Player object who is assigned to this UserStory
     */
    public void setOwner(Player player) {
        this.owner = player;
    }

    /**
     * Get the owner of this UserStory
     *
     * @return a Player object representing the owner of this UserStory
     */
    public Player getOwner() {
        return this.owner;
    }

    public void setIsSprintBacklog() {
        this.SprintBacklog = true;
    }

    public Boolean getIsSprintBacklog() {
        return this.SprintBacklog;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCompletionDate(Date date) {
        this.completionDate = date;
    }

    public Date getCompletionDate() {
        return this.completionDate;
    }
}
