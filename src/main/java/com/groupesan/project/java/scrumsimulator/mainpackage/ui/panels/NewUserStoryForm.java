package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStoryFactory;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class NewUserStoryForm extends JFrame implements BaseComponent {

    Double[] pointsList = {null, 1.0, 2.0, 3.0, 5.0, 8.0, 11.0, 19.0, 30.0, 49.0};
    Double[] businessValueList = {null, 1.0, 3.0, 7.0, 11.0, 17.0, 23.0};


    public NewUserStoryForm() {
        this.init();
    }

    private JTextField nameField = new JTextField();
    private JTextArea descArea = new JTextArea();
    private JComboBox<Double> pointsCombo = new JComboBox<>(pointsList);
    private JComboBox<Double> businessValueCombo = new JComboBox<>(businessValueList);

    public void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("New User Story");
        setSize(400, 300);

        nameField = new JTextField();
        descArea = new JTextArea();
        pointsCombo = new JComboBox<>(pointsList);
        businessValueCombo = new JComboBox<>(businessValueList);

        businessValueCombo.setEnabled(SimulationSwitchRolePane.getCurrentRole().equals("Product Owner"));

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

        JLabel pointsLabel = new JLabel("Points:");
        myJpanel.add(
                pointsLabel,
                new CustomConstraints(
                        0, 2, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(
                pointsCombo,
                new CustomConstraints(
                        1, 2, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));
        JLabel businessValueLabel = new JLabel("Business Value:");
        myJpanel.add(
                businessValueLabel,
                new CustomConstraints(
                        0, 3, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(
                businessValueCombo,
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
                        String name=nameField.getText().trim();
                        String description=descArea.getText().trim();

                        // Delete previous error messages
                        boolean isInvalid = false;

                        // Check if name is empty
                        if(name.isEmpty()){
                            JOptionPane.showMessageDialog(NewUserStoryForm.this,
                                    "User Story Name cannot be empty.", "Error in New User Story Form",
                                    JOptionPane.ERROR_MESSAGE);
                            isInvalid = true;
                        }
                        //Validate description only if name is valid
                        if(!isInvalid && description.isEmpty()){
                            JOptionPane.showMessageDialog(NewUserStoryForm.this,
                                    "User Story Description cannot be empty.", "Error in New User Story Form",
                                    JOptionPane.ERROR_MESSAGE);
                            isInvalid = true;
                        }
                        if(!isInvalid) {
                            getUserStoryObject();
                            dispose();
                        }

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

    public UserStory getUserStoryObject() {
        String name = nameField.getText();
        String description = descArea.getText();
        Double points = (Double) pointsCombo.getSelectedItem();
        Double businessValue = (Double) businessValueCombo.getSelectedItem();

        // Option to handle null values explicitly if needed
        if (points == null) {
            System.out.println("Points value is not set. Proceeding with null.");
        }
        if (businessValue == null) {
            System.out.println("Business value is not set. Proceeding with null.");
        }

        UserStoryFactory userStoryFactory = UserStoryFactory.getInstance();

        UserStory userStory = userStoryFactory.createNewUserStory(name, description, points,businessValue);

        try {
            userStory.doRegister();
            System.out.println("UserStory registered: " + userStory);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Failed to register User Story: " + e.getMessage(),
                    "Registration Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        return userStory;
    }
}