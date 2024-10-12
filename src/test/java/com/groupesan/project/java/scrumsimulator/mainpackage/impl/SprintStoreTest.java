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
        Object sprintsObj = sprintsField.get(sprintStore);
        if (sprintsObj instanceof List<?> sprintsList) {
            if (sprintsList.stream().allMatch(Sprint.class::isInstance)) {
                List<Sprint> sprintList = (List<Sprint>) sprintsList;
                sprintList.clear(); // Clear the list
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
        Sprint addedSprint = sprints.getFirst();
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
}


