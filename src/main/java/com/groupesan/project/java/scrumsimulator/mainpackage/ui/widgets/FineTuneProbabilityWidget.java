package com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlocker;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlockerSolution;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlockerSolutionStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlockerStore;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FineTuneProbabilityWidget extends JPanel implements BaseComponent, Serializable {


    private JLabel selectedBlockerValue;
    private JLabel selectedSolutionValue;
    private final JComboBox<PossibleBlocker> blockerDropdown;
    private final JComboBox<PossibleBlockerSolution> solutionDropdown;
    private final JSlider blockerProbabilitySlider;
    private final JSlider solutionProbabilitySlider;
    private final SecureRandom secureRandom;

    private final transient Map<PossibleBlocker, Integer> blockerProbabilities = new HashMap<>();
    private final transient Map<PossibleBlockerSolution, Integer> solutionProbabilities = new HashMap<>();



    public FineTuneProbabilityWidget() {

        this.secureRandom = new SecureRandom();
        blockerDropdown = new JComboBox<>();
        blockerProbabilitySlider = probabilitySlider(0);
        solutionDropdown = new JComboBox<>();
        solutionProbabilitySlider = probabilitySlider(100);

        set(new GridBagLayout());
        this.init();
        paneUI();
        populateftpDropdown();
        paneActionListener();

    }


    public void init() {

        add(blockerDropdown, new GridBagConstraints(0, 0, 2, 1, 0.5, 0.5, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 10), 0, 0));
        add(solutionDropdown, new GridBagConstraints(0, 3, 2, 1, 0.5, 0.5, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 10), 0, 0));
    }

    private void populateftpDropdown() {

        //Data fetch from thr PB and PBS
        List<PossibleBlocker> possibleBlockers = PossibleBlockerStore.getInstance().getPossibleBlockers();
        List<PossibleBlockerSolution> possibleBlockerSolutions = PossibleBlockerSolutionStore.getInstance().getPossibleBlockerSolutions();

        blockerDropdown.removeAllItems();
        solutionDropdown.removeAllItems();

        for (PossibleBlocker blocker : possibleBlockers) {
            blockerDropdown.addItem(blocker);
        }

        for (PossibleBlockerSolution solution : possibleBlockerSolutions) {
            solutionDropdown.addItem(solution);
        }

        //Force Refresh
        blockerDropdown.revalidate();
        blockerDropdown.repaint();
        solutionDropdown.revalidate();
        solutionDropdown.repaint();

    }

    //Slider
    private JSlider probabilitySlider(int initialValue) {
        JSlider slider = new JSlider(0, 100, initialValue);
        slider.setMinorTickSpacing(5);
        slider.setMajorTickSpacing(25);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        return slider;
    }


    //paneUI
    private void paneUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // 1. Dropdown
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Blocker:"), gbc);

        gbc.gridx = 1;
        add(blockerDropdown, gbc);

        //2. Slider
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        add(blockerProbabilitySlider, gbc);
        selectedBlockerValue = new JLabel("Blocker: 0%");
        gbc.gridy = 2;
        add(selectedBlockerValue, gbc);


        //1. Dropdwon
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(new JLabel("Solution:"), gbc);
        gbc.gridx = 1;
        add(solutionDropdown, gbc);


        //2. Slider
        gbc.gridx = 1;
        gbc.gridy = 6;
        add(solutionProbabilitySlider, gbc);
        selectedSolutionValue = new JLabel("Solution: 100%");
        gbc.gridy = 7;
        add(selectedSolutionValue, gbc);


        ftpButton("Set", 8);
        ftpButton("Random", 10);
    }


    //FTP-Button for set n rndm
    private void ftpButton(String label, int gridY) {

        JButton button = new JButton(label);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = gridY;
        gbc.gridwidth = 2;
        add(button, gbc);

        button.addActionListener(e -> {
            if (label.equals("Set")) {
                setAction();
            } else if (label.equals("Random")) {
                randomAction();
            }
        });
    }


    private void setAction() {
        PossibleBlocker selectedBlocker = (PossibleBlocker) blockerDropdown.getSelectedItem();
        PossibleBlockerSolution selectedSolution = (PossibleBlockerSolution) solutionDropdown.getSelectedItem();

        //check set btn
        if (selectedBlocker != null && selectedSolution != null) {

            //to store probabilites
            int blockerProbability = blockerProbabilitySlider.getValue();
            int solutionProbability = solutionProbabilitySlider.getValue();

            //to store probabilities in maps
            blockerProbabilities.put(selectedBlocker, blockerProbability);
            solutionProbabilities.put(selectedSolution, solutionProbability);

            JOptionPane.showMessageDialog(this,
                    "Probabilities set Success-> " +"Blocker: " +blockerProbability + "%" + " &" + " Solution: " +solutionProbability + "%");
        }
    }

    //random value select
    private void randomAction() {
        blockerProbabilitySlider.setValue(secureRandom.nextInt(101));
        solutionProbabilitySlider.setValue(secureRandom.nextInt(101));
        setAction();
    }

    //reflect selected probability
    private void paneActionListener() {
        blockerProbabilitySlider.addChangeListener(e -> {
            int probability = blockerProbabilitySlider.getValue();
            PossibleBlocker selectedBlocker = (PossibleBlocker) blockerDropdown.getSelectedItem();
            if (selectedBlocker != null) {
                selectedBlocker.setProbability(probability);
                blockerProbabilities.put(selectedBlocker, probability);
            }
            selectedBlockerValue.setText("Blocker Probability: " + probability + "%");

        });




        solutionProbabilitySlider.addChangeListener(e -> {
            int probability = solutionProbabilitySlider.getValue();
            PossibleBlockerSolution selectedSolution = (PossibleBlockerSolution) solutionDropdown.getSelectedItem();
            if (selectedSolution != null) {
                selectedSolution.setProbability(probability);
                solutionProbabilities.put(selectedSolution, probability);
            }
            selectedSolutionValue.setText("Solution Probability: " + probability + "%");
        });
    }

    public static void openFineTuneWindow() {
        FineTuneProbabilityWidget staticInstance = new FineTuneProbabilityWidget();
        staticInstance.setPreferredSize(new Dimension(400, 500));
        JFrame fineTuneFrame = new JFrame("Fine Tune Probability");
        fineTuneFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fineTuneFrame.getContentPane().add(staticInstance);
        fineTuneFrame.pack();
        fineTuneFrame.setLocationRelativeTo(null);
        fineTuneFrame.setVisible(true);
    }

    private void set(GridBagLayout gridBagLayout) {
        setLayout(gridBagLayout);
    }


}
