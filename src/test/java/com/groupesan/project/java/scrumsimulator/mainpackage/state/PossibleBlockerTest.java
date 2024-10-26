package com.groupesan.project.java.scrumsimulator.mainpackage.state;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlocker;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PossibleBlockerTest {
    @Test
    public void testCreationOfPossibleBlocker() {
        String name = "Sprint Cycle 3";
        String description = "Dependency issues for a field";
        String userStoryId = "US4";
        PossibleBlocker possibleBlocker = new PossibleBlocker(name, description, userStoryId);
        assertEquals(description, possibleBlocker.getDescription());
        assertEquals(name, possibleBlocker.getName());
        assertEquals(userStoryId, possibleBlocker.getUserStoryId());
    }

    @Test
    public void testThrowsExceptionForEmptyNameThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    new PossibleBlocker("", "Description", "US3");
                });

        assertTrue(exception.getMessage().contains("Name value cannot be empty"));
    }

    @Test
    public void testThrowsExceptionForEmptyUserStoryId() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    new PossibleBlocker("Name", "Description", "");
                });

        assertTrue(exception.getMessage().contains("User Story ID value cannot be empty"));
    }

    @Test
    public void testThrowsExceptionForEmptyDescription() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    new PossibleBlocker("Name", "", "US2");
                });

        assertTrue(exception.getMessage().contains("Description value cannot be empty"));
    }


}
