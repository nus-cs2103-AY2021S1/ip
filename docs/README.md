# User Guide

Stuff is a Graphical User Interface (GUI) desktop app for managing tasks, optimized for fast typists to use. 
If you can type fast, Stuff can manage your tasks faster than traditional GUI apps.

## Features 

**Notes about the command format:**

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `todo NAME_OF_TASK`, `NAME_OF_TASK` is a parameter which can be used as `todo homework`.

* Items in square brackets are optional.<br>
  e.g `NAME [#TAG]` can be used as `John Doe #friend` or as `John Doe`.

* Items with `…` after them can be used multiple times including zero times.<br>
  e.g. `[#TAG]…` can be used as ` ` (i.e. 0 times), `#friend`, `#friend #family` etc.

### Adding a todo: `todo`
Adds a todo to your list of tasks.

Format: `todo NAME_OF_TASK [#TAG]...`

**Tip:** A todo can have 0 or more tags for easier searching.

Examples:
* `todo CS2103T ip`
* `todo team project #CS #2103T`

### Adding an event: `event`
Adds an event to your list of tasks.

Format: `event NAME_OF_EVENT /at TIME_OF_EVENT [#TAG]...`
* Adds an event which will occur at the specified `TIME_OF_EVENT`.
* The time of event must be in the format YYYY-MM-DD HHMM.

**Tip:** A event can have 0 or more tags for easier searching.

Examples:
* `event CS2103T tutorial /at 2020-9-16 1700`
* `event CS2101 /at 2020-9-17 1600 #CS #CS2101`

### Adding an deadline: `deadline`
Adds a deadline to your list of tasks.

Format: `deadline NAME_OF_DEADLINE /by TIME_OF_DEADLINE [#TAG]...`
* Adds a deadline which should be done before the specified `TIME_OF_DEADLINE`.
* The time of deadline must be in the format YYYY-MM-DD HHMM.

**Tip:** A deadline can have 0 or more tags for easier searching.

Examples:
* `deadline CS2100 homework /by 2020-9-20 2359`
* `deadline ES2660 presentation /by 2020-9-17 2359 #help #nomore`

### Listing all tasks: `list`
Shows a list of all tasks in the task list.

Format: `list`

### Marking a task as done: `done`
Marks a task in your list of tasks as done.

Format: `done INDEX`
* Marks the task at the specified `INDEX` as done. The index refers to
the index number shown in the task list.
* The index **must be a positive integer** 1, 2, 3, ...

Examples:
* `done 4` Marks the 4th task in the task list as done.
* `done 81` Marks the 81st task in the task list as done.

### Deleting a task: `delete`
Deletes a task from your list of tasks.

Format: `delete INDEX`
* Deletes the person at the specified `INDEX`. The index refers to the
index number shown in the task list.
* The index **must be a positive integer** 1, 2, 3, ...

Examples:
* `delete 4` Deletes the 4th task in the task list.
* `delete 81` Deletes the 81st task in the task list.

### Finding a task: `find`
Find tasks whose names or tags containing the given keywords.

Format `find [KEYWORDS]...`
* The search is case-sensitive. e.g. `hans` will not match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only names and tags are searched.
* Parts of the words will be matched. e.g. `Han` will match `Hans`
* Only tasks matching all keywords will be returned e.g. `Hans Bo` will not
return `Hans Gruber` or `Bo Yang`

Examples:
* `find John` returns `John` and `John Doe`.
* `find robert` returns `John` if `John` has `robert` as a tag.

### Exiting the program: `bye`
Exits the program and saves your tasks.

Format: `bye`

## Command summary

| Action | Format |
| :----- | :----- |
| **Adding a todo** | `todo NAME_OF_TASK [#TAG]...` |
| **Adding an event** | `event NAME_OF_EVENT /at TIME_OF_EVENT [#TAG]...` |
| **Adding a deadline** | `deadline NAME_OF_DEADLINE /by TIME_OF_DEADLINE [#TAG]...` |
| **Listing all tasks** | `list` |
| **Marking a task as done** | `done INDEX` |
| **Deleting a task** | `delete INDEX` |
| **Finding a task** | `find [KEYWORDS]...` |
| **Exiting the program** | `bye` |

