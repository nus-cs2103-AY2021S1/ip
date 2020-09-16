
# User Guide

This is duckmoon99's Duke's user guide. The Duke is a CLI task manager.

## Features 

### Feature 1 - Create, read, update, and delete tasks
There are 3 types of tasks

- To-do tasks
- Deadline tasks
- Event tasks

So users can choose the appropriate task type. Each task can be marked done.

### Feature 2 - Find tasks
Find all the tasks quickly by searching keywords.

### Feature 3 - Friendly syntax
Users can quickly execute commands by simply typing the first few letters of the command, so long as there is no ambiguity. For example, if `list` is the command, typing `l` will suffice because no other commands starts with `l`.

For the demonstration below, the full command will be used for the sake of clarity.

## Usage

### `todo <description>` - Add to-do task

Adds a to-do task with the given `description`.

Example usage: 

`todo 2103 ip`

Expected outcome:

```
Noted, I have added the following task(s):
[T][✗] 2103 ip
```

### `deadline <description> /by <date>` - Add deadline task

Adds a deadline task with the given `description`, and deadline by `date`. Date formatted as `yyyy-mm-dd` will be recognized, and will be displayed in a more friendly manner.

Example usage 1:

`deadline 2103 ip /by next Wednesday`

Expected outcome 1:
```
Noted, I have added the following task(s):
[D][✗] 2103 ip (by: next Wednesday)
```

Example usage 2:
`deadline 2103 ip /by 2020-09-20`

Expected outcome 2:
```
Noted, I have added the following task(s):
[D][✗] 2103 ip (by: Sep 20 2020)
```
### `event <description> /at <date>` - Add event task

Adds an event task with the given `description`, and period at `date`. Date formatted as `yyyy-mm-dd` will be recognized, and will be displayed in a more friendly manner.

Example usage 1:

`event 2103 ip /at next week`

Expected outcome 1:
```
Noted, I have added the following task(s):
[E][✗] 2103 ip (at: next week)
```

Example usage 2:
`event 2103 ip /at 2020-09-20`

Expected outcome 2:
```
Noted, I have added the following task(s):
[E][✗] 2103 ip (at: Sep 20 2020)
```

### `list` - List all tasks

Lists out all tasks.

Example usage:
`list`

Expected outcome:
```
You currently have 3 task(s)
1. [T][✗] 1035 reading
2. [D][✓] 2103 ip (by: Sep 20 2020)
3. [E][✗] mock exam (at: Sep 23 2020)
```
### `done <n>` - Mark task as done

Marks task indexed at `n` to be done.

Example usage:
```
list
done 3
list
```
Expected outcome:
```
You currently have 3 task(s)
1. [T][✗] 1035 reading
2. [D][✓] 2103 ip (by: Sep 20 2020)
3. [E][✗] mock exam (at: Sep 23 2020)

Noted, I have marked the following task(s) as done:
[E][✓] mock exam (at: Sep 23 2020)

You currently have 3 task(s)
1. [T][✗] 1035 reading
2. [D][✓] 2103 ip (by: Sep 20 2020)
3. [E][✓] mock exam (at: Sep 23 2020)
```

### `delete <n>` - Delete task

Deletes task indexed at `n`.

Example usage:

`delete 3`

Expected outcome:
```
Noted, I have deleted the following task(s):
[E][✓] mock exam (at: Sep 23 2020)
You currently have 2 task(s)
1. [T][✗] 1035 reading
2. [D][✓] 2103 ip (by: Sep 20 2020)
```

### `find <keyword>` - Find tasks

Finds all tasks with `keyword` in the their string form.

Example usage:
`find 2103`

Expected outcome:
```
Noted, I have found the following 3 task(s):
1. [T][✗] 2103 quiz
2. [D][✓] 2103 ip (by: Sep 20 2020)
3. [E][✗] 2103 tp (at: Saturday)
```

### `bye` - Exit Duke

Exits Duke.

Example usage:
`bye`

Expected outcome: -
