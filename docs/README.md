# User Guide

## Features 

### Description of feature.
Juan's Duke program is a personal tasks handler that keeps track of your daily todos.

## Contents
- Quickstart
- Features
    - adding a new todo task: ```todo```
    - adding a new deadline task:  ```deadline``` 
    - adding a new event task: ```event```
    - marking a task as done: ```done```
    - deleting a task: ```delete```
    - finding a task: ```find```
    - listing all tasks: ```list```
    - undoing a task: ```undo```
- FAQ
- Command Summary

### Quickstart
1. Start Juan's Duke program.
2. Duke will show up and look something like this.
![Duke starting page](/Users/juanlie/cs2103t/pw2/ip/src/main/resources/images/Screenshot 2020-09-16 at 6.37.07 PM.jpg)
3. Type the command in the user input box provided located at the bottom.
4. Press enter or click send to send out the command. 
Some command examples that you can try:
    - `todo hw`
    - `deadline project /by 2020-10-02`
    - `event meeting /at school`
    - `undo`
    - `done 1`
    - `delete 1`
    - `list`
    - `find hw`
5. Refer to the Features list below for details of each command.

### Features

#### Adding a todo task: `todo`
Adds a todo to the list of task.

Format: `todo <description>`

Examples: 
- todo homework
- todo laundry

#### Adding a deadline task: `deadline`
Adds a deadline to the list of task.

Format: `deadline <description> /by <time deadline>`
- Time only in the format YYYY-MM-DD

Examples: 
- deadline homework /by 2020-03-15
- deadline project /by Monday


#### Adding an Event task: `event`
Adds an event to the list of task.

Format: `event <description> /at <time or place>`
- Time only in the format YYYY-MM-DD

Examples:
- event meeting /at School
- event lecture /at 2020-05-19

#### Marking a task as done: `done`
Marks a task in the list as done.

Format: `done <task number>`
- Task number is the order shown in the list.

Examples:
- done 1
- done 2

#### Deleting a task: `delete`
Deletes a task in the list.

Format: `delete <task number>`
- Task number is the order shown in the list.

Examples:
- delete 1
- delete 2

#### Finding a task: `find`
Finds a task that matches the keyword.

Format: `find <keyword>`
- Keyword is case-sensitive.

Examples:
- find hw
- find school

#### Listing all tasks: `list`
Lists all tasks in the list.

Format: `list`

Examples:
- list

#### Undoing an action: `undo`
Undo the most recent action.

Format: `undo`

Examples:
- undo

### FAQ
Q: How do I save my list for future use?

A: Duke automatically saves the list.

Q: Can `undo` be used to undo more than one actions?

A: Yes, undo will work until there is no action to undo.

Q: Can I redo an action?

A: Duke does not allow redo.

### Command summary
Action | Format
------------ | -------------
Adding a todo task | `todo <description>`
Adding a deadline task | `deadline <description> /by <time deadline>`
Adding an event task | `event <description> /at <time or place>`
Marking a task as done | `done <task number>`
Deleting a task | `delete <task number>`
Finding a task | `find <keyword>`
Listing all tasks | `list`
Undoing an action | `undo`

