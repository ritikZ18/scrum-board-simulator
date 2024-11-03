package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlocker;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStoryStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EditPossibleBlockerForm extends JFrame implements BaseComponent {

    private JComboBox<String> statusComboBox;
    private JComboBox<String> userStoriesDropDown;

    public EditPossibleBlockerForm(PossibleBlocker possibleBlocker) {
        this.possibleBlocker = possibleBlocker;
        this.init();
    }

    private PossibleBlocker possibleBlocker;
    private JTextField nameField = new JTextField();
    private JTextArea descArea = new JTextArea();

    public void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Edit Possible Blocker " + possibleBlocker.getId().toString());
        setSize(400, 300);

        nameField = new JTextField(possibleBlocker.getName());
        descArea = new JTextArea(possibleBlocker.getDescription());

        GridBagLayout myGridbagLayout = new GridBagLayout();
        JPanel myJpanel = new JPanel();
        myJpanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        myJpanel.setLayout(myGridbagLayout);

        BorderLayout myBorderLayout = new BorderLayout();

        setLayout(myBorderLayout);

        JLabel nameLabel = new JLabel("Name:");
        myJpanel.add(
                nameLabel,
                new CustomConstraints(
                        0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(
                nameField,
                new CustomConstraints(
                        1, 0, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

        JLabel descLabel = new JLabel("Description:");
        myJpanel.add(
                descLabel,
                new CustomConstraints(
                        0, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(
                new JScrollPane(descArea),
                new CustomConstraints(
                        1, 1, GridBagConstraints.EAST, 1.0, 0.3, GridBagConstraints.BOTH));

        JLabel userStoryLabel = new JLabel("User Story:");
        userStoriesDropDown = new JComboBox<>(getUserStories());
        userStoriesDropDown.setSelectedItem(possibleBlocker.getUserStoryId());
        myJpanel.add(userStoryLabel,
                new CustomConstraints(0, 2, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(userStoriesDropDown,
                new CustomConstraints(1,2, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));

        JLabel statusLabel = new JLabel("Status:");
        String[] statusOptions = {"Unresolved", "Resolved"};
        statusComboBox = new JComboBox<>(statusOptions);
        statusComboBox.setSelectedItem(possibleBlocker.getStatus());
        myJpanel.add(
                statusLabel,
                new CustomConstraints(
                        0, 3, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(
                statusComboBox,
                new CustomConstraints(
                        1, 3, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

        JButton cancelButton = new JButton("Cancel");

        cancelButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });

        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String name = nameField.getText();
                        String description = descArea.getText();
                        String status = (String) statusComboBox.getSelectedItem();

                        possibleBlocker.setName(name);
                        possibleBlocker.setDescription(description);
                        possibleBlocker.setStatus(status);
                        dispose();
                    }
                });

        myJpanel.add(
                cancelButton,
                new CustomConstraints(0, 4, GridBagConstraints.EAST, GridBagConstraints.NONE));
        myJpanel.add(
                submitButton,
                new CustomConstraints(1, 4, GridBagConstraints.WEST, GridBagConstraints.NONE));

        add(myJpanel);
    }
    // Method to get user stories from UserStoryStore
    private String[] getUserStories() {
        List<UserStory> userStories = UserStoryStore.getInstance().getUserStories();
        return userStories.stream()
                .map(userStory -> userStory.getId().toString())
                .toArray(String[]::new);
    }
}
