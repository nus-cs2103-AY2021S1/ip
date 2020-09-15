# User Guide for Rude Duke
Rude Duke is a rude chat bot AI to keep track of your tasks.
Use at your own risk.

## Features 

### Viewing help
Shows a message on the list of commands

##### Usage:
Format: ```help```

### Saving the data
Saves the data to the text file

##### Usage:
Format: ```Bye```

### Viewing your current list
Display the current list of tasks

##### Usage:
Format: ```list```

### Completing a task
Mark a task as done
##### Usage:
Format: ```done <number>```
##### Example:
To mark task 1 as done: `done 1`

### Deleting a task
Delete a task from your current list of tasks
##### Usage:
Format: ```delete <number>```
##### Example:
To delete task 1: `delete 1`

### Finding a task
Find a task with a specific keyword from your current list of tasks.
Rude Duke will return you a list of tasks containing that keyword.
##### Usage:
Format: ```find <keyword>```
##### Example:
To find all the tasks that contain the keyword "chores": `find chores`

### Snoozing a task
Snooze a task by a certain number of days. Rude Duke will snooze it by one day by default.
##### Usage:
Format: ```snooze <number> <number of days(optional)>```
##### Example:
To snooze task 1 by 2 days:
`snooze 1 2`

### Adding a new to do task
To add a new to do task to your current list
##### Usage:
Format: ```todo <task message>```
##### Example:
To add a new chore to do:
`todo chores`

### Adding a new event task
To add a new event task to your current list with a timeframe
##### Usage:
Format: ```event <task message> /<preposition> <yyyy-MM-dd HH:mm>```
##### Example:
To add a new chore event:
`event do chores /by 2020-08-08 12:00`

### Adding a new deadline task
To add a new event task to your current list by a certain timeframe
##### Usage:
Format: ```deadline <task message> /<preposition> <yyyy-MM-dd HH:mm>```
##### Example:
To add a new chore deadline:
`deadline do chores /by 2020-08-08 12:00`


