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

                        // Load DemoPane
                        DemoPane form = new DemoPane();
                        form.setVisible(true);
                    }
                });
    }

    private void initializeUserStories() {
        UserStory a =
                UserStoryFactory.getInstance()
                        .createNewUserStory("predefinedUS1", "description1", 1.0,3.0);
        a.doRegister();
        UserStoryStore.getInstance().addUserStory(a);

        UserStory b =
                UserStoryFactory.getInstance()
                        .createNewUserStory("predefinedUS2", "description2", 2.0,1.0);
        b.doRegister();
        UserStoryStore.getInstance().addUserStory(b);

        UserStory c =
                UserStoryFactory.getInstance()
                        .createNewUserStory("predefinedUS3", "description3", 3.0,5.0);
        c.doRegister();
        UserStoryStore.getInstance().addUserStory(c);
    }

    private void initializePossibleBlockers() {
        PossibleBlocker a =
                PossibleBlockerFactory.getInstance()
                        .createNewPossibleBlocker("Sprint cycle 1", "US3","US10","unresolved");
        a.doRegister();
        PossibleBlockerStore.getInstance().addPossibleBlocker(a);

        PossibleBlocker b =
                PossibleBlockerFactory.getInstance()
                        .createNewPossibleBlocker("Sprint cycle 2", "US4","US11","unresolved");
        b.doRegister();
        PossibleBlockerStore.getInstance().addPossibleBlocker(b);

    }

    private void initializePossibleBlockerSolutions() {
        PossibleBlockerSolution x =
                PossibleBlockerSolutionFactory.getInstance()
                        .createNewPossibleBlockerSolution("Dependencies on team mates", "");
        x.doRegister();
        PossibleBlockerSolutionStore.getInstance().addPossibleBlockerSolution(x);

        PossibleBlockerSolution y =
                PossibleBlockerSolutionFactory.getInstance()
                        .createNewPossibleBlockerSolution("Technical dependencies", "");
        y.doRegister();
        PossibleBlockerSolutionStore.getInstance().addPossibleBlockerSolution(y);

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