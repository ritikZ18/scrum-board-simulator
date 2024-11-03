package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlocker;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlockerStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStoryStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.FineTuneProbabilityWidget;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.PossibleBlockerWidget;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class PossibleBlockersListPane extends JFrame implements BaseComponent {
    private List<PossibleBlockerWidget> widgets = new ArrayList<>();
    private JComboBox<String> userStoriesDropDown;
    private FineTuneProbabilityWidget fineTuneProbabilityWidget;
    private Set<String> needsMoreResources = new HashSet<>();

    public PossibleBlockersListPane() {
        this.fineTuneProbabilityWidget = new FineTuneProbabilityWidget();
        this.init();
    }

    public void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Blockers list");
        setSize(700, 400);

        GridBagLayout myGridbagLayout = new GridBagLayout();
        JPanel myJpanel = new JPanel();
        myJpanel.setBorder(new EmptyBorder(20, 10, 20, 10));
        myJpanel.setLayout(myGridbagLayout);

        // Spike Panel
        GridBagLayout spikeGridbagLayout = new GridBagLayout();
        JPanel spikePanel = new JPanel();
        spikePanel.setBorder(new EmptyBorder(20, 10, 20, 10));
        spikePanel.setLayout(spikeGridbagLayout);

        for (PossibleBlocker possibleBlocker : PossibleBlockerStore.getInstance().getPossibleBlockers()) {
            widgets.add(new PossibleBlockerWidget(possibleBlocker, fineTuneProbabilityWidget));
        }

        JPanel subPanel = new JPanel();
        subPanel.setLayout(new GridBagLayout());
        int i = 0;
        for (PossibleBlockerWidget widget : widgets) {
            subPanel.add(
                    widget,
                    new CustomConstraints(
                            0,
                            i++,
                            GridBagConstraints.WEST,
                            1.0,
                            0.1,
                            GridBagConstraints.HORIZONTAL));
        }

        myJpanel.add(
                new JScrollPane(subPanel),
                new CustomConstraints(
                        0, 0, GridBagConstraints.WEST, 1.0, 0.8, GridBagConstraints.HORIZONTAL));

        JButton newPossibleBlockerButton = new JButton("New Blocker");
        newPossibleBlockerButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        NewPossibleBlockerForm form = new NewPossibleBlockerForm();
                        form.setVisible(true);

                        form.addWindowListener(
                                new WindowAdapter() {
                                    public void windowClosed(
                                            WindowEvent windowEvent) {
                                        PossibleBlocker possibleBlocker = form.getPossibleBlockerObject();
                                        if (possibleBlocker != null) {
                                            PossibleBlockerStore.getInstance().addPossibleBlocker(possibleBlocker);
                                            addPossibleBlockerWidget(possibleBlocker, fineTuneProbabilityWidget);
                                        }
                                    }
                                });
                    }

                    private void addPossibleBlockerWidget(PossibleBlocker possibleBlocker, FineTuneProbabilityWidget fineTuneProbabilityWidget) {
                        PossibleBlockerWidget widget = new PossibleBlockerWidget(possibleBlocker, fineTuneProbabilityWidget);
                        widgets.add(widget);
                        int idx = widgets.size() - 1;
                        subPanel.add(
                                widget,
                                new CustomConstraints(
                                        0,
                                        idx,
                                        GridBagConstraints.WEST,
                                        1.0,
                                        0.1,
                                        GridBagConstraints.HORIZONTAL));
                        subPanel.revalidate();
                        subPanel.repaint();
                    }
                });
        myJpanel.add(
                newPossibleBlockerButton,
                new CustomConstraints(
                        0, 1, GridBagConstraints.WEST, 1.0, 0.2, GridBagConstraints.HORIZONTAL));

        // Adding dropdown for selecting user stories
        userStoriesDropDown = new JComboBox<>(getUserStories());
        myJpanel.add(
                userStoriesDropDown,
                new CustomConstraints(
                        0, 2, GridBagConstraints.WEST, 1.0, 0.1, GridBagConstraints.HORIZONTAL));

        // Adding "Add Spike" button
        JButton addSpikeButton = new JButton("Add Spike");
        addSpikeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedUserStory = (String) userStoriesDropDown.getSelectedItem();
                if (selectedUserStory != null && !selectedUserStory.isEmpty()) {
                    addSpike(selectedUserStory);
                    displaySpikedPanel(spikePanel);
                } else {
                    JOptionPane.showMessageDialog(
                            PossibleBlockersListPane.this,
                            "Please select a user story",
                            "No User Story Selected",
                            JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        });
        spikePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEtchedBorder(),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        myJpanel.add(
                addSpikeButton,
                new CustomConstraints(
                        1, 2, GridBagConstraints.WEST, 1.0, 0.1, GridBagConstraints.HORIZONTAL));
        myJpanel.add(
                spikePanel,
                new CustomConstraints(
                        0, 3, GridBagConstraints.WEST, 1.0, 0.8, GridBagConstraints.HORIZONTAL));
        add(myJpanel);
        displaySpikedPanel(spikePanel);
    }

    // Add spike method implementation
    private void addSpike(String userStoryId) {
        if (!PossibleBlockerStore.getInstance().getSpikedUserStories().contains(userStoryId)) {
            PossibleBlockerStore.getInstance().addSpikedUserStory(userStoryId);
            showSpikeAdded(userStoryId);
            showManagementDetailsConfirmation(userStoryId, null, null, true);
        } else {
            JOptionPane.showMessageDialog(this, "Already added", "Duplicated Spike", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showSpikeAdded(String userStoryId) {
        JOptionPane.showMessageDialog(
                this,
                "Spike added for user story " + userStoryId,
                "Spike Added",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void showManagementDetailsConfirmation(String userStoryId, JButton allocateResourcesButton, JButton spikeSuccessButton, boolean initialStatus) {
        String message = "Management team has provided details";
        int option = JOptionPane.showConfirmDialog(
                this,
                message,
                "Management Details",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE
        );

        if (option == JOptionPane.NO_OPTION) {
            PossibleBlockerStore.getInstance().updateSpikedStatus(userStoryId, "Spike Fail");
            if (allocateResourcesButton != null) allocateResourcesButton.setEnabled(false);
            if (spikeSuccessButton != null) spikeSuccessButton.setEnabled(false);
        } else if (option == JOptionPane.YES_OPTION) {
            if (initialStatus) {
                PossibleBlockerStore.getInstance().updateSpikedStatus(userStoryId, "Spike Added");
            } else {
                PossibleBlockerStore.getInstance().updateSpikedStatus(userStoryId, "Resources Requested");
            }

        }
    }
    private void displaySpikedPanel(JPanel spikePanel) {
        spikePanel.removeAll();

        List<String> spikedStories = PossibleBlockerStore.getInstance().getSpikedUserStories();
        int row = 0;

        for (String userStoryId : spikedStories) {
            JLabel userStoryLabel = new JLabel(userStoryId);
            spikePanel.add(userStoryLabel, new CustomConstraints(0, row, GridBagConstraints.WEST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

            String currentStatus = PossibleBlockerStore.getInstance().getSpikedStatus(userStoryId);
            JLabel statusLabel = new JLabel(currentStatus);
            spikePanel.add(statusLabel, new CustomConstraints(1, row, GridBagConstraints.WEST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

            JButton allocateResourcesButton = new JButton("Allocate More Resources");
            JButton spikeSuccessButton = new JButton("Spike Success");

            allocateResourcesButton.setEnabled(needsMoreResources.contains(userStoryId) || "Spike Added".equals(currentStatus));

            allocateResourcesButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    PossibleBlockerStore.getInstance().updateSpikedStatus(userStoryId, "Resources Requested");
                    statusLabel.setText("Resources Requested");
                    int response = showManagementDetailsConfirmation(userStoryId);
                    if (response == JOptionPane.YES_OPTION) {
                        PossibleBlockerStore.getInstance().updateSpikedStatus(userStoryId, "Resources Allocated");
                        statusLabel.setText("Resources Allocated");
                        needsMoreResources.remove(userStoryId);
                        displaySpikedPanel(new JPanel());
                    } else {
                        PossibleBlockerStore.getInstance().updateSpikedStatus(userStoryId, "Spike Fail");
                        statusLabel.setText("Spike Fail");
                        allocateResourcesButton.setEnabled(false);
                        spikeSuccessButton.setEnabled(false);
                    }
                }
            });
            spikePanel.add(allocateResourcesButton, new CustomConstraints(2, row, GridBagConstraints.WEST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

            spikeSuccessButton.setEnabled(!"Spike Success".equals(currentStatus) && !"Spike Fail".equals(currentStatus));
            spikeSuccessButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    PossibleBlockerStore.getInstance().updateSpikedStatus(userStoryId, "Spike Success");
                    statusLabel.setText("Spike Success");
                    needsMoreResources.remove(userStoryId);
                    allocateResourcesButton.setEnabled(false);
                    spikeSuccessButton.setEnabled(false);
                }
            });
            spikePanel.add(spikeSuccessButton, new CustomConstraints(3, row, GridBagConstraints.WEST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

            if ("Spike Added".equals(currentStatus)) {
                int response = showManagementDetailsConfirmation(userStoryId);
                if (response == JOptionPane.YES_OPTION) {
                    needsMoreResources.add(userStoryId);
                    allocateResourcesButton.setEnabled(true);
                } else if (response == JOptionPane.NO_OPTION) {
                    PossibleBlockerStore.getInstance().updateSpikedStatus(userStoryId, "Spike Fail");
                    statusLabel.setText("Spike Fail");
                    allocateResourcesButton.setEnabled(false);
                    spikeSuccessButton.setEnabled(false);
                }
            }

            row++;
        }

        spikePanel.revalidate();
        spikePanel.repaint();
    }

    private int showManagementDetailsConfirmation (String userStoryId) {
        String message = "Management team has provided details for user story " + userStoryId + ". Proceed?";
        int response = JOptionPane.showConfirmDialog(
                this,
                message,
                "Management Details",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE
        );


        if (response == JOptionPane.YES_OPTION) {
            needsMoreResources.add(userStoryId);
        }
        return response;
    }

    // Method to get user stories from UserStoryStore
    private String[] getUserStories() {
        List<UserStory> userStories = UserStoryStore.getInstance().getUserStories();
        return userStories.stream()
                .map(userStory -> userStory.getId().toString())
                .toArray(String[]::new);
    }
}