# User Guide

## Features 

### List all Task entries
```
list
```

Lists all available task entries.

Example of usage: 

`list`

Expected outcome:
```
Here are the tasks in your list:
1.[T][✓] read book
2.[D][✗] return book (by: 6 JUNE 2020, 800am)
```

### Delete Task entry
```
delete <TASK INDEX>
```
Deletes the task entry specified by its serial number.

Example of usage: 

`delete 4`

Expected outcome:
```
Noted. I've removed this task:
	[T][✓] join sports club
Now you have 7 tasks in the list.
```

### Mark Task as Done
```
done <TASK INDEX>
```
Marks the task entry specified by its serial number as done.

Example of usage: 

`delete 4`

Expected outcome:
```
Noted. I've removed this task:
	[T][✓] join sports club
Now you have 7 tasks in the list.
```

### Find Task
```
find <WORD IN TASK DESCRIPTION>
```
Finds all tasks that have the input keyword(s) in it's **description**.

Example of usage: 

`delete 4`

Expected outcome:
```
Noted. I've removed this task:
	[T][✓] join sports club
Now you have 7 tasks in the list.
```

### Exit
```
bye
```
Exits from **Duke**.

Example of usage: 

`delete 4`

Expected outcome:
```
Noted. I've removed this task:
	[T][✓] join sports club
Now you have 7 tasks in the list.
```

### Add Task

---

#### Deadline
```
deadline <TASK> /by <DATE IN D/MM/YYYY> <24H TIME FORMAT>
```
Adds a *deadline*. Includes the date and time the *deadline* is due by.

Example of usage: 

`deadline Do CS2103T User Guide /by 14/09/2020 1200`

Expected outcome:
```
Got it. I've added this task:
	[D][✗] Do CS2103T User Guide (by: 14 SEPTEMBER 2020, 0pm)
Now you have 9 tasks in the list.
```

#### Event
```
event <TASK> /by <DATE IN D/MM/YYYY> <24H TIME FORMAT>
```
Adds an *event*. Includes the date and time the *event* is due by.

Example of usage: 

`event Halloween Party /at 18/09/2020 1800`

Expected outcome:
```
Got it. I've added this task:
	[E][✗] Halloween Party (at: 18 SEPTEMBER 2020, 600pm)
Now you have 8 tasks in the list.
```

#### ToDo
```
todo <TASK>
```
Adds a *todo*.

Example of usage: 

`todo Hang Laundry`

Expected outcome:
```
Got it. I've added this task:
	[T][✗] Hang Laundry
Now you have 10 tasks in the list.
```

### Add Recurring Task

---

#### Recurring Deadline
```
recurring deadline <TASK> /by <DATE IN D/MM/YYYY> <24H TIME FORMAT>
```
Adds a *recurring deadline*. Includes the date and time the *recurring deadline* is due by.

Example of usage: 

`recurring deadline Watch CS2103T lecture /by 14/09/2020 1400 /weekly`

Expected outcome:
```
Got it. I've added this task:
	[R] [D][✗] Watch CS2103T lecture (by: 14 SEPTEMBER 2020, 200pm)
Now you have 12 tasks in the list.
```

#### Recurring Event
```
recurring event <TASK> /by <DATE IN D/MM/YYYY> <24H TIME FORMAT>
```
Adds a *recurring event*. Includes the date and time the *recurring event* is due by.

Example of usage: 

`recurring event Stand-up Meeting /at 18/09/2020 1800 /daily`

Expected outcome:
```
Got it. I've added this task:
	[R] [E][✗] Stand-up Meeting (at: 18 SEPTEMBER 2020, 600pm)
Now you have 13 tasks in the list.
```

#### ToDo
```
recurring todo <TASK>
```
Adds a *recurring todo*.

Example of usage: 

`recurring todo Hang Laundry`

Expected outcome:
```
Got it. I've added this task:
	[R] [T][✗] Hang Laundry
Now you have 14 tasks in the list.
```

