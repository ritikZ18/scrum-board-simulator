package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlocker;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlockerFactory;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewPossibleBlockerForm extends JFrame implements BaseComponent {

    public NewPossibleBlockerForm() {
        this.init();
    }

    private JTextField nameField = new JTextField();
    private JTextArea descArea = new JTextArea();

    public void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("New Possible Blocker");
        setSize(400, 300);

        nameField = new JTextField();
        descArea = new JTextArea();

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
                            JOptionPane.showMessageDialog(NewPossibleBlockerForm.this,
                                    "User Story Name cannot be empty.", "Error in New User Story Form",
                                    JOptionPane.ERROR_MESSAGE);
                            isInvalid = true;
                        }
                        //Validate description only if name is valid
                        if(!isInvalid && description.isEmpty()){
                            JOptionPane.showMessageDialog(NewPossibleBlockerForm.this,
                                    "User Story Description cannot be empty.", "Error in New User Story Form",
                                    JOptionPane.ERROR_MESSAGE);
                            isInvalid = true;
                        }
                        if(!isInvalid) {
                            getPossibleBlockerObject();
                            dispose();
                        }

                    }
                });

        myJpanel.add(
                cancelButton,
                new CustomConstraints(0, 2, GridBagConstraints.EAST, GridBagConstraints.NONE));
        myJpanel.add(
                submitButton,
                new CustomConstraints(1, 2, GridBagConstraints.WEST, GridBagConstraints.NONE));

        add(myJpanel);
    }

    public PossibleBlocker getPossibleBlockerObject() {
        String name = nameField.getText().trim();
        String description = descArea.getText().trim();

        PossibleBlockerFactory possibleBlockerFactory = PossibleBlockerFactory.getInstance();
        PossibleBlocker possibleBlocker = possibleBlockerFactory.createNewPossibleBlocker(name, description);

        try {
            possibleBlocker.doRegister();
            System.out.println("PossibleBlocker registered: " + possibleBlocker);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Failed to register Possible Blocker: " + e.getMessage(),
                    "Registration Error", JOptionPane.ERROR_MESSAGE);
            return null; // Return null if registration fails
        }

        return possibleBlocker;
    }
}

