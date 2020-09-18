# User Guide

## Introduction 
Duke is a simple application that helps you keep track of your ToDos, Deadlines and Events all in one list.

## List of Features 

**`todo`**: Adds a ToDo task into your list.

**`event`**: Adds an Event task into your list.

**`deadline`**: Adds a Deadline task into your list.

**`list`**: Lists all tasks in your list.

**`find`**: Lists all tasks in your list that matches the keyword.

**`done`**: Marks a task as done.

**`delete`**: Deletes a task from your list.

**`bye`**: Terminates the Application.

## Usage of Features

#### 1. **`todo`**: Adds a ToDo task ino your list
- This command adds a ToDo task into your list
- ToDo task is useful if your task doesn't require any deadline

Format:

`todo <description>`

Example of usage: 

`todo CS2103T assignment`

Expected outcome:

```
Roger. I've added this task:
[T][✘] CS2103T assignment
Now you have 1 tasks in the list.
```

#### 2. **`event`**: Adds an Event task into your list.
- This command adds an Event task into your list
- Event task is useful if you need to note down an upcoming event that is happening on a certain day
- Date format has to be strictly adhered to
- Description and Date **must** be provided

Format:

`event <description> /at <D/M/YYYY>`

Example of usage: 

`event Brian's birthday /at 4/5/2020`

Expected outcome:

```
Roger. I've added this task:
[E][✘] Brian's birthday (by: May 4 2020)
Now you have 2 tasks in the list.
```

#### 3. **`deadline`**: Adds a Deadline task into your list.
- This command adds a Deadline task into your list
- Deadline task is useful when your ToDo task has a deadline, as suggested by the name itself 
- Deadline format has to be strictly adhered to
- Description and Deadline **must** be provided

Format:

`deadline <description> /by <D/M/YYYY>`

Example of usage: 

`deadline CS2103 Lab /by 31/9/2020`

Expected outcome:

```
Roger. I've added this task:
[D][✘] CS2103 Lab (by: Sep 30 2020)
Now you have 3 tasks in the list.
```

#### 4. **`list`**: Lists all tasks in your list.
- This command will display all the tasks in your list

Format:

`list`

Example of usage: 

`list`

Expected outcome:

```
Here are the tasks in your list:
1.[T][✘] CS2103T assignment
2.[E][✘] Brian's birthday (by: May 4 2020)
3.[D][✘] CS2103 Lab (by: Sep 30 2020)
```

#### 5. **`find`**: Lists all tasks in your list that matches the keyword.
- This command lists all the tasks in your list, if any
- Keyword **must** be provided and it is not case-sensitive
- All tasks containing the keyword would be listed, even if the keyword matches the task only partially, as shown in the example below


Format:

`find <keyword>`

Example of usage: 

`find 2103`

Expected outcome:

```
Here are the matching tasks in your list:
1.[T][✘] CS2103T assignment
2.[D][✘] CS2103 Lab (by: Sep 30 2020)
```

#### 6. **`done`**: Marks a task as done.
- This command marks your task as done
- This is indicated by the change from [✘] to [✓]
- Index of the task can be seen using the `list` command
- Index **must** be provided

Format:

`done <index>`

Example of usage: 

`done 1`

Expected outcome:

```
Nice! I've marked this task as done:
[T][✓] CS2103T assignment
```

#### 7. **`delete`**: Deletes a task from your list.
- This command deletes task from your list
- You can also delete multiple tasks from your list 
- Index / Indexes **must** be provided

Format:

`delete <index>` or `delete <index, ... ,index>`

Example of usage: 

Case 1: `delete 1`

Case 2: `delete 2,3`

Expected outcome:

Case 1: 
```
Alright! I've removed this task:
[T][✓] CS2103T assignment
Now you have 2 tasks in the list.
```
Case 2:
```
Alright! I've removed the following tasks:
1.[E][✘] Brian's birthday (by: May 4 2020)
2.[D][✘] CS2103 Lab (by: Sep 30 2020)
Now you have 0 tasks in the list.
```

#### 8. **`bye`**: Terminates the Application.
- This command terminates Duke application.

Format:

`bye`

Example of usage: 

`bye`
