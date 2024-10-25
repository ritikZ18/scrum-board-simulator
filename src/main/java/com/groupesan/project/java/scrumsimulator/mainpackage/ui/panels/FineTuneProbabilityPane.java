package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlocker;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlockerSolution;

import javax.swing.*;
import java.awt.*;
import java.security.SecureRandom;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FineTuneProbabilityPane extends JPanel {

   // private PossibleBlocker selectedBlocker;
    //private PossibleBlockerSolution selectedSolution;

    // Labels to display the selected probability values
    private final JLabel selectedBlockerValueLabel;
    private final JLabel selectedSolutionValueLabel;


    //Random Value Generation
    private SecureRandom secureRandom; //declare as class field

    public FineTuneProbabilityPane(List<PossibleBlocker> possibleBlockers , List<PossibleBlockerSolution> possibleBlockerSolutions){

        GridBagLayout layout = new GridBagLayout();
        setLayout(layout);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        setSize(600, 400);
        gbc.insets = new Insets(15, 15, 15, 15);


        //init the SecureRandom
        secureRandom = new SecureRandom();




        /*
         * Everything on Solution
         * 1. Blocker Probability Dropdown
         * 2. Blocker Probability Slider to set Prob
         * 3. Slider Location set
         * */

        //1. Blocker Button
        JComboBox<PossibleBlocker> blockerDropdown = new JComboBox<>(possibleBlockers.toArray(new PossibleBlocker[0]));
        gbc.gridwidth = 1; // Reset grid width
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Blocker:"), gbc);
        gbc.gridx = 1;
        add(blockerDropdown, gbc);

        //2. Slider for Blocker Probability
        JSlider blockerProbabilitySlider = new JSlider(0 ,100 ,50);
        blockerProbabilitySlider.setMajorTickSpacing(25);
        blockerProbabilitySlider.setMinorTickSpacing(5);
        // blockerProbabilitySlider.setValue(50);
        blockerProbabilitySlider.setPaintTicks(true);
        blockerProbabilitySlider.setPaintLabels(true);

        //3. Location
        gbc.gridx = 0;

        gbc.gridwidth = 2; // Span across both columns
        // add(new JLabel("Blocker Probability:"), gbc);
        gbc.gridy = (int) 1.5; // Move to next row for the slider
        add(blockerProbabilitySlider, gbc);

        selectedBlockerValueLabel = new JLabel("Selected Blocker Probability: 50%");
        gbc.gridy = 2;
        add(selectedBlockerValueLabel, gbc);



        /*
         * Everything on Solution
         * 1. Solution Probability Dropdown
         * 2. Solution Probability Slider to set Prob
         * 3. Slider Location set
         * */
        //Solution Button
        JComboBox<PossibleBlockerSolution> solutionDropdown = new JComboBox<>(possibleBlockerSolutions.toArray(new PossibleBlockerSolution[0]));
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Solution:"), gbc);
        gbc.gridx = 1;
        add(solutionDropdown, gbc);


        //Slider for Solution Probability
        JSlider solutionProbabilitySlider = new JSlider(0 ,100 ,50);
        solutionProbabilitySlider.setMajorTickSpacing(25);
        solutionProbabilitySlider.setMinorTickSpacing(5);
        //solutionProbabilitySlider.setValue(50);
        solutionProbabilitySlider.setPaintTicks(true);
        solutionProbabilitySlider.setPaintLabels(true);

        gbc.gridx = 0;
        //add(new JLabel("Solution Probability:"), gbc);
        gbc.gridy = 6;
        add(solutionProbabilitySlider, gbc);

        selectedSolutionValueLabel = new JLabel("Selected Solution Probability: 50%");
        gbc.gridy = 7;
        add(selectedSolutionValueLabel, gbc);





// Fine Tune button
        JButton fineTuneButtonSet = new JButton("Set");
        fineTuneButtonSet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Set probability slider value
                int blockerProbability = blockerProbabilitySlider.getValue();
                int solutionProbability = solutionProbabilitySlider.getValue();


                // Implement fine-tuning logic here
                PossibleBlocker selectedBlocker = (PossibleBlocker) blockerDropdown.getSelectedItem();
                PossibleBlockerSolution selectedSolution = (PossibleBlockerSolution) solutionDropdown.getSelectedItem();

                // Example action
                if (selectedBlocker != null && selectedSolution != null) {
                    JOptionPane.showMessageDialog(FineTuneProbabilityPane.this,
                            "Set " + selectedBlocker.getName() + " with solution " + selectedSolution.getSolution()
                                    + " at " + blockerProbability + "% blocker probability and " + solutionProbability + "% solution probability.",
                            "Set Successful", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        add(fineTuneButtonSet, gbc);




        JButton fineTuneButtonRandom = new JButton("Random");
        fineTuneButtonRandom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Random Probability Slider here by using secureRandom
                //potential for SpotBugsMain (Next developer dont touch or will create Havoc)

              blockerProbabilitySlider.setValue(secureRandom.nextInt(101));
              solutionProbabilitySlider.setValue(secureRandom.nextInt(101));



                // Implement fine-tuning logic here
                PossibleBlocker selectedBlocker = (PossibleBlocker) blockerDropdown.getSelectedItem();
                PossibleBlockerSolution selectedSolution = (PossibleBlockerSolution) solutionDropdown.getSelectedItem();

                // Example action
                if (selectedBlocker != null && selectedSolution != null) {
                    JOptionPane.showMessageDialog(FineTuneProbabilityPane.this,
                            "Set " + selectedBlocker.getName() + " with solution " + selectedSolution.getSolution()
                                    + " at random probabilities.",
                            "Set Successful", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });


        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        add(fineTuneButtonRandom, gbc);

        // Update labels based on slider changes
        blockerProbabilitySlider.addChangeListener(e -> {
            int value = blockerProbabilitySlider.getValue();
            selectedBlockerValueLabel.setText("Selected Blocker Probability: " + value + "%");
        });

        solutionProbabilitySlider.addChangeListener(e -> {
            int value = solutionProbabilitySlider.getValue();
            selectedSolutionValueLabel.setText("Selected Solution Probability: " + value + "%");
        });




    }




}
