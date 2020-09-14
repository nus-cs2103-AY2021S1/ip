# User Guide for duke bot

## Features 

### Say bye to duke `bye` 

Exit the program.

Input instruction: 

`bye`

### Find a task `find` 

Find the expected task with given index in the task list.

Input instruction: 

`find` + `index`

Example of usage: find 1 

### Delete a task `delete` 

Delete the expected task with given index in the task list.

Input instruction: 

`delete` + `index`

Example of usage: delete 1 

### Create a todo task `todo` 

Create a task that you are going to do and store in the tasklist.

Input instruction: 

`todo` + `task content`

Example of usage: todo play games

### Create a deadline task `deadline` 

Create a task with a deadline

Input instruction: 

`deadline` + `task content` + `/by` + `YYYY-MM-DD`

Example of usage: deadline finish homework /by 2020-05-12

### Create a event task `event` 

Create an event

Input instruction: 

`event` + `task content` + `/at` + `YYYY-MM-DD`

Example of usage: event celebrate holiday /at 2020-05-12

### List your tasks `list` 

Show the list of tasks

Input instruction: 

`list`

Example of usage: list

### Find out statistics `stats` 

Show the number of task completed

Input instruction: 

`stats`

Example of usage: stats

### Says hi to duke `hi` 

Greet the bot and it will greet you back

Input instruction: 

`hi`

Example of usage: hi

### Mark your task done `done` 

Mark your task done 

Input instruction: 

`done` + `index`

Example of usage: done 1

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