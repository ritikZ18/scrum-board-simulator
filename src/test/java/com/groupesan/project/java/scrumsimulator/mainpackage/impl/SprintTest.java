package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SprintTest {
    private Sprint mySprint;

    @BeforeEach
    public void setup() {
        mySprint = SprintFactory.getSprintFactory().createNewSprint("testSprint", "testSprint", 5, "testSimulationID");
    }

    @Test
    public void testDecrementDay() {
        int duration = mySprint.getDaysRemaining();
        mySprint.decrementRemainingDays();
        assertEquals(duration - 1, mySprint.getDaysRemaining());
    }

    @Test
    public void testAddUserStory(){
        UserStory testUserStory = new UserStory("testUserStory", 1.0, 5.0);
        mySprint.addUserStory(testUserStory);
        assertEquals(true, mySprint.getUserStories().contains(testUserStory));
    }
}
