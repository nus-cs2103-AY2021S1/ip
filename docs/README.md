# User Guide
## About 
Duke the All-Knowing is a simple offline application which specialises in task management. It helps keep track of your task, offers find, delete and mark as done functions among other.

## Features 
- Duke greets the user upon launch
- Duke displays the logo after user clear the default sending line "Enter your problems here..." by click send
- Users can add a specific type of task : todo/event/deadline
- Users can find a task using part of its description entered
- Users can make a task as done
- Users can delete a task
- Users can list all tasks 
- Users can store all tasks in a local file in the hard disk "duke.txt"
- Users can load all saved tasks

### Feature Usage and Commands 
#### 1. `event <description> /at <yyyy-mm-dd> <hr:min>` - Add a specific type of task
The task specified is saved to the list and in data file at the same time.

Example (User input command):
```$xslt
event team meeting /at 2020-02-02 18:00
```

Expected Response :
```
Got it. I have added this task:
[E][✘] team meeting /at 2020-02-02 18:00 (at:
2020 FEBRUARY 2 SUNDAY 18:00hr)
```

Note that user input commands for event and deadline tasks are similar

#### 2. `done <seq number>` - Mark an existing task as done

Error message is shown if the seq number exceeds length of the current list

Example (User input command):
```$xslt
done 2
```

Expected Response :
```
Nice! I have marked this task as done:
[E][✓] team meeting /at 2020-02-02 18:00 (at:
2020 FEBRUARY 2 SUNDAY 18:00hr)
```

Error Response :
``````

### `Keyword` - Describe action

Describe action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

`outcome`
