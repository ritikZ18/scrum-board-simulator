package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlocker;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlockerSolution;

import javax.swing.*;
import java.awt.*;
import java.security.SecureRandom;
import java.util.List;


public class FineTuneProbabilityPane extends JPanel {


    private JLabel selectedBlockerValue;
    private JLabel selectedSolutionValue;
    private final JComboBox<PossibleBlocker> blockerDropdown;
    private final JComboBox<PossibleBlockerSolution> solutionDropdown;
    private final JSlider blockerProbabilitySlider;
    private final JSlider solutionProbabilitySlider;
    private final SecureRandom secureRandom;


    public FineTuneProbabilityPane(List<PossibleBlocker> possibleBlockers, List<PossibleBlockerSolution> possibleBlockerSolutions) {

        //init the SecureRandom (be careful!!)
        this.secureRandom = new SecureRandom();
        blockerDropdown = new JComboBox<>(possibleBlockers.toArray(new PossibleBlocker[0]));
        blockerProbabilitySlider = probabilitySlider(0);
        solutionDropdown = new JComboBox<>(possibleBlockerSolutions.toArray(new PossibleBlockerSolution[0]));
        solutionProbabilitySlider = probabilitySlider(100);

        paneUI();
        paneActionListener();

    }

    private JSlider probabilitySlider(int initialValue) {
        JSlider slider = new JSlider(0, 100, initialValue);
        slider.setMinorTickSpacing(5);
        slider.setMajorTickSpacing(25);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        return slider;
    }


    private void paneUI() {

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        /*
         * Everything on Solution
         * 1. Blocker Probability Dropdown
         * 2. Blocker Probability Slider to set Prob
         * */

        // 1. Dropdown

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Blocker:"), gbc);
        gbc.gridx = 1;
        add(blockerDropdown, gbc);

        //2. Slider
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.gridy = 1;
        add(blockerProbabilitySlider, gbc);
        selectedBlockerValue = new JLabel("Selected Blocker Probability: 0%");
        gbc.gridy = 4;
        add(selectedBlockerValue, gbc);


        /*
         * Everything on Solution
         * 1. Solution Probability Dropdown
         * 2. Solution Probability Slider to set Prob
         * */

        //1. Dropdwon
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(new JLabel("Solution:"), gbc);
        gbc.gridx = 1;
        add(solutionDropdown, gbc);


        //2. Slider
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(solutionProbabilitySlider, gbc);
        selectedSolutionValue = new JLabel("Selected Solution Probability: 100%");
        gbc.gridy = 7;
        add(selectedSolutionValue, gbc);


        ftpButton("Set", 8);
        ftpButton("Random", 10);

    }

    private void ftpButton(String label, int gridY) {

        JButton button = new JButton(label);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = gridY;
        //gbc.gridx = gridX;
        gbc.gridwidth = 2;
        add(button, gbc);


        button.addActionListener(e -> {
            if (label.equals("Set")) {
                setAction();
            }
            else if(label.equals("Random")) {
                randomAction();
            }
        });

    }


    private void setAction() {
        PossibleBlocker selectedBlocker = (PossibleBlocker) blockerDropdown.getSelectedItem();
        PossibleBlockerSolution selectedSolution = (PossibleBlockerSolution) solutionDropdown.getSelectedItem();


        if (selectedBlocker != null && selectedSolution != null) {
            JOptionPane.showMessageDialog(FineTuneProbabilityPane.this,
                    "Set " + selectedBlocker.getName() + " with solution " + selectedSolution.getSolution()
                            + " at random probabilities.",
                    "Set Successful", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private void randomAction() {
        blockerProbabilitySlider.setValue(secureRandom.nextInt(101));
        solutionProbabilitySlider.setValue(secureRandom.nextInt(101));


        // Implement fine-tuning logic here
        PossibleBlocker selectedBlocker = (PossibleBlocker) blockerDropdown.getSelectedItem();
        PossibleBlockerSolution selectedSolution = (PossibleBlockerSolution) solutionDropdown.getSelectedItem();


        if (selectedBlocker != null && selectedSolution != null) {
            JOptionPane.showMessageDialog(FineTuneProbabilityPane.this,
                    "Set " + selectedBlocker.getName() + " with solution " + selectedSolution.getSolution()
                            + " at random probabilities.",
                    "Set Successful", JOptionPane.INFORMATION_MESSAGE);
        }


    }

    private void paneActionListener() {

        blockerProbabilitySlider.addChangeListener(e -> {
            int value = blockerProbabilitySlider.getValue();
            selectedBlockerValue.setText("Blocker Probability: " + value + "%");
        });

        solutionProbabilitySlider.addChangeListener(e -> {
            int value = solutionProbabilitySlider.getValue();
            selectedSolutionValue.setText("Solution Probability: " + value + "%");
        });

    }





}
