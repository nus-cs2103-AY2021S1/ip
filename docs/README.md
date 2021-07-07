# User Guide

## Features 

### **Feature 1:** Adding Tasks
Schedule deadlines, events and todos for your daily tasks!

### **Feature 2:** Save and load tasks from custom files!
Keep different sets of tasks using different files! Keep work related tasks in work_tasks while school work can be in school_work.

### **Feature 3:** Delete Tasks
Once you no longer need tasks, you can delete them to save space and remove clutter!

### **Feature 4:** Mark Tasks as Done
If you finish a task, you can mark them as done!

## Usage
## *Adding tasks*
### `deadline` - Create a new deadline

Example of usage: 

`deadline (description) /by (YYYY-MM-DD)`

e.g. `deadline finish ip /by 2020-12-09`

Expected outcome:

```
Got it. I've added this task:
[D][X] finish ip (by: Dec 9 2020)
Now you got 5 tasks in the list
```

### `event` - Schedule an event


Example of usage: 

`event (description) /at (time)`

e.g. `event sleep /at 2020-12-09`

Expected outcome:

```
Got it. I've added this task:
[E][X] sleep (at: 9pm)
Now you got 5 tasks in the list
```

### `todo` - List all events happening on a certain date

Example of usage: 

`todo (description)`

e.g. `todo study`

Expected outcome:

```
Got it. I've added this task:
 [T][X] study 
Now you got 7 tasks in the list
```
 
 
## *Manage your tasks*

### `delete` - Delete a task

Example of usage: 

`delete (task id)`

e.g. `delete 1`

Expected outcome:

```
Noted. I've removed this task:
[T][✔] make game
Now you have 1 tasks in the list.
```

### `done` - Mark a task as done

Example of usage: 

`done (task id)`

e.g. `done 1`

Expected outcome:
```
Nice! I've marked this task as done:
[D][✓] finish increments (by: Sep 19 2020)
```

### `find` - Search your tasks containing a certain keyword

Example of usage: 

`find (keyword)`

e.g. `find finish`

Expected outcome:

```
Here are the matching tasks in your list:
3. [T][✔] finish cs2103t ip
4. [D][✔] finish increments (by: Sep 19 2020)
```

or

```
No matching tasks, sorry
```

### `list` - List all tasks

Example of usage: 

`list`

Expected outcome:

```
1. [T][X] make game
2. [T][X] sleep
3. [T][X] finish cs2103t ip
4. [D][X] finish increments (by: Sep 19 2020)
```


## *Application features*

### `save` - saves current list to path

Example of usage: 

`save`

Expected outcome:

```
Saved to default_tasks.txt!
Current tasks are
1. [T][✔] make game
2. [T][X] sleep
3. [T][X] finish cs2103t ip
4. [D][X] finish increments (by: Sep 19 2020)
```

### `load` - takes in a new path to load from and save tasks to file

Example of usage: 

`load (file_name)`

e.g. `load school_tasks`

Expected outcome:

```
Changed save file to school_tasks.txt,
No tasks!
```


### `bye` - Close Duke

Example of usage: 

`bye`

Expected outcome:

```
Duke closes and saves the file
```

