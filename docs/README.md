# User Guide

## User Interface

![UI](Ui.png)

## Features

### Todo List App

Duke is a todo list app that works with using text commands. It allows users to add, delete and search todos, deadlines and events.

## Usage

### 1. `list` - List tasks

Lists all tasks

Example of usage:

`list`

Expected outcome:

`1.[T][✗] Finish CS2103T IP`

### 2. `bye` - Exit application

Exits Duke application.

Example of usage:

`bye`

Expected outcome:

`Application exits`

### 3. `todo TASKNAME` - Add todo

Adds a Todo Task to task list.

Example of usage:

`todo do laundry`

Expected outcome:

```text
Got it. I've added this task:
  [T][✗] do laundry
Now you have 4 tasks in the list.
```

### 4. `deadline TASKNAME /by DD/MM/YYYY HHmm` - Add Deadline

Adds a Deadline Task to task list.

Example of usage:

`deadline do laundry /by 01/01/2020 1200`

Expected outcome:

```text
Got it. I've added this task:
  [D][✗] do laundry (by: Jan 01 2020 1200)
Now you have 4 tasks in the list.
```

### 5. `event TASKNAME /AT DURATION` - Add event

Adds a Event Task to task list.

Example of usage:

`event do laundry /at 01/01/2020 1200 to 1300`

Expected outcome:

```text
Got it. I've added this task:
  [E][✗] do laundry (at: 01/01/2020 1200 to 1300)
Now you have 4 tasks in the list.
```

### 6. `done TASKINDEX` - Mark task as done

Marks the task at TASKINDEX as done.

Example of usage:

`done 1`

Expected outcome:

```text
Nice! I've marked this task as done:
  [T][✓] Finish CS2103T IP
```

### 7. `delete TASKINDEX` - Delete task

Deletes a task at TASKINDEX.

Example of usage:

`delete 1`

Expected outcome:

```text
Noted. I've removed this task:
  [T][✓] Finish CS2103T IP
Now you have 3 tasks in the list.
```

### 8. `find KEYWORD` - Find task

Find a task by TASKNAME substring.

Example of usage:

`find CS2103`

Expected outcome:

```text
Here are the matching takss in your list:
1.[T][✗] Finish CS2103T IP
```
