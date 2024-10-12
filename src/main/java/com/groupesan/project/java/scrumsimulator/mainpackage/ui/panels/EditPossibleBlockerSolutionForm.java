package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlockerSolution;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditPossibleBlockerSolutionForm extends JFrame implements BaseComponent {

    public EditPossibleBlockerSolutionForm(PossibleBlockerSolution possibleBlockerSolution) {
        this.possibleBlockerSolution = possibleBlockerSolution;
        this.init();
    }

    private PossibleBlockerSolution possibleBlockerSolution;

    private JTextField nameField = new JTextField();
    private JTextArea solArea = new JTextArea();

    public void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Edit Possible Blocker " + possibleBlockerSolution.getId().toString());
        setSize(400, 300);

        nameField = new JTextField(possibleBlockerSolution.getName());
        solArea = new JTextArea(possibleBlockerSolution.getSolution());

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

        submitButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String name = nameField.getText();
                        String solution = solArea.getText();

                        possibleBlockerSolution.setName(name);
                        possibleBlockerSolution.setSolution(solution);
                        dispose();
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
}
