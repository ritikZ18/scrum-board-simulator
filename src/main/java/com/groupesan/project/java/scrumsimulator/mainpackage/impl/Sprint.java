package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Calendar;

public class Sprint {
    private ArrayList<UserStory> userStories = new ArrayList<>();
    private String name;

    private String description;

    private int length;

    private int remainingDays;

    private int id;

    private String simulationID;

    private boolean sprintRunning = false;

    private boolean sprintCompleted = false;

    private Date sprintStartDate;

    private Date sprintEndDate;

    public Sprint(String name, String description, int length, int id, String simulationID) {
        this.name = name;
        this.description = description;
        this.length = length;
        this.remainingDays = length;
        this.id = id;
        this.simulationID = simulationID;
    }

    public void addUserStory(UserStory us) {
        userStories.add(us);
    }

    public List<UserStory> getUserStories() {
        return new ArrayList<>(userStories);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getLength() {
        return length;
    }

    public int getDaysRemaining() {
        return remainingDays;
    }

    public void decrementRemainingDays() {
        if (remainingDays > 0) remainingDays--;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        String header = "Sprint " + this.id + ": " + this.name + "\n";
        StringBuilder USes = new StringBuilder();

        for (UserStory us : userStories) {
            USes.append(us.toString()).append("\n");
        }
        return header + USes;
    }

    public String getSimulationID() {
        return this.simulationID;
    }

    public void setSprintRunning() {
        this.sprintRunning = true;
    }

    public boolean getSprintRunning() {
        return this.sprintRunning;
    }

    public void setSprintCompleted() {
        this.sprintCompleted = true;
    }

    public boolean getSprintCompleted() {
        return this.sprintCompleted;
    }

    public void setSprintStartDate(Date date) {
        this.sprintStartDate = date;
    }
    
    public Date getSprintStartDate() {
        return this.sprintStartDate;
    }

    public void setSprintEndDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.sprintStartDate);
        calendar.add(Calendar.DAY_OF_MONTH, this.length - 1);
        this.sprintEndDate = calendar.getTime();
        System.out.println(this.sprintEndDate);
    }

    public Date getSprintEndDate() {
        return this.sprintEndDate;
    }
}
