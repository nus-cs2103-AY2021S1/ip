# User Guide
- Table of Contents
    - [Description](#description)
    - [Setting up](#setting-up)
    - [Features](#features)

## Description
This is a chat bot named after the Java mascot _Duke_. _Duke_ can help you to manage your tasks.
![Ui](./docs/Ui.png)

## Setting up 
1. Download the jar file from the releases. 
2. Put the file in its own folder.
3. Double click on it to run.
4. Have fun :) 

## Features

- #### Greet the chat bot
 `hello`
 
 - #### Add a task with description 
    - Todo: 
        `todo {description} [/every {interval}]`
        - e.g. `todo read a book /every week`
    - Event:
        `event {description} /at yyyy-MM-dd [HHmm] [/every {interval}]`
        - e.g. `event dinner party /at 2020-09-16 1730`
    - Deadline:
        `deadline {description} /by yyyy-MM-dd [HHmm] [/every {interval}]`
        - e.g. `deadline homework /by 2020-09-18 2359`
        
 - #### Complete a task
 `done {task number}`
        
 - #### Delete a task
 `delete {task number}`
 
 - #### List all your tasks
 `list`
 
 - #### Find tasks that contain a keyword
 `find {keyword}`
 
 - #### Save and exit the application
 `bye`
