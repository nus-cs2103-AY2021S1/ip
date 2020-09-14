# Awesome-O: User Guide

This is a relatively small greenfield project created for CS2103T Software Engineering.

It is simply a chat-bot that the user can interact with via the command-line to track tasks.

Types of tasks currently supported:
- Todos: tasks with no specific date/time
- Events: tasks with a set date/time of occurrence
- Deadlines: tasks with a set date date/time completion 

I've decided to customize it with the theme of one of my favourite shows: South Park :)

![South Park boys](https://www.kindpng.com/picc/m/258-2584013_south-park-png-south-park-transparent-png.png)

## Features (types of commands currently supported)

1. [**`help`**](#help): provides a list of possible commands
1. [**`list`**](#list): shows the list of tasks
1. [**`done`**](#done): marks a task as complete
1. [**`delete`**](#delete): deletes a task from the list
1. [**`clear`**](#clear): deletes *all* tasks from the list
1. **Task adding**:
   1. [**`todo`**](#todo): creates a todo task with specific description
   1. [**`event`**](#event): creates an event task with specific description and date/time
   1. [**`deadline`**](#deadline): creates a deadline task with specific description and date/time
1. [**`find`**](#find): searches for task(s) which match the given keyword(s)
1. [**`schedule`**](#schedule): shows the tasks due/at at a specific a date
1. [**`bye`**](#bye): ends the conversation with Awesome-O

## Feature Details

### <a name="help"></a>1. `help`

This command shows a list of all possible commands Awesome-O can recognize.

Each item shows the usage for each command as well.

#### Usage

#### `help` - Describe action

Self-explanatory.

Example of usage: 

`help`

Expected outcome:

```
- Help: Provides a list of possible commands
    "help"
- List: Shows the list of tasks
    "list"
- Done: Marks a task as complete
    "done <task number>"
- Delete: Deletes a task from the list
    "delete <task number>"
- Clear: Deletes ALL tasks from the list
    "clear"
- Todo: Creates a todo task with specific description
    "todo <description>"
- Event: Creates an event task with specific description and date/time
    "event <description> /at <time>"
    Time formatting: dd-MM-yyyy HH:mm
- Deadline: Creates a deadline task with specific description and date/time
    "deadline <description> /by <time>"
    Time formatting: dd-MM-yyyy HH:mm
- Find: Searches for task(s) which match the given keyword(s)
    "find <keyword(s)>"
- Schedule: Shows the tasks due/at a specific date
    "schedule <date>"
    Date formatting: dd-MM-yyyy
- Bye: Ends the conversation with Awesome-O
    "bye"
```

### <a name="list"></a>2.`list`

This command shows the current list of tasks that has been saved.

#### Usage

#### `list`

Self-explanatory.

Example of usage: 

`list`

Expected outcome:

```
Awesome-O now shows you your tasks:
1. [T][✓] hello world
2. [E][✘] play warcraft (at: 18 Sep 2020, 11:00 PM)
3. [D][✘] submit this project (by: 18 Sep 2020, 11:59 PM)
```

### <a name="done"></a>3. `done`

This command marks a single task as having been completed.

#### Usage

#### `done <task number>`

If the task corresponding with the task number was marked as uncompleted `[✘]`,
it will now be marked as completed `[✓]`.

Example of usage: 

`done 2`

Expected outcome:

```
Awesome-O marked this task as done:
   [E][✓] play warcraft (at: 18 Sep 2020, 11:00 PM)
```

### <a name="delete"></a>4. `delete`

This command deletes a *single* task from the list.

#### Usage

#### `delete <task number>`

The task corresponding with the task number will be removed from the list.

Example of usage: 

`delete 1`

Expected outcome:

```
Awesome-O removed this task:
   [T][✓] hello world
Now you have 2 tasks in the list.
```

### <a name="clear"></a>5. `clear`

This command deletes *all* tasks from the list, leaving an empty list thereafter.

#### Usage

#### `list`

Self-explanatory.

Example of usage: 

`list`

Expected outcome:

```
Awesome-O has cleared all tasks.
```

### <a name="todo"></a>6.i. `todo`

This command adds a *todo* task to the list.

This task contains just a description and has no specified date/time.

#### Usage

#### `todo <description>`

A *todo* will be added with the given description.

Example of usage: 

`todo hello world`

Expected outcome:

```
Awesome-O added this task:
   [T][✘] hello world
Now you have 1 tasks in the list.
```

### <a name="event"></a>6.ii `event`

This command adds an *event* task to the list.

This task contains a description as well as a stipulated date/time of occurrence.

#### Usage

#### `event <description> /at <time>`

An *event* will be added with the given description and date/time of occurrence.

Date/time formatting: dd-MM-yyyy HH:mm

Example of usage: 

`event play warcraft /at 18-09-2020 23:00`

Expected outcome:

```
Awesome-O added this task:
   [E][✘] play warcraft (at: 18 Sep 2020, 11:00 PM)
Now you have 2 tasks in the list.

```

### <a name="deadline"></a>6.iii `deadline`

This command adds a *deadline* task to the list.

This task contains a description as well as a stipulated due date/time.

#### Usage

#### `deadline <description> /by <time>`

A *deadline* will be added with the given description and due date/time.

Date/time formatting: dd-MM-yyyy HH:mm

Example of usage: 

`deadline submit this project /by 18-09-2020 23:59`

Expected outcome:

```
Awesome-O added this task:
   [D][✘] submit this project (by: 18 Sep 2020, 11:59 PM)
Now you have 3 tasks in the list.
```


### <a name="find"></a>7. `find`

This command checks against the tasks that match the given keyword(s), and shows them, if any.

#### Usage

#### `find <keyword(s)>`

Any tasks that match the keyword(s) will be found and listed.

Example of usage: 

`find play`

Expected outcome:

```
Awesome-O shows you the tasks that match the keyword:
1. [E][✘] play warcraft (at: 18 Sep 2020, 11:00 PM)
```

### <a name="schedule"></a>8. `schedule`

This command checks against the tasks whose specified date matches the given date, and shows them, if any.

#### Usage

#### `schedule <date>`

Any tasks that match the date will be found and list (sorted chronologically).

Date formatting: dd-MM-yyyy

Example of usage: 

`schedule 18-09-2020`

Expected outcome:

```
Awesome-O shows you the tasks scheduled on this day:
1. [E][✘] play warcraft (at: 18 Sep 2020, 11:00 PM)
2. [D][✘] submit this project (by: 18 Sep 2020, 11:59 PM)
```

### <a name="bye"></a>9. `bye`

This command simply ends the conversation with Awesome-O and the application thereafter.

#### Usage

#### `bye`

The application window will close about 1.5 seconds after this command is entered.

Example of usage: 

`bye`

Expected outcome:

```
Awesome-O thanks you for talking to him.
Awesome-O WILL MISS YOU.
```
