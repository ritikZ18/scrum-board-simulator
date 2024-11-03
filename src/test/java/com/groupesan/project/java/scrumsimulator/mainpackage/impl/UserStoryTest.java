package com.groupesan.project.java.scrumsimulator.mainpackage.impl;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumIdentifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserStoryTest {
    private UserStory myUserStory;

    @BeforeEach
    public void setup() {
        myUserStory =
                UserStoryFactory.getInstance()
                        .createNewUserStory("predefinedUS1", "description1", 1.0,3.0, "New");
    }

    @Test
    public void testUserStoryUnregistered1() {
        // modified from example code from Baeldung
        // https://www.baeldung.com/junit-assert-exception

        Exception exception =
                assertThrows(
                        IllegalStateException.class,
                        () -> {
                            ScrumIdentifier id = myUserStory.getId();
                        });

        String actualMessage = exception.getMessage();

        assertEquals(
                "This UserStory has not been registered and does not have an ID yet!",
                actualMessage);
    }

    /** Test case to ensure that toString handles the unregistered state */
    @Test
    public void testUserStoryUnregistered2() {
        String string = myUserStory.toString();

        assertEquals("(unregistered) - predefinedUS1", string);
    }

    @Test
    public void testUserStoryRegistered() {
        myUserStory.doRegister();

        ScrumIdentifier id = myUserStory.getId();

        assertNotNull(id);
    }

    /** Test case to ensure that exception is thrown when name is empty.**/
    @Test
    public void testNameEmptyThrowsException(){
        Exception exception =
                assertThrows(
                        IllegalArgumentException.class,
                        ()->{
                            UserStoryFactory.getInstance().createNewUserStory("",
                                    "to delete a button and show response",1.0,1.0,"New");
                        });
        assertEquals("User Story Name value cannot be empty",exception.getMessage());

    }

    /** Test case to ensure that exception is thrown when description is empty.**/
    @Test
    public void testDescriptionEmptyThrowsException(){
        Exception exception =
                assertThrows(
                        IllegalArgumentException.class,
                        ()->{
                            UserStoryFactory.getInstance().createNewUserStory("deletion of button",
                                    "",1.0,1.0,"New");
                        });
        assertEquals("User Story Description value cannot be empty",exception.getMessage());

    }


    /** Test case to ensure that business value is updated successfully. */
    @Test
    public void testBusinessValueUpdate() {
        myUserStory.setBusinessValue(5.0);
        myUserStory.setBusinessValue(8.0);
        assertEquals(8.0, myUserStory.getBusinessValue(), "Business value should be updated to 8.0");
    }

    /** Test case to ensure that setting business value to null is handled correctly. */
    @Test
    public void testBusinessValueUpdateToNull() {
        myUserStory.setBusinessValue(5.0);
        myUserStory.setBusinessValue(Double.NaN);
        assertTrue(Double.isNaN(myUserStory.getBusinessValue()), "Business value should be updated to NaN");
    }

    @Test
    public void testSetSprintView() {
        myUserStory.setIsSprintBacklog();
        assertEquals(true, myUserStory.getIsSprintBacklog());
    }

}