# User Guide to the Red Prince
Ever wanted a red lizard, fallen from grace, to keep track of your todo items for you? Now you can!


![Screenshot of the Red Prince](Ui.png)
## Features 

### Add Todos, Events, and Deadlines!
Force your lizard to store the tasks in his memory! 
He accepts 3 different types of tasks: Todos, Events, and Deadlines!
* Todo: The basic task type with only a name to it.
  * `todo <taskName>`
* Event: A task with a name and an event date.
  * `event <taskName> /at <yyyy-mm-dd>`
* Deadline: A task with a name and a deadline date.
  * `deadline <taskName> /by <yyyy-mm-dd>`
  
### Mark Tasks as Done!
Just finished murdering the dictator that terrorized your settlement? Mark that task as done in your list! 
You can also mark multiple tasks at once!
* `done <taskIndexes>` eg. `done 2` or `done 2 4 5`

### Find Tasks by Their Name!
Sometimes you have too many tasks in your list, and just need to clarify if that one pesky sidequest is done.
The Red Prince will look for the specified tasks with a matching name!
* `find <searchString>` eg. `find escape the`


## Usage

### `todo` - Add a task of type Todo

Creates and adds a new Todo to the list, the basic task type with only a name to it.

Usage: 

`todo <taskName>` 

### `event` - Add a task of type Event

Creates and adds a new Event to the list, a task with a name and an event date.

Usage: 

`event <taskName> /at <yyyy-mm-dd>`

### `deadline` - Add a task of type Deadline

Creates and adds a new Deadline to the list, a task with a name and a deadline date.

Usage: 

`deadline <taskName> /by <yyyy-mm-dd>`

### `done` - Mark a task as done

Mark a task as done by their given index. Supports marking multiple tasks at once.

Usage: 

`done <taskIndexes>` 

eg. `done 2` marks task number 2 as done, `done 2 4 5` marks tasks 2, 4, and 5 as done.


### `delete` - Delete a task from the list

Delete a task by their given index. Supports deleting multiple tasks at once.

Usage: 

`delete <taskIndexes>` 

eg. `delete 2` deletes task number 2, `delete 2 4 5` deletes tasks 2, 4, and 5.


### `find` - Find tasks with a matching name

Takes a String and searches for all tasks that contain the given String in their names

Usage: 

`find <searchString>` 

eg. `find escape the` will return tasks such as "Escape the Joy" and "Play escape themed games"
