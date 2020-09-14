# User Guide

Duke is a **desktop app for keeping track of tasks, optimized for use via a Command Lind Interface (CLI)**
while still having the benefits of Graphical User Interface (GUI). If you can type fast, Duke can get help you managing your task list
faster than traditional GUI apps
## Features 

### Basic CRUD
Duke allows you to **Create, Read, Update, and Delete** your tasks

### Three types of tasks
Tasks stored in Duke are categorized into 3 types `todo`, `deadline`, and `event`:
* *Todo*: tasks that do not have any timestamp attached to it
* *Deadline*: tasks that due on a specific timestamp
* *Event*: tasks that occur between 2 timestamp 

### Clone task
Duke allows you to clone your task in another position

### Find task
You can find tasks by date, by keyword, and by status (done or not done)

### Undo your mistake easily
Duke can help you quickly recover from your mistakes

### Local storage
Duke store your data locally so they will not be lost between uses

## Usage

### `hello` - Start the program

If the program is sleeping, type `hello` to wake it up.

**Command:** `hello`

### `bye` - Close the program

If the program is working, type `bye` to exit the program.
When the program is sleeping, any input command except `hello` will be responded 
with the `zzz...I'm sleeping` message.

**Command:** `bye`

>**_NOTE:_**
>`bye` only put the chat bot into sleep mode, not closing the chat window. 
>You have to manually close the window yourself

### `todo` - Add a todo task

You can use `todo` to add a todo task to your list

**Command:** `todo <description>`

**Example of usage:**

`todo buy food for the whole week` 

**Expected outcome:**

```markdown
Got it. I've added this task:
[T] [X] buy food for the whole week
You have 1 task(s) in your list
```

> **_NOTE:_**
>The number of tasks in the expected outcome depends on your actual task list  

### `deadline` - Add a deadline task

You can use `deadline` to add a deadline task to your list

**Command:** `deadline <description> /by <time>`

> **_NOTE:_** 
>The time can only be parsed if it follows some specific format 
>(as specified in `Appendix -> Parsing date`).
>Otherwise, the time will be stored as a string without parsing.

**Example of usage 1 (correct time format):**

`deadline CS2100 assignment 1 /by 29/8/2020`

**Expected outcome 1:**

```markdown
Got it. I've added this task:
[D] [X] CS2100 assignment 1 (by: 29 Aug 2020)
You have 1 task(s) in your list
```

**Example of usage 2 (incorrect time format):**

`deadline CS2100 assignment 1 /by next thursday`

**Expected outcome 2:**

```
Got it. I've added this task:
[D] [X] CS2100 assignment 1 (by: next thursday)
You have 1 task(s) in your list
```

> **_NOTE:_** 
>The number of tasks in the expected outcome depends on your actual task list  

### `event` - Add an event task

You can use `event` to add a event task 
to your list

**Command:** `event <description> /at <time>`

> **_NOTE:_** 
>The time can only be parsed if it follows this format
>`START_DATE to END_DATE` where **both** `START_DATE` and `END_DATE` must follow
>some specific format (as specified in `Appendix -> Parsing date`), 
>and `END_DATE` must happen from `START_DATE` onwards.
>Otherwise, the time will be stored as a string without parsing

**Example of usage 1 (correct time format):**

`event Summer Internship /at 20/4/2020 to 2/9/2020`

**Expected outcome 1:**

```
Got it. I've added this task:
[E] [X] Summer Internship (at: from 20 Apr 2020 to 2 Sep 2020)
You have 1 task(s) in your list
```

**Example of usage 2 (incorrect time format):**

`event Summer Internship /at 20/4/2020 to end of april`

**Expected outcome 2:**

```
Got it. I've added this task:
[E] [X] Summer Internship (at: from 20/4/2020 to end of april)
You have 1 task(s) in your list
```

> **_NOTE:_** The number of tasks in the expected outcome depends on your actual task list  

### `list` - Show the tasks in your list

You can use `list` to show the tasks in your list

**Command:** `list`

**Example of usage:**

`list`

**Expected outcome:**

```
Here are the task(s) in your list:
1. [T] [X] buy food for the whole week
2. [D] [X] CS2100 assignment 1 (by: 29 Aug 2020)
3. [E] [X] Summer Internship (at: from 20 Apr 2020 to 2 Sep 2020)
```

### `done` - Mark a task as done

You can use `done` to mark the task at that position as done

**Command:** `done <index>`

**Example of usage:** 

Assume that you have `[T] [X] buy food for the whole week` as the 3rd task in your list

```markdown
done 3
```

**Expected outcome:**

```markdown
Good job bud! I've marked this task as done:
[T] [✔] buy food for the whole week
```

>**_NOTE:_** 
>You will still receive the above message no matter the task is marked as done before or not

>**_NOTE:_**
>The `<index>` must be between 1 and the number of tasks in your list

### `delete` - Delete a task

You can use `delete` to delete the task at that position from your list

**Command:** `delete <index>`

**Example of usage:**

Assume that you are having 5 tasks in your class and having `[T] [X] buy food for the whole week` as the 3rd task in your list.

`delete 3`

**Expected of outcome:**

```markdown
Noted. I've deleted this task:
[T] [X] buy food for the whole week
You have 4 task(s) in your list
```
>**_NOTE:_**
>The `<index>` must be between 1 and the number of tasks in your list

### `find` - Find task base on keywords

You can use `find` to find all the task that contain all of the keyword(s)

**Command:** `find <keyword_1> <keyword_2> ... <keyword_n>`

>**_NOTE:_** 
>If the keyword is in some specific date format 
>(as specified in `Appendix -> Parsing date`), the program will also find for deadlines that due on that day
>or events that occur on that day

**Example of usage 1:**

`find 20/4/2020`

**Expected outcome 1:**

```markdown
Here are the matching task(s) in your list:
1. [T] [X] Buy birthday present for 20/4/2020
2. [D] [X] Submit homework (by: 20 Apr 2020)
3. [E] [X] Hackathon (/at from 15 Apr 2020 to 30 Apr 2020)
```

>**_NOTE:_** 
>If the keyword is `done`, the program will also find tasks that are done.
>If the keyword is `not_done`, the program will also find tasks that are not done yet.

**Example of usage 2:**

`find done`

**Expected outcome 2:**

```markdown
Here are the matching task(s) in your list:
1. [T] [X] add feature: marking all the tasks as undone
2. [D] [✔] Submit homework (by: 20 Apr 2020)
3. [E] [✔] Hackathon (/at from 15 Apr 2020 to 30 Apr 2020)
```

### `clone` - Clone the task

You can use `clone` to clone the task in your task list

**Command:**

`clone <source_index> <destination_index>` to clone the task at the source position to the destination position

`clone <source_index>` to clone the task at the source position to the position below it

Assume that you are having the following tasks in your list:
1. [T] [X] buy food for the whole week
2. [D] [X] Submit homework (by: 20 Apr 2020)

**Example of usage 1:**

`clone 1`

**Expected outcome:**
```markdown
I have cloned this task:
[T] [X] buy food for the whole week
```

_Your list after cloning:_

_1. [T] [X] buy food for the whole week_

_2. [T] [X] buy food for the whole week_

_3. [D] [X] Submit homework (by: 20 Apr 2020)_

**Example of usage 2:**

`clone 1 3`

**Expected outcome 2:**
```markdown
I have cloned this task:
[T] [X] buy food for the whole week
```

_Your list after cloning:_

_1. [T] [X] buy food for the whole week_

_2. [D] [X] Submit homework (by: 20 Apr 2020)_

_3. [T] [X] buy food for the whole week_

>**_NOTE:_**
>The `<source_index>` must be between 1 and the number of tasks in your list. 
>The `<destination_index>` must be between 1 and (the number of tasks in your list plus 1)

>**_NOTE:_** 
>After cloning, the cloned task and the task that has been cloned will be treated as 2 separate task
>(i.e Changes in one task do not affect the other)

### `update` - Update the task

You can use `update` to update part of the task at a specified position's detail
without rewriting the whole task

**Command:**

*For Todo task*: `update <index> /description <new_description>`

*For Deadline or Event task*: `update <index> /description <new_description> /time <new_time>`

>**_NOTE:_** The value of `<index>` must be between 1 and the number of tasks in the list

>**_NOTE:_** For Deadline or Event task, you can omit either `/description <new_description>` or `/time <new_description>`, but not both

>**_NOTE:_**
>For `<new_time>` to be parsed to date, it must follow the time requirement of deadline or event
>according to the type of the task

**Example of usage 1:**

Assume that this is the 3rd task in your list: `[T] [X] buy food for the whole week`

`update 3 /description buy food for monday only`

**Expected outcome 1:**

```markdown
The task at position 3 has been updated to this:
[T] [X] buy food for the whole monday only
```

**Example of usage 2:**

Assume that this is the 4th task in your list: `[D] [X] CS2100 assignment 1 (by: 29 Aug 2020)`

`update 4 /time 2020/08/30`

**Expected outcome 2:**

```markdown
The task at position 4 has been updated to this:
[D] [X] CS2100 assignment 1 (by: 30 Aug 2020)
```
