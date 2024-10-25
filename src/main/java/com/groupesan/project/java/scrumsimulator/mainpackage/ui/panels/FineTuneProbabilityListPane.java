package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;


import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlocker;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlockerSolution;
import java.util.List;
import javax.swing.*;
import java.awt.*;


public class FineTuneProbabilityListPane extends JPanel {



    public FineTuneProbabilityListPane(List<PossibleBlocker> possibleBlockers, List<PossibleBlockerSolution> possibleBlockerSolutions) {
        setLayout(new BorderLayout());

        // Create a title label
        JLabel titleLabel = new JLabel("Fine Tune Probability List", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // Create a panel for displaying blockers and solutions --> Working
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(possibleBlockers.size() + possibleBlockerSolutions.size(), 1));

        // Add blockers to the content panel --> Not Working
        for (PossibleBlocker blocker : possibleBlockers) {
            contentPanel.add(new JLabel("Blocker: " + blocker.getName()));
        }

        // Add solutions to the content panel --> Not Working
        for (PossibleBlockerSolution solution : possibleBlockerSolutions) {
            contentPanel.add(new JLabel("Solution: " + solution.getName()));
        }





        add(contentPanel, BorderLayout.CENTER);


        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> {

            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            parentFrame.setContentPane(new JPanel()); // Reset to an empty panel or a previous one
            parentFrame.revalidate();
            parentFrame.repaint();
        });

        add(closeButton, BorderLayout.SOUTH);
    }



}
