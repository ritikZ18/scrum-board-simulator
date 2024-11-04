## How to Build and Run

This project uses gradle as its build tool. Specifically, we use Gradle 8.10.1 and Java SDK 21
Therefore, you can use the `gradlew` and `gradlew.bat` executables to run gradle (depending on your platform).

### Building
To build the project, run the following gradle command in the root directory of the project:

### Windows/Command Prompt/PowerShell:
`.\gradlew.bat build`

### Mac/Linux/Git Bash/WSL:
`./gradlew build`

### Running
To run the project, run the following gradle command in the root directory of the project:  

### Windows/Command Prompt/PowerShell:
`.\gradlew.bat run`

### Mac/Linux/Git Bash/WSL:
`./gradlew run`

### Cleaning
To clean the project (remove all build artifacts), run the following gradle command in the root directory of the project:

### Windows/Command Prompt/PowerShell:
`.\gradlew.bat clean`

### Mac/Linux/Git Bash/WSL:
`./gradlew clean`

# Sprint 2
# Simulation flow to generate burndown chart
- Click on Create Simulation in Demo Panel, add simulation name,number of sprints, and sprint length and click on create simulation button.
- Click on Sprints button in Demo Panel. Click on New Sprint, add parameters and submit.
- Click on Populate Sprint Backlog  in Demo Panel. Select the stories from dropdown, click on Select. Select Sprint and move to Sprint backlog.
- Click on View Sprint Backlog in Demo Panel. Click on Select sprint.

# About the Use Case (Start Sprint Cycle)
- The Start Sprint Cycle is not a part of the SBS document but it is placed to generate the outcomes of a simulation in a burndown chart form.

How to test

#### Switch Role
- Click "Switch Role" -> Click "Scrum Master" -> Click "Switch Role" -

#### Create New Simulation
- Click "Create Simulation" -> Populate all fields -> Click "Create Simulation"

#### Create Sprints
- Click "Sprints" -> Populate fields -> Select the Simulation ID -> Reapeat to create Multiple Sprints.

#### Populate Sprint Backlog
- Check "Move User Story to Sprint" (Button: Populate Sprint Backlog)

#### Start Simulation 
- Click "Start Simulation" -> Click "Update User Story Status" -> Update completed status and completion date for all user stories

#### Stop Simulation

- Click Stop Simulation -> View burndown chart in "stats" folder.

**Note**: 
- Start and stop simulation will run one sprint cycle. To run each sprint cycle click start and stop simulation.
- The date range for Sprint 1 will be set from today's date to sprint length. The start date of the next sprint will be one day after the previous sprint end date to sprint length.
- If three sprints were run for a simulation three burndown chart will be created for each sprint.

# About the Use Case (SRS_UC_06)

- When development team resolve the blocker from the blocker list by selecting a possible solution they should update the status to resolved else keep it as unresolved.

How to test

- Click on “Possible Blockers” button —> click on any one of the listed blocker and select the required status.

# About the Use Case (SRC_UC_02)
User Story Deletion
Product Owner assumes role and is able to delete user story in the user stories wigdet in DemoPane, which will be deleted from entire simualtion

How to test

- onClick "User Stories Widget" -->  click "Delete" , selected User Story is deleted.

# About the Use Case (SRC_UC_08)
Fine Tune Probabilities
Scrum Administartor is only able to invoke the Fine Tune Probability functionality which selects the Blockers and Solutions from their respective widgets List , which are seen in the Fine tune proabability Dropdowns respectively.

How to test

- "Switch Role" --> Scrum Administrator --> "Fine Tune Probability"(FTP) sets to visible in demopane --> onClick "FTP" select Blocker and Solution from the dropdown --> select "Set or Random" btn to set proabability, which will be set and reflected in the Possible Blocker and Possible Blocker Solution widgets

# About the Use Case (SRS_UC_07)

How to test

- click on 'Possible Blocker'
Select 'User story' from dropdown -> click on 'Add Spike'.
The system notifies the user that a spike has been added for the selected user story and the user story is marked as blocked.

After a spike is added, the user must confirm if management has provided the necessary information .
The user makes this decision by clicking 'Yes' or 'No'.

Click 'Yes' -> the spike is considered successful at this stage.
Click 'No'-> the spike fails, and the user story is marked as blocked, preventing any status updates.

Handle Spike Outcome:

If Management Feedback is Positive:

Click on 'Allocate More Resources'
If selected, the status of the user story changes to 'Resources Requested'.
The user is again prompted to decide if management has provided additional resources.
Click 'Yes'-> proceed to the next step.
Click 'No' -> the spike fails.

Click on 'Spike Success' -> Spike Success, and the user story is re-enabled to resume in the sprint.

# Sprint 1
# About the Use Case (SRS_UC_04)

- User Should be able to move user stories from product backlog to sprint backlog.

SRS_UC_04_Variation

- User should be able to move randomly selected user stories from product backlog to sprint backlog.


How to test

#### Create new sprint
 
 - Click "Sprints" -> Click "New Sprint" -> Fill all fields -> Click "Submit"

#### Move User Story to Sprint

 - Click "Populate Sprint Backlog" -> Select User Story from dropdown-> Select Sprint from dropdown -> Click "Move to Sprint Backlog"

#### Deselect All User Stories

 - Click "Deselect"

#### Move Random User Story

 - Select Sprint from dropdown -> Click "Move Random User Story"

#### View Sprint Backlog

 - Click "View Sprint Backlog" -> Select Sprint from dropdown -> Click "Select"


# About the Use Case (SRS_UC_02)

 - Up on creation of a UserStory, it includes a business value attribute in New User Story Form and there are restrictions for name and description to be mandatory while submitting a form. This can only be altered by the Product Owner. Other roles, including the Scrum Master and Developer, do not have permission to modify this attribute.

 - When a new possible blocker is added, it will automatically appear in the system without the need for a page reload, and it can be edited repeatedly.

# About the Use Case (SRS_UC_03)
 - CLick 'General Page' -> User can populate sprint count and sprint duration
 - Click 'Switch Role' -> select 'Scrum Master'
 - Student with scrum role is only able to populate the sprint count and sprint duration

# Able to edit user stories multiple times
- Click 'User Story' -> Click on user story
- Update values and click on submit
- Repeat multiple times

# List of possible solutions
- Click on 'Posible Blocker Solutions'
- User can populate and update the list of blockers







