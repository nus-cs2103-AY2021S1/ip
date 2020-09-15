# User Guide

## Features 

### Feature 1: Help
Gives a description on how to use the bot. 

### Feature 2: Add a task
Adds a task (ToDo [T], Event [E], or Deadline [D]) to the task list. 

### Feature 3: View all tasks
Shows all items in the task list with task type, status, and dates (if applicable). 

### Feature 4: Mark a task as done
Marks the status of a selected task as done. 

### Feature 5: Snooze a task
Delays the date of the selected task by one day. 

### Feature 6: Delete a task
Deletes a selected task. 

### Feature 7: Find tasks
Shows all tasks with the keyword given. 

### Feature 8: Exit bot
Closes the window and the application for you. 

## Usage

### `help` - Shows a message with all commands that are supported

Shows a user instruction with all commands that are supported by the bot. 

Format: `help`

Example of usage: 

`help`

Expected outcome:

```
   ----------------------------
    I support these commands: 
    todo: 
        add a todo item with a description. 
        format: todo {description} 
    deadline: 
        add a deadline with a description and date. 
        format: deadline {description} /by {yyyy-mm-dd} 
    event: 
        add an event with a description, date, 
        start time and end time. 
        format: event {description} /at 
        {yyyy-mm-dd} {hh:mm} {hh:mm}
    done: 
        mark an item as done. 
        format: done {taskNumber} 
    delete: 
        delete an item. 
        format: delete {taskNumber} 
    list: 
        see all the tasks you have now. 
    find: 
        search your task list with keywords. 
        format: find {keywords} 
    snooze: 
        snooze your task by one day. 
        format: snooze {taskNumber} 
    bye: 
        say goodbye to me :<
    ----------------------------
```

### `todo` - Adds a ToDo task to the task list

Adds a todo task with a description.

Format: `todo {description}`

Example of usage: 

`todo buy iphone 12`

Expected outcome:

```
   ----------------------------
   Got it. I've added this task: 
      [T][✘] buy iphone 12
   Now you have 4 tasks in the list.
   ----------------------------
```

### `deadline` - Adds a Deadline task to the task list

Adds a deadline with a description and date.

Format: `deadline {description} /by {yyyy-mm-dd}`

Example of usage: 

`deadline submit CS2106 Lab 2 /by 2020-09-23`

Expected outcome:

```
   ----------------------------
   Got it. I've added this task: 
      [D][✘] submit CS2106 Lab 2 (by: Sep 23 2020)
   Now you have 5 tasks in the list.
   ----------------------------
```

### `event` - Adds an Event task to the task list

Adds an event with a description, date, start time and end time.

Format: `event {description} /at {yyyy-mm-dd} {hh:mm} {hh:mm}`

Example of usage: 

`event meet Cristina /at 2020-09-24 12:00 13:00`

Expected outcome:

```
   ----------------------------
   Got it. I've added this task: 
      [E][✘] meet Cristina (at: Sep 24 2020 12:00 to Sep 24 2020 13:00)
   Now you have 6 tasks in the list.
   ----------------------------
```

### `done` - Marks a task as done

Marks a selected task from the task list as done. 

Format: `done {taskNumber}`

Example of usage: 

`done 2`

Expected outcome:

```
   ----------------------------
   Nice! I've marked this task as done: 
      [D][✓] do CS2103T tutorial (by: Sep 17 2020)
   ----------------------------
```


### `delete` - Deletes a task from the list

Deletes a selected task from the task list. 

Format: `delete {taskNumber}`

Example of usage: 

`delete 2`

Expected outcome:

```
   ----------------------------
   Got it. I've deleted this task: 
      [D][✓] do CS2103T tutorial (by: Sep 17 2020)
   Now you have 5 tasks in the list.
   ----------------------------
```


### `list` - Shows all the tasks in the task list

Shows all items in the task list with task type, status, and dates (if applicable). 

Format: `list`

Example of usage: 

`list`

Expected outcome:

```
   ----------------------------
   Here are the tasks in your list:
   1. [T][✘] buy tissue paper
   2. [E][✘] meet Jason (at: Sep 15 2020 12:25 to Sep 15 2020 13:25)
   3. [T][✘] buy iphone 12
   4. [D][✘] submit CS2106 Lab 2 (by: Sep 23 2020)
   5. [E][✘] meet Cristina (at: Sep 24 2020 12:00 to Sep 24 2020 13:00)
   -----
```

### `find` - Searches for tasks with keywords

Shows all tasks with the keyword given. 

Format: `find {keywords}`

Example of usage: 

`find buy`

Expected outcome:

```
   ----------------------------
   Here are the matching tasks in your list:
   1. [T][✘] buy tissue paper
   2. [T][✘] buy iphone 12
   ----------------------------
```

### `snooze` - Snoozes a task by one day

Delays the date of a specific task (if applicable) by one day. Only Deadline and Event can be snoozed. 

Format: `snooze {taskNumber}`

Example of usage: 

`snooze 4`

Expected outcome:

```
   ----------------------------
   Got it. I've snoozed this task by 1 day: 
      [D][✘] submit CS2106 Lab 2 (by: Sep 24 2020)
   Now you have 5 tasks in the list.
   ----------------------------
```

### `bye` - Exits

Closes the window and exits the bot. 

Format: `bye`

Example of usage: 

`bye`

Expected outcome:

Window closes and program exits. 
