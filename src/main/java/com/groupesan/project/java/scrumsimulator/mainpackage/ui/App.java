package com.groupesan.project.java.scrumsimulator.mainpackage.ui;

import com.formdev.flatlaf.FlatLightLaf;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.*;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.DemoPane;
import javax.swing.*;

public class App {
    public App() {}
    public void start() {
        this.loadTheme();
        SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        // Initialize User Stories in helper function now
                        initializeUserStories();
                        // Initializing possible blockers
                        initializePossibleBlockers();
                        //Initialiting Possible Blockers Solution
                        initializePossibleBlockerSolutions();

                        // Load DemoPane
                        DemoPane form = new DemoPane();
                        form.setVisible(true);
                    }
                });
    }

    private void initializeUserStories() {
        UserStory a =
                UserStoryFactory.getInstance()
                        .createNewUserStory("predefinedUS1", "description1", 1.0,3.0, "New");
        a.doRegister();
        UserStoryStore.getInstance().addUserStory(a);

        UserStory b =
                UserStoryFactory.getInstance()
                        .createNewUserStory("predefinedUS2", "description2", 2.0,1.0, "New");
        b.doRegister();
        UserStoryStore.getInstance().addUserStory(b);

        UserStory c =
                UserStoryFactory.getInstance()
                        .createNewUserStory("predefinedUS3", "description3", 3.0,5.0, "New");
        c.doRegister();
        UserStoryStore.getInstance().addUserStory(c);
    }

    private void initializePossibleBlockers() {
        PossibleBlocker a =
                PossibleBlockerFactory.getInstance()
                        .createNewPossibleBlocker("Bug", "Delete user story","US#1","unresolved");
        a.doRegister();
        PossibleBlockerStore.getInstance().addPossibleBlocker(a);

        PossibleBlocker b =
                PossibleBlockerFactory.getInstance()
                        .createNewPossibleBlocker("Bug", "Update point value","US#2","unresolved");
        b.doRegister();
        PossibleBlockerStore.getInstance().addPossibleBlocker(b);

    }


    private void initializePossibleBlockerSolutions() {
        PossibleBlockerSolution x =
                PossibleBlockerSolutionFactory.getInstance()
                        .createNewPossibleBlockerSolution("PB1","get knowledge from other teams");
        x.doRegister();
        PossibleBlockerSolutionStore.getInstance().addPossibleBlockerSolution(x);

        PossibleBlockerSolution y =
                PossibleBlockerSolutionFactory.getInstance()
                        .createNewPossibleBlockerSolution( "PB2","get information from geeks for geeks");
        y.doRegister();
        PossibleBlockerSolutionStore.getInstance().addPossibleBlockerSolution(y);

        PossibleBlockerSolution z =
                PossibleBlockerSolutionFactory.getInstance()
                        .createNewPossibleBlockerSolution("PB3","update knowledge from other teams");
        z.doRegister();
        PossibleBlockerSolutionStore.getInstance().addPossibleBlockerSolution(z);
    }



    private void loadTheme() {
        try {
            // TODO support setting theme from a configuration file
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
    }
}