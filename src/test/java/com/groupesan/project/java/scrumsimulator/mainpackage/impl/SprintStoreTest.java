package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SprintStoreTest {
    private SprintStore sprintStore;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        sprintStore = SprintStore.getInstance();
        clearSprintStore();
    }

    private void clearSprintStore() throws NoSuchFieldException, IllegalAccessException {
        Field sprintsField = SprintStore.class.getDeclaredField("sprints");
        sprintsField.setAccessible(true);
        List<?> sprintsList = (List<?>) sprintsField.get(sprintStore); // Use traditional cast
        if (sprintsList != null) {
            for (Object obj : sprintsList) {
                if (obj instanceof Sprint) {
                    //Clear list
                    ((List<Sprint>) sprintsList).clear();
                    break;
                }
            }
        }
    }

    @Test
    void testGetInstance() {
        SprintStore sampleInstance = SprintStore.getInstance();
        assertSame(sprintStore, sampleInstance, "should return same instance");
    }

    @Test
    void testAddSprint() {
        Sprint sprint = new Sprint("Sprint1", "Delete button", 14, 1);
        sprintStore.addSprint(sprint);
        List<Sprint> sprints = sprintStore.getSprints();
        assertEquals(1, sprints.size());
        Sprint addedSprint = sprints.getF();
        assertEquals("Sprint1", addedSprint.getName());
        assertEquals("Delete button", addedSprint.getDescription());
        assertEquals(14, addedSprint.getLength());
        assertEquals(1, addedSprint.getId());
    }

    @Test
    void testGetSprints() {
        Sprint sprint1 = new Sprint("Sprint1", "Delete button", 14, 1);
        Sprint sprint2 = new Sprint("Sprint2", "Add button", 7, 2);
        sprintStore.addSprint(sprint1);
        sprintStore.addSprint(sprint2);
        List<Sprint> sprints = sprintStore.getSprints();
        assertEquals(2, sprints.size());
        //The first sprint should be Sprint1
        assertEquals("Sprint1", sprints.get(0).getName());
        //The second sprint should be Sprint2
        assertEquals("Sprint2", sprints.get(1).getName());
    }

    @Test
    void testGetSprintByName() {
        Sprint sprint1 = new Sprint("Sprint1", "Delete button", 14, 1);
        Sprint sprint2 = new Sprint("Sprint2", "Add button", 7, 2);
        sprintStore.addSprint(sprint1);
        sprintStore.addSprint(sprint2);
        Sprint foundSprint = sprintStore.getSprintByName("Sprint1");
        assertNotNull(foundSprint, "sprint should be found");
        assertEquals("Sprint1", foundSprint.getName(), "sprint name should match");
        assertEquals("Delete button", foundSprint.getDescription(), "sprint description should match");
        assertEquals(14, foundSprint.getLength(), "sprint length should match");
        assertEquals(1, foundSprint.getId(), "sprint ID should match");
    }

    @Test
    void testSprintNotFoundReturnsNull() {
        Sprint notFoundSprint = sprintStore.getSprintByName("dummy non existing sprint");
        assertNull(notFoundSprint, "should return null for sprints not found");
    }

    @Test
    void testAddMultipleSprints() {
        Sprint sprint1 = new Sprint("Sprint1", "First sprint", 14, 1);
        Sprint sprint2 = new Sprint("Sprint2", "Second sprint", 14, 2);
        sprintStore.addSprint(sprint1);
        sprintStore.addSprint(sprint2);
        List<Sprint> sprints = sprintStore.getSprints();
        // Should add 2 sprints
        assertEquals(2, sprints.size());
        assertTrue(sprints.contains(sprint1));
        assertTrue(sprints.contains(sprint2));
    }

    @Test
    void testSprintStoreClearing() throws NoSuchFieldException, IllegalAccessException {
        Sprint sprint1 = new Sprint("Sprint1", "First sprint", 14, 1);
        Sprint sprint2 = new Sprint("Sprint2", "Second sprint", 14, 2);

        // Add some sprints
        sprintStore.addSprint(sprint1);
        sprintStore.addSprint(sprint2);

        // Clear the sprint store
        clearSprintStore();

        // After clearing, ensure the sprint store is empty
        List<Sprint> sprints = sprintStore.getSprints();
        assertEquals(0, sprints.size(), "Sprint store should be empty after clearing");
    }
}


