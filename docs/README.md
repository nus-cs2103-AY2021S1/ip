# Junimo User Guide
Junimo is a personal task manager that allows you to add, store, delete, find tasks :)

## Quick Start
1. Ensure that you have Java `11` or above installed in your Computer.
2. Download the latest `Junimo.jar` from [here](https://github.com/jeannetoh99/ip/releases/tag/A-Release).
3. Copy the file to the folder you want to use as the *home folder* for Junimo.
4. Double-click the file to start the app. The GUI similar to that below should appear in a few seconds. Note how the 
   app contains some sample data. 

   ![Sample Junimo GUI](https://github.com/jeannetoh99/ip/blob/master/docs/Ui.png?raw=true)
   
5. Talk to Junimo to get it to manage your tasks for you! :)
6. Refer to [Features](#features) below for details of each command.

## Features
There are in total 10 features on Junimo for users for you to use. <br/>

Feature | Description
------- | -----------
`todo DESCRIPTION` | Adds a todo to the task list.
`deadline DESCRIPTION /by YYYY-MM-DD` | Adds a deadline to the task list.
`event DESCRIPTION /at LOCATION` | Adds an event to the task list.
`list` | Displays the current task list.
`done TASK_INDEX` | Marks the task at specified index in task list as done.
`find KEYWORD` | Displays the list of tasks with the keyword found in their descriptions.
`archive TASK_INDEX` | Archives the task at specified index in task list.
`unarchive TASK_INDEX` | Unarchives the task at specified index in archives list.
`archives` | Displays the list of tasks in archives.
`bye` | Exits the Junimo application.


### 1. Adding a todo: `todo`
Adds a todo to the task list.

Format: `todo DESCRIPTION`

Example: 
```
todo read book
```

Expected outcome: 
```
added: [T][✘] read book.
Now you have 5 tasks in the list.
```


### 2. Adding a deadline: `deadline`
Adds a deadline to the task list.

Format: `deadline DESCRIPTION /by YYYY-MM-DD`

Example: 
```
deadline do project /by 2020-09-26
```

Expected outcome: 
```
added: [D][✘] do project (by: Sep 26 2020).
Now you have 6 tasks in the list.
```


### 3. Adding an event: `event`
Adds an event to the task list.

Format: `event DESCRIPTION /at LOCATION`

Example: 
```
event party /at the beach
```

Expected outcome: 
```
added: [E][✘] party (at: the beach).
Now you have 7 tasks in the list.
```

### 4. Viewing your tasks: `list`
Displays the current task list.

Format: `list`

Example: 
```
list
```

Expected outcome: 
```
Here are the tasks in your list:
1. [T][✘] eat ice cream
2. [E][✘] book event (at: library)
3. [D][✓] write book (by: Dec 12 2020)
4. [T][✓] sleep
5. [T][✘] read book
6. [D][✘] do project (by: Sep 26 2020)
7. [E][✘] party (at: the beach)
```

### 5. Marking tasks as done: `done`
Marks the task at the specified index in the task list as done.

Format: `done TASK_INDEX`

Example: 
```
done 5
```

Expected outcome: 
```
Nice! I've marked this task as done:
[T][✓] read book
```

### 6. Finding Tasks using Keyword: `find`
Displays the list of tasks with the keyword found in their descriptions.

Format: `find KEYWORD`

Example: 
```
find book
```

Expected outcome: 
```
Here are the matching tasks in your list:
1. [E][✘] book event (at: library)
2. [D][✓] write book (by: Dec 12 2020)
3. [T][✓] read book
```

### 7. Archive tasks: `archive`
Archives the task at specified index in task list.

Format: `archive TASK_INDEX`

Example: 
```
archive 6
```

Expected outcome: 
```
The following task has been archived:
[D][✘] do project (by: Sep 26 2020)
```

### 8. Unarchive tasks: `unarchive`
Unarchives the task at specified index in archives list.

Format: `unarchive TASK_INDEX`

Example: 
```
unarchive 2
```

Expected outcome: 
```
The following task has been unarchived:
[D][✘] do project (by: Sep 26 2020)
```

### 9. View your archives: `archives`
Displays the list of tasks in archives.

Format: `archives`

Example: 
```
archives
```

Expected outcome: 
```
Here are the archived tasks in your list:
1. [T][✘] archive this
```

### 10. Exit the program: `bye`
Exits the Junimo application.

Format: `bye`

Example: 
```
bye
```
Expected outcome: Displays the following, then exits the application.
```
Bye!! Hope you have a productive day ahead! :))
```

