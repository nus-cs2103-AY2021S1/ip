# User Guide

Spanish Duke is a version of Duke, a task manager, where your inputs are in English but Duke responds to you in Spanish.

## Features 

The Spanish Duke chat bot is equipped with the following features.
1. [Add tasks](#feature-1-add-tasks)
2. [Delete tasks](#feature-2-delete-tasks)
3. [View all tasks](#feature-3-view-all-tasks)
4. [Mark completed tasks](#feature-4-mark-completed-tasks)
5. [Search tasks](#feature-5-search-tasks)
6. [Repeat tasks](#feature-6-repeat-tasks)

## Usage

### Feature 1: Add tasks
#### `todo` - Adds a Todo task to Duke.

Format: `todo {description}`

Example: __todo return spanish book__ 

Expected outcome: 
                  
                  Entendido. He agregado esta tarea:
                  [T] [X]return spanish book
                  Ahora tienes 1 tareas en la lista.
                  
#### `deadline` - Adds a Deadline task to Duke.

Format: `deadline {description} /by {time}`

Example: __deadline return spanish book /by 20/09/2020 1600__ 

Expected outcome: 
                  
                  Entendido. He agregado esta tarea:
                  [D] [X]return spanish book (by:Sep 20 2020 16:00PM)
                  Ahora tienes 1 tareas en la lista.

#### `event` - Adds a Event task to Duke.

Format: `event {description} /at {time}`

Example: __event return spanish book /at 20/09/2020 1600__ 

Expected outcome: 
                  
                  Entendido. He agregado esta tarea:
                  [E] [X]return spanish book (at:Sep 20 2020 16:00PM)
                  Ahora tienes 1 tareas en la lista.

### Feature 2: Delete tasks
#### `delete` - Deletes a task from Duke.

Format: `delete {position}`

Example: __delete 1__ 

Expected outcome: 
                  
                  Celebre. He eliminado esta tarea:
                  [T] [X]return spanish book
                  

### Feature 3: View all tasks
#### `list` - Views all tasks in Duke.

Format: `list`

Example: __list__ 

Expected outcome: 
                  
                  Estas son las tareas de su lista:
                  1. [T] [X]return spanish book
                  2. [D] [X]return spanish book (by:Sep 20 2020 16:00PM)
                  3. [E] [X]return spanish book (at:Sep 20 2020 16:00PM)
                  
                  
### Feature 4: Mark completed tasks
#### `done` - Marks a completed task in Duke.

Format: `done {position}`

Example: __done 2__ 

Expected outcome: 
                  
                  Agradable! He marcado esta tarea como hecha:
                  [D] [O]return spanish book (by:Sep 20 2020 16:00PM)


### Feature 5: Search tasks
#### `find` - Searches for tasks in Duke.

Format: `find {keyword}`

Example: __find spanish__ 

Expected outcome: 
                  
                  Aqui estan las tareas coincidentes en su lista:
                  1. [T] [X]return spanish book
                  2. [D] [O]return spanish book (by:Sep 20 2020 16:00PM)
                  3. [E] [X]return spanish book (at:Sep 20 2020 16:00PM)


### Feature 6: Repeat tasks
#### `repeat` - Repeats a tasks daily/weekly/monthly.

Format: `repeat {position} {frequency}`

Example: __repeat 2 daily__ 

Expected outcome: 
                  
                  Entendido. He agregado esta tarea:
                  [D] [O][daily]return spanish book (by:Sep 20 2020 16:00PM)
                  Ahora tienes 3 tareas en la lista.
