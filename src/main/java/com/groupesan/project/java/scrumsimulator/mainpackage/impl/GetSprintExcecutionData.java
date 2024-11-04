package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class GetSprintExcecutionData {
    
    private Sprint currentSprint;
    private int totalPoint = 0;
    private int completedPoints = 0;
    private boolean sprintCompleted;
    private SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

    public GetSprintExcecutionData(Sprint currentSprint) {
        this.currentSprint = currentSprint;
        this.sprintCompleted = getIsSprintSuccess();
    }

    public boolean getIsSprintSuccess() {
        for(UserStory userStory: currentSprint.getUserStories()) {
            totalPoint += userStory.getPointValue();
            if(userStory.getStatus().equals("completed")){
                completedPoints += userStory.getPointValue();
            }
        }
        return totalPoint == completedPoints;
    }

    public CategoryDataset generateDataSet() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int decrementPoints = totalPoint;
        if(sprintCompleted) {
            dataset.addValue(totalPoint, "User Stories", formatter.format(currentSprint.getSprintStartDate()));
            for(UserStory userStory: currentSprint.getUserStories()){
                String formattedCompletionDate = this.formatter.format(userStory.getCompletionDate());
                decrementPoints -= userStory.getPointValue();
                dataset.addValue(decrementPoints, "User Stories", formattedCompletionDate);
            }
        } else {
            for(int i = 0; i < currentSprint.getLength(); i++){
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(currentSprint.getSprintStartDate());
                calendar.add(Calendar.DAY_OF_MONTH, i);
                Date modifiedDate = calendar.getTime();

                String formattedDate = this.formatter.format(modifiedDate);
                dataset.addValue(totalPoint, "User Stories", formattedDate);
            }
        }

        return dataset;
    }



}
