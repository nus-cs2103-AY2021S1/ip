# Duke Chatbot
Duke is a Personal Assistant Chatbot that helps a user keep track of his tasks.

## Sample interaction with Duke
![Sample interaction photo](/docs/Ui.png)

## Features 
* Add different types of tasks (Todo, Deadline, Event)
* List all current tasks
* Mark a task as done
* Delete a task
* Find a task by keyword
* Provides error messages when input is not what is expected
* Sort the tasks with uncompleted ones first automatically
* Saves the data automatically

## Usage
### Add a Todo task - `todo`

Adds a Todo task to the task list.

Format: `todo TODO_DESCRIPTION`

Example of usage: 

`todo CS2103T tutorial`

Expected outcome:

```
Got it. I've added this task:
[T][✘] CS2103T tutorial
Now you have 1 task(s) in the list.
```
<br />

### Add a Deadline task - `deadline`

Adds a Deadline task with date and time to the task list.

Format: `deadline DEADLINE_DESCRIPTION /by DD/MM/YYYY 24H_TIME_FORMAT`

Example of usage: 

`deadline assignment /at 21/09/2020 2359`

Expected outcome:

```
Got it. I've added this task:
[D][✘] assignment (by: 21 SEPTEMBER 2020 11.59PM)
Now you have 2 task(s) in the list.
```
<br />

### Add an Event task - `event`

Adds an Event task with date and time to the task list.

Format: `event EVENT_DESCRIPTION /at DD/MM/YYYY 24H_TIME_FORMAT-24H_TIME_FORMAT`

Example of usage: 

`event project meeting /at 30/09/2020 1400-1600`

Expected outcome:

```
Got it. I've added this task:
[E][✘] project meeting (at: 30 SEPTEMBER 2020 2PM to 4PM)
Now you have 3 task(s) in the list.
```
<br />

### List all the tasks - `list`

Lists all the tasks in the task list.

Format: `list`

Expected outcome:

```
Here are the task(s) in your list:
1. [T][✘] CS2103T tutorial
2. [D][✘] assignment (by: 21 SEPTEMBER 2020 11.59PM)
3. [E][✘] project meeting (at: 30 SEPTEMBER 2020 2PM to 4PM)
```
<br />

### Mark a task as done - `done`

Marks a specified task in the task list as done.

Format: `done INDEX`
* Marks the task at the specified index as done.
* The index refers to the index number shown in the displayed task list.
* The index must be a positive integer 1, 2, 3, …​

Example of usage: 

`done 1` - marks the task at index 1 as done

Expected outcome:

```
Nice! I've marked this task as done:
[T][✓] CS2103T tutorial
```
<br />

### Delete a task - `delete`

Deletes a specified task in the task list.

Format: `delete INDEX`
* Deletes the task at the specified index.
* The index refers to the index number shown in the displayed task list.
* The index must be a positive integer 1, 2, 3, …​

Example of usage: 

`delete 2` - deletes the task at index 2

Expected outcome:

```
Noted. I've removed this task:
[D][✘] assignment (by: 21 SEPTEMBER 2020 11.59PM)
Now you have 2 task(s) in the list.
```

<br />

### Find all the tasks with the keyword - `find`

Finds all the tasks with the given keyword.

Format: `find KEYWORD`

Example of usage: 

`find meeting` - finds all tasks with meeting in the description

Expected outcome:

```
Here are the matching task(s) in your list:
1. [E][✘] project meeting (at: 30 SEPTEMBER 2020 2PM to 4PM)
```

<br />

### Exit the programe - `bye`

Exits the program.

Format: `bye`

<br />

### Error messages
Error messages will be displayed when the user input is not what is expected.

Sample outputs:

```
☹ OOPS!!! I'm sorry, but I don't know what that means :-(
```

```
☹ OOPS!!! The description of a todo cannot be empty.
```

```
☹ OOPS!!! I don't know which task to delete.
```

<br />

### Sorting the tasks
Tasks are sorted automatically after any command that changes the task list.

<br />

### Saving the data
Task list data are saved in the hard disk automatically after any command that changes the data. 
There is no need to save manually.
