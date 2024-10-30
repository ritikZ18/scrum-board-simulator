package com.groupesan.project.java.scrumsimulator.mainpackage.impl;
import com.groupesan.project.java.scrumsimulator.mainpackage.state.UserStoryCompletedState;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class GetSprintExcecutionData {
    
    private Sprint currentSprint;

    public GetSprintExcecutionData(Sprint currentSprint) {
        this.currentSprint = currentSprint;
    }


    public CategoryDataset generateDataSet() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for(UserStory userStory: currentSprint.getUserStories()) {
            if(userStory.getUserStoryState() instanceof UserStoryCompletedState){
            dataset.addValue(userStory.getPointValue(), userStory.getName(), "");
            } else {
                break;
            }
        }

        return dataset;
    }

}
