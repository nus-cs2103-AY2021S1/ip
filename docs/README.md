# User Guide
Roger is a **desktop app for managing tasks, optimised for use via a Command Line Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). If you are someone who can type fast, Roger can help you track your tasks faster than traditional GUI apps.

## Quick Start
1. Ensure you have Java 11 or later installed.
1. Download the jar file from the project website.
1. Copy the file to the folder you want to use as the home folder for the application.
1. Double-click the file to start the app. A GUI should appear, with the field bar to input commands. The list of commands are available below.

## Features
1. Adding three types of tasks:
    1. Todo: `todo`
    1. Deadline: `deadline`
    1. Event: `event`
1. Mark tasks as done: `done`
1. Delete tasks: `delete`
1. List tasks: `list`

## Usage
> Command format:
> - Words in `UPPER_CASE` are parameters supplied by the user
> - Parameters containing `DATE` must be supplied in this format: `YYYY-MM-DD` where:
>   - `YYYY` is a valid year as a four digit integer (e.g. `2021`)
>   - `MM` is a valid month as a two digit integer (e.g. `11`, `01`)
>   - `DD` is a valid date as a two digit integer (e.g. `29`, `04`)

### `todo` `deadline` `event` - Adding a Todo, Deadline and an Event
Adds a todo, deadline, or an event respectively to the list of tasks.

Todos are tasks that do not have an end or starting date.
Deadlines are tasks that have a specific end date.
Events are tasks that have a specific starting date.

Format:
```
// todo
todo DESCRIPTION

// deadline
deadline DESCRIPTION /by DATE

// event
event DESCRIPTION /at DATE
```

Example usage:
```
todo make fried rice
deadline upload youtube video /by 2020-09-29
event meet auntie hersha /at 2020-10-20
```

Expected outcome:
```
Uncle Roger add task:
    [T][✘] make fried rice
Now you have 1 task(s) in the list lah!

Uncle Roger add task:
    [D][✘] upload youtube video (by: Sep 29, 2020)
Now you have 2 task(s) in the list lah!

Uncle Roger add task:
    [E][✘] meet auntie hersha (at: Oct 20 2020)
Now you have 3 task(s) in the list lah!
```

### `list` - Listing tasks
Shows a list of all tasks.

Format:
```
list
```

Example usage:
```
// list
list
```

Expected outcome:
```
Here the list lah:
    1.[T][✘] make fried rice
    2.[D][✘] upload youtube video (by: Sep 29, 2020)
    3.[E][✘] meet auntie hersha (at: Oct 20 2020)
```

### `done` - Marking a task as done
Marks a task as done.

Format:
```
// mark the task with index I as done
done I
```

Example usage:
```
done 1
```

Expected outcome:
```
Uncle Roger mark this task done:
    [T][O] make fried rice
```

### `delete` - Deleting a task
Deletes a task.

Format:
```
// delete the task index I
delete I
```

Example usage:
```
delete 1
```

Expected outcome:
```
Uncle Roger remove task:
    [T][O] make fried rice
Now you have 2 tasks in the list lah!
```

### `bye` - Exiting the program
Quits the program. The data involved will be automatically saved.

Format:
```
bye
```
