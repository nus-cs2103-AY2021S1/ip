# User Guide

## Features 

### list 
Displays all tasks in a list.

### done
Marks a task as complete.

### delete
Deletes the task at the stated index.

### find
Searches all tasks that contained the search keyword, and displays those containing the keyword 
on the screen.

### undo
Undoes the previous action taken. This only works for the action immediately preceding it.

### event
Creates an event at the specified time.

### deadline
Creates a deadline task to be completed by the specified time.

### todo
Creates a todo task.

## Usage

### ```list``` - List all tasks

Displays all tasks on the screen.

Example of usage: 

```list```

Expected outcome:

```Here are the tasks in your list:
 <tasks>
 ```

### ```done``` - Marks task as complete

Marks a task as complete, and displays a confirmation.

Example of usage: 

```done 5```

Expected outcome:

```Nice! I've marked this task as done:
 <task>
 ```

### ```delete``` - Delete the specified task

Deletes the task from the tasklist and displays a confirmation

Example of usage: 

```delete 5```

Expected outcome:

```Noted. I've removed this task:
 <task>
 ```

### ```find``` - Search for tasks by keyword

Displays all tasks on the screen that contain the specified keyword

Example of usage: 

```find kappa```

Expected outcome:

```Here are the matching tasks in your list:
 <tasks>
 ```

### ```undo``` - Undoes the latest action

Undoes the latest action.

Example of usage: 

```undo```

Expected outcome:

```Nice! I've undid your previous action!
 Here are the tasks in your list:
 <tasks>
 ```

### ```event``` - Creates an event

Creates an event at the specified time.

Example of usage: 

```event kappa /at 2020-02-20 1900```

Expected outcome:

```Got it. I've added this task:
 [E][x] kappa (at: 20 Feb 2020 19:00)
 ```

### ```deadline``` - Creates a deadline

Creates a deadline to be completed by the specified time.

Example of usage: 

```deadline kek /by 2020-03-30 1800```

Expected outcome:

```Got it. I've added this task:
 [D][x] kek (by: 30 Mar 2020 18:00)
 ```

### ```todo``` - Creates a todo

Creates a todo.

Example of usage: 

```todo lel```

Expected outcome:

```Got it. I've added this task:
 [T][x] lel
 ```
