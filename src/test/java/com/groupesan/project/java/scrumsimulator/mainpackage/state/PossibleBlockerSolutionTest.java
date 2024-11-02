package com.groupesan.project.java.scrumsimulator.mainpackage.state;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlockerSolution;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PossibleBlockerSolutionTest {
    @Test
    public void testCreationOfPossibleBlockerSolution() {
        String solution = "Deletion issues for a field can be resolved using this resource.";
        PossibleBlockerSolution possibleBlockerSolution = new PossibleBlockerSolution("PB1",solution);
        assertEquals(solution, possibleBlockerSolution.getSolution());
    }

    @Test
    public void testThrowsExceptionForEmptySolution() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    new PossibleBlockerSolution("PB5","");
                });

        System.out.println(exception.getMessage());

        assertTrue(exception.getMessage().contains("Possible Blockersolution value cannot be empty"));
    }


}
