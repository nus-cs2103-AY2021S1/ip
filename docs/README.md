# Duke User Guide
This is a greenfield project specified under CS2103T Software Engineering.

Duke is a bot that helps you keep track of your tasks at hand.
## Features supported

1) **`todo`**: Add tasks of type todo to your list.

2) **`deadline`**: Add tasks of type deadline to your list.

3) **`event`**: Add tasks of type event to your list.

4) **`done`**: Marks a task as done.

5) **`delete`**: Deletes a task.

6) **`list`**: Displays a list of tasks.

7) **`find`**: Look up tasks with a keyword.

8) **`schedule`**: Look up tasks scheduled on a given date.

9) **`bye`**: Quits the application. 


## Usage of commands

### 1) `todo` 

- This command adds a task of type *todo* to your list.
- A description of your task (eg. do homework) is to be 
typed after your command.

Usage format: 
`todo <description>`

Example of usage: 
`todo do homework`

Expected outcome:
```
Got it! I've added this task:
[T][✘] do homework
Now you have 1 tasks in the list.
```
### 2) `deadline`
- This command adds a task of type *deadline* to your list.
- A description of your task and a formatted date and
 time of the task is required.
 
 Usage format: `deadline <description> /by dd/mm/yyyy HH:mm PM/AM`
 
 Example of usage: 
 `deadline submit homework /by 23/04/2020 12:00 PM`
 
 Expected outcome:
 ```
 Got it! I've added this task:
 [D][✘] submit homework (by: 23 Apr 2020,
12:00:00 PM)
 Now you have 2 tasks in the list.
 ```
### 3) `event`
- This command adds a task of type *event* to your list.
- A description of your task and a formatted date and
 time of the task is required.
 
 Usage format: `event <description> /at dd/mm/yyyy HH:mm PM/AM`
 
 Example of usage: 
 `event exam /at 23/09/2020 04:00 PM`
 
 Expected outcome:
 ```
 Got it! I've added this task:
 [E][✘] exam (at: 23 Sep 2020,
4:00:00 PM)
 Now you have 3 tasks in the list.
 ```

### 4) `done`
- This command marks a task as done.
- The task index in the list is required.
- The status of task will turn from [✘] to [✓].
 
 Usage format: `done <task index in list>`
 
 Example of usage: 
 `done 2`
 
 Expected outcome:
 ```
 Great work! I've marked this task as done:
 [D][✓] submit homework (by: 23 Apr 2020,
 12:00:00 PM)
 Keep the ticks going! ^_^
 ```

### 5) `delete`
- This command deletes a task from your list.
- The task index in the list is required.
 
 Usage format: `delete <task index in list>`
 
 Example of usage: 
 `delete 1`
 
 Expected outcome:
 ```
 Noted! I've deleted this task:
 [T][✘] do homework
 Now you have two tasks in the list.
 ```

### 6) `list`
- This command displays your list of tasks.
 
 Usage format: 
 `list`
 
 Example of usage:
 `list`
 
 Expected outcome:
 ```
Here are the tasks in your list:
 1.[D][✓] submit homework (by: 23 Apr 2020,
 12:00:00 PM)
 2.[E][✘] exam (at: 23 Sep 2020,
 4:00:00 PM)
 ```

### 7) `find`
- This command displays tasks that contains a given keyword.
- A keyword is required.
- The keyword can match the item only partially.
 
 Usage format:
 `find <keyword>`
 
 Examples of usage: 
 `find exam`
 `find ex`
 
 Expected outcome:
 ```
These are the tasks with your keyword:
 1.[E][✘] exam (at: 23 Sep 2020, 4:00:00 PM)
 ```
### 8) `schedule`
- This command displays tasks that are scheduled on the given date.
- A date with the format **dd/mm/yyyy** is required.
 
 Usage format:
 `schedule <scheduled date>`
 
 Example of usage: 
 `schedule 23/09/2020`

 Expected outcome:
 ```
Here are the tasks scheduled for this date:
 1.[E][✘] exam (at: 23 Sep 2020, 4:00:00 PM)
 ```

### 9) `bye`
- This command quits the application immediately.
 
 Usage format:
 `bye`
 
 Example of usage:
 `bye`
 

