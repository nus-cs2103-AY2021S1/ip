# User Guide

## A. Quick Start
1. Ensure you have Java 11 or above installed in your Computer.

2. Download the latest duke.jar from the releases page of this repo.

3. Copy the file to the folder you want to use as the home folder to run Duke.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.

![Screenshot of Duke](./Screenshot.JPG)

## B. Features 
- Add a task that can either be a todo, deadline, or event.
- Delete any of the tasks added.
- View all the tasks you have keyed in.
- Mark any task done. 
- Take down any quick short notes. 
- Save these tasks to you computer


## C. Usage

#### 1. Greet Duke

Say Hi to Duke!

Example of usage: <br/>
`hi` , `yo` , `hello`

Expected outcome: <br/>
`outcome`

#### 2. Add a task

A task can either be a todo, deadline or event. <br/>
A todo needs the description of the task. <br/>
A deadline needs both the description of the task and the deadline to complete it by. <br/>
An event needs both the description of the task and the date of the event. 

Format <br/>
1. To add a todo: `todo (description)`
2. To add a deadline: `deadline (description) / (date)`
3. To add an event : `event (description)  / (date)`

Example of usage: <br/>
`todo finish cs2103t tasks` <br/> 
Adds a todo with the description "finish cs2103 tasks" <br/>
`deadline finish cs2103t tasks / 18092020` <br/> 
Adds a deadline with the description "finish cs2103 tasks" by the deadline 18-09-2020<br/>
`event cs2103t team meeting / 20092020` <br/> 
Adds a event with the description "cs2103t team meeting" on the date 18-09-2020<br/>

Ensure your date is keyed in `ddmmyyyy` format. 

Expected outcome:<br/>
`outcome`

#### 3. Lists all tasks

This lists all the tasks currently stored. 

Example of usage: <br/>
`list`

Expected outcome:<br/>
`outcome`

#### 4. Marks a specific task done

This marks the specified task done, regardless of which type of task it is (i.e. works for todo, deadline, event).
You need to know the number of the task you wish to mark done, which can be found by the list command. 
The number should be a positive number and within than the number of tasks you have. 

Format:  <br/>
 `done INDEX` <br/> 
 This marks the task done at the specified index.
 
Example of usage: <br/>
`done 1`  <br/>
This marks the first task done.<br/>
`done 13` <br/>
This marks the 13th task done.

Expected outcome:<br/>
`outcome`

#### 5. Deletes a specific task

This deletes the specified task, regardless of which type of task it is (i.e. works for todo, deadline, event).
You need to know the number of the task you wish to delete, which can be found by the list command. 
The number should be a positive number and within than the number of tasks you have. 
 
Format:  <br/>
`delete INDEX` <br/>
This deletes the task at the specified index
 
Example of usage: <br/>
`delete 1`  <br/>
This deletes the first task.<br/>
`delete 13` <br/>
This deletes the 13th task.
 
Expected outcome:<br/>
`outcome`

#### 6. Finds a task

This helps you search for a task that matches your input.

Format: <br/>
`find (input)` <br/>
`search (input)`
This helps you search for a task that matches the input given.

Example of usage: <br/>
`find cs2103t` 
This finds all tasks that includes cs2103t.

Expected outcome:<br/>
`outcome`

#### 7. Leave the conversation

Say bye to duke.

Example of usage: <br/>
`bye` , `cya`

Expected outcome:<br/>
`outcome`
