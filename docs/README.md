# ClubbyDuke User Guide
A CS2103T individual project to create a 
Personal Assistant Chatbot that helps a person 
to keep track of various things.
![Image of banner](banner.png)

## ClubbyDuke Features
These are the currently implemented features in ClubbyDuke
1. **Add** various types of task to the bot.
2. **List** out all the currently added tasks.
3. **Tag** task as completed.
4. **Search** for tasks in the list of currently added tasks.
5. **Delete** task that is no longer relevant.
6. **Detect** any duplicated task input.
7. **Store & Load** task locally.
8. **Alert** the user upon receiving invalid input

---
### 1. Add task
Users will be allowed to add task into the bot.
- Todo
- Event
- Deadline
#####a. Todo
Command: `todo <task description>`

This will add a todo task into the bot

Example of usage: 
```
todo CS2103T Assignment 1
```
Expected outcome:
```
Got it. I've added this task:
[T][✘] Complete CS2103T Assignment 1
Now you have 1 tasks in the list.
```

#####b. Event
Command: `event <task description> /at <When the event will happen>`

This will add an event task into the bot

Example of usage: 
```
event Justin Boy Concert /at Wednesday
```
Expected outcome:
```
Got it. I've added this task:
[E][✘] Justin Boy Concert (at: Wednesday)
Now you have 2 tasks in the list.
```

#####c. Deadline
Command: `deadline <task description> /by <the deadline of the task>`

Deadline format: `yyyy-MM-dd HH:mm` or `string input`


This will add a deadline task into the bot

Example of usage: 
```
deadline MA1101R Assignment 2 /by 2020-09-20 23:59
```
Expected outcome:
```
Got it. I've added this task:
[D][✘] MA1101R Assignment 2 (by: Sep 20 2020 11:59 pm)
Now you have 3 tasks in the list.
```
---
### 2. Listing out all tasks
Command: `list`

This will show the user all the current tasks in the list

Example of usage: 
```
list
```
Expected outcome:
```
Here are the tasks in your list:
1. [T][✘] Complete CS2103T Assignment 1
2. [E][✘] Justin Boy Concert (at: Wednesday)
3. [D][✘] MA1101R Assignment 2 (by: Sep 20 2020 11:59 pm)
```
---
### 3. Tag (mark as complete)
Command: `done <task index>`

This will mark the task completion status with either a `X` or `✓`.

Example of usage: 
```
done 1
```
Expected outcome:
```
Nice! I've marked this task as done:
[T][✓] Complete CS2103T Assignment 1
```
---
### 4. Search
Command: `find <keywords>`

This will search within the list of tasks for their description and return a list of task 
with matching description as keywords.

Example of usage: 
```
find Assignment
```
Expected outcome:
```
Here are the tasks in your list:
1. [T][✓] Complete CS2103T Assignment 1
2. [D][✘] MA1101R Assignment 2 (by: Sep 20 2020 11:59 pm)
```
---
### 5. Delete task
Command: `delete <task index>`

This will remove the selected task from the list of tasks.

Example of usage: 
```
delete 1
```
Expected outcome:
```
Noted. I've removed this task:
[T][✓] Complete CS2103T Assignment 1
Now you have 2 tasks in the list.
```
---
### 6. Detect duplicate

Detect duplicate input is a built in function, when the user enter a task with
same description as a previously added task, a warning message will be shown and the
task will not be added into the list.

Example of usage: 
```
todo MA1101R Assignment 2
```
Expected outcome:
```
☹ OOPS!!! This is a duplicated task!
```
---
### 7. Store & Load task

ClubbyDuke will retrieve a set of saved data locally in a txt file upon launch.

Likewise, it will also save all the entries in the same txt file upon exit.

Data will be stored at `"data/duke.txt"` of the root folder.

---
### 9. Alert

ClubbyDuke will alert the user with error message upon invalid entries.

Example of usage: 
```
add todo see doctor
```
Expected outcome:
```
☹ OOPS!!! wait..... I don't understand your order my sir.
```
---
### 10. Exit
Command: `bye`

This will close ClubbyDuke and save all your entries.

Example of usage: 
```
bye
```
Expected outcome:
```
Bye. Hope to see you again soon!!!
```
---
## Thank you for reading!
## Have a good time with ClubbyDuke!!! :)