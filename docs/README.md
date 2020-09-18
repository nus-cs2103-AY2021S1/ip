# User Guide
This is a user guide for the personalised task manager, Duke.

## Features 
1. Add
2. Delete
3. Complete
4. Find
5. List
6. View statistics
7. Exit

## Usage

### `todo {name}` - Adds a new todo task to Duke

Todos are tasks without any date attached to it.
Adds a todo to Duke. Name of task has to be specified after the todo command.

Example of usage: 

`todo eat`

Expected output: 

`added!`
`[todo][x] eat`
`total task: 1`
`:o`

### `deadline {name} /by {date}` - Adds a new deadline task to Duke

Deadlines are tasks that has to be done before a specific date.
Adds a deadline to Duke. Name of task and date of task has to be specified after the todo command. Date has to be specified in YYYY-MM-DD format.

Example of usage: 

`deadline homework /by 2020-09-20`

Expected output: 

`added!`
`[deadline][x] homework (by: 20 Sep 2020)`
`total task: 2`
`:o`

### `event {name} /at {date}` - Adds a new event task to Duke

Events are tasks that occur on a specific date.
Adds an event to Duke. Name of task and date of task has to be specified after the todo command. Date has to be specified in YYYY-MM-DD format.

Example of usage: 

`event birthday /at 2020-10-10`

Expected output: 

`added!`
`[event][x] birthday (at: 10 Oct 2020)`
`total task: 3`
`:o`

### `delete {task number}` - Deletes a task in Duke

Deletes a task in Duke. Task number has to be specified.

Example of usage: 

`delete 3`

Expected output: 

`removed!! ^^`
`[event][x] birthday (at: 10 Oct 2020)`
`total task: 2`
`:o`

### `done {task number}` - Completes a task in Duke

Completes a task in Duke. Task number has to be specified.

Example of usage: 

`done 5`

Expected output: 

`gfy youve managed to finish the following...`
`[todo][o] read book`

### `find {keyword}` - Finds a task in Duke

Finds task matching specified keyword in Duke. Keyword has to be specified.

Example of usage: 

`find book`

Expected output: 

`1. [todo][o] eat`
`2. [event][x] book club (at: 11 Oct 2020)`

### `list` - List all tasks added to Duke

Lists all tasks added to Duke.

Expected output: 

`1. [todo][x] book review`
`2. [event][x] book club (at: 11 Oct 2020)`
`3. [todo][o] eat`
`4. [deadline][x] homework (by: 20 Sep 2020)`

### `statistics` - Show statistics of tasks in Duke

Shows weekly statistics.

Expected output: 

`total tasks added: 3`
`total tasks completed: 1`
`total tasks deleted: 1`

### `bye` - Terminates Duke

Terminates Duke.