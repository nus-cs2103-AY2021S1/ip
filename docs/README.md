# User Guide
__Duke__ is a desktop app for managing tasks, using both Command Line Interface (CLI) and Graphical User 
Interface (GUI), so that you can have a better user experience when interacting with the bot named _Meimei_. If your prefer typing with only a few easy 
to remember commands, __Duke__ is an easy-to-use GUI for you to have an overview of all your daily tasks.

## Features 
Words in UPPER_CASE are the parameters to be supplied by the user. 
i.e. `todo DESCRIPTION`, **DESCRIPTION** is a parameter to describe a `todo` task.


### `todo` - Adding a Todo

Adds a Todo task into the list of tasks, marked as undone by default.

Format: `todo DESCRIPTION`

Example of usage: 

- `todo borrow book`

Expected outcome:

- `[T][x] borrow book` is added to the list.

### `event` - Adding an Event

Adds an Event task into the list of tasks, marked as undone by default.

Format: `event DESCRIPTION /at DATE`
- DATE should be in the format `YYYY-MM-DD`

Example of usage: 

- `event return book /at 2020-12-01`

Expected outcome:

- `[E][x] return book (at: Dec 01 2020)` is added to the list.

### `deadline` - Adding a Deadline

Adds a Deadline task into the list of tasks, marked as undone by default.

Format: `deadline DESCRIPTION /by DATE`
- DATE should be in the format `YYYY-MM-DD`

Example of usage: 

- `deadline return book /by 2020-12-05`

Expected outcome:

- `[D][x] return book (by: Dec 05 2020)` is added to the list.

### `list` - List of all tasks

Lists all the tasks.

Format: `list`

### `find` - Finding task(s) by keyword

Finds any task(s) containing the keyword.

Format: `find KEYWORD`
- finds any task with description containing the _keyword_
- returns you more than one tasks

Example of usage: 

- `find book`

Expected outcome: 
>Using examples from above, these are the tasks that will be returned.
- `[D][x] return book (by: Dec 05 2020)` 
- `[E][x] return book (at: Dec 01 2020)` 

### `done` - Marking a task as done

Marks a task in the list as done.

Format: `done INDEX`
- Index should be a **positive integer**.
- Marks a task at position INDEX in the list as done.

Example of usage: 

- `done 1`

Expected outcome:
>Using examples from above in `find`. 
- `[D][✓] return book (by: Dec 05 2020)` task is marked as done.

### `delete` - Deleting a specific task

Deletes a specific task in the list.

Format: `delete INDEX`
- Index should be a **positive integer**.
- Removes a task at position INDEX from the list.

Example of usage: 

- `delete 1`

Expected outcome: 
> Given a list of 2 items
>1. [D][x] return book (by: Dec 05 2020) 
>2. [E][x] return book (at: Dec 01 2020) 
>
> `delete 1`
>
> `list`
>
>1. [E][x] return book (at: Dec 01 2020) 

- `delete 1` will remove task 1 from the list in example. Your list will now contain 1 task only.

### `prioritise` - Prioritising a task

Prioritize a task in the list at a specific index.

Format: `prioritise 1`
- Index should be a **positive integer**.
- All tasks will not be prioritised by default with no tags. 
- Prioritizes a task at position INDEX in the list as #HIGH.

Example of usage: 

- `prioritise 1`

Expected outcome:

- `#HIGH [D][x] return book (by: Dec 05 2020)` task is prioritised.

### `bye` - Exiting the app
Exits the app.

Format: `bye`

## Summary of Features

Command | Example of usage
------- | ---------
todo | `todo BORROW BOOK`
event | `event RETURN BOOK /at 2020-12-01`
deadline | `deadline RETURN BOOK /by 2020-12-05`
list | `list`
find | `find exercise` where the list has 2 or more items.
delete | `delete 1` where the list has 1 or more items.
prioritise | `prioritise 2` where the list has 2 or more items.
