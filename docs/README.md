# User Guide

## Features 

### Feature 1 
Description of feature.

## Usage

### `todo` - Create New To Do Task

Create a new to do task and add to the user's task list.

Command format:

`todo <string: description>`

Example of usage:

`todo Finish CS2103T Project`

Expected outcome:

```
<<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>>
  Your task has been recorded.
    [T][✘] Finish CS2103T Project
  You have 1 tasks currently.
<<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>>
```

### `deadline` - Create New Deadline Task

Create a new deadline task and add to the user's task list.

Command format:

`deadline <string: description> /by <string: yyyy-mm-dd>`

Example of usage:

`deadline CCA Registration /by 2020-09-14`

Expected outcome:

```
<<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>>
  Your task has been recorded.
    [D][✘] CCA Registration (by: Sep 14 2020)
  You have 2 tasks currently.
<<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>>
```

### `event` - Create New Event Task

Create a new event task and add to the user's task list.

Command format:

`event <string: description> /at <string: yyyy-mm-dd>`

Example of usage:

`event Application Release /at 2020-09-18)`

Expected outcome:

```
<<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>>
  Your task has been recorded.
    [E][✘] Application Release (at: Sep 18 2020)
  You have 2 tasks currently.
<<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>>
```

### `list`  - List All Tasks

List all the to dos, deadlines, and events that the user have.

Command format:

`list`

Example of usage: 

`list`

Expected outcome:

```
<<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>>
  Let me list out all your tasks...
  1. [T][✓] Finish CS2103T Project
  2. [D][✘] CCA Registration (by: Sep 14 2020)
  3. [E][✘] Application Release (at: Sep 18 2020)
<<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>>
```

### `done` - Mark Task As Completed

Mark a to do, deadline, or event as completed.

Command format:

`done <integer: task number>`

Example of usage:

`done 2`

Expected outcome:

```
<<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>>
  Congratulations for finishing this task!
  Let me mark this as done for you.
    [D][✓] CCA Registration (by: Sep 14 2020)
<<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>>
```

### `update` - Change Task Details

Change task description, time, and completion status.

Command format:

`update <integer: task number> description <string: new description>`

`update <integer: task number> time <string: yyyy-mm-dd>`

`update <integer: task number> mark <integer: 1 for done or 0 for not done>`

Example of usage:

`update 2 description NUS Angklung CCA Registration`

Expected outcome:

```
<<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>>
  Your task has been updated
    [D][✓] NUS Angklung CCA Registration (by: Sep 14 2020)
<<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>>
```

### `find` - Find Task From List

Find all tasks with description that matches the keyword.

Command format:

`find <string: keyword>`

Example of usage:

`find Release`

Expected outcome:

```
<<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>>
  Let me list out the matching tasks for you...
  1. [E][✘] Application Release (at: Sep 18 2020)
<<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>>
```

### `delete` - Delete Task From List

Delete a certain task from list.

Command format:

`delete <integer: task number>`

Example of usage:

`delete 1`

Expected outcome:

```
<<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>>
  Okay, I will remove this task for you
    [T][✓] Finish CS2103T Project
  You have 2 tasks currently.
<<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>>
```

### `bye` - Exit Application

Prints out a goodbye message and exits the application after 1 second.

Command format:

`bye`

Example of usage:

`bye`

Expected outcome:

```
<<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>>
██████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████
██                  ████      ████████  ██████████████████      ████    ██  ▒▒██████████  ████████  ████████        ░░██    ██████████
██                  ████      ████████    ████████  ██████      ██      ██    ████████    ░░████▒▒    ████            ██      ▓▓      
██                  ████      ██████      ████████    ▒▒██      ██              ██████      ████                              ██      
████      ████      ████      ██████        ██████              ██            ████████                        ████            ██      
████      ████      ▒▒        ████          ▒▒████              ██          ████████████            ██      ██████            ██      
████      ████                ████    ░░      ████              ██        ████████████████          ██      ██████            ██      
████      ████                ██                ██              ██          ██████████████        ████      ████▒▒            ██      
████      ████      ████    ██░░                ▒▒      ██      ██            ▓▓██████████        ████                ██              
████      ████      ████    ██        ████              ████░░  ██            ████████████        ████▒▒              ██              
████      ████      ████    ██▒▒      ████    ████      ██████████      ██    ████████████        ██████░░          ██████          ██
████████████████████████████████████████████████████████████████████████████████████████████████████████████    ████████████    ██████
██████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████
████████████████████████████████████████████████████████████████      ████████████████████████████████████████████████████████████████
████████████████████████████████████████████████████████████            ██████████████████████████████████████████████████████████████
████████████████████████████████████████████████████████████      ██    ██████████████████████████████████████████████████████████████
████████████████████████████████████████████████████████████            ██████████████████████████████████████████████████████████████
████████████████████████████████████████████████████████████▓▓        ██  ████████████████████████████████████████████████████████████
██████████████████████████████████████████████████████████                  ██████████████████████████████████████████████████████████
██████████████████████████████████████████████████████████      ██          ██████████████████████████████████████████████████████████
██████████████████████████████████████████████████████████                  ██████████████████████████████████████████████████████████
██████████████████████████████████████████████████████████▒▒              ████████████████████████████████████████████████████████████
██████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████
██████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████
██████████████████        ████████████      ██████████      ██████▒▒        ██████▒▒        ██████  ██████░░████████████▒▒  ██████████
██████████████              ░░████            ████            ██              ██              ██    ██████    ░░            ██████████
████████████░░              ████                                                                      ██                    ██████████
████████████      ░░████░░████        ████            ████            ██░░            ██    ▒▒██              ██      ████████████████
██████████      ████                ██████          ██████            ████                  ░░████          ▒▒██          ████████████
██████████      ████                ██████          ██████            ████                      ████        ████          ████████████
██████████      ██████              ████            ████              ██      ██      ████      ████      ██████            ██████████
██████████░░                ▒▒                ██              ██              ██                ████      ██████            ██████████
████████████                ████            ████            ░░██            ████              ██████      ██████            ██████████
██████████████            ████████          ██████          ████          ██████            ████████      ██████            ██████████
<<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>>
```