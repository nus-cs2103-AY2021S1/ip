# User Guide for duke bot
1. [Features](#1-features)<br>
    1.1 [Say bye to duke: `bye`](#11-say-bye-to-duke-bye)<br>
    1.2 [Find a task: `find`](#12-find-a-task-find)<br>
    1.3 [Delete a task: `delete` ](#13-delete-a-task-delete)<br>
    1.4 [Create a todo task: `todo`](#14-create-a-todo-task-todo)<br>
    1.5 [Create a deadline task: `deadline`](#15-create-a-deadline-task-deadline)<br>
    1.6 [Create a event task: `event`](#16-create-a-event-task-event)<br>
    1.7 [List your tasks: `list`](#17-list-your-tasks-list)<br>
    1.8 [Find out statistics: `stats`](#18-find-out-statistics-stats)<br>
    1.9 [Says hi to duke: `hi`](#19-says-hi-to-duke-hi)<br>
    2.0 [Mark your task done: `done`](#20-mark-your-task-done-done)<br>
2. [Command Summary](#2-command-summary)
## 1. Features 

### 1.1 Say bye to duke `bye` 

Exit the program.

Input instruction: 

`bye`

### 1.2 Find a task `find` 

Find the expected task with given keyword.

Input instruction: 

`find` + `keyword`

Example of usage: find homework 

### 1.3 Delete a task `delete` 

Delete the expected task with given index in the task list.

Input instruction: 

`delete` + `index`

Example of usage: delete 1 

### 1.4 Create a todo task `todo` 

Create a task that you are going to do and store in the tasklist.

Input instruction: 

`todo` + `task content`

Example of usage: todo play games

### 1.5 Create a deadline task `deadline` 

Create a task with a deadline

Input instruction: 

`deadline` + `task content` + `/by` + `YYYY-MM-DD`

Example of usage: deadline finish homework /by 2020-05-12

### 1.6 Create a event task `event` 

Create an event

Input instruction: 

`event` + `task content` + `/at` + `YYYY-MM-DD`

Example of usage: event celebrate holiday /at 2020-05-12

### 1.7 List your tasks `list` 

Show the list of tasks

Input instruction: 

`list`

Example of usage: list

### 1.8 Find out statistics `stats` 

Show the number of task completed

Input instruction: 

`stats`

Example of usage: stats

### 1.9 Says hi to duke `hi` 

Greet the bot and it will greet you back

Input instruction: 

`hi`

Example of usage: hi

### 2.0 Mark your task done `done` 

Mark your task done 

Input instruction: 

`done` + `index`

Example of usage: done 1

## 2. Command summary

Action | Format, Examples
--------|------------------
**Find Task** | `find keyword` <br> e.g.,`find homework`
**Delete Task** | `delete index`<br> e.g.,`delete 1`
**Todo Task** | `todo description` <br> e.g., `doto play games`
**Deadline Task** | `deadline description /by YYYY-MM-DD` <br> e.g., `deadline finish homework /by 2020-05-12`
**Event Task** | `event description /at YYYY-MM-DD` <br> e.g., `event celebrate holiday /at 2020-05-12`
**List Task** | `list` <br>
**Stats** | `stats` <br>
**Done** | `done index ` <br> e.g., `done 1`
**Bye** | `bye`<br>

//@@author dcchan98-reused
//Reused from https://github.com/dcchan98/ip/blob/master/src/main/java/Storage.java with minor modification

public void writeToFile(Task myTask, int todoNum) {
 
         createToDo("ToDo/item" + todoNum + ".txt");
 
         try {
             ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("ToDo/item" + todoNum + ".txt"));
             out.writeObject(myTask);
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
public Task readFromFile(String fileDir) {
          Task myTask = null;
          try {
              ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileDir));
              myTask = (Task) in.readObject();
          } catch (IOException | ClassNotFoundException e) {
              e.printStackTrace();
          }
          return myTask;
  
      }
public void updateDirectory(TaskList myTaskList) {
  
          // deleting all files in directory
          File dir = new File("ToDo");
          File[] myItems = dir.listFiles();
          for (File child : myItems) {
              if (child.toString().substring(0, 9).equals("ToDo/item")) {
                  Path path = FileSystems.getDefault().getPath(child.toString());
                  try {
                      Files.delete(path);
                  } catch (NoSuchFileException x) {
                      System.err.format("%s: no such" + " file or directory%n", path);
                  } catch (IOException x) {
                      System.err.println(x);
                  }
              }
          }
  
          // repopulating directory with that in arraylist taks
          for (int i = 0; i < myTaskList.getTasks().size(); i++) {
              writeToFile(myTaskList.getTasks().get(i), i);
          }
      }
//@@author dcchan98-reused