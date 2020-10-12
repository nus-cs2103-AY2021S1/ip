# User Guide
Viscount is a **desktop app for managing tasks, optimised for use via a Command Line Interface (CLI)** while still having the benefits of a Graphical User Interface (GUI). If you are someone who can type fast, Viscount can help you track your tasks faster than traditional GUI apps.

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

### `add` - Adding a task
Adds a task into the list.

There are three types of tasks: `Todo`, `Deadline` and `Event`.

Format:
```
// add a todo
add todo DESCRIPTION

// add a deadline
add deadline DESCRIPTION /by DATE_DUE

// add an event
add event DESCRIPTION /at DATE_OF_EVENT
```

Example usage:
```
add todo read book
add deadline return book /by 28-09-2020
add event go to library /at 28-09-2020 1000
```

Expected outcome:
```
Very well. I've added this todo:
[T][✘] read book
Now you have 1 tasks in the list.

Very well. I've added this deadline:
[D][✘] return book (by: Sep 28 2020 00:00)
Now you have 2 tasks in the list.

Very well. I've added this event:
[E][✘] go to library (at: Sep 28 2020 10:00)
Now you have 3 tasks in the list.
```

### `list` - Listing tasks
Shows a list of all tasks.
Filter the list by:
- task type 
- keyword search with `/find`
- date search with `/on`

Format:
```
list [TASK_TYPE] [/on DATE_FILTER] [/find KEYWORD]
```

Example usage:
```
// list all tasks
list

// list all todos
list todo

// list all tasks occurring today
list /on today

// list all tasks containing the word 'book'
list /find book

// list all deadlines occurring on 28 Sep 2020 containing the word 'book'
list deadline /on 28-09-2020 /find book
OR
list deadline /find book /on 28-09-2020
// order of the two parameters does not matter
```

Expected outcome:
```
Here are the tasks in your list:
1.[T][✘] read book
2.[D][✘] return book (by: Sep 28 2020 00:00)
3.[E][✘] go to library (at: Sep 28 2020 10:00)

Here are the todos in your list:
1.[T][✘] read book

Here are the tasks occurring today in your list:
<tasks occurring on the actual day will be reflected here>

Here are the tasks containing 'book' in your list:
1.[T][✘] read book
2.[D][✘] return book (by: Sep 28 2020 00:00)

Here are the deadlines occurring on Sep 28 2020 containing 'book' in your list:
1.[D][✘] return book (by: Sep 28 2020 00:00)
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
done all
```

Expected outcome:
```
Very good! I have marked this todo as done:
[T][✓] read book

Very good! I have marked all these tasks as done:
1.[D][✓] return book (by: Sep 28 2020 00:00)
2.[E][✓] go to library (at: Sep 28 2020 10:00)
```

### `edit` - Editing a task
Edits an existing task by index.

Format:
```
// must have at least /desc or /date
edit INDEX [/desc DESCRIPTION] [/date DATE_TASK]
```

Example usage:
```
edit 1 /desc read book and take notes
edit 2 /date 03-12-2020
edit 3 /desc take bus to library /date 03-12-2020 1200
```

Expected Outcome:
```
Very well. I've edited the description of this todo:
[T][✓] read book and take notes

Very well. I've edited the due date of this deadline:
[D][✓] return book (by: Dec 03 2020 00:00)

Very well. I've edited the due date of this deadline:
[E][✓] go to library (at: Dec 03 2020 12:00)
Very well. I've edited the description of this todo:
[E][✓] take bus to library (at: Dec 03 2020 12:00)
```

### `delete` - Deleting a task
Deletes a task.

Format:
```
// delete the task with INDEX
delete INDEX

// delete all tasks
delete all

// delete all tasks that are marked as done
delete done
```

Example usage:
```
delete 1
delete done
add todo t1
add todo t2
delete all
```

Expected outcome:
```
Very well. I've removed this todo:
[T][✓] read book
Now you have 2 tasks in the list.

Very well. I've removed all the done tasks in the list:
1.[D][✓] return book (by: Sep 28 2020 00:00)
2.[E][✓] go to library (at: Sep 28 2020 10:00)
Now you have 0 tasks in the list.

Very well. I've added this todo:
[T][✘] t1
Now you have 1 tasks in the list.

Very well. I've added this todo:
[T][✘] t2
Now you have 2 tasks in the list.

Very well. I've removed all the tasks in the list:
1.[T][✘] t1
2.[T][✘] t2
The list is now empty.
```

### `bye` - Exiting the program
Closes the program. The task data will be automatically saved.

Format:
```
bye
```
