# User Guide

## Features 

### 1. Add todo
   -> Create todo and add it to Duke.

   Format:

   `todo [description]` 

   Example of usage: 

   `todo homework`

   Expected outcome:

   `Duke heard:
   Got it. I've added this task: 
   [T][✘] homework
   Now you have 3 tasks in the list.`

### Feature 2 
List all the tasks in Duke.

## Usage

### `list` 

List all the tasks in the Duke.

Example of usage: 

`list`

Expected outcome:

`Here are the tasks in your list: 
 1. [T][✘] [!!] dont do homework 
 2. [T][✘] homework 
 3. [T][✘] description `

### Feature 3 
Delete the task from Duke.

## Usage

### `delete [index]` 

Delete task according to its index.

Example of usage: 

`delete 2`

Expected outcome:

`Noted. I've removed this task: 
 [T][✘] [!!] dont do homework
 Now you have 1 tasks in the list.`

### Feature 4 
Close the Duke application.

## Usage

### `bye` 

Close the Duke application.

Example of usage: 

`bye`

Expected outcome:

`Bye. Hope to see you again soon!`