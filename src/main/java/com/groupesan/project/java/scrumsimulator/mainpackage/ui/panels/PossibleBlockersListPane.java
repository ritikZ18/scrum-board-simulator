

package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlocker;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlockerStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStoryStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.PossibleBlockerWidget;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class PossibleBlockersListPane extends JFrame implements BaseComponent {
    private List<PossibleBlockerWidget> widgets = new ArrayList<>();
    private JComboBox<String> userStoriesDropDown;
    private List<String> spikedUserStories = new ArrayList<>(); // In-memory storage for spikes

    public PossibleBlockersListPane() {
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

        for (PossibleBlocker possibleBlocker : PossibleBlockerStore.getInstance().getPossibleBlockers()) {
            widgets.add(new PossibleBlockerWidget(possibleBlocker));
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
                                new java.awt.event.WindowAdapter() {
                                    public void windowClosed(
                                            java.awt.event.WindowEvent windowEvent) {
                                        PossibleBlocker possibleBlocker = form.getPossibleBlockerObject();
                                        if (possibleBlocker != null) {
                                            PossibleBlockerStore.getInstance().addPossibleBlocker(possibleBlocker);
                                            addPossibleBlockerWidget(possibleBlocker);
                                        }
                                    }
                                });
                    }

                    private void addPossibleBlockerWidget(PossibleBlocker possibleBlocker) {
                        PossibleBlockerWidget widget = new PossibleBlockerWidget(possibleBlocker);
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

        // Adding  dropdown for selecting user stories
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
                }else {
                    JOptionPane.showMessageDialog(
                            PossibleBlockersListPane.this,
                            "Please select a user story",
                            "No User Story Selected",
                            JOptionPane.WARNING_MESSAGE
                    );
                }
            }
        });
        myJpanel.add(
                addSpikeButton,
                new CustomConstraints(
                        1, 2, GridBagConstraints.WEST, 1.0, 0.1, GridBagConstraints.HORIZONTAL));

        add(myJpanel);
    }

    // Add spike method implementation
    private void addSpike(String userStoryId) {
        if (!spikedUserStories.contains(userStoryId)) {
            showManagementDetailsConfirmation(userStoryId);
        }
        else {
            JOptionPane.showMessageDialog(this, "Already added","Duplicated Spike",JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showManagementDetailsConfirmation(String userStoryId) {
        String message = "Management team has provided details";
        int option = JOptionPane.showConfirmDialog(
                this,
                message,
                "Management Details",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE
        );

        if (option == JOptionPane.YES_OPTION) {
            spikedUserStories.add(userStoryId);
            JOptionPane.showMessageDialog(
                    this,
                    "Spike added for user story " + userStoryId,
                    "Spike Added",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
        // If NO_OPTION is selected, do nothing
    }


    // Method to get user stories from UserStoryStore
    private String[] getUserStories() {
        List<UserStory> userStories = UserStoryStore.getInstance().getUserStories();
        return userStories.stream()
                .map(userStory -> userStory.getId().toString())
                .toArray(String[]::new);
    }
}
