# User Guide
* Features
    * [Adding a ToDo task: `todo`](#todo)
    * [Adding a Deadline task: `deadline`](#deadline)
    * [Adding an Event task: `event`](#event)
    * [Listing all task: `list`](#list)
    * [Locating a task by name: `find`](#find)
    * [Deleting a task: `delete`](#delete)
    * [Complete a task: `done`](#done)
    * [Exiting the program: `bye`](#bye)

---
## Features 

> Notes about the command format:
> * Words in `UPPER_CASE` are the parameters to be supplied by the user.  
> e.g in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo Submit work`.
> Items in square brackets are optional.  
> e.g. `KEYWORD [MORE_KEYWORD]` can be used as `try` or as `try this`
> Items with `...` can be used multiple times including zero times.  
> e.g. `KEYWORD [MORE_KEYWORD]` can be used as `just` or as `just try`, `just try this` etc.
> Parameters must be in the specified order.

### Adding a ToDo task: `todo` <a name="todo"></a>
Adds a ToDo task to Dude Bot.
Format: `todo DESCRIPTION`
Example of usage: 
* `todo Do my laundry`

Expected outcome:  
![todo outcome](rss/todo.PNG "todo")


### Adding a Deadline task: `deadline` <a name="deadline"></a>
Adds a Deadline task to Dude Bot.
Format: `deadline DESCRIPTION /by DATE`
* `DATE` **must be** in the form `YYYY-MM-DD`.
Example of usage: 
* `deadline Submit math homework /by 2020-10-04`

Expected outcome:  
![deadline outcome](rss/deadline.PNG "deadline")

### Adding an Event task: `event`: todo <a name="event"></a>
Adds an Event task to Dude Bot.
Format: `event DESCRIPTION /at DATE`
* `DATE` **must be** in the form `YYYY-MM-DD`.
Example of usage: 
* `event Go for communications class /at 2020-09-20`

Expected outcome:  
![event outcome](rss/event.PNG "event")

### Listing all task: `list` todo <a name="list"></a>
Shows a list of all tasks in Dude Bot.
Format: `list`

### Locating a task by name: `find` todo <a name="find"></a>
Finds tasks whose descriptions contains the given keyword.
Format: `find KEYWORD [MORE_KEYWORD]...`
* The search is case-insensitive. e.g `teSt` will match `Test`
* The order of the keywords does not matter. e.g `try this` will match `this try`
* Only the description is searched.
* Partial matching of words is allowed. e.g. `te` will match `ted` and `test`

Example of usage: 
* `find this try`

Expected outcome:  
![find outcome](rss/find.PNG "find")

### Deleting a task: `delete` todo <a name="delete"></a>
Deletes the specified task from Dude Bot.
Format: `delete INDEX`
* Deletes the task at the specified `INDEX`.
* The index refers to the index number displayed on the task list.
* The index **must be a positive integer**.

Example of usage: 
* `delete 2` deletes the 2nd person in the task list.

### Complete a task: `done` todo <a name="done"></a>
Marks the specified task as complete in Dude Bot.
Format: `done INDEX`
* Marks the tasks at the specified `INDEX`.
* The index refers to the index number displayed on the task list.
* The index **must be a positive integer**.

### Exiting the program: `bye` <a name="bye"></a>
Exits the program.

Format: `exit`

## Command summary
| Action | Format |
| :----- | :----- |
| bye | `bye` 
| deadline | `deadline DESCRIPTION /by DATE` <br/> e.g. `deadline Drink Tea /by 2020-09-16`
| delete | `delete INDEX` <br/> e.g. `delete 2`
| done | `done INDEX` <br/> e.g. `done 1` 
event | `event DESCRIPTION /at DATE` <br/> e.g. `event Have fun /at 2020-12-10`
find | `find KEYWORD` <br/> e.g. `find fun`
list | `list`
todo | `todo DESCRIPTION` <br/> e.g. `todo Charge airpods`
