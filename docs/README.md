# User Guide

## Features 

### Listing Tasks
Shows the full list of Tasks saved.

### `list` - Shows the full list of Tasks saved.  

Duke will reply with a list of Tasks. 

Example of usage: 

`list`

Expected outcome:

```
1. [T] [✓] Todo
2. [D] [✓] Deadline (by: Oct 20 2020)
3. [E] [✗] Event (at: Nov 5 2020)
```

### Finding Tasks
Duke can find Tasks that have names containing a specified keyword. 
The results are numbered according to their index in the full list, so the same indexes can be used for other commands. 

### `find {keyword}` - Finds all Tasks that contain {keyword} in the name.

Example of usage:
 
`find deadline`

Expected outcome:

```
2. [D] [✓] Deadline (by: Oct 20 2020)
```

### Marking Tasks as completed
Duke can mark Tasks as completed based on the index in the list.


