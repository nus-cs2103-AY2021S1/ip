# User Guide

## Features 

### Feature 1 
Description of feature.

## Usage

### `hello` - Start the program

If the program is sleeping, type `hello` to wake it up.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

`outcome`

### `bye` - Close the program

If the program is working, type `bye` to exit the program.
When the program is sleeping, any input command except `hello` will be responded 
with the `zzz...I'm sleeping` message.

### `todo` - Add a todo task

You can use `todo <description>` to add a todo task to your list

Example of usage:

`todo buy food for the whole week` 

Expected outcome:

```
Got it. I've added this task:
[T] [X] buy food for the whole week
You have 1 task(s) in your list
```

> **_NOTE:_** The number of tasks in the expected outcome depends on your actual task list  

### `deadline` - Add a deadline task

You can use `deadline <description> /by <time>` to add a deadline task to your list

> **_NOTE:_** The time can only be parsed if it follows some specific format 
>(see `Appendix -> Parsing date` for more information).
>Otherwise, the time will be stored as a string without parsing.

Example of usage 1 (correct time format):

`deadline CS2100 assignment 1 /by 29/8/2020`

Expected outcome 1:

```
Got it. I've added this task:
[D] [X] CS2100 assignment 1 (by: 29 Aug 2020)
You have 1 task(s) in your list
```

Example of usage 2 (incorrect time format):

`deadline CS2100 assignment 1 /by next thursday`

Expected outcome 2:

```
Got it. I've added this task:
[D] [X] CS2100 assignment 1 (by: next thursday)
You have 1 task(s) in your list
```

> **_NOTE:_** The number of tasks in the expected outcome depends on your actual task list  

### `event` - Add an event task

You can use `event <description> /at <time>` to add a event task 
to your list

> **_NOTE:_** The time can only be parsed if it follows this format
>`START_DATE to END_DATE` where **both** `START_DATE` and `END_DATE` must follow
>some specific format (see `Appendix -> Parsing date` for more information).
>Otherwise, the time will be stored as a string without parsing

Example of usage 1 (correct time format):

`event Summer Internship /at 20/4/2020 to 2/9/2020`

Expected outcome 1:

```
Got it. I've added this task:
[E] [X] Summer Internship (at: from 20 Apr 2020 to 2 Sep 2020)
You have 1 task(s) in your list
```

Example of usage 2 (incorrect time format):

`event Summer Internship /at 20/4/2020 to end of april`

Expected outcome 2:

```
Got it. I've added this task:
[E] [X] Summer Internship (at: from 20/4/2020 to end of april)
You have 1 task(s) in your list
```

> **_NOTE:_** The number of tasks in the expected outcome depends on your actual task list  

### `list` - Show the tasks in your list

You can use `list` to show the tasks in your list

Example of usage:

`list`

Expected outcome:

```
Here are the task(s) in your list:
1. [T] [X] buy food for the whole week
2. [D] [X] CS2100 assignment 1 (by: 29 Aug 2020)
3. [E] [X] Summer Internship (at: from 20 Apr 2020 to 2 Sep 2020)
```

### `done` - Mark a task as done

You can use `done <index>` to mark the task at that position as done

Example of usage: 

Assume that you have `[T] [X] buy food for the whole week` as the 3rd task in your list

```markdown
done 3
```

Expected outcome:

```markdown
Good job bud! I've marked this task as done:
[T] [✔] buy food for the whole week
```

>**_NOTE:_** You will still receive the above message no matter the task is marked as done before or not

### `delete` - Delete a task

You can use `delete <index>` to delete the task at that position from your list

Example of usage:

Assume that you are having 5 tasks in your class and having `[T] [X] buy food for the whole week` as the 3rd task in your list.

`delete 3`

Expected of outcome:

```markdown
Noted. I've deleted this task:
[T] [X] buy food for the whole week
You have 4 task(s) in your list
```

### `find` 
