# Jackie Chan Bot User Guide

# Usable commands: 
* [list](#list)
* [todo](#todo)
* [deadline](#deadline)
* [event](#event)
* [done](#done)
* [delete](#delete)
* [find](#find)
* [bye / exit](#bye--exit)

# Command Description
``` 
All command syntax listed with <> are mandatory 
All command syntax listed with [] are optional
```

## list
Displays the list of tasks currently in the application

### Syntax: ```list [Modifiers]```

> This command can be used without special modifiers

### Special Modifiers

| Keyword | Description |
| ---------------- | --------------------- |
| ```-priority``` | Displays the list sorted by Priority  (Highest to Lowest Priority) |
| ```-priority:rev```  | Displays the list sorted in the reverse order (Lowest to Highest Priority) |

---

## todo 
Adds a new task of todo type. 

### Syntax: ``` todo <desc> [Modifiers] ```

> If the desc is empty, an error message will be displayed. 

### Special Modifiers

| Type | Example | Description |
| -------- | -------- | -------- |
| Priority Level| ![_value_] | Sets a priority for this task 

``` todo read books !1 ``` will create a new todo task of "read book" with Critical priority.  

>You can refer to [Priority Value](#Priority-Value) for valid priority types

---

## deadline 
Adds a new task of deadline type. 

### Syntax: ``` deadline <desc> [Modifiers] /by <date time>  ```

> If the desc or date/time is empty, an error message will be displayed. 

### Special Modifiers

| Type | Example | Description |
| -------- | -------- | -------- |
| Priority Level| ![_value_] | Sets a priority for this task 

> ``` deadline read books !1 /by 12/09/2020 1200``` 
will create a new deadline task of "read book" with Critical priority and set the deadline by 12 September 2020, 12PM.  

>You can refer to [Priority Value](#Priority-Value) for valid priority types

>You can refer to [Date Time format](#Allowed-Date-Time-Format) for allowed date-time format. 

---

## event 
Adds a new task of event type. 

### Syntax: ``` event <desc> [Modifiers] /at <date time>  ```

> If the desc or date/time is empty, an error message will be displayed. 

### Special Modifiers

| Type | Example | Description |
| -------- | -------- | -------- |
| Priority Level| ![_value_] | Sets a priority for this task 

> ``` event read books !1 /at 12/09/2020 1200``` 
will create a new event task of "read book" with Critical priority and set the event at 12 September 2020, 12PM.  

>You can refer to [Priority Value](#Priority-Value) for valid priority types

>You can refer to [Date Time format](#Allowed-Date-Time-Format) for allowed date-time format. 

---
## done
Marks the selected task as completed 

### Syntax ```done <index>```
> If the task is already completed, this command will not change its status. 

>If an invalid index number is given, an error message will be displayed. 

---

## delete
Deletes the selected task from the application 

### Syntax ```delete <index>```

>If an invalid index number is given, an error message will be displayed. 

---

## find
Finds the given keyword from all tasks, and display it 

### Syntax: ```find <keyword>```

> If no keyword is given, it will print the entire task list (which is equivalent to calling ```list```)

---
##  bye / exit 
Indicates to programme that the user is exiting. The application will save all of the task. 

### Syntax: ```bye``` or ```exit```

---
# Priority Value


| Priority Type | Priority Value |
| ----------- | ----------------- |
| CRITICAL   | 1     |
| HIGH   | 2     |
| MID   | 3     |
| LOW   | 4     |
| NONE   | 5     |

---
# Allowed Date Time Format 
This application allows 2 type of date (and time) formats. 
> Time is optional. If no time is specified, it will be default to 0000H. 

**Allowed Format:** 
* DD/MM/YYYY
* DD/MM/YYYY HHMM
* YYYY-MM-DD
* YYYY-MM-DD HHMM 
