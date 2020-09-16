# User Guide

Duke is a chatbot that helps the user manage tasks.

Currently, Duke supports 3 types of tasks -
* Todos
* Deadlines
* Events

## Getting Started
1. Download the [latest release](https://github.com/PrestonTYR/ip/releases) from the GitHub repo and place it in any folder.
2. Run the .jar file.

## Features
1. Adding Tasks
    * Add a Todo
    * Add a Deadline
    * Add an Event
2. List all Tasks
3. Delete Tasks
4. Mark a Task as Done
5. Finding Tasks based on Keywords
6. Archive the Current List of Tasks
7. Exiting the App


### Adding Tasks

#### Add a Todo

Todo tasks store only the description of the task and nothing else.

Syntax: `todo <description>`

Example: `todo example task`

**Expected Outcome:**

```
Got it, I've added this task:
[T][✗] example task
You still have 1 tasks left.
```

#### Add a Deadline

Deadline tasks store the description of the task, along with a deadline when it should be completed.

Syntax: `deadline <description> /by <YYYY-MM-DD>`

Example: `deadline example deadline /by 2020-09-16`

**Expected Outcome:**

```
Got it, I've added this task:
[D][✗] example deadline (by:16 Sep 2020)
You still have 2 tasks left.
```

#### Add an Event

Event tasks store the description of the task, along with the time it starts.

Syntax: `event <description> /at <YYYY-MM-DD>`

Example: `event example event /at 2020-09-16`

**Expected Outcome:**

```
Got it, I've added this task:
[E][✗] example event (at:16 Sep 2020)
You still have 3 tasks left.
```

### List all Tasks

Displays all tasks currently added in the form of a list.

Syntax: `list`

**Expected Outcome:**

```
Here are your tasks:
1. [T][✗] example task
2. [D][✗] example deadline (by:16 Sep 2020)
3. [E][✗] example event (at:16 Sep 2020)
```

### Delete Tasks

Deletes a specified task from the list.

Syntax: `delete <index>`

Example: `delete 3`

**Expected Outcome:**

```
Alright, I've removed this task:
[E][✗] example event (at:16 Sep 2020)
You still have 2 tasks left.
```

### Mark a Task as Done

Marks a specified task in the list as done.

Syntax: `done <index>`

Example: `done 2`

**Expected Outcome:**

```
Good job kid!
[D][✓] example deadline (by:16 Sep 2020)
```

### Finding Tasks based on Keywords

Filters all tasks currently in the list for those matching the specified keyword.

Syntax: `find <keyword>`

Example `find task`

**Expected Outcome:**

```
Were these what you were looking for?
1. [T][✗] example task
```

### Archive the Current List of Tasks

Saves the current tasks into a specified text folder.

Syntax: `archive <name of folder>`

Example `archive example.txt`

**Expected Outcome:**

```
Got it, I've saved them somewhere else.
```

### Exiting the App

Command the app to close.

Syntax: `bye`

**Expected Outcome:**

```
Come visit again soon!
```
