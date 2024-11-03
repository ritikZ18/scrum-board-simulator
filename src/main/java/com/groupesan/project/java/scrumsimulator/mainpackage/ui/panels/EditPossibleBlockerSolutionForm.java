package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlocker;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlockerSolution;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlockerStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EditPossibleBlockerSolutionForm extends JFrame implements BaseComponent {

    public EditPossibleBlockerSolutionForm(PossibleBlockerSolution possibleBlockerSolution) {
        this.possibleBlockerSolution = possibleBlockerSolution;
        this.init();
    }

    private PossibleBlockerSolution possibleBlockerSolution;
    private JTextArea solArea = new JTextArea();
    private JComboBox<String> possibleBlockersDropdown;

    public void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Edit Solution for Blocker " + possibleBlockerSolution.getId().toString());
        setSize(400, 300);


        solArea = new JTextArea(possibleBlockerSolution.getSolution());

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

        submitButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String solution = solArea.getText();

                        possibleBlockerSolution.setSolution(solution);
                        dispose();
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
    // Method to get possible blockers from PossibleBlockerStore
    private String[] getPossibleBlockers() {
        List<PossibleBlocker> possibleBlockers = PossibleBlockerStore.getInstance().getPossibleBlockers();
        return possibleBlockers.stream()
                .map(blocker -> blocker.getId().toString())
                .toArray(String[]::new);
    }
}
