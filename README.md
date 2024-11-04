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



