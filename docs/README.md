# User Guide
MuskBot was designed to make your work easier and more organized 

## Features 
 list \
 deadline,
 event,
 todo \
 delete\
 help\
 find\
 stat\
 bye\
 done

#list
List all current tasks, can pass date to filter tasks
####Usage
`list [date]` \
Example
`list 2020-12-12` \
returns all tasks that have deadline on that day

#deadline
Add a deadline task
####Usage
`deadline <description> /by <date> [time]` \
Example
`deadline do something /by 2020-12-12 1500` \
adds the deadline at 12th, Dec 2020 at 3pm


#event
Add an event task
####Usage
`event <description> /at <date> [time]` \
Example
`event do something /at 2020-12-12 1500` \
adds the event at 12th, Dec 2020 at 3pm

#todo
Add a todo task
####Usage
`todo <description>` \
Example
`event do something` \
adds the todo task

#delete
Delete a task
####Usage
`delete <index number>` \
Example
`delete 1` \
deletes the task with index number 1

#find
Find a task with keyword
####Usage
`find <keyword>`\
Example
`find book`\
returns tasks that have description containing `book`
 
#stat
Show statistics i.e usage frequency
####Usage
`stat` \
Example
`stat` \
shows the bar chart indicating usage frequency since the 1st time using the app

#done
Mark a task as done
####Usage
`done <index number>` \
Example
`done 1` \
marks the task with index number 1 as done

#bye
Close the app
####Usage
`bye` \
Example
`bye` \
closed the app

#help
Shows available commands
####Usage
`help` \
Example
`help` \
shows available commands and descriptions e.g list, delete, find,...
