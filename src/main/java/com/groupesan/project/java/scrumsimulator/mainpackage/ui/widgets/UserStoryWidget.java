package com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SprintStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStoryStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.state.UserStoryDeletedState;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.EditUserStoryForm;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels.SimulationSwitchRolePane;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import javax.swing.*;

public class UserStoryWidget extends JPanel implements BaseComponent, Serializable {
    private static final long serialVersionUID = 1L;

    private JLabel id;
    private JLabel points;
    private JLabel businessValue;
    private JLabel name;
    private JLabel desc;
    private JLabel statusLabel;
    private JButton delete;

    private JComboBox<String> statusCombo;
    protected Boolean SprintView = false;

    // TODO: This is a non transient field and this class is supposed to be serializable. this needs
    // to be dealt with before this object can be serialized
    private transient UserStory userStory;
    //  private transient UserStoryController userStoryController; //delete user story from Widget

    //ActionListener actionListener = e -> {};

    private MouseAdapter createEditDialogListener () {
        return new MouseAdapter() {
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
                e.consume();
            }
        };
    }

    public UserStoryWidget(UserStory userStory) {
        this.userStory = userStory;
        this.init();
    }

    public void init() {
        removeAll();


        id = new JLabel(userStory.getId().toString());
        points = new JLabel(userStory.getPointValue() == null ? "" : Double.toString(userStory.getPointValue()));
        businessValue = new JLabel(userStory.getBusinessValue() == null ? "" : Double.toString(userStory.getBusinessValue()));
        name = new JLabel(userStory.getName());
        desc = new JLabel(userStory.getDescription());
        delete = new JButton("Delete");

        delete.setEnabled(SimulationSwitchRolePane.getCurrentRole().equals("Product Owner"));

        statusLabel = new JLabel(userStory.getStatus());
        String[] statuses = {"New", "In Progress", "ReadyForTest", "Done"};
        statusCombo = new JComboBox<>(statuses);
        statusCombo.setSelectedItem(userStory.getStatus());

        MouseAdapter openEditDialog = createEditDialogListener();

        id.addMouseListener(openEditDialog);
        points.addMouseListener(openEditDialog);
        businessValue.addMouseListener(openEditDialog);
        name.addMouseListener(openEditDialog);
        desc.addMouseListener(openEditDialog);
        delete.addActionListener(e -> deleteUserStory());

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


        //Delete Functionality Goes Here

        add(
                delete,
                new CustomConstraints(
                        5, 0, GridBagConstraints.WEST, 0.1, 0.0, GridBagConstraints.HORIZONTAL));


        delete.setVisible(true);

        add(
                statusLabel,
                new CustomConstraints(
                        6, 0, GridBagConstraints.WEST, 0.2, 0.0, GridBagConstraints.HORIZONTAL));





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

    private void deleteUserStory(){

        int check = JOptionPane.showConfirmDialog(this, "Are you sure want to delete?", "Delete User Story",JOptionPane.YES_NO_OPTION);
        if( check == JOptionPane.YES_NO_OPTION){
            userStory.changeState(new UserStoryDeletedState(userStory));
            boolean removed = UserStoryStore.getInstance().removeUserStory(userStory);

            if (removed) {
                System.out.println("User story successfully removed from the store.");
                UserStoryStore.getInstance().removeUserStory(userStory);

                //Special Method
                Container parent = this.getParent();
                if (parent != null) {
                    parent.remove(this);
                    parent.revalidate();
                    parent.repaint();
                }
                else {
                    System.out.println("Warning: Attempted to remove UserStoryWidget from a null parent.");
                }
            }
            else {
                System.out.println("User story was not found in the store.");
            }


        }
    }

    //delete btn restriction to widget
    public void setDeleteBtnVisible(boolean visible) {
        delete.setVisible(visible);
    }

}
