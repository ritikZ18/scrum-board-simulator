package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlockerStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class EditUserStoryForm extends JFrame implements BaseComponent {



    Double[] pointsList = {null, 1.0, 2.0, 3.0, 5.0, 8.0, 11.0, 19.0, 30.0, 49.0};
    Double[] businessValueList = {null,1.0, 3.0, 7.0, 11.0, 17.0, 23.0};

    public EditUserStoryForm(UserStory userStory) {
        this.userStory = userStory;
        this.init();
        if (PossibleBlockerStore.getInstance().hasSpike(userStory.getId().toString())) { // Check if the user story has a spike
            disableEditing();
        }
        updateEditability();
    }

    public void updateEditability() {
        boolean isEditable = PossibleBlockerStore.getInstance().isUserStoryEditable(userStory.getId().toString());
        nameField.setEnabled(isEditable && SimulationSwitchRolePane.getCurrentRole().equals("Product Owner"));
        descArea.setEnabled(isEditable && SimulationSwitchRolePane.getCurrentRole().equals("Product Owner"));
        businessValueCombo.setEnabled(isEditable && SimulationSwitchRolePane.getCurrentRole().equals("Product Owner"));
        pointsCombo.setEnabled(isEditable && SimulationSwitchRolePane.getCurrentRole().equals("Developer"));
        statusCombo.setEnabled(isEditable && SimulationSwitchRolePane.getCurrentRole().equals("Developer"));
    }

    public void disableEditing() {
        nameField.setEnabled(false);
        descArea.setEnabled(false);
        pointsCombo.setEnabled(false);
        businessValueCombo.setEnabled(false);
        statusCombo.setEnabled(false);
    }



    private UserStory userStory;

    public UserStory getUserStory() {
        return userStory;
    }

    private JTextField nameField = new JTextField();
    private JTextArea descArea = new JTextArea();
    private JComboBox<Double> pointsCombo = new JComboBox<>(pointsList);
    private JComboBox<Double> businessValueCombo = new JComboBox<>(businessValueList);
    private JComboBox<String> statusCombo;

    public void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Edit User Story " + userStory.getId().toString());
        setSize(400, 300);

        String[] statuses = {"New", "In Progress", "ReadyForTest", "Done"};

        nameField = new JTextField(userStory.getName());
        descArea = new JTextArea(userStory.getDescription());
        pointsCombo = new JComboBox<>(pointsList);
        pointsCombo.setSelectedItem(userStory.getPointValue());
        businessValueCombo = new JComboBox<>(businessValueList);
        businessValueCombo.setSelectedItem(userStory.getBusinessValue());
        statusCombo = new JComboBox<>(statuses);
        statusCombo.setSelectedItem(userStory.getStatus());

        nameField.setEnabled(SimulationSwitchRolePane.getCurrentRole().equals("Product Owner"));
        descArea.setEnabled(SimulationSwitchRolePane.getCurrentRole().equals("Product Owner"));
        businessValueCombo.setEnabled(SimulationSwitchRolePane.getCurrentRole().equals("Product Owner"));
        pointsCombo.setEnabled(SimulationSwitchRolePane.getCurrentRole().equals("Developer"));
        statusCombo.setEnabled(SimulationSwitchRolePane.getCurrentRole().equals("Developer"));

        GridBagLayout myGridbagLayout = new GridBagLayout();
        JPanel myJpanel = new JPanel();
        myJpanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        myJpanel.setLayout(myGridbagLayout);

        BorderLayout myBorderLayout = new BorderLayout();

        setLayout(myBorderLayout);

        myJpanel.add(new JLabel("Name:"), new CustomConstraints(0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(nameField, new CustomConstraints(1, 0, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

        myJpanel.add(new JLabel("Description:"), new CustomConstraints(0, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(new JScrollPane(descArea), new CustomConstraints(1, 1, GridBagConstraints.EAST, 1.0, 0.3, GridBagConstraints.BOTH));

        myJpanel.add(new JLabel("Points:"), new CustomConstraints(0, 2, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(pointsCombo, new CustomConstraints(1, 2, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

        JLabel businessValueLabel = new JLabel("business Value:");// start Changes done by Sumathi

        myJpanel.add(
                businessValueLabel,
                new CustomConstraints(
                        0, 3, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(
                businessValueCombo,
                new CustomConstraints(
                        1, 3, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

        JLabel statusLabel = new JLabel("Status:");
        myJpanel.add(
                statusLabel,
                new CustomConstraints(
                        0, 4, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(
                statusCombo,
                new CustomConstraints(
                        1, 4, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

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
                        Double points = (Double) pointsCombo.getSelectedItem();
                        Double businessValue = (Double) businessValueCombo.getSelectedItem();
                        String status = (String) statusCombo.getSelectedItem();


                        boolean isInvalid = false;

                        if(name.isEmpty()){
                            JOptionPane.showMessageDialog(EditUserStoryForm.this,
                                    "User Story Name cannot be empty.", "Error in New User Story Form",
                                    JOptionPane.ERROR_MESSAGE);
                            isInvalid = true;
                        }
                        if(!isInvalid && description.isEmpty()){
                            JOptionPane.showMessageDialog(EditUserStoryForm.this,
                                    "User Story Description cannot be empty.", "Error in New User Story Form",
                                    JOptionPane.ERROR_MESSAGE);
                            isInvalid = true;
                        }
                        if(!isInvalid && businessValue==null){
                            JOptionPane.showMessageDialog(EditUserStoryForm.this,
                                    "User Story business value cannot be empty.", "Error in New User Story Form",
                                    JOptionPane.ERROR_MESSAGE);
                            isInvalid = true;
                        }
                        if(!isInvalid) {
                            userStory.setName(name);
                            userStory.setDescription(description);
                            userStory.setPointValue(points);
                            userStory.setBusinessValue(businessValue);
                            userStory.setStatus(status);
                            if (userStory.getName() != null && userStory.getDescription() != null) {
                                dispose();
                            }
                        }
                    }
                });

        myJpanel.add(
                cancelButton,
                new CustomConstraints(0, 5, GridBagConstraints.EAST, GridBagConstraints.NONE));
        myJpanel.add(
                submitButton,
                new CustomConstraints(1, 5, GridBagConstraints.WEST, GridBagConstraints.NONE));

        add(myJpanel);
    }
}
