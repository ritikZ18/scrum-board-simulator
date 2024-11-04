package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Sprint;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SprintStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStoryStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.state.UserStorySelectedState;
import com.groupesan.project.java.scrumsimulator.mainpackage.state.UserStoryUnselectedState;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.SprintWidget;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.UserStoryWidget;
import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class PopulateSprintBacklogPane extends JFrame implements BaseComponent {

    private ArrayList<SprintWidget> sprintWidgets = new ArrayList<>();
    private JComboBox<String> selectComboBox;
    private JPanel selectedSubPanel;

    public PopulateSprintBacklogPane() {
        this.init();
    }

    public void init() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Product Backlog");
        setSize(600, 600);

        GridBagLayout myGridbagLayout = new GridBagLayout();
        JPanel myJpanel = new JPanel();
        myJpanel.setBorder(new EmptyBorder(10, 10, 20, 10));
        myJpanel.setLayout(myGridbagLayout);

        selectComboBox = new JComboBox<>();
        populateUserStoryComboBox();
        myJpanel.add(selectComboBox, new CustomConstraints(0, 0, GridBagConstraints.WEST, 1.0, 0.2, GridBagConstraints.PAGE_START));

        selectedSubPanel = new JPanel();
        selectedSubPanel.setLayout(new GridBagLayout());
        populateSelectedUserStoriesPanel();
        myJpanel.add(new JScrollPane(selectedSubPanel), new CustomConstraints(0, 2, GridBagConstraints.WEST, 1.0, 0.8, GridBagConstraints.HORIZONTAL));

        // Select User Stories
        JButton SelectUSButton = new JButton("Select");
        SelectUSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectUserStory();
            }
        });
        myJpanel.add(SelectUSButton, new CustomConstraints(0, 4, GridBagConstraints.WEST, 1.0, 0.2, GridBagConstraints.HORIZONTAL));

        // Deselect all User Stories
        JButton DeselecAllUS = new JButton("Deselect All");
        DeselecAllUS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deselectAllUserStories();
            }
        });
        myJpanel.add(DeselecAllUS, new CustomConstraints(0, 5, GridBagConstraints.WEST, 1.0, 0.2, GridBagConstraints.HORIZONTAL));

        // Select from sprints
        JComboBox<String> SprintselectComboBox = new JComboBox<>();
        for (Sprint sprint : SprintStore.getInstance().getSprints()) {
            SprintselectComboBox.addItem(sprint.getName());
            sprintWidgets.add(new SprintWidget(sprint));
        }
        myJpanel.add(SprintselectComboBox, new CustomConstraints(0, 6, GridBagConstraints.WEST, 1.0, 0.2, GridBagConstraints.HORIZONTAL));

        // Move to sprint backlog
        JButton MoveButton = new JButton("Move to Sprint Backlog");
        MoveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(SprintselectComboBox.getSelectedItem() != null){
                moveToSprintBacklog((String) SprintselectComboBox.getSelectedItem());
                } else {
                    JOptionPane.showMessageDialog(null, "Sprint not selected.");
                }
        }
        });
        myJpanel.add(MoveButton, new CustomConstraints(0, 8, GridBagConstraints.WEST, 1.0, 0.2, GridBagConstraints.HORIZONTAL));
        
        JButton SelectRandomUSButton = new JButton("Move Random User Stories");
        SelectRandomUSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(SprintselectComboBox.getSelectedItem() != null){
                    selectRandomUS((String) SprintselectComboBox.getSelectedItem());
                } else {
                    JOptionPane.showMessageDialog(null, "Sprint not selected.");
                }
            }
        });

        myJpanel.add(SelectRandomUSButton, new CustomConstraints(0, 7, GridBagConstraints.WEST, 1.0, 0.2, GridBagConstraints.HORIZONTAL));

        add(myJpanel);
    }

    private void populateUserStoryComboBox() {
        selectComboBox.removeAllItems();
        for (UserStory userStory : UserStoryStore.getInstance().getUserStories()) {
            if (userStory.getUserStoryState() instanceof UserStoryUnselectedState && !userStory.getIsSprintBacklog()) {
                selectComboBox.addItem(userStory.toString());
            }
        }
    }

    private void populateSelectedUserStoriesPanel() {
        selectedSubPanel.removeAll();
        int i = 0;
        for (UserStory userStory : UserStoryStore.getInstance().getUserStories()) {
            if (userStory.getUserStoryState() instanceof UserStorySelectedState && !userStory.getIsSprintBacklog()) {
                UserStoryWidget userStoryWidget = new UserStoryWidget(userStory);
                userStoryWidget.setSprintView();
                selectedSubPanel.add(userStoryWidget, new CustomConstraints(0, i++, GridBagConstraints.WEST, 1.0, 0.1, GridBagConstraints.HORIZONTAL));
            }
        }
        selectedSubPanel.revalidate();
        selectedSubPanel.repaint();
    }

    private void selectUserStory() {
        String selectedUserStoryString = (String) selectComboBox.getSelectedItem();
        for (UserStory userStory : UserStoryStore.getInstance().getUserStories()) {
            if (userStory.toString().equals(selectedUserStoryString)) {
                userStory.changeState(new UserStorySelectedState(userStory));
                break;
            }
        }
        populateUserStoryComboBox();
        populateSelectedUserStoriesPanel();
    }

    private void deselectAllUserStories() {
        for (UserStory userStory : UserStoryStore.getInstance().getUserStories()) {
            if (userStory.getUserStoryState() instanceof UserStorySelectedState) {
                userStory.changeState(new UserStoryUnselectedState(userStory));
            }
        }
        populateUserStoryComboBox();
        populateSelectedUserStoriesPanel();
    }

    private void moveToSprintBacklog(String sprintName) {
        Sprint sprint = SprintStore.getInstance().getSprintByName(sprintName);
        for (UserStory userStory : UserStoryStore.getInstance().getUserStories()) {
            if (userStory.getUserStoryState() instanceof UserStorySelectedState && !userStory.getIsSprintBacklog()) {
                sprint.addUserStory(userStory);
                userStory.setIsSprintBacklog();
            }
        }
            
        
        populateUserStoryComboBox();
        populateSelectedUserStoriesPanel();
    }

    public void selectRandomUS(String sprintName) {
        SecureRandom secureRandom = new SecureRandom();
        int randomUSCount;
        StringBuffer userStoriesString = new StringBuffer();
        ArrayList<UserStory> randomUserStories = new ArrayList<>();
        ArrayList<UserStory> unselectedUserStories = new ArrayList<>();
        Sprint selectedSprint = SprintStore.getInstance().getSprintByName(sprintName);
        
        for (UserStory userStory : UserStoryStore.getInstance().getUserStories()) {
            if (userStory.getUserStoryState() instanceof UserStoryUnselectedState && !userStory.getIsSprintBacklog()) {
                unselectedUserStories.add(userStory);
            }
        }
    
        if (unselectedUserStories.size() > 0) {
            randomUSCount = secureRandom.nextInt(unselectedUserStories.size()) + 1;
        
            while (randomUSCount > 0 && !(unselectedUserStories.isEmpty())) {
                int randomUSIdx = secureRandom.nextInt(unselectedUserStories.size());
                randomUserStories.add(unselectedUserStories.get(randomUSIdx));
                unselectedUserStories.remove(randomUSIdx);
                randomUSCount--;
            }
        }
    
        for (UserStory userStory : randomUserStories) {
            userStory.changeState(new UserStorySelectedState(userStory));
            userStory.setIsSprintBacklog();
            selectedSprint.addUserStory(userStory);
            userStoriesString.append(userStory.getName()).append("\n");
        }
    
        if (randomUserStories.size() > 0) {
            userStoriesString.append("The above user stories are added to ").append(selectedSprint.getName()).append(".");
        } else {
            userStoriesString.append("No user stories were selected.");
        }
    
        JOptionPane.showMessageDialog(null, userStoriesString.toString());
        populateUserStoryComboBox();
        populateSelectedUserStoriesPanel();
}
    
}
