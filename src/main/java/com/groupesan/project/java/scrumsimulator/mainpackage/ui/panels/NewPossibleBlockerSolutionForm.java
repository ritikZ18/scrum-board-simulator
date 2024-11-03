package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlocker;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlockerSolution;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlockerSolutionFactory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlockerStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class NewPossibleBlockerSolutionForm extends JFrame implements BaseComponent {

    public NewPossibleBlockerSolutionForm() {
        this.init();
    }

    private JTextArea solArea = new JTextArea();
    private JComboBox<String> possibleBlockersDropdown;

    public void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("New Possible Blocker");
        setSize(400, 300);

        solArea = new JTextArea();

        GridBagLayout myGridbagLayout = new GridBagLayout();
        JPanel myJpanel = new JPanel();
        myJpanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        myJpanel.setLayout(myGridbagLayout);

        BorderLayout myBorderLayout = new BorderLayout();

        setLayout(myBorderLayout);

        JLabel solLabel = new JLabel("Solution:");
        myJpanel.add(
                solLabel,
                new CustomConstraints(
                        0, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(
                new JScrollPane(solArea),
                new CustomConstraints(
                        1, 1, GridBagConstraints.EAST, 1.0, 0.3, GridBagConstraints.BOTH));
        // Dropdown for selecting possible blockers
        possibleBlockersDropdown = new JComboBox<>(getPossibleBlockers());
        myJpanel.add(
                new JLabel("Select Existing Blocker:"),
                new CustomConstraints(0, 2, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(
                possibleBlockersDropdown,
                new CustomConstraints(1, 2, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));



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
                String solution = solArea.getText().trim();

                boolean isInvalid = false;

                if (solution.isEmpty()) {
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
                new CustomConstraints(0, 3, GridBagConstraints.EAST, GridBagConstraints.NONE));
        myJpanel.add(
                submitButton,
                new CustomConstraints(1, 3, GridBagConstraints.WEST, GridBagConstraints.NONE));

        add(myJpanel);
    }

    public PossibleBlockerSolution getPossibleBlockerSolutionObject() {
        String solution = solArea.getText().trim();
        String blockerId = (String) possibleBlockersDropdown.getSelectedItem();

        PossibleBlockerSolutionFactory possibleBlockerSolutionFactory = PossibleBlockerSolutionFactory.getInstance();
        PossibleBlockerSolution possibleBlockerSolution = possibleBlockerSolutionFactory.createNewPossibleBlockerSolution(blockerId,solution);

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
    // Method to get possible blockers from PossibleBlockerStore
    private String[] getPossibleBlockers() {
        List<PossibleBlocker> possibleBlockers = PossibleBlockerStore.getInstance().getPossibleBlockers();
        return possibleBlockers.stream()
                .map(blocker -> blocker.getId().toString())
                .toArray(String[]::new);
    }
}