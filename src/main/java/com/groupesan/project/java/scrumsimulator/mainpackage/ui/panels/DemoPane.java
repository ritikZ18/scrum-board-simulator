package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.core.Player;
import com.groupesan.project.java.scrumsimulator.mainpackage.core.ScrumRole;
import com.groupesan.project.java.scrumsimulator.mainpackage.state.SimulationManager;
import com.groupesan.project.java.scrumsimulator.mainpackage.state.SimulationStateManager;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.FineTuneProbabilityWidget;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.utils.WizardManager;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SimulationStore;

import java.awt.event.WindowEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class DemoPane extends JFrame implements BaseComponent {
    private Player player = new Player("bob", new ScrumRole("Scrum Master"));
    private JPanel fineTunePanel;
    private JLabel currentRoleLabel;
    private SimulationSwitchRolePane simulationSwitchRolePane;

    public DemoPane() {
        this.init();
        player.doRegister();
        simulationSwitchRolePane = new SimulationSwitchRolePane(this);
    }
    /**
     * Initialization of Demo Pane. Demonstrates creation of User stories, Sprints, and Simulation
     * start.
     */
    public void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Demo");
        setSize(1000, 300);

        GridBagLayout myGridbagLayout = new GridBagLayout();
        JPanel myJpanel = new JPanel();
        myJpanel.setBorder(new EmptyBorder(1, 1, 1, 1));
        myJpanel.setLayout(myGridbagLayout);


        JButton sprintsButton = new JButton("Sprints");
        sprintsButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        SprintListPane form = new SprintListPane();
                        form.setVisible(true);
                    }
                });

        myJpanel.add(
                sprintsButton,
                new CustomConstraints(
                        1, 1, GridBagConstraints.WEST, 0, 0, GridBagConstraints.HORIZONTAL));




        SimulationStateManager simulationStateManager = new SimulationStateManager();
        SimulationPanel simulationPanel = new SimulationPanel(simulationStateManager);
        myJpanel.add(
                simulationPanel,
                new CustomConstraints(
                        0, 3, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));

//        myJpanel.add(
//                sprintsButton,
//                new CustomConstraints(
//                        0, 0, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));


        JButton userStoriesButton = new JButton("User Stories");
        userStoriesButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        UserStoryListPane form = new UserStoryListPane();
                        form.setVisible(true);
                    }
                });

        myJpanel.add(
                userStoriesButton,
                new CustomConstraints(
                        0, 1, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));

        JButton updateStoryStatusButton = new JButton("Update User Story Status");
        updateStoryStatusButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(SimulationStore.getInstance().getRunningSimulationSprints().length() == 0){
                            JOptionPane.showMessageDialog(null, "No sprints found for running simulation.");
                            return;
                        }

                        UpdateUserStoryPanel form = new UpdateUserStoryPanel();
                        form.setVisible(true);
                    }
                });

        myJpanel.add(
                updateStoryStatusButton,
                new CustomConstraints(
                        2, 3, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));

        // Blockers button for the sprint
        JButton possibleBlockersButton = new JButton("Possible Blockers");
        possibleBlockersButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        PossibleBlockersListPane form = new PossibleBlockersListPane();
                        form.setVisible(true);
                    }
                });

        myJpanel.add(
                possibleBlockersButton,
                new CustomConstraints(
                        0, 2, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));


//        // Simulation button for Demo
//        JButton simulationButton = new JButton("Add User");
//        simulationButton.addActionListener(
//                new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        SimulationPane simulationPane = new SimulationPane();
//                        simulationPane.setVisible(true);
//                    }
//                });
//
//        myJpanel.add(
//                simulationButton,
//                new CustomConstraints(
//                        7, 0, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));
//
//
//

        // Modify Simulation button
        JButton modifySimulationButton = new JButton("Create Simulation");
        modifySimulationButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        SimulationManager simulationManager = new SimulationManager();
                        ModifySimulationPane modifySimulationPane =
                                new ModifySimulationPane(simulationManager);
                        modifySimulationPane.setVisible(true);
                    }
                });

        // Add the button to the panel
        myJpanel.add(
                modifySimulationButton,
                new CustomConstraints(
                        1, 3, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));

        // *** Role Selection now through SimulationUI ***
        // JButton roleSelectionButton = new JButton("Role Selection");
        // roleSelectionButton.addActionListener(
        //         new ActionListener() {
        //             @Override
        //             public void actionPerformed(ActionEvent e) {
        //                 RoleSelectionPane roleSelectionPane = new RoleSelectionPane();
        //                 roleSelectionPane.setVisible(true);
        //             }
        //         });

        // myJpanel.add(
        //         roleSelectionButton,
        //         new CustomConstraints(
        //                 4, 0, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));
        // *** Role Selection now through SimulationUI ***



//        // Join Simulation button
//        JButton joinSimulationButton = new JButton("Join Simulation");
//        joinSimulationButton.addActionListener(
//                e -> {
//                    SimulationUI simulationUserInterface = new SimulationUI();
//                    simulationUserInterface.setVisible(true);
//                });
//
//        myJpanel.add(
//                joinSimulationButton,
//                new CustomConstraints(
//                        6, 0, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));
//



        // Simulation button for Demo
        JButton simulationSwitchRoleButton = new JButton("Switch Role");
        simulationSwitchRoleButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        SimulationSwitchRolePane feedbackPanelUI = new SimulationSwitchRolePane(DemoPane.this); //instantiate
                        feedbackPanelUI.setVisible(true);
                        feedbackPanelUI.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosed(WindowEvent e) {
                                updateVisibilityForFineTuneButton();
                                updateRoleLabel();
                            }
                        });
                    }
                });

        myJpanel.add(
                simulationSwitchRoleButton,
                new CustomConstraints(
                        0, 0, GridBagConstraints.WEST, 0.2, 0, GridBagConstraints.HORIZONTAL));


//
//        // New button for Variant Simulation UI
//        JButton variantSimulationUIButton = new JButton("Variant Simulation UI");
//        variantSimulationUIButton.addActionListener(
//                new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//                        VariantSimulationUI variantSimulationUI = new VariantSimulationUI();
//                        variantSimulationUI.setVisible(true);
//                    }
//                });
//
//        // Adding the button to the panel
//        myJpanel.add(
//                variantSimulationUIButton,
//                new CustomConstraints(
//                        3, 0, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));



        //  Populate Sprint Backlog button
        JButton PopulateSprintBacklogButton = new JButton("Populate Sprint Backlog");
        PopulateSprintBacklogButton.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!(player.getRole().getName() == ("Scrum Master"))) {
                        JOptionPane.showMessageDialog(null, "Invalid role. Valid role is Scrum Master.");
                    }
                    // Load PopulateSprintBacklogPane
                    PopulateSprintBacklogPane populateSprintBacklogPane = new PopulateSprintBacklogPane();
                    populateSprintBacklogPane.setVisible(true);
                }
            });

        myJpanel.add(PopulateSprintBacklogButton, new CustomConstraints(
            2, 1, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));

        // View Sprint Backlog Button
        JButton ViewSprintBacklogButton = new JButton("View Sprint Backlog");
        ViewSprintBacklogButton.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){

                    // Load ViewSprintBacklogPane
                    ViewSprintBacklogPane viewSprintBacklogPane = new ViewSprintBacklogPane();
                    viewSprintBacklogPane.setVisible(true);
                }
            }
        );
        myJpanel.add(ViewSprintBacklogButton, new CustomConstraints(
            3, 1, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));


        //        JButton SprintUIButton = new JButton("US Selection UI");
        //        SprintUIButton.addActionListener(
        //                new ActionListener() {
        //                    @Override
        //                    public void actionPerformed(ActionEvent e) {
        //                        // Load SprintUIPane
        //                        SprintUIPane sprintUIPane = new SprintUIPane(player);
        //                        sprintUIPane.setVisible(true);
        //                    }
        //                });
        //
        //        // Adding the button to the panel
        //        myJpanel.add(
        //                SprintUIButton,
        //                new CustomConstraints(
        //                        8, 0, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));
//



//        JButton openGeneralPageButton = new JButton("General Page");
//        openGeneralPageButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                WizardManager.get().showSimulationWizard();
//            }
//        });
//
//        myJpanel.add(
//                openGeneralPageButton,
//                new CustomConstraints(
//                        3, 3, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));



        JButton possibleBlockerSolutionsButton = new JButton("Possible Blocker Solutions");
        possibleBlockerSolutionsButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        PossibleBlockerSolutionsListPane form = new PossibleBlockerSolutionsListPane();
                        form.setVisible(true);
                    }
                });

        myJpanel.add(
                possibleBlockerSolutionsButton,
                new CustomConstraints(
                        1, 2, GridBagConstraints.WEST, 1.0, 1.0, GridBagConstraints.HORIZONTAL));


        // Create a panel for the Fine Tune Probability button
        fineTunePanel = new JPanel();
        fineTunePanel.setLayout(new GridBagLayout());
        JButton fineTuneProbabilityButton = new JButton("Fine Tune Probability");
        fineTuneProbabilityButton.addActionListener(e -> FineTuneProbabilityWidget.openFineTuneWindow());
        fineTunePanel.add(fineTuneProbabilityButton);
        fineTuneProbabilityButton.setEnabled(true);
        fineTunePanel.setVisible(false);
        // Add the fine tune panel to the main panel
        myJpanel.add(fineTunePanel,
                new CustomConstraints(
                        2, 2, GridBagConstraints.EAST, 0, 0, GridBagConstraints.HORIZONTAL));


        //Get Current Role
        currentRoleLabel = new JLabel("Current Role: " + SimulationSwitchRolePane.getCurrentRole());
        currentRoleLabel.setFont(new Font("Poppins", Font.BOLD, 13));
        myJpanel.add(currentRoleLabel, new CustomConstraints(1, 0, GridBagConstraints.NORTHWEST, 0, 0, GridBagConstraints.NONE));

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Confirmation dialog before closing
                int confirm = JOptionPane.showOptionDialog(
                        DemoPane.this,
                        "Are you sure you want to close the application?",
                        "Exit Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null, null, null);

                if (confirm == JOptionPane.YES_OPTION) {
                    // Call clearData() method for cleanup
                    SimulationStore.getInstance().clearSimulationData();
                    dispose();
                }
            }
        });

        getContentPane().add(myJpanel);
        setLocationRelativeTo(null);
        setVisible(true);

    }
    // Method to update visibility for  fine tune button based on the current role.
    private void updateVisibilityForFineTuneButton() {
        String currentRole = SimulationSwitchRolePane.getCurrentRole();
        boolean isScrumAdmin = "Scrum Administrator".equals(currentRole);
        fineTunePanel.setVisible(isScrumAdmin);
    }

    //Current Role
    protected  void updateRoleLabel() {
        currentRoleLabel.setText("Current Role: " + SimulationSwitchRolePane.getCurrentRole());
    }




}
