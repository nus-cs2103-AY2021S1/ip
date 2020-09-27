# User Guide

KaTo Task Manager (KTM) is a desktop app for managing tasks, 
optimized for use via a Chat Window with Ka To.
Ka To can keep track of all your remaining tasks,
and help you to finish the tasks on time.
At the same time, you could update Ka To to mark the tasks as done or delete them.
KTM helps you to be more aware of your schedule and become a better task manager.

## Features with commands
  Notes about the command format:
 
  - `+` represents typing down the information in order, without the `+` itself,
    e.g. `Done + " " + 3` can be typed as `Done 3` in  the send box
  - `" "` represents a space, 
    e.g. `todo + " " + info` can be typed as `todo info` in the text box 
  - `Number` represents any number sequence of the task lists,
    e.g. `Done 3` is one possible way of `Done + " " + Number`
  - `info` represents any details provided for the task",
    e.g. `todo eat` is one possible way of `todo + "_" + info`
  - `time` represents any date target for the task and it must be in the format of "yyyy-MM-dd"
    e.g. 2020-09-09 is one possible way of time
 
   Feature List:
 1. Checking for food left in fridge: `food`
 2. Checking for allowance left in account: `allowance`
 3. Adding a todo task: `todo + " " + info`
 4. Adding a deadline task: `deadline + " " + info + "/by" + time`
 5. Adding a event task: `event + " " + info + "/at" + time`
 6. Deleting a task: `delete + " " + Number` 
 7. Marking a task as done: `done + " " + Number`
 8. Finding relevant tasks: `find + " " + info`
 9. Viewing the task list: `tasks`
 10. Clearing the task list: `clear`
 11. Saying bye to KaTo: `bye`
 12. Exiting KTM : `exit`
 
 
### Feature 1 
Checking for food left in fridge: `food`

- Example of usage: 
`food left`

- Expected outcome:
KaTo will reply  `"Only an apple pie"` in the window

### Feature 2
Checking for allowance left in account: `allowance`

- Example of usage: 
`allowance left`

- Expected outcome:
KaTo will reply  `I have checked, it is 2000000 SGD` in the window

### Feature 3
Adding a todo task: `todo + " " + info`

- Example of usage: 
`todo finish my ip`

- Expected outcome:
KaTo will add `todo finish my ip` to the list, 
KaTo will inform you whether it is successful 
and state the total number of tasks in the task list.

### Feature 4
Adding a deadline task: `deadline + " " + info + "/by" + time`
  ( The time format must be "yyyy-MM-dd" )
- Example of usage: 
`deadline finish my ip /by 2020-09-17`

- Expected outcome:
KaTo will add `deadline finish my ip /by 2020-09-17` to the list, 
KaTo will inform you whether it is added successfully 
and state the total number of tasks in the task list.

### Feature 5
Adding a event task: `event + " " + info + "/at" + time`
  ( The time format must be "yyyy-MM-dd" )
- Example of usage: 
`event finish my ip /at 2020-09-17`

- Expected outcome:
KaTo will add `event finish my ip /at 2020-09-17` to the list, 
KaTo will inform you whether it is added successfully 
and state the total number of tasks in the task list. 

### Feature 6
Deleting a task: `delete + " " + Number` 

- Example of usage: 
`delete 3`

- Expected outcome:
KaTo will delete the task with sequence number 3 in the list. 

  Note:   
   (The number must be not be larger than the size of the current task list 
and be a positive integer number)

   KaTo will inform you whether it is successful and update the task list and storage.

### Feature 7
Marking a task as done: `done + " " + Number`

- Example of usage: 
`done 3`

- Expected outcome:
KaTo will change the status of the respective task from  a cross to a tick.

  Note:   
   (The number must be not be larger than the size of the current task list 
and be a positive integer number)

   KaTo will inform you whether it is successful and update the task list and storage.
   
### Feature 8
Finding relevant tasks: `find + " " + info`

- Example of usage: 
`find ip`

- Expected outcome:

KaTo will list out the relevant tasks that contain `info`.

### Feature 9
Viewing the task list: `tasks`

- Example of usage: 
`tasks`

- Expected outcome:

KaTo will list out the current tasks in the task list and inform the user if it is empty.

### Feature 10
Viewing the task list: `clear`

- Example of usage: 
`clear`

- Expected outcome:

KaTo will clear all the current tasks in the task list and inform the user if it is empty.

### Feature 11

Saying bye to KaTo: `bye`

- Example of usage: 
`bye`

- Expected outcome:

KaTo will print out the bye message to the user.

 ### Feature 12
Exiting KTM : `exit`

- Example of usage: 
`exit`

- Expected outcome:

The user will exit the KTM application.
 

