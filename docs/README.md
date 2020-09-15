# User Guide
Welcome to Duke App, This App is written by Jin Yuze from CS2103 National University of Singapore.

Duke can help you to manage your daily tasks. It provides several types of task management. 
It also provides task deletion, task marking and searching functions. You can also own multiple independent task lists.

Hope Duke can be your best helper!

## Features 

### Add tasks: todo/deadline/event
You can add three kinds of tasks in Duke. They are Todo, Deadline, and Event.

To add them, you can use these commands.

* todo [TASK CONTENT]
* deadline [TASK CONTENT] / [TASK DEADLINE]
* event [TASK CONTENT] / [TASK TIME]

Note:

Remember to add "/" between your task content and time description.

Remember the format for time description is "yyyy-mm-dd".

### List add tasks: list
You can list all the tasks inside your current task list.

* list

### Mark task as done: done
When you finish a task, you can mark it as done.

* done [TASK NUMBER]

Note: 

You can find task number in the output of list command.

### Delete task: delete
You can delete a task if you don't want to keep it inside your program anymore.

* delete [TASK NUMBER]

Note:

You can find task number in the output of list command.

### Search for a task: find
You can search for a task by keywords. Duke will find all the tasks with the keyword you give.

* find [KEYWORD]

### Exit Duke: bye
Say bye to Duke and you will exit the app. Don't worry, all your data is stored in disk.

* bye