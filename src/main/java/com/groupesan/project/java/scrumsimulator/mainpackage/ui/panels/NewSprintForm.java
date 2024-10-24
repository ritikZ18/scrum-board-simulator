package com.groupesan.project.java.scrumsimulator.mainpackage.ui.panels;

import com.groupesan.project.java.scrumsimulator.mainpackage.state.SimulationStateManager;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.Sprint;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SprintFactory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SprintStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStory;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.UserStoryStore;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.BaseComponent;
import com.groupesan.project.java.scrumsimulator.mainpackage.ui.widgets.SprintWidget;
import com.groupesan.project.java.scrumsimulator.mainpackage.impl.SimulationStore;

import com.groupesan.project.java.scrumsimulator.mainpackage.utils.CustomConstraints;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;

import org.json.JSONObject;

public class NewSprintForm extends JFrame implements BaseComponent {
    
    private int sprintLength = 2;

    JTextField nameField = new JTextField();
    JTextArea descArea = new JTextArea();
    SpinnerNumberModel spinnerNumberModel = new SpinnerNumberModel(0, 0, sprintLength, 1);
    JSpinner sprintDays = new JSpinner(spinnerNumberModel);

    DefaultListModel<String> listModel;
    JList<String> usList;

    private SimulationStore simulationStore = new SimulationStore();

    // private String simulationId = new String();
    private JComboBox<String> simulationComboBox;

    public NewSprintForm() {
        this.init();
    }

    public void init() {

        setTitle("New Sprint");
        setSize(400, 300);

        GridBagLayout myGridbagLayout = new GridBagLayout();
        JPanel myJpanel = new JPanel();
        myJpanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        myJpanel.setLayout(myGridbagLayout);

        BorderLayout myBorderLayout = new BorderLayout();

        setLayout(myBorderLayout);

        JLabel nameLabel = new JLabel("Name:");
        myJpanel.add(
                nameLabel,
                new CustomConstraints(
                        0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(
                nameField,
                new CustomConstraints(
                        1, 0, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.HORIZONTAL));

        JLabel descLabel = new JLabel("Description:");
        myJpanel.add(
                descLabel,
                new CustomConstraints(
                        0, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(
                new JScrollPane(descArea),
                new CustomConstraints(
                        1, 1, GridBagConstraints.EAST, 1.0, 0.3, GridBagConstraints.BOTH));

        JLabel pointsLabel = new JLabel("Length (Days):");
        myJpanel.add(
                pointsLabel,
                new CustomConstraints(
                        0, 2, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL));
        myJpanel.add(
                sprintDays,
                new CustomConstraints(
                        1, 2, GridBagConstraints.EAST, 1.0, 0.0, GridBagConstraints.WEST));

        JButton cancelButton = new JButton("Cancel");

        cancelButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });

        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });


        simulationComboBox = new JComboBox<>();

        ArrayList<String> simulationIds = simulationStore.getSimulationsIDs();

        for (String simulationId : simulationIds) {
            simulationComboBox.addItem(simulationId);
        
        }
        myJpanel.add(simulationComboBox, new CustomConstraints(0, 3, GridBagConstraints.WEST, 1.0, 0.2, GridBagConstraints.HORIZONTAL));

        simulationComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sprintLength = simulationStore.getSprintLengthBySimId(simulationComboBox.getSelectedItem().toString());
                spinnerNumberModel.setMaximum(sprintLength);
                spinnerNumberModel.setValue(sprintLength);
                myJpanel.revalidate();
                myJpanel.repaint();
            }
        });

        // listModel = new DefaultListModel<>();
        // for (UserStory userStory : UserStoryStore.getInstance().getUserStories()) {
        //     listModel.addElement(userStory.toString());
        // }

        // usList = new JList<>(listModel);
        // usList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        // JScrollPane scrollPane = new JScrollPane(usList);
        // scrollPane.setPreferredSize(new Dimension(300, 100));

        // JLabel userStoriesLabel = new JLabel("User Stories:");
        // myJpanel.add(
        //         userStoriesLabel,
        //         new CustomConstraints(
        //                 0, 3, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL));
        // myJpanel.add(
        //         usList,
        //         new CustomConstraints(
        //                 1, 3, GridBagConstraints.WEST, 1.0, 0.0, GridBagConstraints.NONE));

        myJpanel.add(
                cancelButton,
                new CustomConstraints(0, 4, GridBagConstraints.EAST, GridBagConstraints.NONE));
        myJpanel.add(
                submitButton,
                new CustomConstraints(1, 4, GridBagConstraints.WEST, GridBagConstraints.NONE));

        add(myJpanel);
    }

    public Sprint getSprintObject() {
        String name = nameField.getText();
        String description = descArea.getText();
        Integer length = (Integer) sprintDays.getValue();

        String simulationId = simulationComboBox.getSelectedItem().toString();

        SprintFactory sprintFactory = SprintFactory.getSprintFactory();

        Sprint mySprint = sprintFactory.createNewSprint(name, description, length, simulationId);

        // int[] selectedIdx = usList.getSelectedIndices();

        // for (int idx : selectedIdx) {
        //     String stringIdentifier = listModel.getElementAt(idx);
        //     for (UserStory userStory : UserStoryStore.getInstance().getUserStories()) {
        //         if (stringIdentifier.equals(userStory.toString())) {
        //             mySprint.addUserStory(userStory);
        //             break;
        //         }
        //     }
        // }

        SprintStore.getInstance().addSprint(mySprint);

        System.out.println(mySprint.getName() +"----"+mySprint.getSimulationID());

        simulationStore.addSprint(mySprint, simulationId);

        return mySprint;
    }

    // public String getSelectedSimulation() {
    //      ;
    // }
}
