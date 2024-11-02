package com.groupesan.project.java.scrumsimulator.mainpackage.state;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlockerSolution;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PossibleBlockerSolutionTest {
    @Test
    public void testCreationOfPossibleBlockerSolution() {
        String name = "deletion issue";
        String solution = "Deletion issues for a field can be resolved using this resource.";
        PossibleBlockerSolution possibleBlockerSolution = new PossibleBlockerSolution(name, solution);
        assertEquals(name, possibleBlockerSolution.getName());
        assertEquals(solution, possibleBlockerSolution.getSolution());
    }

    @Test
    public void testThrowsExceptionForEmptyName() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    new PossibleBlockerSolution("", "Description");
                });

        assertTrue(exception.getMessage().contains("Name value cannot be empty"));
    }

    @Test
    public void testThrowsExceptionForEmptySolution() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    new PossibleBlockerSolution("Name", "");
                });

        System.out.println(exception.getMessage());
        assertTrue(exception.getMessage().contains("Possible Blocker solution value cannot be empty"));
    }


}
