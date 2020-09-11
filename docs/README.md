# User Guide

## Features 

### Feature 1 
Description of feature.

## Usage

### `todo` - Create a New To Do Task

Create a new to do task and add to the user's task list.

Command form:

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

### `deadline` - Create a New Deadline Task

Create a new deadline task and add to the user's task list.

Command form:

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

### `event` - Create a New Event Task

Create a new event task and add to the user's task list.

Command form:

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

Command form:

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

### `done` - Mark a Task as Completed

Mark a to do, deadline, or event as completed.

Command form:

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
