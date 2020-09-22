# Duke project - Individual project for CS2103 

![Duke UI](https://github.com/theodoreleebrant/ip/blob/master/docs/Ui.png)

Duke is a **desktop app for managing tasks, optimised for use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). If you are someone who can type fast, Duke can help you track your tasks faster than traditional GUI apps.

## Quick Start
1. Ensure you have Java 11 or later installed.
1. Download the jar file from the project website.
1. Copy the file to the folder you want to use as the home folder for the application.
1. Double-click the file to start the app. A GUI should appear, with the field bar to input commands. The list of commands are available below.

## Features 
1. Three types of tasks:
    1. Todos
    1. Deadlines
    1. Events
1. Add, mark as done, and delete tasks
1. List tasks, filterable by:
    1. task type
    1. keyword search
    1. date search
1. Mass operations
    1. mark all tasks as done
    1. delete all dasks
    1. delete all done tasks.

## Usage
> Command format:
> - Words in `UPPER_CASE` are parameters supplied by the user
> - Parameters starting with `DATE` must either be `today` or supplied in this format: `dd-MM-yyyy hhmm` where:
>   - `dd` is the date as a valid integer (e.g. `28`, `03`)
>   - `MM` is the month as a valid integer (e.g. `01`, `12`)
>   - `yyyy` is the year as a valid integer (e.g. `2020`)
>   - (optional) `hhmm` is the time in 24 hour format (e.g. `1500` for 3:00pm)
> - Parameters in square brackets are optional

### `todo`, `deadline`, `event` - Adding a task
Adds a task into the list.

There are three types of tasks: `Todo`, `Deadline` and `Event`.

Format:
```
// add a todo
todo DESCRIPTION

// add a deadline
deadline DESCRIPTION /by DATE_DUE

// add an event
event DESCRIPTION /at DATE_OF_EVENT
```

Example usage:
```
todo read book
deadline return book /by 28-09-2020
event go to library /at 28-09-2020 1000
```

Expected outcome:
```
Got it. I've added this task:
[T][✘] read book
Now you have 1 tasks in the list.

Got it. I've added this task:
[D][✘] return book (by: Sep 28 2020 00:00)
Now you have 2 tasks in the list.

Got it. I've added this task:
[E][✘] go to library (at: Sep 28 2020 10:00)
Now you have 3 tasks in the list.
```

### `list` - Listing tasks
Shows a list of all tasks.

Format:
```
list
```

Example usage:
```
// list all tasks
list
```

Expected outcome:
```
Here are the tasks in your list:
1.[T][✘] read book
2.[D][✘] return book (by: Sep 28 2020 00:00)
3.[E][✘] go to library (at: Sep 28 2020 10:00)
```

### `done` - Marking a task as done
Marks a task as done.

Format:
```
// mark the task with INDEX as done
done INDEX

// mark all tasks as done
done all
```

Example usage:
```
done 1
```

Expected outcome:
```
Nice! I've marked this task as done:
[T][✓] read book
```

### `delete` - Deleting a task
Deletes a task.

Format:
```
// delete the task with INDEX
delete INDEX
```

Example usage:
```
delete 1
```

Expected outcome:
```
Noted. I've removed this task:
[T][✓] read book
Now you have 2 tasks in the list.
```

### `help` - Printing Help Message
Prints the help message

Format:
```
help
```

### `bye` - Exiting the program
Closes the program. The task data will be automatically saved.

Format:
```
bye
```
