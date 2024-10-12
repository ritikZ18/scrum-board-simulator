package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlockerSolution;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlockerSolutionFactory;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewPossibleBlockerSolutionForm extends JFrame implements BaseComponent {

    public NewPossibleBlockerSolutionForm() {
        this.init();
    }

    private JTextField nameField = new JTextField();
    private JTextArea solArea = new JTextArea();

    public void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("New Possible Blocker");
        setSize(400, 300);

        nameField = new JTextField();
        solArea = new JTextArea();

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

        JLabel solLabel = new JLabel("Solution:");
        myJpanel.add(
                solLabel,
                new CustomConstraints(
                        0, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(
                new JScrollPane(solArea),
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

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                String solution = solArea.getText().trim();

                boolean isInvalid = false;

                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(NewPossibleBlockerSolutionForm.this,
                            "Blocker Name cannot be empty.", "Error in New Blocker Form",
                            JOptionPane.ERROR_MESSAGE);
                    isInvalid = true;
                }
                if (!isInvalid && solution.isEmpty()) {
                    JOptionPane.showMessageDialog(NewPossibleBlockerSolutionForm.this,
                            "Blocker Solution cannot be empty.", "Error in New Blocker Form",
                            JOptionPane.ERROR_MESSAGE);
                    isInvalid = true;
                }
                if (!isInvalid) {
                    PossibleBlockerSolution newSolution = getPossibleBlockerSolutionObject();
                    if (newSolution != null) {
                        dispose();
                    }
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

    public PossibleBlockerSolution getPossibleBlockerSolutionObject() {
        String name = nameField.getText().trim();
        String solution = solArea.getText().trim();

        PossibleBlockerSolutionFactory possibleBlockerSolutionFactory = PossibleBlockerSolutionFactory.getInstance();
        PossibleBlockerSolution possibleBlockerSolution = possibleBlockerSolutionFactory.createNewPossibleBlockerSolution(name, solution);

        try {
            possibleBlockerSolution.doRegister();
            System.out.println("PossibleBlockerSolutionSolution registered: " + possibleBlockerSolution);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Failed to register Possible Blocker: " + e.getMessage(),
                    "Registration Error", JOptionPane.ERROR_MESSAGE);
            return null; // Return null if registration fails
        }

        return possibleBlockerSolution;
    }
}

