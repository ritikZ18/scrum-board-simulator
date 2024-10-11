

package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlocker;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.PossibleBlockerStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.PossibleBlockerWidget;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class PossibleBlockersListPane extends JFrame implements BaseComponent {
    public PossibleBlockersListPane() {
        this.init();
    }

    private List<PossibleBlockerWidget> widgets = new ArrayList<>();

    public void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Blockers list");
        setSize(400, 300);

        GridBagLayout myGridbagLayout = new GridBagLayout();
        JPanel myJpanel = new JPanel();
        myJpanel.setBorder(new EmptyBorder(20, 10, 20, 10));
        myJpanel.setLayout(myGridbagLayout);

        for (PossibleBlocker possibleBlocker : PossibleBlockerStore.getInstance().getPossibleBlockers()) {
            widgets.add(new PossibleBlockerWidget(possibleBlocker));
        }

        JPanel subPanel = new JPanel();
        subPanel.setLayout(new GridBagLayout());
        int i = 0;
        for (PossibleBlockerWidget widget : widgets) {
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

        JButton newPossibleBlockerButton = new JButton("New Blocker");
        newPossibleBlockerButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        NewPossibleBlockerForm form = new NewPossibleBlockerForm();
                        form.setVisible(true);

                        form.addWindowListener(
                                new java.awt.event.WindowAdapter() {
                                    public void windowClosed(
                                            java.awt.event.WindowEvent windowEvent) {
                                        PossibleBlocker possibleBlocker = form.getPossibleBlockerObject();
                                        PossibleBlockerStore.getInstance().addPossibleBlocker(possibleBlocker);
                                        widgets.add(new PossibleBlockerWidget(possibleBlocker));
                                        int idx = widgets.size() - 1;
                                        subPanel.add(
                                                widgets.get(idx),
                                                new CustomConstraints(
                                                        0,
                                                        idx,
                                                        GridBagConstraints.WEST,
                                                        1.0,
                                                        0.1,
                                                        GridBagConstraints.HORIZONTAL));
                                    }
                                });
                    }
                });
        myJpanel.add(
                newPossibleBlockerButton,
                new CustomConstraints(
                        0, 1, GridBagConstraints.WEST, 1.0, 0.2, GridBagConstraints.HORIZONTAL));

        add(myJpanel);
    }


}

