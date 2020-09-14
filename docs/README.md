
# DUKE USER GUIDE - Penn Han's 2103T iP Todolist Chatbot

## Features 
- Add Tasks: 
	- Todo
	- Event /by date
	- Deadline /at date
- Lists all Saved Tasks
- Delete Tasks
- Mark Tasks as Done
- Mark Tasks as Important
- Find Specific Tasks by Keyword
- Refresh the Entire List

Duke also prioritises more Urgent and Important Tasks to be listed on top while Completed Tasks are
listed at the bottom. 

### Feature 1 : Add Tasks 
There are three classification of tasks. **Todo**, **Event** and **Deadline**. 
**Todo** tasks are general tasks that are not tagged to a specific Date.
Use this for general errands like "Buy Milk!"  

	To add a Todo task. Input this command
		- Todo (task)
		
	eg. Todo Buy Milk

**Event** tasks are tasks that have a specific Date.
Use this for recording future appointments like "Lunch with Emily" 

	To add an Event task, Input this command
		- Event (task) /at MMM D YYYY hh:mm (date & time)
		
	eg. Event Lunch with Emily /at Sep 18 2020 12:30

**Deadline** tasks are tasks that have a specific Date.
Use this for recording future deadlines like "Report Submission" 

	To add a Deadline task, Input this command
		- Deadline (task) /by MMM D YYYY hh:mm (date & time)
	
	eg. Deadline 2103T Report Submission /by Sep 18 2020 23:59
	
### Feature 2 : List all Saved Tasks
Users can view all Current Tasks that have been added into the List. This
includes Saved Tasks from previous usages.

	Example: 
	command: list
	list: 
	1. [T][X] Read Book
	2. [E][O] Lunch with Emily at Sep 18 2020 12:30
	3. [D][O] 2103T Report Submission by Sep 18 2020 23:59

### Feature 3 : Delete Task 
Users can delete Tasks that have been completed. They can do so by
indicating the index of the Task that they would like to delete. 

	Example: 
	1. [T][X] Read Book
	2. [E][X] Lunch with Emily at Sep 18 2020 12:30
	3. [D][O] 2103T Report Submission by Sep 18 2020 23:59

	command: delete 2
	
	resultant list:
	1. [T][X] Read Book
	2. [D][O] 2103T Report Submission by Sep 18 2020 23:59


### Feature 4 : Mark Tasks as Done
Users can indicate that a Task has been completed. They can do so by
indicating the index of the Task that they would like to mark as done.

	Example: 
	1. [T][X] Read Book
	2. [E][X] Lunch with Emily at Sep 18 2020 12:30
	3. [D][O] 2103T Report Submission by Sep 18 2020 23:59

	command: done 2
	
	resultant list:
	1. [T][X] Read Book
	2. [E][O] Lunch with Emily at Sep 18 2020 12:30
	3. [D][O] 2103T Report Submission by Sep 18 2020 23:59
	
### Feature 5 : Mark Tasks as Important
Users can indicate that a Task is important. They can do so by
indicating the index of the Task that they would like to mark as important

	Example: 
	1. [T][X] Read Book
	2. [E][X] Lunch with Emily at Sep 18 2020 12:30
	3. [D][O] 2103T Report Submission by Sep 18 2020 23:59

	command: important 2
	
	resultant list:
	1. [E][X]** Lunch with Emily at Sep 18 2020 12:30
	2. [T][X] Read Book
	3. [D][O] 2103T Report Submission by Sep 18 2020 23:59
	
	_note that the order changed in order of urgency and importance_

### Feature 6 : Find Specific Tasks by Keyword
Users can view specific Tasks that have been added into the List. This
includes Saved Tasks from previous usages.

	Example: 
	command: find book
	
	list: 
	1. [T][X] Read Book

### Feature 7 : Refresh the Entire List of Tasks
Users can refresh the entire list of Tasks to produce an empty list
for them to work with.
**Note that this will remove all saved tasks and deleted tasks are not recoverable** 

	Example:
	command: refresh
	
	list:
	__________________
