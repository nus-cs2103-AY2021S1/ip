# User Guide
## Features 

### Manage Your Tasks
Add, delete, show, and mark your tasks as done.

## Usage

Note: 
Examples below are assumed to be executed in order.
Items in [] are optional.

### `todo` - Add a new task

Format: `todo <task>`

Example of usage: 

    todo hello

Expected outcome:

    Task added: [T][X] hello

### `deadline` - Add a new task with a deadline

Format: `deadline <task> /by yyyy-mm-dd`

Example of usage: 

    deadline hello /by 2020-09-30

Expected outcome:

    Task added: [D][X] hello (by: 30 Sep 2020)

### `event` - Add a new task that happens on a certain date

Format: `event <task> /at yyyy-mm-dd`

Example of usage: 

    event hello /at 2020-09-30

Expected outcome:

    Task added: [E][X] hello (at: 30 Sep 2020)

### `done` - Mark a task as done

Format: `done <task number>`

Example of usage: 

    done 1

Expected outcome:

    Task marked as done: [T][V] hello

### `delete` - Delete a task

Format: `delete <task number>`

Example of usage: 

    delete 2

Expected outcome:

    Task deleted: [D][X] hello (by: 30 Sep 2020)

### `list` - List all tasks, or all tasks on a certain date

Format: `list [yyyy-mm-dd]`

Example of usage: 

    list 2020-09-30

Expected outcome:

    2. [E][X] hello (at: 30 Sep 2020)

### `find` - Search for a task using keywords

Format: `find <keywords>`

Example of usage: 

    find hello
    
Expected outcome:

    1. [T][V] hello
    2. [E][X] hello (at: 30 Sep 2020)

### `bye` - Exit the chat bot

Format: `bye`

Expected outcome:

Window closes.

## Credits
Followed the guides from [SE-EDU](https://se-education.org/guides/index.html).