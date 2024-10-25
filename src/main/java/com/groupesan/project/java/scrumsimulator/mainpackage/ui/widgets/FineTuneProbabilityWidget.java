package com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlocker;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlockerSolution;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlockerSolutionStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlockerStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.FineTuneProbabilityListPane;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.FineTuneProbabilityPane;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.List;



public class FineTuneProbabilityWidget extends JPanel implements BaseComponent, Serializable {


    private transient JComboBox<String> blockerDropdown;
    private transient JComboBox<String> solutionDropdown;



    public FineTuneProbabilityWidget() {
        this.init();
    }

   public static void openFineTuneWindow() {
        //Data fetch from thr PB and PBS
        List<PossibleBlocker> possibleBlockers = PossibleBlockerStore.getInstance().getPossibleBlockers();
        List<PossibleBlockerSolution> possibleBlockerSolutions = PossibleBlockerSolutionStore.getInstance().getPossibleBlockerSolutions();

        //  ftp-->Pane Instance
        FineTuneProbabilityPane ftpPanel = new FineTuneProbabilityPane(possibleBlockers, possibleBlockerSolutions);
        ftpPanel.setPreferredSize(new Dimension(400, 500));

        JFrame fineTuneFrame = new JFrame("Fine Tune Probability");

        fineTuneFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fineTuneFrame.getContentPane().add(ftpPanel);
        fineTuneFrame.pack();
        fineTuneFrame.setLocationRelativeTo(null);
        fineTuneFrame.setVisible(true);
    }

    public void init() {
        set(new GridBagLayout());
        JButton fineTuneButton = new JButton("Fine Tune Probability");


        //init the drops but make sure they dont ssow up on demopane
        blockerDropdown = new JComboBox<>();
        solutionDropdown = new JComboBox<>();

        blockerDropdown.setVisible(false);
        solutionDropdown.setVisible(false);

        //adding button
        add(fineTuneButton, new GridBagConstraints(0, 0, 1, 1, 0.5, 0.5, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 10), 0, 0));
        add(blockerDropdown, new GridBagConstraints(0, 1, 1, 1, 0.5, 0.5, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 10), 0, 0));
        add(solutionDropdown, new GridBagConstraints(0, 2, 1, 1, 0.5, 0.5, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(10, 10, 10, 10), 0, 0));


        // Action listener
        fineTuneButton.addActionListener(e -> {

            List<PossibleBlocker> blockers = PossibleBlockerStore.getInstance().getPossibleBlockers();
            List<PossibleBlockerSolution> solutions = PossibleBlockerSolutionStore.getInstance().getPossibleBlockerSolutions();

            FineTuneProbabilityListPane fineTunePane = new FineTuneProbabilityListPane(blockers, solutions);

            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            if (parentFrame != null) {
                parentFrame.setContentPane(fineTunePane);
                parentFrame.revalidate();
                parentFrame.repaint();
            }
            else{
                System.err.println("No parent frame found! Cannot set content pane.");
            }
        });


    }

    private void set(GridBagLayout gridBagLayout) {
        setLayout(gridBagLayout);
    }
}
