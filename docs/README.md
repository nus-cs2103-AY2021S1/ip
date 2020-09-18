# User Guide

## Features 

### Add tasks
Use Duke to add tasks to keep track of. Duke supports adding Todos, Deadlines and Events.

- *Todos* are tasks to indicate activities that need to be completed
- *Deadlines* are tasks to indicate activities and the deadline they need to be completed by
- *Events* are tasks to indicate activities and the time they (should) occur

### View your tasks
You can ask Duke to display your current tasks, including whether they are completed
 or not, and if they have a timing (deadline/ event time) specified. 

### Mark tasks
You can ask Duke to mark tasks in the list as done after you have completed them!

### Delete tasks
You can ask Duke to delete tasks that are no longer valid, or those that you don't want to keep track of anymore.

### Search tasks
You can ask Duke to search through the list and find tasks that contain your search input.
Duke will then display these tasks as a list.

### Conduct operations on multiple tasks
Duke allows you to conduct some operations, like delete, on multiple tasks
at the same time! Simply indicate the operation and the search key to find the tasks
to perform the operation on.

## Usage

### `todo` - Add a Todo task

Example of usage: 

`todo go to the store`

Expected outcome:

`Added task: [T][&#2717] go to the store`

### `deadline` - Add a Deadline task

Example of usage: 

`deadline submit math paper /by 2020-12-1`

Expected outcome:

`Added task: [D][&#2717] submit math paper (by: 1 Dec 2020)`

### `event` - Add an Event task

Example of usage: 

`event pizza party /at 2020-12-1`

Expected outcome:

`Added task: [E][&#2717] pizza party (by: 1 Dec 2020)`

### `list` - List your tasks

Displays currently stored tasks in a list format.

Example of usage: 

`list`

Expected outcome:

```
[D][&#2717] submit math paper (by: 1 Dec 2020)
[E][&#2717] pizza party (by: 1 Dec 2020)
```
### `done` - Mark a task as done

Marks indicated task, using the index in the list, as done.

Example of usage: 

`done 1`

Expected outcome:

`Marked task as done: [D][&#2713] submit math paper (by: 1 Dec 2020)`

### `delete` - Delete a task

Deletes the indicated task, using the index in the list, from the list.

Example of usage: 

`delete 1`

Expected outcome:

`Deleted task: [D][&#2713] submit math paper (by: 1 Dec 2020)`

### `find` - Search for tasks

Searches for tasks using the given search key.

Example of usage: 

`find party`

Expected outcome:

```
Tasks found:
[E][&#2717] pizza party (by: 1 Dec 2020)
```

### `mass` - Conduct operations on multiple tasks

Searches for tasks using the given search key. Then conducts the specified operation
on all the tasks found.

Example of usage: 

`mass done party`

Expected outcome:

```
Tasks marked as done: 
[E][&#2717] pizza party (by: 1 Dec 2020)
```