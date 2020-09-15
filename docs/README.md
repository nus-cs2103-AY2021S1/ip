# Eu Zin's Duke User Guide

Eu Zin's Duke is a simple no frills(because Eu Zin is not great at jxml yet) to-do list that can help you keep track of your bust day! 

## Contents:
  - Quickstart
  - Features
      - List View
      - Add todo 
      - Add event
      - Add deadline
      - Mark as Complete
      - Delete task
      - Set task duration
      - Search for a task
  - FAQ 
  - Command Summary
---

# Quickstart
Due to the easy-to-use nature of Eu Zin's Duke, this will be the quickest Quickstart Guide you've ever seen.

### 1. Open Eu Zin's Duke
You will arrive at the main dialog page. It will look something like this:

![Image](start.png)

You will notice 3 important features:
1. The User Input Box - For you to type in the commands for Eu Zin's Duke
2. The Send Button - To send your commands to Eu Zin's Duke when you are done entering them
3. The Scroll Bar - To Scroll up and down the dialog box

### 2. Create your first Task
Your task can come in 3 forms: todo, event and deadline.

##### todo
These tasks are the most basic of tasks, they **only have a description**.
To create a todo input:
`todo <description>` and hit *Send*

##### event
These tasks have a **description** and a **timeframe**.
To create an event input:
`event <description> /at <timeframe>` and hit *Send*
The description and timefram cane be of any string format.

##### deadline
These tasks have a **description** and a **date-time**
To create a deadline input:
`deadline <description> /by <date-time>` and hit *Send*
The description can be of any String format.
> **_NOTE:_** **The date-time must be strictly in either of these formats:** DD/MM/YYYY TTTT   *OR*   YYYY/MM/DD TTTT

### Complete your task
Once you've completed your task, input:
`done <index>`
index being the number of the task you just completed.

You should see your task appear completed in this format
![Image](done.png)

That's it for the Quickstart. You can refer to the Features sections below for everything Eu Zin's Duke can do.

---

# Features
Eu Zin's Duke contains easy to use features that are activated by typing commands in the User Input Box and hitting the *Send* Button.

### List View: `list`
Shows every task in your list, completed or not.

Format: `list`

### Add todo: `todo`
Adds a todo to the end of your list.
A todo consists of only a **description**.

Format: `todo <description>`

### Add event: `event`
Adds an event to the end of your list.
An event consists of a **description** and a **timeframe**, which can be input in any string format.

Format: `event <description> /at <timeframe>`

### Add deadline: `deadline`
Adds a deadline to the end of your list.
A deadline consists of a **description** and a **date-time**. The description can be in any string format.
> **_NOTE:_** **The date-time must be strictly in either of these formats:** DD/MM/YYYY TTTT   *OR*   YYYY/MM/DD TTTT

Format: `deadline <description> /by <date-time>`

### Mark as Complete: `done`
When you have finished a task, you can mark it as complete. It will be displayed on your list with a tick.
The task to mark as complete is **specified by its index number in integer form**

Format: `done <index>`

### Delete task: `delete`
Removed a task completely from your list.
The task to delete is **specified by its index number in integer form**

Format: `delete <index>`

### Set task duration: `duration`
Set a duration for a particular task in your list.
The task to set duration for is **specified by its index number in integer form**
The duration can be enter in any string format.

Format: `duration <index> <duration>`

### Search for a task: `find`
Find a particular task in your list using a keyword.
The keyword can be in any string format.

Format: `find <keyword>`

---
# FAQ
##### Q. Will my list be saved after I close Eu Zin's Duke?
##### A: Yes it will be saved and loaded when you reopen Eu Zin's Duke.

---
# Command Summary

| Action | Format |
| ------ | ------ |
| Add todo | Format: `todo <description>` |
| Add event | Format: `event <description> /at <timeframe>`|
| Add deadline | Format: `deadline <description> /by <date-time>` |
| Mark as Complete | Format: `done <index>` |
| Delete Task | Format: `delete <index>` |
| Search for Task | Format: `find <keyword>` |