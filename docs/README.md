# User Guide

## Features 

### CRUD for tasks (ToDos, Deadlines, and Events)
You can create ToDos, Deadlines, and Events, by using the [todo](#todo---create-a-todo-task), [deadline](#deadline---create-a-deadline-task), and [event](#event---create-an-event-task) commands respectively. You can view them using the [list](#list---list-all-tasks) command. You can update them by setting them as done using the [done](#done---mark-a-task-as-done) command. You can delete them by using the [delete](#delete---delete-a-task) command.

### Search for tasks
You can search for tasks by using the [find](#find---find-tasks) command.

### Local saving
Your tasks will automatically be saved into a text file under the data folder whenever the task list updates, and will be loaded when Chatterbox starts up.

### Archive tasks
You can archive all tasks into a backup file in the data folder, and start with a clean state using the [archive](#archive---archive-tasks) command.

## Usage

### `todo` - Create a ToDo task

By entering `todo` followed by the contents of the task, you can create a ToDo task.

Example of usage:

`todo task 1`

Expected outcome:
 ```
 Got it. I've added this task:
 [T][✗] task 1
 You now have 1 task in your list
 ```
 
### `deadline` - Create a Deadline task

By entering `deadline` followed by the contents of the task, you can create a Deadline task. You can also add `/by <date> <time>` after the contents to attach an actual deadline to the task, where `<date>` is in either `d/M/y` or `d-M-y` format, and time is in `HHmm` format.

Example of usage:

`deadline task 2 /by 23/09/20 1100`

Expected outcome:
 ```
 Got it. I've added this task:
 [D][✗] task 2 (by: Sep 23 2020 1100H)
 You now have 2 tasks in your list
 ```

### `event` - Create an Event task

By entering `event` followed by the contents of the task, you can create an Event task. You can also add `/at <date> <time>` after the contents to attach an actual event timing to the task, where `<date>` is in either `d/M/y` or `d-M-y` format, and time is in `HHmm` format.

Example of usage:

`event task 3 /at 12-12-20 1200`

Expected outcome:
 ```
 Got it. I've added this task:
 [E][✗] task 3 (by: Dec 12 2020 1200H)
 You now have 3 tasks in your list
 ```

### `list` - List all tasks

By entering `list`, Chatterbox will respond with a list of all ToDos, Events, and Deadlines you have.

Example of usage:

`list`

Expected outcome:
 ```
 1. [T][✗] task 1
 2. [D][✗] task 2 (by: Sep 23 2020 1100H)
 3. [E][✗] task 3 (by: Dec 12 2020 1200H)
 ```

### `done` - Mark a task as done

By entering `done` followed by the task number, you can mark that task as done.

Example of usage:

`done 1`

Expected outcome:
 ```
 Nice! I've marked this task as done:
 [T][✓] task 1
 ```
 
### `delete` - Delete a task

By entering `delete` followed by the task number, you can delete that task.

Example of usage:

`delete 1`

Expected outcome:
 ```
 Noted! I've removed this task from your list:
 [T][✓] task 1
 You now have 2 tasks in your list
 ```
 
### `find` - Find tasks

By entering `find` followed by keyword(s), you can get a list of tasks that contain that keyword.

Example of usage:

`find 3`

Expected outcome:
 ```
 2. [E][✗] task 3 (by: Dec 12 2020 1200H)
 ```
 
### `archive` - Archive tasks

By entering `archive`, you can archive all your tasks and start your task list over from a clean state.

Example of usage:

`archive`

Expected outcome:
 ```
 I have archived all your tasks into the archive file chatterbox_0.bak, you can find it in the data folder
 ```
 
