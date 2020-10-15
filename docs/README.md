# User Guide

<img src="https://i.imgur.com/Ge20QYq.png" alt="drawing" width="300"/>

## Features 

### Customised for young punks.

The grandma task manager application caters to the younger generation using crude language and gen Y humour.

## Usage

The curly braces, such as {USER_INPUT}, means that user input is required.

Square brackets, such as [OPTIONAL_FIELD], means that the specified input is not required

### Help - view the list of available commands.

    help

### Adding "to do" task - adds a to do task into grandma.

    todo {DESCRIPTION} #{TAG} [/by {Date in DDMMYY format} {time in HHmm format}]

>*entries are case sensitive*
>
>*note that date and time comes together, either there exist both or none at all*

### Adding "deadline" task - adds a task with deadline into grandma.

    deadline {DESCRIPTION} #{TAG} [/by {Date in DDMMYY format} {time in HHmm format}]

>*entries are case sensitive*
>
>*note that date and time comes together, either there exist both or none at all*

### Adding "event" task - adds an event task into grandma.

    event {description} #{tag} [/at {Date in DDMMYY format} {time in HHmm format}]

>*entries are case sensitive*
>
>*note that date and time comes together, either there exist both or none at all*

### Delete task - delete a task from grandma.

    delete {TASK_NUMBER}

>*task number can be viewed using list*

### Complete task - mark a task as complete in grandma.

    done {TASK_NUMBER}

>*task number can be viewed using list*

### List tasks - view the list of tasks in grandma.

    list

### Find tasks - view the list of tasks in grandma that contains the keyword.

    find {KEYWORD}

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