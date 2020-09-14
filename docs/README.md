# Duke User Guide
A simple personal tasks manager created as part of NUS CS2103T Individual Project.

This application follows the theme of winter, with the usage of some images that I had personally taken when in Japan.

![Duke](./Ui.png)

## Features 
The Duke chat bot is equipped with the following features.
1. [Addition of tasks](#feature-1-addition-of-tasks)
2. [Deletion of tasks](#feature-2-deletion-of-tasks)
3. [View list of all tasks](#feature-3-view-list-of-all-tasks)
4. [Mark completion of tasks](#feature-4-mark-completion-of-tasks)
5. [Undo last action](#feature-5-undo-last-action)
6. [Search for tasks](#feature-6-search-for-tasks)
7. [Storage and retrieval of tasks from local drive](#feature-7-storage-and-retrieval-of-tasks-from-local-drive)

## Usage
### Feature 1: Addition of tasks
#### `todo` - Adds a Todo task

Adds a Todo task to the list of tasks.

Command:

    todo TODO_NAME

Example of usage: 

    todo grocery run

Expected outcome:

    Got it. I've added this task:
    [T][not done] grocery run
    You have 1 tasks in the list.
    
### `event` - Adds an Event

Adds an Event with details on its date to the list of tasks.

Command:

    event EVENT_NAME /at DD-MM-YYYY HHMM

Example of usage: 

    event NUS Open House /at 12-03-2020 1200

Expected outcome:

    Got it. I've added this task:
    [E][not done] NUS Open House (at: 12 March 2020 12:00 PM)
    You have 2 tasks in the list.

### `deadline` - Adds a Deadline

Adds a task with details on its deadline to the list of tasks.

Command:

    deadline DEADLINE_NAME /by DD-MM-YYYY HHMM

Example of usage: 

    deadline open book assignment /by 15-9-2020 2359

Expected outcome:

    Got it. I've added this task:
    [D][not done] open book assignment (by: 15 September 2020 11:59 PM)
    You have 3 tasks in the list.
___
### Feature 2: Deletion of tasks
#### `delete` - Deletes a task

Deletes a task from the list of tasks.

Command:

    delete TASK_NUMBER

Example of usage: 

    delete 1

Expected outcome:

    Noted. I've removed this task:
    [T][not done] grocery run
    You have 2 tasks in the list.
___
### Feature 3: View list of all tasks
#### `list` - Lists out all tasks

Shows the list of all tasks with its details and its status of completion.

Command:

    list

Expected outcome:

    1. [E][not done] NUS Open House (at: 12 March 2020 12:00 PM)
    2. [D][not done] open book assignment (by: 15 September 2020 11:59 PM)
___
### Feature 4: Mark completion of tasks
#### `done` - Marks a task as completed

Marks a task from the list of tasks as completed.

Command:

    done TASK_NUMBER

Example of usage: 

    done 1

Expected outcome:

    Nice! I've marked this task as done:
    [E][done] NUS Open House (at: 12 March 2020 12:00 PM)
___
### Feature 5: Undo last action
#### `undo` - Undo last action

Reverts user actions. Will not work if the last action is `list` or `find`.

Command:

    undo

Expected outcome:

    Got it. I've undone your previous action!
    Here is your updated list of tasks.
    1. [E][not done] NUS Open House (at: 12 March 2020 12:00 PM)
    2. [D][not done] open book assignment (by: 15 September 2020 11:59 PM)
___
### Feature 6: Search for tasks
#### `find` - Search for tasks with keyword

Searches for and provides the list of tasks that consist of the keyword

Command:

    find KEYWORD

Example of usage: 

    find open

Expected outcome:

    1. [E][not done] NUS Open House (at: 12 March 2020 12:00 PM)
    2. [D][not done] open book assignment (by: 15 September 2020 11:59 PM)
___
### Feature 7: Storage and retrieval of tasks from local drive
The Duke chat bot is capable of retrieving the list of tasks from past sessions. The user is able to make modifications
to the list in the active session which would be reflected in the storage on the local drive.

##Thank you for reading this user guide!
If you have any queries, please do not hesitate to contact me via GitHub @ameliatjy!