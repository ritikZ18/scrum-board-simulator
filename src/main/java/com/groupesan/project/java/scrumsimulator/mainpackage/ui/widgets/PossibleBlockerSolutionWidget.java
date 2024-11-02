package com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlockerSolution;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.EditPossibleBlockerSolutionForm;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.io.Serializable;

public class PossibleBlockerSolutionWidget extends JPanel implements BaseComponent, Serializable {
    private static final long serialVersionUID = 1L;

    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel solLabel;
    private JLabel probabilityLabel;

    private transient PossibleBlockerSolution possibleBlockerSolution;
    private  transient FineTuneProbabilityWidget fineTuneProbabilityWidget;

    public PossibleBlockerSolutionWidget(PossibleBlockerSolution possibleBlockerSolution, FineTuneProbabilityWidget fineTuneProbabilityWidget) {
        this.possibleBlockerSolution = possibleBlockerSolution;
        this.fineTuneProbabilityWidget = fineTuneProbabilityWidget != null ? fineTuneProbabilityWidget : new FineTuneProbabilityWidget();
        this.initLabels();
        this.init();
    }

    public void initLabels(){
        idLabel = new JLabel(possibleBlockerSolution.getId().toString());
        nameLabel = new JLabel(possibleBlockerSolution.getName());
        solLabel = new JLabel(possibleBlockerSolution.getSolution());
        probabilityLabel = new JLabel("Probability: " + possibleBlockerSolution.getSolutionProbability() + "%");
    }

    public void init() {
        removeAll();

        idLabel = new JLabel(possibleBlockerSolution.getId().toString());
        nameLabel = new JLabel(possibleBlockerSolution.getName());
        solLabel = new JLabel(possibleBlockerSolution.getSolution());

        MouseAdapter openEditDialog = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openEditDialog();
            }
        };

        idLabel.addMouseListener(openEditDialog);
        nameLabel.addMouseListener(openEditDialog);
        solLabel.addMouseListener(openEditDialog);

        setLayout(new GridBagLayout());

        add(idLabel, new CustomConstraints(0, 0, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        add(nameLabel, new CustomConstraints(1, 0, GridBagConstraints.WEST, 0.2, 0.0, GridBagConstraints.HORIZONTAL));
        add(solLabel, new CustomConstraints(2, 0, GridBagConstraints.WEST, 0.7, 0.0, GridBagConstraints.HORIZONTAL));
        add(probabilityLabel, new CustomConstraints(0, 3, GridBagConstraints.WEST, 1.0, 0.0, GridBagConstraints.HORIZONTAL)); // Add to the panel

        revalidate();
        repaint();
    }

    private void openEditDialog() {
        EditPossibleBlockerSolutionForm form = new EditPossibleBlockerSolutionForm(possibleBlockerSolution);
        form.setVisible(true);

        form.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent windowEvent) {
                updateDisplay();
            }
        });
    }

    private void updateDisplay() {
        idLabel.setText(possibleBlockerSolution.getId().toString());
        nameLabel.setText(possibleBlockerSolution.getName());
        solLabel.setText(possibleBlockerSolution.getSolution());
        probabilityLabel.setText("Probability: "+possibleBlockerSolution.getSolutionProbability()+ "%");

        revalidate();
        repaint();
    }
}
