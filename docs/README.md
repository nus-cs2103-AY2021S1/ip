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
```I am afraid that it is not possible to delete an unknown task.```

Note that the format of delete <seq number> operation is the same as done <seq number>.

#### 3. `list` - Display all tasks in sequence

Example (User input command):
```$xslt
list
```

Expected Response :
```
Here are the tasks in your list:
1.[E][✓] team meeting /at 2020-02-02 18:00 (at:
2020 FEBRUARY 2 SUNDAY 18:00hr)
2.[T][✓] borrow book
```

If the list is empty, no tasks will be displayed following the sentence "Here are the tasks in your list:", although the sentence will still be displayed.

#### 4. `find <description>` - Search all tasks containing the description specified

Example (User input command):
```$xslt
find borrow
```

Expected Response :
```
Here are the matching tasks in your list:
1.[E][✓] borrow book from lib /at 2020-02-02 18:00 (at:
2020 FEBRUARY 2 SUNDAY 18:00hr)
2.[T][✓] borrow mask
```

If the list is empty, no tasks will be displayed following the sentence "Here are matching the tasks in your list:", although the sentence will still be displayed.

#### 5. `reminder` - Display the nearest upcoming deadline(s)


Example (User input command):
```$xslt
reminder 
```

Expected Response :
```
Here is the nearest deadline(s) in your list:
1.[D][✘] borrow book from lib /at 2020-02-02 18:00 (at:
2020 FEBRUARY 2 SUNDAY 18:00hr)
```

if there are no deadline tasks in the list, the response is:
```
Nice! No upcoming deadline :).
```

#### 6. `bye` - Display a goodbye message to and tell you to close the window

Example (User input command):
```$xslt
bye 
```

Expected Response :
```
Bye. Hope to see you again soon! You can now close this window to quit me as a program.
```

#### 7. Exception Handling

For all other commands that duke does not recognise, the default exception which is caused by illegal arguments will be thrown and the error message will be displayed.

Expected Error Response

```
OOPS!!! I'm sorry, but I don't know what that means :-(
```

### Future Expansion Plan

To further develop this software, some other functions and current features can be improved.

They include:

- Improve GUI to make it more appealing
- Add check duplicate function
- Add archive function

among other.

Hope you will have a good time using duke. 
