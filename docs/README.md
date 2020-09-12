# User Guide
- [Introduction](#Introduction)

- [Quick Start](#Quick-Start)

- [Features](#Features)

## Introduction

Duke is an application using Command Line Tool to manage your tasks.

## Quick Start

1. Ensure you have Java `11` or above installed in your computer.

2. Download the latest `Duke.jar` from [here](!https://github.com/Lingy12/ip/releases)

3. Copy the file to the folder you want to use as the home folder for Duke

4. Double-click the file to start the app. The GUI similar to below will appear in a few seconds.

<p align="center">
    <img src="/img/duke_main.png">
</p>

## Features 


<div markdown="block" class="alert alert-info">

**Notes about the command format:**<br>
   * `( )` represents the parameter for the command
   
   * The input format of time is **yyyy-MM-dd HH:mm**
   
</div> 

---

### `todo` - Add a TODO item into the list

Format: `todo (your task description)`

Example of usage:

`todo read book`

Expect Output:

The task is added to the list with not done status, and gives out the total tasks in the list.

<p align="center">
    <img src="/img/duke_todo.png">
</p>

***

### `event` - Add an event to the task list

Format: `event (description) /at (time)` 

Example of usage:

`event Midterm Test /at 2020-10-02 16:00`

Expected Output:

The event has been recorded into the system and give out the total number of tasks in the list.

<p align="center">
    <img src="/img/duke_event.png">
</p>

***


### `deadline` - Add an deadline to the task list

Format: `deadline (description) /by (time)`

Example of usage: 

`deadline assignment 1 /by 2020-09-27 15:00`

Expect output:

The deadline is added and give out the total task in the list.

<p align="center">
    <img src="/img/duke_deadline.png">
</p>

### `list` - List all the item in the list

Format: `list`

Example:

<p align="center">
    <img src="/img/duke_list.png">
</p>

***

### `done` - Mark certain task as done

Format: `done (index)`

index: The index of the task in the list (can be found out by calling list)

Example of usage:

`done 2`

Expected output:

The task with the target index is marked as done. 

<p align="center">
    <img src="/img/duke_done.png">
</p>

***

### `delete` - Delete certain task in the list

Format: `delete (index)`

index: The index of the task in the list (can be found out by calling list)

Example of usage:

`delete 2`

Expected output:

The task with index 2 is deleted from the task list.

<p align="center">
    <img src="/img/duke_delete.png">
</p>

***

### `clear` - Clear all the task in the list

Format: `Clear`

Example of usage:

<p align="center">
    <img src="/img/duke_clear.png">
</p>

***

### `reschedule` - Reschedule the task to future date

Format: `reschedule \to (time)`

Example of usage: 

`reschedule 2 /to 2020-12-20 19:00`

<p align="center">
    <img src="/img/duke_reschedule.png">
</p>


