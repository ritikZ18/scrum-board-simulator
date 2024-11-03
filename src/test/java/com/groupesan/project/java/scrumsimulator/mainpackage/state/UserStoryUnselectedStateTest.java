package com.groupesan.project.java.scrumsimulator.mainpackage.state;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserStoryUnselectedStateTest {

    private UserStory userStory;
    private UserStoryUnselectedState unselectedState;

    @BeforeEach
    public void setUp() {
        userStory = new UserStory("Delete button needed", 5.0, 3.0);
        unselectedState = new UserStoryUnselectedState(userStory);
        userStory.changeState(unselectedState);
    }

    @Test
    public void testOnSelectFunctionality() {
        String result = unselectedState.onSelect();
        assertEquals("Selected", result);
        assertTrue(userStory.getUserStoryState() instanceof UserStorySelectedState,
                "state must change to UserStorySelectedState");
    }

}
