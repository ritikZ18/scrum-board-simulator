package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UserStoryStoreTest {

    private UserStoryStore userStoryStore;

    @BeforeEach
    public void setUp() {

        userStoryStore =  UserStoryStore.getInstance();

    }




    @Test
    public void testGetUserStories() {
        UserStory userStory1 = new UserStory("Feature A", 3.0, 7.0);
        UserStory userStory2 = new UserStory("Feature B", 2.0, 7.0);
        userStoryStore.addUserStory(userStory1);
        userStoryStore.addUserStory(userStory2);

        List<UserStory> stories = userStoryStore.getUserStories();
        assertEquals(2, stories.size(), "User story list must contain 2 stories.");
        assertTrue(stories.contains(userStory1), "User story list must contain Feature A.");
        assertTrue(stories.contains(userStory2), "User story list must contain Feature B.");
    }

    @Test
    public void testRemoveUserStory() {
        UserStory userStory = new UserStory("Remove Feature", 1.0, 1.0);
        userStoryStore.addUserStory(userStory);

        assertTrue(userStoryStore.removeUserStory(userStory), "return true removing existing user story.");
        assertFalse(userStoryStore.getUserStories().contains(userStory), "User story list must not contain removed user story.");
    }

    @Test
    public void testRemoveNonExistentUserStory() {
        UserStory userStory = new UserStory("Feature does not exist", 1.0, 1.0);
        assertFalse(userStoryStore.removeUserStory(userStory), " return false when remove non-existent user story.");

    }

}
