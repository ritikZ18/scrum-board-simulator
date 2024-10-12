package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Sprint;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SprintStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.SprintWidget;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.UserStoryWidget;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;

import java.util.List;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.GridBagConstraints;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class ViewSprintBacklogPane extends JFrame implements BaseComponent {
    private ArrayList<SprintWidget> sprintWidgets = new ArrayList<>();
    private JPanel userStoryPanel;

    public ViewSprintBacklogPane() {
        this.init();
    }

    public void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("View Sprint Backlog");
        setSize(500, 400);

        GridBagLayout myGridBagLayout = new GridBagLayout();
        JPanel myJPanel = new JPanel();
        myJPanel.setBorder(new EmptyBorder(10, 10, 20, 10));
        myJPanel.setLayout(myGridBagLayout);

        JComboBox<String> SprintselectComboBox = new JComboBox<>();
        for (Sprint sprint : SprintStore.getInstance().getSprints()) {
            SprintselectComboBox.addItem(sprint.getName());
            sprintWidgets.add(new SprintWidget(sprint));
        }
        myJPanel.add(SprintselectComboBox, new CustomConstraints(0, 0, GridBagConstraints.WEST, 1.0, 0.2, GridBagConstraints.PAGE_START));

        userStoryPanel = new JPanel();
        userStoryPanel.setLayout(new GridBagLayout());
        myJPanel.add(new JScrollPane(userStoryPanel), new CustomConstraints(0, 3, GridBagConstraints.WEST, 1.0, 0.8, GridBagConstraints.HORIZONTAL));
        
        JButton SelectSprintButton = new JButton("Select Sprint");
        SelectSprintButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // populate user story panel
                populateUserStoryPanel(SprintselectComboBox.getSelectedItem().toString());
                myJPanel.repaint();
                myJPanel.revalidate();
            }
        });

        myJPanel.add(SelectSprintButton, new CustomConstraints(0, 2, GridBagConstraints.WEST, 1.0, 0.2, GridBagConstraints.HORIZONTAL));
    
        add(myJPanel);
        }
    
    
    public void populateUserStoryPanel(String selectedSprint) {
        userStoryPanel.removeAll();
        
        Sprint sprint = SprintStore.getInstance().getSprintByName(selectedSprint);
        
        List<UserStory> sprintStories = sprint.getUserStories();
        
        if(sprintStories.size() > 0){
            int i = 0;
            for (UserStory userStory : sprintStories) {
                UserStoryWidget userStoryWidget = new UserStoryWidget(userStory);
                userStoryWidget.setSprintView();
                userStoryPanel.add(userStoryWidget, new CustomConstraints(0, i++, GridBagConstraints.WEST, 1.0, 0.1, GridBagConstraints.HORIZONTAL));
            }
        } else {
            JOptionPane.showMessageDialog(null, "Sprint: "+selectedSprint+" has no backlog.");
        }
        userStoryPanel.revalidate();
        userStoryPanel.repaint();
    }

}