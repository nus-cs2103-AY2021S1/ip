# User Guide

![Grandma application](https://i.imgur.com/Ge20QYq.png)

## Features 

### Customised for young punks.

The grandma task mananger application caters to the younger generation using crude language and gen Y humour.

## Usage

### Help - view the list of available commands.

    help

### Adding "to do" task - adds a to do task into grandma.

    todo {description} #{tag} /by DDMMYY HHMM

>**entries are case sensitive**

### Adding "deadline" task - adds a task with deadline into grandma.

    deadline {description} #{tag} /by DDMMYY HHMM

>**entries are case sensitive**

### Adding "event" task - adds an event task into grandma.

    event {description} #{tag} /at DDMMYY HHMM

>**entries are case sensitive**

### Delete task - delete a task from grandma.

    delete {task number}

>*task number can be viewed using list*

### Complete task - mark a task as complete in grandma.

    done {task number}

>*task number can be viewed using list*

### List tasks - view the list of tasks in grandma.

    list

### Find tasks - view the list of tasks in grandma that contains the keyword.

    find {keyword}

>*the find function is case insensitive*

### Exit grandma - Save information and leave the application.

    bye

## Examples

todo something #fun

    Got it, here yur task bij [T] [✘] something #fun

deadline homework #sad /by 311220 2021

    Got it, here yur task bij [D] [✘] homework #sad (by: Dec 31 2020, 8:21 pm)

done 1

    okcan mark completed: [T] [✓] something #fun Now you have 2 tasks in the list.

delete 2

    okcan deleted: [D] [✘] homework #sad (by: Dec 31 2020, 8:21 pm) Now you have 1 tasks in the list.

list

    Here yur tasks faggit: 1. [T] [✓] something #fun Now you have 2 tasks in the list

### Contact raythx98@gmail.com or @raythx98 regarding any enquiries