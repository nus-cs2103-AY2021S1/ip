# Mochi The Penguin User Guide 

## Task Tracker
Mochi the penguin is your personal task tracker. It can help you to add, delete, 
search, and update tasks on the go! 

## Features 

### Creating new tasks
#### 1. `todo DESCRIPTION` - Adds a Todo Task

Adds a Todo Task

Example of usage: 

```
todo Catch fishes
```

Expected outcome:

```
Got it! I've added this task:
[T][✘] Catch fishes
Now you have 2 tasks in the list!
```

#### 2. `event DESCRIPTION /at TIME` - Adds an event

Adds an Event Task.

Example of usage: 

```
event Catch fishes /at tomorrow
```

Expected outcome:

```
Got it! I've added this task:
  [E][✘] Catch fishes (at: tomorrow)
Now you have 1 task in the list!
```

#### 3. `deadline DESCRIPTION /by TIME` - Adds a deadline

Adds a Deadline Task.

Example of usage: 

```
deadline Catch fishes /by tomorrow
```

Expected outcome:

```
Got it! I've added this task:
  [D][✘] Catch fishes (by: tomorrow)
Now you have 1 task in the list!
```

### Action commands
#### 1. `bye` - Exits application

Exits Mochi The Penguin application.

Example of usage: 

```
bye
```

Expected outcome:

```
☀ Bye. Hope to see you again soon!
```

#### 2. `delete ID` - Deletes a task

Deletes a task.

Example of usage: 

```
delete 1
```

Expected outcome:

```
Noted! I've removed this task:
  [D][✘] Catch fishes (by: tomorrow)
Now you have 0 tasks in the list!
```

#### 3. `done ID` - Marks a task as done

Marks the task as done.

Example of usage: 

```
done 1
```

Expected outcome:

```
Nice! I've marked this task as done:
  [D][✓] Catch fishes (by: tomorrow)
```

#### 4. `find QUERY` - Searches tasks

Finds task according to query.

Example of usage: 

```
find fishes
```

Expected outcome:

```
Here are the matching tasks in your list:
1.[E][✘] Catch fishes (at: tomorrow)
```

#### 5. `list` - Lists tasks

Displays all the tasks you have added.

Example of usage: 

```
list
```

Expected outcome:

```
Here are the tasks in your list:
  1.[E][✘] Catch fishes (at: tomorrow)
```
#### 6. `undo` - Undoes the previous action

Undoes the previous command called.

Example of usage: 

```
delete 1
undo
list
```

Expected outcome:

```
Noted! I've removed this task:
  [D][✘] Catch fishes (by: tomorrow)
Now you have 0 tasks in the list!

Undo succeeded:
  1.[D][✘] Catch fishes (by: tomorrow)

Here are the tasks in your list:
  1.[D][✘] Catch fishes (by: tomorrow)
```
