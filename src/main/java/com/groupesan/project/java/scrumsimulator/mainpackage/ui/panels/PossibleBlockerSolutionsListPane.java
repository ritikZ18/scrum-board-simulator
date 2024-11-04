package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlockerSolution;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlockerSolutionStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.FineTuneProbabilityWidget;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.PossibleBlockerSolutionWidget;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class PossibleBlockerSolutionsListPane extends JFrame implements BaseComponent {

    private FineTuneProbabilityWidget fineTuneProbabilityWidget;


    public PossibleBlockerSolutionsListPane() {
        this.fineTuneProbabilityWidget = new FineTuneProbabilityWidget();
        this.init();
    }

    private List<PossibleBlockerSolutionWidget> widgets = new ArrayList<>();

    public void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Solutions list for blockers");
        setSize(600, 400);

        GridBagLayout myGridbagLayout = new GridBagLayout();
        JPanel myJpanel = new JPanel();
        myJpanel.setBorder(new EmptyBorder(20, 10, 20, 10));
        myJpanel.setLayout(myGridbagLayout);

        for (PossibleBlockerSolution possibleBlockerSolution : PossibleBlockerSolutionStore.getInstance().getPossibleBlockerSolutions()) {
            widgets.add(new PossibleBlockerSolutionWidget(possibleBlockerSolution, fineTuneProbabilityWidget));
        }

        JPanel subPanel = new JPanel();
        subPanel.setLayout(new GridBagLayout());
        int i = 0;
        for (PossibleBlockerSolutionWidget widget : widgets) {
            subPanel.add(
                    widget,
                    new CustomConstraints(
                            0,
                            i++,
                            GridBagConstraints.WEST,
                            1.0,
                            0.1,
                            GridBagConstraints.HORIZONTAL));
        }

        myJpanel.add(
                new JScrollPane(subPanel),
                new CustomConstraints(
                        0, 0, GridBagConstraints.WEST, 1.0, 0.8, GridBagConstraints.HORIZONTAL));

        JButton newPossibleBlockerSolutionButton = new JButton("New Solution");
        newPossibleBlockerSolutionButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        NewPossibleBlockerSolutionForm form = new NewPossibleBlockerSolutionForm();
                        form.setVisible(true);

                        form.addWindowListener(
                                new java.awt.event.WindowAdapter() {
                                    public void windowClosed(
                                            java.awt.event.WindowEvent windowEvent) {
                                        PossibleBlockerSolution possibleBlockerSolution = form.getPossibleBlockerSolutionObject();
                                        if (possibleBlockerSolution != null) {
                                            PossibleBlockerSolutionStore.getInstance().addPossibleBlockerSolution(possibleBlockerSolution);
                                            addPossibleBlockerSolutionWidget(possibleBlockerSolution, fineTuneProbabilityWidget);
                                        }
                                    }
                                });
                    }

                    private void addPossibleBlockerSolutionWidget(PossibleBlockerSolution possibleBlockerSolution, FineTuneProbabilityWidget fineTuneProbabilityWidget) {
                        PossibleBlockerSolutionWidget widget = new PossibleBlockerSolutionWidget(possibleBlockerSolution, fineTuneProbabilityWidget);
                        widgets.add(widget);
                        int idx = widgets.size() - 1;
                        subPanel.add(
                                widget,
                                new CustomConstraints(
                                        0,
                                        idx,
                                        GridBagConstraints.WEST,
                                        1.0,
                                        0.1,
                                        GridBagConstraints.HORIZONTAL));
                        subPanel.revalidate();
                        subPanel.repaint();
                    }
                });
        myJpanel.add(
                newPossibleBlockerSolutionButton,
                new CustomConstraints(
                        0, 1, GridBagConstraints.WEST, 1.0, 0.2, GridBagConstraints.HORIZONTAL));

        add(myJpanel);
    }


}

