# User Guide

# Features 

> Notes about the command format
>
> Words in `UPPER_CASE` are parameters to be supplied by the user.
> Example: In `todo DESCRIPNTION`, `DESCRIPTION` is a parameter which can
> be supplied as `finish homework`.

## Basic Features

### `todo` – Add a todo

Adds a todo to duke.

Format: `todo DESCRIPTION`

Example of usage: 

`todo cry`

Expected outcome:

```
Got it. I've added this task.
[T][✘] cry
Now you have 1 tasks in the list 
```

### `deadline` – Add a task with deadline

Adds a new task with deadline to duke.

Format: `deadline DESCRIPTION /by YYYY-MM-DD`

Example of usage: 

`deadline finish iP /by 2020-09-14`

Expected outcome:

```
Got it. I've added this task.
[D][✘] finish iP (by: 2020-09-14)
Now you have 2 tasks in the list 
```

### `event` – Add an event

Adds a new event with a specified date and time to duke.

Format: `event DESCRIPTION /at DATE_AND_TIME`

Example of usage: 

`event watch youtube /at now`

Expected outcome:

```
Got it. I've added this task.
[E][✘] watch youtube (at: now)
Now you have 3 tasks in the list 
```

### `list` – List all tasks and events

List all tasks and events in duke.

Format: `list`

Example of usage: 

`list`

Expected outcome:

```
Here are the tasks in your list.
1. [T][✘] cry
2. [D][✘] finish iP (by: 2020-09-14)
3. [E][✘] watch youtube (at: now)
```

### `done` – Marks task or event as done

Selects a task or event by index and marks it as done

Format: `done INDEX`

Example of usage: 

`done 1`

Expected outcome:

```
Nice! I've marked this task as done:
[T][✓] cry
```

### `find` – Find a task or event

Finds task or event by keyword.

Format: `find KEYWORD`

Example of usage: 

`find finish`

Expected outcome:

```
Here are your tasks matching finish
1. [D][✘] finish iP (by: 2020-09-14)
```

### `bye` – Save and exit

Saves the current list of tasks, events and notes to storage and exits.

Format: `bye`

Example of usage: 

`bye`

Expected outcome:

```
Bye. Hope to see you again soon!
File saved!
```

## Notes

### `notes add` – Add a note to duke

Adds a new note to duke.

Format: `notes add title/TITLE description/DESCRIPTION priority/PRIORITY`

* Duke has three recognised priorities – Low, Medium and High
  * To specify a priority, any of the following formats below are accepted
    * Low – `l`, `low`, `Low`
    * Medium – `m`, `medium`, `Medium`
    * High – `h`, `high`, `High`

Example of usage: 

`notes add title/it's week 6!! description/die :( priority/high`

Expected outcome: 

`Note with title it's week 6!! created!`

### `notes list` – List all notes

Lists all notes in duke.

Format: 

`notes list`

### `notes delete` – Deletes a note

Deletes a specific note.

Format: `notes delete INDEX`

Example of usage: 

`notes delete 3`

Expected outcome: 

```
Okay. I've removed this note: 
[L] TITLE: DESCRIPTION
```

