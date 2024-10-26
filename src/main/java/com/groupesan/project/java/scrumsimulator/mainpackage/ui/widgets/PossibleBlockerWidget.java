package com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlocker;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.EditPossibleBlockerForm;
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

public class PossibleBlockerWidget extends JPanel implements BaseComponent, Serializable {
    private static final long serialVersionUID = 1L;

    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel descLabel;
    private JLabel statusLabel;

    // TODO: This is a non transient field and this class is supposed to be serializable. this needs
    // to be dealt with before this object can be serialized
    private transient PossibleBlocker possibleBlocker;

    public PossibleBlockerWidget(PossibleBlocker possibleBlocker) {
        this.possibleBlocker = possibleBlocker;
        this.init();
    }

    public void init() {
        removeAll();

        idLabel = new JLabel(possibleBlocker.getId().toString());
        nameLabel = new JLabel(possibleBlocker.getName());
        descLabel = new JLabel(possibleBlocker.getDescription());
        statusLabel = new JLabel(possibleBlocker.getStatus());

        MouseAdapter openEditDialog = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openEditDialog();
            }
        };

        idLabel.addMouseListener(openEditDialog);
        nameLabel.addMouseListener(openEditDialog);
        descLabel.addMouseListener(openEditDialog);
        statusLabel.addMouseListener(openEditDialog);

        setLayout(new GridBagLayout());

        add(idLabel, new CustomConstraints(0, 0, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        add(nameLabel, new CustomConstraints(1, 0, GridBagConstraints.WEST, 0.2, 0.0, GridBagConstraints.HORIZONTAL));
        add(descLabel, new CustomConstraints(2, 0, GridBagConstraints.WEST, 0.7, 0.0, GridBagConstraints.HORIZONTAL));
        add(statusLabel, new CustomConstraints(3, 0, GridBagConstraints.WEST, 0.3, 0.0, GridBagConstraints.HORIZONTAL));

        revalidate();
        repaint();
    }

    private void openEditDialog() {
        EditPossibleBlockerForm form = new EditPossibleBlockerForm(possibleBlocker);
        form.setVisible(true);

        form.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent windowEvent) {
                updateDisplay();
            }
        });
    }

    private void updateDisplay() {
        idLabel.setText(possibleBlocker.getId().toString());
        nameLabel.setText(possibleBlocker.getName());
        descLabel.setText(possibleBlocker.getDescription());
        statusLabel.setText(possibleBlocker.getStatus());
        revalidate();
        repaint();
    }
}
