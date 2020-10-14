# BrawlyDuke User Guide
part of the CS2103T module Individual Project (IP). 
BrawlyDuke is a task management Chatbot to help keep track of you activities.

BrawlyDuke was designed with one of my favourite mobile games Brawl Stars
![BrawlyDuke](Ui.png)

## Features 
1. **Add** various types of task to the bot.
2. **Delete** tasks that are no longer relevant/completed.
3. **List** out all the tasks on your list.
4. **Complete** tasks and mark them as done.
5. **Search** for tasks in the list through keywords.
6. **Help** on how to use the Chatbot.
7. **Exit** the application and have your data saved.
##



### Add Tasks
Users will be able to add various different types of tasks:
* todo _a generic task_
* deadline _a task with a specific date to be completed_
* event _a task with a specific location_


#### Todo

Usage

`todo <task description>`

Example

`todo go swim`

Expected Outcome
```
Got it I have added this task:
[T][✘] go swim
you now have 1 task on the list
```
#### Deadline
Usage

`deadline <task description> /by <YYYY-MM-DD>`

Example

`deadline finish math homework /by 2020-10-10`

Expected Outcome
```
Got it I have added this task:
[D][✘] finish math homework (by:Oct 10 2020)
you now have 1 task on the list
```
#### Event
Usage

`event <task description> /at <task location>`

Example

`event go run /at East Coast Park`

Expected Outcome
```
Got it I have added this task:
[E][✘] go run (at:East Coast Park)
you now have 1 task on the list
```
##

### Delete Tasks
Users will be able to delete tasks based on the index in the list.

####delete

Usage

 `delete <task index>`
 
Example
 
`delete 1`
 
Expected outcome
 
```
Noted I've removed this task
[T][✘]go swim
You now have 2 tasks on the list
```
##

### List Tasks
Users will be able to view the full list of tasks that they have added.

####list

Usage and example

`list`

Expected Outcome
```
Here are the tasks in your list:
1.[T][✘]go swim
2.[T][✘]go run
...
```
##

### Complete Tasks
Users will be able to mark tasks as completed based on the index in the list.

#### done

Usage

`done <task index>`

Example

`done 1`

Expected Outcome
```
I have marked this as done:
[✓]go run
```
##

### Search Tasks

Users will be able to search for tasks based on the keyword, and tasks in the list.

####find

Usage

`find <keyword>`

Example

`find run`

Expected Outcome
```
[T][✓]go run
[E][✘]go run (at:East Coast Park)
```
##

### Help
Users will be able to ask for help for specific tasks.

####help

Example and Usage

`help`

Expected Outcome
```
Here are the available commands:
-todo <description>
-deadline <description> /by YYYY-MM-DD
...
```
For more specific instructions for tasks you can use:
* `help todo`
* `help deadline`
* `help task`
##

###Bye
Users will be able to exit the application and have their tasks saved.

####bye
Example and Usage

`bye`

Expected Outcome
```
Bye have a good day!
application will be closing shortly
```
## Thank you and enjoy BrawlyDuke!


