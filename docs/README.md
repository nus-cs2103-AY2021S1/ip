# User Guide
Siri, your personal assistant to make task management easier.

![Image of Siri](./Ui.png)

## Features 
### Create, read, update, and delete task 
Manage all your different types of tasks - events, to-dos and deadlines by being able to start a new task,
view existing tasks, mark task as completed, and delete a completed task.

- Deadline and Event tasks have a completion date and time associated with them.

### Mark task as done 
Once you have completed your task, mark your task as done to see at a glance what tasks remain.

### Find specific task
Search for the exact task(s) you want to find by keywords in its description.

### List all tasks
Ask Siri to show you all the tasks that you have - completed and uncompleted.

### Undo previous change
Revert the changes you made and go back to the previous state by undoing your last command.

## Usage
### `list` - Show all tasks

Display all the tasks that Siri has - both completed and uncompleted

Example of usage: 

`list`

Expected outcome:

```
Here are all the items in your list:
1. E | 0 | dinner party | Nov 12 2020, 15:30:00
2. D | 1 | Individual project | Aug 25 2020, 16:00:00
3. D | 0 | readings | Aug 25, 2020, 18:00:00
4. T | 0 | walk dog
5. T | 0 | take a walk
```

### `todo <description>` - Add a new to-do item

Add a new to-do item for Siri to keep track of.

Example of usage: 

`todo walk dog`

Expected outcome:

```
Got it. I've added this task:  
   T | 0 | walk dog
Now you have 5 tasks in the list.
```

### `event <description> /at <YYYY/MM/DD> <HH:MM>` - Add a new event item

Add a new event item with its date for Siri to keep track of.

Example of usage: 

`event Christmas /at 2020/12/25 19:30`

Expected outcome:

```
Got it. I've added this task:  
   E | 0 | Christmas | Dec 25 2020,
19:30:00
Now you have 5 tasks in the list.
```

### `deadline <description> /by <YYYY/MM/DD> <HH:MM>` - Add a new deadline item

Add a new deadline item with its completion deadline for Siri to keep track of.

Example of usage: 

`deadline IP Submission /by 2020/09/16 23:59`

Expected outcome:

```
Got it. I've added this task:  
   D | 0 | IP Submission | Sep 16 2020,
23:59:00
Now you have 5 tasks in the list.
```

### `done <taskId>` - Mark a task as done

Mark the task with the given task ID as complete. The task ID can be seen from the list of all the tasks that Siri has.

Example of usage: 

`done 1`

Expected outcome:

```
Nice! I've marked this task as done:
T | 1 | jumping jacks
```

### `delete <taskId>` - Delete a task

Remove a task from Siri's list of tasks. The task ID can be seen from the list of all the tasks that Siri has.

Example of usage: 

`delete 1`

Expected outcome:

```
Noted. I've removed this task:
T | 1 | jumping jacks
Now you have 5 tasks in the list.
```

### `find <keyword>` - Search for tasks

Search for all the tasks that Siri has with a task description that matches the given keyword.

Example of usage: 

`find walk`

Expected outcome:

```
I've found these tasks that matches your keyword:
1. T | 0 | walk dog
2. E | 0 | take a walk | Sep 18, 2020, 16:00:00
3. T | 0 | sell walkie-talkie
```

### `undo` - Undo previous change

Undo previous command given to Siri - undo command can be performed until the Siri's startup state.

Example of usage: 

`undo`

Expected outcome:

```
Undo successful! This is what your list looks like now:
1. E | 0 | dinner party | Nov 12 2020, 15:30:00
2. D | 1 | Individual project | Aug 25 2020, 16:00:00
3. D | 0 | readings | Aug 25, 2020, 18:00:00
4. T | 0 | walk dog
5. T | 0 | take a walk
```

### `bye` - Exit program

Say goodbye to Siri and exit the program.

Example of usage: 

`bye`

Expected outcome: Program window closes.
