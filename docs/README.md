# User Guide
Marco is a desktop app for task management, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Marco can get your contact management tasks done faster than traditional GUI apps.
## Features 

### Feature 1 
Add and delete tasks into an existing task list specified (in the form of todo, event or deadline)

### Feature 2
Mark tasks as done when they are completed.

### Feature 3
List all the existing tasks in the task list with their description and status.
Time related to event task, and deadline to deadline task will also be shown.

### Feature 4
Search for tasks by keyword in their descriptions.

### Feature 5
Tag tasks with a specific tag.

## Usage


#### 1. `help` 
- Lists all available commands with their formats and usages.

Example of usage: 

`help`

Expected outcome:
```
Here are the list of commands that Marco understands:
- help                                    [get a full list of commands and their usage]
- bye                                     [say goodbye to Marco]
- list                                    [list down all existing tasks in the task list]
- done TASK_NUMBER                        [mark the task indexed at TASK_NUMBER as done]
- find KEY_WORD                           [search for tasks contains KEY_WORD in description]
- delete TASK_NUMBER                      [delete the task indexed at TASK_NUMBER]
- todo DESCRIPTION                        [add a todo task with DESCRIPTION]
- event DESCRIPTION /at YYYY-MM-DD        [add an event with DESCRIPTION at YYYY-MM-DD]
- deadline DESCRIPTION /by YYYY-MM-DD     [add a deadline with DESCRIPTION at YYYY-MM-DD]

```

#### 2. `bye` 
- Get the farewell message and terminate the program.

Example of usage: 

`bye`

Expected outcome:

             Awwww, I guess you are gonna leave... o(TヘTo)
             Marco will keep track of your tasks nicely! 
             Call me when you need me! 
             Marco is always here waiting for you ☀♪ ~ 
             Bye~ Have a nice day~ (●'◡'●)ﾉ♥ 

#### 3. `list` 
- Lists all existing tasks in the task list.

Example of usage: 

`list`

Expected outcome 

(if the task list is not empty):
```
Here are the tasks in your list:   
1.[T][✓] dance practice
2.[E][✘] Marco's Bday (at: Oct 02 2020)   
3.[D][✘] prepare gift (by: Sep 28 2020) 
```
(if the task list is empty)
```
Oops, your list is currently empty. Add some tasks first!
```


#### 4. `find KEY_WORD` 
- Search for tasks that contains certain keyword in their description.

Example of usage: 

`find practice`

Expected outcome:

(if there is at least one task found to contain the input keyword):
```
Here are the matching tasks in your list:  
[T][✓] dance practice
```
(if there is no task found to contain the input keyword):
```
There is no matching task in your list! 
```

#### 4. `done TASK_NUMBER` 
- Mark a task as done.

Example of usage: 

`done 1`

Expected outcome (example):
```
Nice! I've marked this task as done: 
[T][✓] dance practice
```

#### 5. `delete TASK_NUMBER` 
- Delete a task.

Example of usage: 

`delete 1`

Expected outcome (example):
```
Got it. I've removed this task:
[T][✘] dance practice
Now you have 2 tasks in the list.
```

#### 6. `tag TASK_NUMBER TAG` 
- Attach a tag to a task.

Example of usage: 

`tag 3 urgent`

Expected outcome (example):
```
Nice! I've added the tag <urgent> for the following task:
[D][✘] prepare gift (by: Sep 28 2020) <urgent>
```

#### 7. `todo DESCRIPTION` 
- Create a todo task with a description.

Example of usage: 

`todo dance practice`

Expected outcome (example):
```
Got it. I've added this task:
[T][✘] dance practice
Now you have 1 tasks in the list.
```

#### 8. `event DESCRIPTION /at YYYY-MM-DD` 
- Create an event task with a description and a time.

Example of usage: 

`event meeting /at 2020-09-21`

Expected outcome (example):
```
Got it. I've added this task:
[E][✘] meeting (at: Sep 21 2020)
Now you have 2 tasks in the list.
```

#### 9. `deadline DESCRIPTION /by YYYY-MM-DD` 
- Create a deadline task with a description and a time.

Example of usage: 

`deadline submit paper /by 2020-09-21`

Expected outcome (example):
```
Got it. I've added this task:
[D][✘] submit paper (by: Sep 21 2020)
Now you have 3 tasks in the list.
```


