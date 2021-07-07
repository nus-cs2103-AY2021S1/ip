# User Guide

## Features 

### Listing Tasks
Shows the full list of Tasks saved.

### `list` - Shows the full list of Tasks saved.  

Duke will reply with a list of Tasks. 

Example of usage: 

`list`

Expected outcome:

```
1. [T] [✓] Todo
2. [D] [✓] Deadline (by: Oct 20 2020)
3. [E] [✗] Event (at: Nov 5 2020)
```

### Finding Tasks
Duke can find Tasks that have names containing a specified keyword. 
The results are numbered according to their index in the full list, so the same indexes can be used for other commands. 

### `find {keyword}` - Finds all Tasks that contain {keyword} in the name.

Example of usage:
 
`find deadline`

Expected outcome:

```
2. [D] [✓] Deadline (by: Oct 20 2020)
```

### Marking Tasks as completed
Duke can mark Tasks as completed based on the index in the list.
If the specified Task is already completed, Duke will mark it as incomplete. 

### `done {index}` - Marks the task at the specified index as complete or incomplete.

Example of usage:

`done 3`

Expected outcome:

```[E] [✓] Event (at: Nov 5 2020)```

### Deleting Tasks
Duke can delete Tasks based on the index in the list.

### `delete {index}` - Deletes the task at the specified index.

Example of usage:

`delete 3`

Expected outcome:

```Deleted [E] [✓] Event (at: Nov 5 2020).```

### Adding a Todo task
A Todo task has no date or deadline, and only needs the user to specify the name. 

### `todo {name}` - Adds a new Todo task with the given name. 

Example of usage:

`todo Do post-lecture quiz`

Expected outcome:

```Added [T] [✗] Do post-lecture quiz.```

### Adding a Event task
An Event task has a name and a date (in YYYY-MM-DD).

### `event {name} /at {YYYY-MM-DD}` - Adds a new Event task with the given name and date.

Example of usage:

`event Results release /at 2020-09-16`

Expected outcome:

```Added [E] [✗] Results release (at: Sep 16 2020).```

### Adding a Deadline task
A Deadline task has a name and a date (in YYYY-MM-DD).

### `deadline {name} /by {YYYY-MM-DD}` - Adds a new Deadline task with the given name and date.

Example of usage:

`deadline Lab submission /by 2020-09-16`

Expected outcome:

```Added [D] [✗] Lab submission (at: Sep 16 2020).```


### Exiting Duke
Closes the program window. 

### `bye` - Closes the program. 

Example of usage:

`bye`

Expected outcome:

```Goodbye```

### Help menu
Shows the help guide. Can be called with a specific command to information on that command, or with no arguments to
 show information about the help command. 
Can be called with `commands` as an argument by using `help commands` to show the full list of commands. 

### `help [{command}]` - Shows information about the command (if any), otherwise shows instructions on how to use the help command.  

Example of usage: 

```help delete```


Expected outcome:
```
delete <index> - Deletes the task at the index of the list.
   
Example usage: "delete 1" -> Removes the first entry of the list. 
```
