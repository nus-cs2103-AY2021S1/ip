# User Guide to Focus

## Introduction
Meet Pocus in Focus! An all-in-one task manager to help you better plan your time.
With a simple interface and easy-to-use commands, you would be able to use Focus effectively!

## Getting started
1. Download the v0.2 JAR file and double-click it to open.
2. You should see something like this:  
<img src="https://github.com/ruilingk/ip/blob/master/docs/images/startingScreen.png" height=50% width=50%>
3. Start by typing in your name!  
<img src="https://github.com/ruilingk/ip/blob/master/docs/images/homeScreen.png" height=50% width=50%>
4. Next, start talking to Pocus and manage your tasks!

## Features 
1. [Add tasks of different types](#feature-1)
2. [Mark tasks as done](#feature-2)
3. [List tasks](#feature-3)
4. [Delete tasks](#feature-4)
5. [Find tasks with specified keyword](#feature-5)
6. [Reminder within specified period](#feature-6)
7. [Settings to change reminder period](#feature-7)
8. [List commands available](#feature-8)
9. [Exit Focus](#feature-9)

### Feature 1
Adds 3 different types of tasks, namely ToDo, Deadline and Event.

#### Usages

##### 1. Add ToDo tasks - `todo TODO_DESCRIPTION`
Adds a todo task to your task list, with no specific deadlines attached to it.

Example of usage: 
`todo user guide for Focus`

Expected outcome:  
<img src="https://github.com/ruilingk/ip/blob/master/docs/images/toodo.png" height=50% width=50%>

##### 2. Add Deadline tasks - `deadline DEADLINE_DESCRIPTION /by YYYY-MM-DD HH:mm`
Adds a deadline task to your task list, with a deadline attached to it.

Example of usage: 
`deadline iP /by 2020-09-18 23:59`

Expected outcome:  
<img src="https://github.com/ruilingk/ip/blob/master/docs/images/deadline.png" height=50% width=50%>

##### 3. Add Event tasks - `event EVENT_DESCRIPTION /at YYYY-MM-DD HH:mm`
Adds an event task to your task list, with a date and time attached to it.

Example of usage: 
`event Christmas Party /at 2020-12-25 18:00`

Expected outcome:  
<img src="https://github.com/ruilingk/ip/blob/master/docs/images/event.png" height=50% width=50%>

### Feature 2
Marks the tasks that you have completed as done.

#### Usage

##### Mark tasks as done - `done TASK_NUMBER`
Marks the task with the task number you have specified to be done.

Example of usage: 
`done 2`

Expected outcome:  
<img src="https://github.com/ruilingk/ip/blob/master/docs/images/done.png" height=50% width=50%>

### Feature 3
Lists the tasks that you have in your task list.

#### Usage

##### List tasks - `list`
Displays the tasks that you have in your task list regardless of whether you have completed or not.

Example of usage: 
`list`

Expected outcome:  
<img src="https://github.com/ruilingk/ip/blob/master/docs/images/list.png" height=50% width=50%>

### Feature 4
Deletes the tasks.

#### Usage

##### Delete tasks - `delete TASK_NUMBER`
Deletes the task with the task number you have specified.

Example of usage: 
`delete 3`

Expected outcome:  
<img src="https://github.com/ruilingk/ip/blob/master/docs/images/delete.png" height=50% width=50%>

### Feature 5
Finds the tasks which contains the keyword you want.

#### Usage

##### Find tasks with specified keyword -`find KEYWORD`
Finds the tasks which has KEYWORD as a description.

Example of usage: 
`find party`

Expected outcome:  
<img src="https://github.com/ruilingk/ip/blob/master/docs/images/find.png" height=50% width=50%>

### Feature 6
Reminds you of the tasks which are due soon (within your specified period of days).

#### Usage

##### Reminder within specified period -`remind`
Reminds you of tasks within specified period of days. Default is 7 days.

Example of usage: 
`remind`

Expected outcome:  
<img src="https://github.com/ruilingk/ip/blob/master/docs/images/remind.png" height=50% width=50%>

### Feature 7
Allows you to change the number of days you require for reminders.

#### Usage

##### Settings to change reminder period -`settings /days NUMBER_OF_DAYS`
Default number of days is 7 days.

Example of usage: 
`settings /days 3`

Expected outcome:  
<img src="https://github.com/ruilingk/ip/blob/master/docs/images/settings.png" height=50% width=50%>

### Feature 8
Lists out the available commands to help you. Good for first-time users!

#### Usage

##### List commands available -`help`
Helps you to get used to the commands Pocus can recognise.

Example of usage: 
`help`

Expected outcome:  
<img src="https://github.com/ruilingk/ip/blob/master/docs/images/help.png" height=50% width=50%>

### Feature 9
Exits the application.

#### Usage

##### Exit Focus -`bye`
Exits the application, and the window will close in 5 seconds automatically.

Example of usage: 
`bye`

Expected outcome:  
<img src="https://github.com/ruilingk/ip/blob/master/docs/images/help.png" height=50% width=50%>
