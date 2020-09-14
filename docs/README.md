# User Guide

Duke allows a user to manage tasks.

There are 3 types of tasks - **todo, event and deadline**.

Each task can be tagged with 3 types of priority - **low, medium and high**.

## Features 

1. add tasks
   * add a todo
   * add an event
   * add a deadline
2. list all tasks
3. delete a task
4. mark a task as done
5. filter tasks based on their priority
6. get tasks based on their dates
7. find tasks based on a keyword
8. say bye to Duke

## Usage

### `todo <description> /p <priority>` - Add a todo

Adds a todo to Duke's list with a priority tag.

*Example of usage:* 

`todo borrow book /p low`

*Expected outcome:*

```
Got it. I've added this task:
[T][L][✘] borrow book
Now you have 1 task in the list.
```

---

### `event <description> /p <priority> /at <DD-MM-YYYY HHMM>` - Add an event

Adds an event to Duke's list with a priority tag and a date and time tag.

The time is in a 24-hour format.

*Example of usage:* 

`event project meeting /p high /at 1-9-2020 0800`

*Expected outcome:*

```
Got it. I've added this task:
[E][H][✘] project meeting (at: 01 Sep 2020, 08:00 AM)
Now you have 2 tasks in the list.
```

---

### `deadline <description> /p <priority> /by <DD-MM-YYYY HHMM>` - Add a deadline

Adds a deadline to Duke's list with a priority tag and a date and time tag.

The time is in a 24-hour format.

*Example of usage:* 

`deadline return book /by 01-09-2020 1800 /p medium`

*Expected outcome:*

```
Got it. I've added this task:
[D][M][✘] return book (by: 01 Sep 2020, 06:00 PM)
Now you have 3 tasks in the list.
```

---

### `list` - List all tasks

Lists all tasks in Duke's list.

*Example of usage:* 

`list`

*Expected outcome:*

```
Here are the 3 tasks in your list:
1. [T][L][✘] borrow book
2. [E][H][✘] project meeting (at: 01 Sep 2020, 08:00 AM)
3. [D][M][✘] return book (by: 01 Sep 2020, 06:00 PM)
```

---

### `delete <index>` - Delete a task

Deletes a task from Duke's list based on the task's index.

*Example of usage:* 

`delete 2` (deletes the second task in Duke's list)

*Expected outcome:*

```
Noted. I've removed this task:
[E][H][✘] project meeting (at: 01 Sep 2020, 08:00 AM)
Now you have 2 tasks in the list.
```

---

### `done <index>` - Mark a task as done

Marks a task from Duke's list as completed based on the task's index.

*Example of usage:* 

`done 1` (marks the first task in Duke's list as done)

*Expected outcome:*

```
Nice! I've marked this as done:
[T][L][✓] borrow book
```

---

### `filter <priority>` - Filter tasks based on their priority

Filters all tasks from Duke's list based on the specified priority.

*Example of usage:* 

`filter medium`

*Expected outcome:*

```
Here are the medium-priority task(s):
1. [D][M][✘] return book (by: 01 Sep 2020, 06:00 PM)
```

---

### `get <DD-MM-YYYY>` - Get tasks based on their dates

Gets all tasks from Duke's list based on the specified date.

*Example of usage:* 

`get 1-9-2020`

*Expected outcome:*

```
Here are the task(s) from 01 Sep 2020:
1. [D][M][✘] return book (by: 01 Sep 2020, 06:00 PM)
```

---

### `find <keyword>` - Find tasks based on a keyword

Finds all tasks from Duke's list based on the specified keyword.

*Example of usage:* 

`find book`

*Expected outcome:*

```
Here are the matching task(s) in your list:
1. [T][L][✓] borrow book
2. [D][M][✘] return book (by: 01 Sep 2020, 06:00 PM)
```

---

### `bye` - Say bye to Duke

Says bye to Duke.

*Example of usage:* 

`bye`

*Expected outcome:*

```
Bye. Hope to see you again soon!
```

---