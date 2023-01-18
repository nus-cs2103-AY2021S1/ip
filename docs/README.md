# User Guide
Duke is a Personal Assistant Chatbot that helps you to keep track of various tasks. 
You can interact with Duke by giving him various commands via a Command Line Interface.
## Features 

### `list` - List all tasks

Format: `list`

Lists all the tasks in your current task list.
There are 3 types of tasks in Duke: `Todo`, `Deadline`, and `Event`.


Expected outcome:

All the tasks will be printed with their task number, completion status, description, date and time (if available).

### `todo` - Create Todo task 

Format: `todo <description>`

Creates a task of type Todo. 
The name of this task should be stated in `<description>`.

Example of usage: 

`todo sleep` will create a Todo task with description 'sleep'.

Expected outcome:

The newly created Todo task will be printed, along with an indication of its completion status and the number of tasks in the list currently. 

### `event` - Create Event task

Format: `event <description> /at <date> <time>`

Creates a task of type Event. 
The name of this task should be stated in `<description>`.
The `<date>` of the task should be in `YYYY-MM-DD` format, while the `<time>` should be in `HHmm` format. 

Example of usage: 

`event project meeting /at 2020-09-23 1900` will create an Event task with description 'project meeting', which will occur on 23 Sept 2020 at 7pm.

Expected outcome:

The newly created Event task will be printed, along with an indication of its completion status and the number of tasks in the list currently.

### `deadline` - Create Deadline task

Format: `deadline <description> /by <date> <time>`

Creates a task of type Deadline. 
The name of this task should be stated in `<description>`.
The `<date>` of the task should be in `YYYY-MM-DD` format, while the `<time>` should be in `HHmm` format. 

Example of usage: 

`deadline assignment /at 2020-09-25 2359` will create an Deadline task with description 'assignment', which will be due on 25 Sept 2020 at 11.59pm.

Expected outcome:

The newly created Deadline task will be printed, along with an indication of its completion status and the number of tasks in the list currently.


### `find` - Locate tasks

Format: `find <keyword>`

Searches for all the tasks with description containing `<keyword>`.

Example of usage: 

`find book` will find all the tasks that contains 'book' in its description.

Expected outcome:

All tasks containing the specified keyword in its description will be printed.

### `done` - Mark task as done

Format: `done <taskNo>`

Marks the task with `<taskNo>` as its task number to be completed.

Example of usage: 

`done 1` will mark the first task in the list to be completed.

Expected outcome:

The task marked as done will be printed with an indication of its completed status.

### `delete` - Delete task

Format: `delete <taskNo>`

Deletes the task with `<taskNo>` as its task number.

Example of usage: 

`delete 1` will delete the first task in the list.

Expected outcome:

The task deleted will be printed. 

### `help` - In-App Guidance

Format: `help`

Provide an in-app guidance for users.


### `bye`- Exit program

Format: `bye`

Exits the Duke program.

