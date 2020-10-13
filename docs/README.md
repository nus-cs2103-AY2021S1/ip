# User Guide
Duke is a chatbot designed for your daily planning user. Given below are the instructions on how to use it!

##Setting up
After downloading the jar file, just double click on the file to open it. 
If you see an error from opening it directly, 
go to your terminal and open it with the following command line

        java -jar {file path of the jar file}

## Features 

### **Feature 1:** Adding Tasks
Schedule deadlines, events and todos for you day to day activities

### **Feature 2:** Viewing Schedule
View your schedule, check what is due by a certain date, what is happening on a certain date

### **Feature 3:** Get Reminders
Define a custom range of dates and view all events/deadlines within that range

## Usage

---

## ***Adding tasks***
### `deadline` - Create a new deadline task


Example of usage: 

    deadline (task description) /by (date)

e.g. `deadline sleep /by 2020-12-09`

Expected outcome:

`Aight new task for you:
[D][X] sleep (by: Dec 9 2020)
Now you got 1 task(s) waiting man`

---

### `event` - Schedule an event


Example of usage: 

`event (task description) /at (date)`

e.g. `event sleep /at 2020-12-09`

Expected outcome:

`Aight new task for you:
[E][X] sleep (at: Dec 9 2020)
Now you got 1 task(s) waiting man`

---

### `todo` - List all events happening on a certain date


Example of usage: 

`todo (task description)`

e.g. `todo sleep`

Expected outcome:

`Aight new task for you:
 [T][X] sleep 
 Now you got 1 task(s) waiting man`
 
---
 
 

## ***Manage your tasks***


### `delete` - Delete a task


Example of usage: 

`delete (task id)`

e.g. `delete 1`

Expected outcome:

`Gotchu, I am removing 
[T][X] sleep
Now you got 1 task(s) waiting man`

---

### `done` - Mark a task as done


Example of usage: 

`done (task id)`

e.g. `done 1`

Expected outcome:

`Gratz, you finished this dawg:
[T][✓] sleep`

---

## ***View your tasks***

### `by` - Check what deadlines you have *by* a certain date


Example of usage: 

`by (date)`

e.g. `by 2020-12-09`

Expected outcome:

`By this day, you have:
*deadlines due before the date*
[A total of 1 deadline(s)]`

---

### `find` - Search your tasks containing a certain keyword


Example of usage: 

`find (keyword)`

e.g. `find sleep`

Expected outcome:

`1. [T][X] sleep`

---

### `list` - List all tasks


Example of usage: 

`list`

Expected outcome:

`Here's your schedule lil dude 1. [T][X] sleep`

---

### `on` - List all events happening on a certain date


Example of usage: 

`on (date)`

e.g. `on 2020-12-09`

Expected outcome:

`On this day, you have: [E][X] sleep (at: Dec 9 2020)`

---

### `reminder` - List all events happening on a certain date


Example of usage: 

`reminder (range)`

e.g. `reminder 100`

Expected outcome:

`In 100 days, you have: 1.[E][X] sleep (at: Dec 9 2020) [A total of 1 reminder(s)]`

---

## ***Other user commands***

### `bye` - Exits the program


Example of usage: 

`bye`

Expected outcome:

`Duke closes`








