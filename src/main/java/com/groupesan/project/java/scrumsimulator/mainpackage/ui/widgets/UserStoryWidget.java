package com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.EditUserStoryForm;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComboBox;

public class UserStoryWidget extends JPanel implements BaseComponent, Serializable {
    private static final long serialVersionUID = 1L;

    private JLabel id;
    private JLabel points;
    private JLabel businessValue;
    private JLabel name;
    private JLabel desc;
    private JLabel statusLabel;

    private JComboBox<String> statusCombo;
    protected Boolean SprintView = false;

    // TODO: This is a non transient field and this class is supposed to be serializable. this needs
    // to be dealt with before this object can be serialized
    private transient UserStory userStory;

    //ActionListener actionListener = e -> {};

    MouseAdapter openEditDialog =
            new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    EditUserStoryForm form = new EditUserStoryForm(userStory);
                    form.setVisible(true);

                    form.addWindowListener(
                            new java.awt.event.WindowAdapter() {
                                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                                    init();
                                }
                            });
                }
            };

    public UserStoryWidget(UserStory userStory) {
        this.userStory = userStory;
        this.init();
    }

    public void init() {
        removeAll();


        id = new JLabel(userStory.getId().toString());
        id.addMouseListener(openEditDialog);
        points = new JLabel(userStory.getPointValue() == null ? "" : Double.toString(userStory.getPointValue()));
        points.addMouseListener(openEditDialog);
        businessValue = new JLabel(userStory.getBusinessValue() == null ? "" : Double.toString(userStory.getBusinessValue()));
        businessValue.addMouseListener(openEditDialog);
        name = new JLabel(userStory.getName());
        name.addMouseListener(openEditDialog);
        desc = new JLabel(userStory.getDescription());
        desc.addMouseListener(openEditDialog);
        statusLabel = new JLabel(userStory.getStatus());
        String[] statuses = {"New", "In Progress", "ReadyForTest", "Done"};
        statusCombo = new JComboBox<>(statuses);
        statusCombo.setSelectedItem(userStory.getStatus());

        statusCombo.addActionListener(e -> {
            String selectedStatus = (String) statusCombo.getSelectedItem();
            updateStatus(selectedStatus); // Update both UserStory and displayed label
        });


        GridBagLayout myGridBagLayout = new GridBagLayout();

        setLayout(myGridBagLayout);

        add(
                id,
                new CustomConstraints(
                        0, 0, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        add(
                points,
                new CustomConstraints(
                        1, 0, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));
        add(
                businessValue,
                new CustomConstraints(
                        2, 0, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));

        add(
                name,
                new CustomConstraints(
                        3, 0, GridBagConstraints.WEST, 0.2, 0.0, GridBagConstraints.HORIZONTAL));
        add(
                desc,
                new CustomConstraints(
                        4, 0, GridBagConstraints.WEST, 0.7, 0.0, GridBagConstraints.HORIZONTAL));
        add(
                statusLabel,
                new CustomConstraints(5, 0, GridBagConstraints.WEST, 0.2, 0.0, GridBagConstraints.HORIZONTAL));

        revalidate();
        repaint();
    }

    public void updateStatus(String newStatus) {
        userStory.setStatus(newStatus);
        statusLabel.setText(newStatus); // Update the label text
    }

    public Boolean setSprintView(){
        this.SprintView = true;
        return SprintView;
    }
}
