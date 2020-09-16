# Duke - User Guide

Duke is a slick chatbot that helps you **manage your tasks and events efficiently**.

## Commands

Duke makes use of a **Command Line Interface (CLI)**, which makes it suitable for users who are able to type fast.

The following are possible commands that you can make with Duke.

### Add Tasks

Duke recognises three types of tasks, which are named '**Todo**', '**Deadline**' and '**Event**' respectively.

#### Adding a 'Todo'

A '**Todo**' is a simple task that is not associated with any deadline or time of occurence.

Format | Example
------------ | -------------
`todo <task description>` | `todo Learn Java Programming`

#### Adding a 'Deadline'

A '**Deadline**' is a task that has a deadline associated with it.

Format | Example
------------ | -------------
`deadline <task description> /by DD-MM-YYYY HHMM` | `deadline CS2103 Individual Project /by 18-09-2020 1200`

#### Adding an 'Event'

An '**Event**' is a task that has a time of occurrence associated with it.

Format | Example
------------ | -------------
`event <task description> /at DD-MM-YYYY HHMM` | `event LeetCode and Chill Session /at 21-09-2020 2100`

### Display List of Tasks

To display the list of tasks that is stored in Duke, simply enter the command '`list`'.

### Mark Tasks as Done

The following allows you to mark a particular task in the task list as Done.

Format | Example
------------ | -------------
`done <task number>` | `done 1`

### Delete Tasks

The following allows you to delete a particular task in the task list.

Format | Example
------------ | -------------
`delete <task number>` | `delete 2`

### Find Tasks by Keyword

The following allows you to find a particular task whose description matches your search query.

Format | Example
------------ | -------------
`find <search query>` | `find LeetCode and Chill`

### Sort Tasks in Alphabetical Order

To sort the list of tasks in alphabetical order, simply enter the command '`sort`'.

### Exit Chatbot

To exit the Duke chatbot, simply enter the command '`bye`'.