# User Guide

## Quick start

1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest address book.jar from [here](https://github.com/tengjianling/ip/releases/tag/A-Release)
3. Copy the file to the folder you want to use as the *home folder* for your Duke chatbot.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.![Screenshot 2020-09-15 at 4.48.10 PM](/Users/tengjianling/Library/Application Support/typora-user-images/Screenshot 2020-09-15 at 4.48.10 PM.png)

5. Type the command in the chat box and press Enter to execute it. e.g typing `list` and pressing Enter will list out all the tasks currently in the task list.

   Some example commands you can try:

   - `list`: Lists all tasks.
   - `todo buy lunch`: Adds a todo that has the description `buy lunch` to the task list.
   - `deadline do homework /by 2020-12-12`: Adds a deadline that has the description `do homework` and the date `Dec 12 2020` to the task list.
   - `event attend wedding /at 2020-09-20`: Adds an event that has the description `attend wedding` and the date `Sep 20 2020` to the task list.
   - `delete 3`: Deletes the 3rd task shown in the current list.
   - `done 1`: Marks the 1st task shown in the current list as done.
   - `find hello hello`: Lists out the tasks in the task list that contains the phrase 'hello hello'.
   - `bye`: Exits the app.

6. Refer to the Features below for details of each command.



## Features 

### Adding a todo: `todo`
Adds a todo to the task list. A todo is a task that does not have a date attached to it.

### Adding a deadline: `deadline`

Adds a deadline to the task list. A deadline is a task that has a date attached to it. 

### Adding an event: `event`

Adds an event to the task list. A event is a task that has a date attached to it. 

### Viewing all current tasks in the list: `list`

Ability to view all the tasks entered by the user into the task list.

### Marking a task as done: `done`

Checks off a task as 'done'.

### Deleting tasks: `delete`

Deletes a task from the task list.

### Finding tasks that matches a word or phrase: `find`

Finds all tasks in the task list that contain a certain word or phrase.

### Detecting duplicates

Duke is able to detect whether the task entered by the user is already a task in the task list. If so, the user will be alerted and this task will not be added again.

## Usage

### `todo` - Adding a todo

Adds a todo to the task list.

Format: `todo DESCRIPTION_OF_TASK`

Example of usage: 

- `todo do something`
- `todo do something else`

Expected outcome:

Assuming the task list was empty before the above 2 commands, the following is shown after user enters `list` command:

```
Here are the tasks in your list:
1. [T][X] do something
2. [T][X] do something else
```



### `deadline` - Adding a deadline

Adds a deadline to the task list.

Format: `deadline DESCRIPTION_OF_TASK /by YYYY-MM-DD `

Example of usage:

- `deadline do something /by 2020-12-12`
- `deadline do something else /by 2020-10-10`

Expected outcome:

Assuming the task list was empty before the above 2 commands, the following is shown after user enters `list` command:

```
Here are the tasks in your list:
1. [D][X] do something (by: Dec 12 2020)
2. [D][X] do something else (by: Oct 10 2020)
```



###`event` - Adds an event into the task list

Adds an event to the task list.

Format: `event DESCRIPTION_OF_TASK /at YYYY-MM-DD`

Example of usage:

- `event do something /at 2020-12-12`
- `event do something else /at 2020-11-11`

Expected outcome:

Assuming the task list was empty before the above 2 commands, the following is shown after user enters `list` command:

```
Here are the tasks in your list:
1. [E][X] do something (by: Dec 12 2020)
2. [E][X] do something else (by: Nov 11 2020)
```



### `list` - Lists out all the tasks currently in the task list

Shows a list of all tasks in the task list.

Format: `list`

Example of usage: 

Assuming the task list was initially empty and the following commands are entered:

1. `todo buy lunch`
2. `event do something /at 2020-12-12`
3. `deadline do work /by 2020-11-11`
4. `list`

Expected outcome:

```
Here are the tasks in your list:
1. [T][X] buy lunch
2. [E][X] do something (at: Dec 12 2020)
3. [D][X] do work (by: Nov 11 2020)
```



### `done` - Marks the specified task as done

Marks the task with the specified index as done.

Format: `done INDEX_OF_TASK`

Example of usage:

Suppose a todo `buy lunch` is the first task on the list, and the following commands are entered:

1. `done 1`

2. `list`

Expected outcome:

```
Here are the tasks in your list:
1. [T][✔] buy lunch
```



### `delete` - Deletes the specified task

Deletes the task with the specified index

Format: `delete INDEX_OF_TASK`

Example of usage:

Suppose a todo `buy lunch` is the first task on the list, and the following commands are entered:

1. `delete 1`
2. `list`

Expected outcome:

```
Here are the tasks in your list:
```



### `find` - Locating tasks by words

Finds tasks whose description contain a given word or phrase.

Format: `find KEYWORD`

Example of usage:

Suppose this is the current list of tasks:

```
Here are the tasks in your list:
1. [T][X] buy eggs
2. [T][X] buy bread
3. [D][X] finish work (by: Dec 12 2020)
```

The following command is entered: `find buy`

Expected outcome:

```
Here are the matching tasks in your list:
1. [T][X] buy eggs
2. [T][X] buy bread
```



