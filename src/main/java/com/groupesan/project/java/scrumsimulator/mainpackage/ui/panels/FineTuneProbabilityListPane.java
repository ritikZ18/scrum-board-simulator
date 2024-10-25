package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;


import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlocker;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlockerSolution;
import java.util.List;
import javax.swing.*;
import java.awt.*;


public class FineTuneProbabilityListPane extends JPanel {



    public FineTuneProbabilityListPane(List<PossibleBlocker> possibleBlockers, List<PossibleBlockerSolution> possibleBlockerSolutions) {
        setLayout(new BorderLayout());

        // dropdown panel for displaying blockers and solutions --> Working
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(possibleBlockers.size() + possibleBlockerSolutions.size(), 1));

        // Add blockers to panel --> Not Working
        for (PossibleBlocker blocker : possibleBlockers) {
            contentPanel.add(new JLabel("Blocker: " + blocker.getName()));
        }

        // add solutions to panel --> Not Working
        for (PossibleBlockerSolution solution : possibleBlockerSolutions) {
            contentPanel.add(new JLabel("Solution: " + solution.getName()));
        }

        add(contentPanel, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> {

            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            parentFrame.setContentPane(new JPanel());
            parentFrame.revalidate();
            parentFrame.repaint();
        });

        add(closeButton, BorderLayout.SOUTH);
    }



}
