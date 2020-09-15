# BrawlyDuke User Guide
part of the CS2103T module Individual Project (IP). 
BrawlyDuke is a task management Chatbot to help keep track of you activities.
## Features 
1. **Add** various types of task to the bot.
2. **Delete** tasks that are no longer relevant/completed.
2. **List** out all the tasks on your list.
3. **Complete** tasks and mark them as done.
4. **Search** for tasks in the list through keywords.
5. **Help** on how to use the Chatbot.
### Add Tasks
Users will be able to add various different types of tasks:
* todo _a generic task_
* deadline _a task with a specific date to be completed_
* event _a task with a specific location_

Command: 
* `todo <task description>`
* `deadline <task description> /by <YYYY-MM-DD>`
* `event <task description> /by <task location>`

### Delete Tasks
Users will be able to delete tasks based on the index in the list.

Command: `delete <task index>`
### List Tasks
Users will be able to view the full list of tasks that they have added.

Command: `list`
### Complete Tasks
Users will be able to mark tasks as completed based on the index in the list.

Command: `done <task index>`
### Search Tasks
Users will be able to search for tasks based on the keyword, and tasks in the list.

Command: `find <keyword>`
### Help
Users will be able to ask for help for specific tasks.
Command:
* `help todo`
* `help deadline`
* `help task`

## Thank you and enjoy BrawlyDuke!


