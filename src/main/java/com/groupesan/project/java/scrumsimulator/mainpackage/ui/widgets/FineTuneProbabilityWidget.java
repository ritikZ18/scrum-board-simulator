package com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlocker;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlockerSolution;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlockerSolutionStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlockerStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.FineTuneProbabilityListPane;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import java.awt.*;
import java.io.Serializable;
import java.util.List;



public class FineTuneProbabilityWidget extends JPanel implements BaseComponent, Serializable {


    private transient JComboBox<String> blockerDropdown;
    private transient JComboBox<String> solutionDropdown;


    // private transient FineTuneProbability fineTuneProbability;

    public FineTuneProbabilityWidget() {

        this.init();
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
            // Fetch PossibleBlockers and PossibleBlockerSolutions
            List<PossibleBlocker> blockers = PossibleBlockerStore.getInstance().getPossibleBlockers();
            List<PossibleBlockerSolution> solutions = PossibleBlockerSolutionStore.getInstance().getPossibleBlockerSolutions();

            // Create and show the FineTuneProbabilityPane
            FineTuneProbabilityListPane fineTunePane = new FineTuneProbabilityListPane(blockers, solutions);

            // Set content pane or show in a dialog (depending on your design)
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




        //action listner
        fineTuneButton.addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent e) {
                loadDropdowns();
                //now we are in the FTP we set drops visible
                blockerDropdown.setVisible(true);
                solutionDropdown.setVisible(true);

            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {

            }

            @Override
            public void ancestorMoved(AncestorEvent event) {

            }


        });
        repaint();
        revalidate();

    }

    private void set(GridBagLayout gridBagLayout) {
        setLayout(gridBagLayout);
    }

    private void loadDropdowns() {
        //fetching PB from PBstore <ths is list>
        List<PossibleBlocker> blockers = PossibleBlockerStore.getInstance().getPossibleBlockers();
        blockerDropdown.removeAllItems(); //clear the dropdown

        for (PossibleBlocker blocker : blockers) {
            blockerDropdown.addItem(blocker.getName()); //add the blocker to drop
        }


        //fetching PBS from PBS store <ths is list>

        List<PossibleBlockerSolution> solutions = PossibleBlockerSolutionStore.getInstance().getPossibleBlockerSolutions();
        solutionDropdown.removeAllItems();  //
        for (PossibleBlockerSolution solution : solutions) {
            solutionDropdown.addItem(solution.getName());
        }


    }


}
