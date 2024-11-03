package com.groupesan.project.java.scrumsimulator.mainpackage.state;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserStorySelectedStateTest {
    private UserStory userStory;
    private UserStorySelectedState selectedState;

    @BeforeEach
    public void setUp() {
        userStory = new UserStory("Delete button needed", 5.0, 3.0);
        selectedState = new UserStorySelectedState(userStory);
        userStory.changeState(selectedState);
    }

    @Test
    public void testOnSelectFunctionality() {
        String result = selectedState.onSelect();
        assertEquals("Unselected", result);
        assertTrue(userStory.getUserStoryState() instanceof UserStoryUnselectedState,
                "state must change to UserStoryUnSelectedState");
    }

    @Test
    public void testOnComplete() {
        String result = selectedState.onComplete();
        assertEquals("Completed", result);
        assertFalse(userStory.getUserStoryState() instanceof UserStorySelectedState);
    }

    @Test
    public void testOnDelete() {
        String result = selectedState.onDelete();
        assertEquals("Deleted", result, "onDelete should return 'Deleted'");
        assertTrue(userStory.getUserStoryState() instanceof UserStoryDeletedState,
                "State should change to UserStoryDeletedState after onDelete");
    }
}
