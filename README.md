<h1>User Guide</h1>

Duke Chatbot is a **desktop app for managing tasks and scehdules**. This chatbot uses a GUI text interface to interact with the user.

- Quick Start
- Features
  - Help
  - Adding a task:
    - ToDo: `todo`
    - Deadline: `deadline`
    - Event: `event`
  - Listing tasks: `list`
  - Finding tasks based on keyword: `find`
  - Marking tasks as done: `done`
  - Deleting tasks: `delete`
  - Exiting the program: `bye`
- Command Summary

<h2>Quick Start</h2>

1. Ensure you have java `11` or newer installed in your computer.
2. Download the latest release from [here](https://github.com/abdurrahmanfaqihiskandar/ip/releases).
3. Add the `duke.jar` file into a directory of your choice to be used as your _home directory_.
4. Double click the `duke.jar` file to launch the application.

<h2>Features</h2>

**Notes about the command format**:
- Words in `UPPER_CASE` are the parameters to be supplied by the user.
  E.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo Clean the dishes`.

<h3>Adding a task</h3>

<h3>Adding a ToDo task:<code>todo</code></3>

Adds a todo task to the tasklist.

Format: `todo DESCRIPTION`

Examples:
- `todo Clean the dishes`

<h3>Adding a task with a deadline:<code>deadline</code></3>

Adds a task with a deadline to the tasklist. A task with a deadline needs to have a deadline date and time in the form `YYYY-MM-DD` and `HH:MM` respectively.

Format: `deadline DESCRIPTION /by YYYY-MM-DD HH:MM`

Example:
- `deadline Project submisstion /by 2020-11-09 23:59`
- `deadline Write my essay /by 2020-12-13 00:30`

<h3>Adding an event task:<code>event</code></3>

Adds an event to the tasklist. An event needs to have an event date in the form `YYYY-MM-DD` and an event time range `HH:MM-HH:MM`.

Format: `event DESCRIPTION /at YYYY-MM-DD HH:MM-HH:MM`

Example:
- `event Birthday party /at 2020-09-26 18:00-20:00`
- `event History exam /at 2020-11-12 17:00-18:45`

<h3>Listing tasks:<code>list</code></h3>

Shows the list of tasks currently.
There are a number of ways to list the tasks in the tasklist. Below are all the different ways to list tasks in the tasklist broken down into the type of tasks:
- All tasks:
  - List all tasks:<code>list all</code>
  - List all tasks that are done:<code>list all done</code>
  - List all tasks that are not done:<code>list all not done</code>
- ToDo tasks:
  - List all todo tasks:<code>list todos</code>
  - List all todo tasks that are done:<code>list todos done</code>
  - List all todo tasks that are not done:<code>list todos not done</code>
- Tasks with a deadline:
  - List all tasks with a deadline:<code>list deadlines</code>
  - List all tasks with a deadline that are done:<code>list deadlines done</code>
  - List all tasks with a deadline that are not done:<code>list deadlines not done</code>
- Events:
  - List all events:<code>list events</code>
  - List all events that are done:<code>list events done</code>
  - List all events that are not done:<code>list events not done</code>
  
<h3>Finding tasks based on keyword:<code>find</code></h3>

Find tasks whose names contain the keyword.

Format: `find KEYWORD`
- `KEYWORD` cannot be empty
- The search is case-insensitive. E.g. `tEsT` will match `test`.
- Only the name is searched.
- Part search will also match full words. E.g. `te` will match `test`.
- The order of the keywords does not matter. E.g. `Exam Mock` will match `Mock Exam`.

Examples:
- `find party`
- `find exam`

<h3>Marking tasks as done:<code>done</code></h3>

Marks tasks as done.

Format: `done INDEX`
- Marks the task at the specified `INDEX` as done.
- The `INDEX` refers to the index number shown in the displayed task list.
- The `INDEX` **must be a positive integer** 1, 2, 3, ...

Examples:
- `list all` to view all the tasks in the tasklist followed by `done 2` will mark the 2nd task as done.
- `done 4` will mark the 4th task as done.

<h3>Deleting tasks:<code>delete</code></h3>

Deletes tasks from the tasklist.

Format: `delete INDEX`
- Deletes the task at the specified `INDEX`.
- The `INDEX` refers to the index number shown in the displayed task list.
- The `INDEX` **must be a positive integer** 1, 2, 3, ...

Examples:
- `list all` to view all the tasks in the tasklist followed by `delete 2` will delete 2nd task.
- `delete 4` will delete the 4th task.

<h3>Exiting the program:<code>bye</code></h3>

Exits the program and closes the application.

Format:`bye`

<h3>Saving the data</h3>

Duke automatically saves the tasklist in the hard disk after any command that changes the data. There is no need to save manually.

<h2>Command Summary</h2>

| Action | Format, Examples |
| --- | --- |
| todo | `todo DESCRIPTION`, e.g. `todo Clean the dishes` |
| deadline | `deadline DESCRIPTION /by YYYY-MM-DD HH:MM`, e.g. `deadline Project submisstion /by 2020-11-09 23:59` |
| event | `event DESCRIPTION /at YYYY-MM-DD HH:MM-HH:MM`, e.g. `event Birthday party /at 2020-09-26 18:00-20:00` |
| list | `list PARAMETER`, e.g. `list events done` |
| find | `find KEYWORD`, e.g. `find homework` |
| done | `done INDEX`, e.g. `done 2`
| delete | `delete INDEX`, e.g. `delete 1`
| bye | `bye` |
