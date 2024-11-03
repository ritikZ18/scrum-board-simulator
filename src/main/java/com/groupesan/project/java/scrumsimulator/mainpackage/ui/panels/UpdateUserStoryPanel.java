package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.toedter.calendar.JDateChooser;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SimulationStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Sprint;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SprintStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import org.json.JSONArray;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class UpdateUserStoryPanel extends JFrame {

    private UserStory selectedUserStory;
    private Sprint runningSprint;

    public UpdateUserStoryPanel() {
        init();
    }
    
    private void init() {
        setTitle("Update User Story Status");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        placeComponents(panel);
    }

    private void placeComponents(JPanel panel) {
        
        JLabel userStoryLabel = new JLabel("Select User Story:");
        userStoryLabel.setBounds(10, 20, 120, 25);
        panel.add(userStoryLabel);

        JComboBox<String> userStoryComboBox = new JComboBox<>();
        userStoryComboBox.setBounds(150, 20, 200, 25);
        panel.add(userStoryComboBox);

        getRunningSimulationSprints();

        if (runningSprint.getUserStories().size() != 0) {
            for (UserStory userStory : runningSprint.getUserStories()) {
                userStoryComboBox.addItem(userStory.getName());
            }

            JLabel statusLabel = new JLabel("Select Status:");
            statusLabel.setBounds(10, 50, 120, 25);
            panel.add(statusLabel);

            String[] statusOptions = {"new", "in progress", "ready for test", "completed"};
            JComboBox<String> statusComboBox = new JComboBox<>(statusOptions);
            statusComboBox.setBounds(150, 50, 200, 25);
            panel.add(statusComboBox);

            JButton updateButton = new JButton("Update Status");
            updateButton.setBounds(150, 110, 150, 25);
            panel.add(updateButton);

            JDateChooser dateChooser = createDateChooser();
            dateChooser.setBounds(150, 80, 200, 25);
            panel.add(dateChooser);

            updateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedUserStoryString = (String) userStoryComboBox.getSelectedItem();
                    String selectedStatus = (String) statusComboBox.getSelectedItem();

                    for (UserStory userStory : runningSprint.getUserStories()){
                        if(selectedUserStoryString.equals(userStory.getName())) {
                            selectedUserStory = userStory;
                        }
                    }
                    
                    if(selectedUserStory != null && dateChooser.getDate() != null){
                    selectedUserStory.setStatus(selectedStatus);
                        if(dateChooser.getDate() != null && selectedStatus == "completed") {
                                selectedUserStory.setCompletionDate(dateChooser.getDate());
                                JOptionPane.showMessageDialog(null, "Status updated successfully!");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Please select all fields");
                        }

                        dispose();
                }
            });
        }
    }

    private JDateChooser createDateChooser() {
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setDate(runningSprint.getSprintStartDate());
        dateChooser.setMinSelectableDate(runningSprint.getSprintStartDate());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(runningSprint.getSprintStartDate());
        calendar.add(Calendar.DAY_OF_MONTH, runningSprint.getLength());
        dateChooser.setMaxSelectableDate(calendar.getTime());

        return dateChooser;
    }

    public void getRunningSimulationSprints() {
        JSONArray sprintArray = SimulationStore.getInstance().getRunningSimulationSprints();
    
        for (Object obj : sprintArray) {
            Sprint currentSprint = SprintStore.getInstance().getSprintByName(obj.toString());
            if (currentSprint.getSprintRunning()) {
                runningSprint = currentSprint;
                break;
            }
        }
}
}
