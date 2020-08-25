import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;

public class Duke {
  public static String duke = "Duke> ";
  public static String home = System.getProperty("user.home");
  public static String filePath = home + "/Desktop/duke.txt";
  // do a check whether file exists
  private Storage storeFile;
  private TaskList list;
  private Ui ui;

  public Duke(String filePath) {
    this.ui = new Ui();
    this.storeFile = new Storage(filePath);
    try {
      list = new TaskList(storeFile.loadFile());
    } catch (DukeException e) {
      ui.showError(e);
      list = new TaskList();
    }
    ui.startupMsg();
  }

  public void run() {
    String userInput;
    String[] s;
    boolean validInput = true;

//MAIN EXEC
    while (true) {
      userInput = ui.readInput();
      if (userInput.equals("bye")) {
        break;
      }
      int idx = 0;
      switch (userInput) {
        case "list":
          if (list.isEmpty()) {
            System.out.println(duke + "Your Task List is empty.");
          } else {
            System.out.println(duke + "Here's your Task List:");
            for (Task t : list.getList()) {
              System.out.println(++idx + ". " + t.toString());
            }
          }
          break;
        case "done":
          if (list.isEmpty()) {
            System.out.println(duke + "Your Task List is empty. There's nothing to be done.");
          } else {
            System.out.println(duke + "Here's your Task List:");
            for (Task t : list.getList()) {
              System.out.println(++idx + ". " + t.toString());
            }
            do {
              System.out.println("Choose the task(s) to be marked as 'Done'");
              try {
                userInput = ui.readInput();
                if (userInput.isBlank()) {
                  validInput = false;
                  throw new DukeException("Yo! Enter the task number(s) to be marked as 'Done'");
                }
                int[] tasksArray =
                        Arrays.stream(userInput.split(" ")).mapToInt(Integer::parseInt).toArray();
                ArrayList<Task> doneTasks = new ArrayList<>();
                for (int index : tasksArray) {
                  try {
                    if (index > list.size() || index <= 0) {
                      throw new DukeException("This task #" + index + " does not exist.");
                    }
                    Task t = list.getTask(index);
                    t.setDone();
                    doneTasks.add(t);
                  } catch (DukeException e) {
                    System.out.println(duke + e.getMessage());
                  }
                }
                if (!doneTasks.isEmpty()) {
                  System.out.println(duke + "Nice! I've marked the following as done:");
                  for (Task t : doneTasks) {
                    System.out.println(t.toString());
                  }
                }
                validInput = true;
              } catch (DukeException e) {
                System.out.println(duke + e.getMessage());
              }
            } while (!validInput);
          }

          try {
            storeFile.saveFile(list);
          } catch (DukeException e) {
            System.out.println(duke + e.getMessage());
          }

          break;
        case "todo":
          do {
            System.out.println(duke + "Enter task details:");
            try {
              userInput = ui.readInput();
              if (userInput.equals("")) {
                validInput = false;
                throw new DukeException("Yo! Task details are missing.");
              }
              Task toDo = new Todo(userInput);
              list.addTask(toDo);
              System.out.println(
                      duke + "I've added '" + toDo.getTaskName() + "' to your Task List");
              validInput = true;
            } catch (DukeException e) {
              System.out.println(duke + e.getMessage());
            }
          } while (!validInput);

          try {
            storeFile.saveFile(list);
          } catch (DukeException e) {
            System.out.println(duke + e.getMessage());
          }

          break;
        case "deadline":
          do {
            System.out.println(duke + "Enter task details:");
            try {
              userInput = ui.readInput();
              if (!userInput.contains("/by")) {
                validInput = false;
                throw new DukeException(
                        "Yo! Command Syntax Error. '<Task Details> /by <Deadline>'");
              }
              s = userInput.split(" /by ");
              if (s.length <= 1) {
                validInput = false;
                throw new DukeException("Yo! Task details/deadline are missing.");
              }
              Task deadLine = new Deadline(s[0], s[1]);
              list.addTask(deadLine);
              System.out.println(
                      duke + "I've added '" + deadLine.getTaskName() + "' to your Task List");
              validInput = true;

            } catch (DukeException e) {
              System.out.println(duke + e.getMessage());
            }
          } while (!validInput);

          try {
            storeFile.saveFile(list);
          } catch (DukeException e) {
            System.out.println(duke + e.getMessage());
          }

          break;
        case "event":
          do {
            System.out.println(duke + "Enter Event details:");
            try {
              userInput = ui.readInput();
              if (!userInput.contains("/at")) {
                validInput = false;
                throw new DukeException(
                        "Yo! Command Syntax Error. '<Event Details> /at <Event Time>'");
              }
              s = userInput.split(" /at ");
              if (s.length <= 1) {
                validInput = false;
                throw new DukeException("Yo! Event details/time are missing.");
              }
              Task event = new Event(s[0], s[1]);
              list.addTask(event);
              System.out.println(
                      duke + "I've added '" + event.getTaskName() + "' to your Task List");
              validInput = true;

            } catch (DukeException e) {
              System.out.println(duke + e.getMessage());
            }
          } while (!validInput);

          try {
            storeFile.saveFile(list);
          } catch (DukeException e) {
            System.out.println(duke + e.getMessage());
          }

          break;
        case "delete":
          if (list.isEmpty()) {
            System.out.println(duke + "Your Task List is empty. There's nothing to be deleted.");
          } else {
            System.out.println(duke + "Here's your Task List:");
            for (Task t : list.getList()) {
              System.out.println(++idx + ". " + t.toString());
            }
            do {
              System.out.println("Choose the task(s) to be deleted.");
              try {
                userInput = ui.readInput();
                if (userInput.isBlank()) {
                  validInput = false;
                  throw new DukeException("Yo! Enter the task number(s) to be deleted.");
                }
                int[] tasksArray =
                        Arrays.stream(userInput.split(" "))
                                .sorted(Collections.reverseOrder())
                                .mapToInt(Integer::parseInt)
                                .toArray();
                ArrayList<Task> deletedTasks = new ArrayList<>();
                for (int index : tasksArray) {
                  try {
                    if (index > list.size() || index <= 0) {
                      throw new DukeException("This task #" + index + " does not exist.");
                    }
                    Task t = list.getTask(index);
                    list.removeTask(index);
                    deletedTasks.add(t);
                  } catch (DukeException e) {
                    System.out.println(duke + e.getMessage());
                  }
                }
                if (!deletedTasks.isEmpty()) {
                  System.out.println(duke + "I've deleted the task(s) you specified:");
                  for (Task t : deletedTasks) {
                    System.out.println(t.toString());
                  }
                }
                validInput = true;
              } catch (DukeException e) {
                System.out.println(duke + e.getMessage());
              }
            } while (!validInput);
          }

          try {
            storeFile.saveFile(list);
          } catch (DukeException e) {
            System.out.println(duke + e.getMessage());
          }

          break;
        case "help":
          ui.showHelp();
          break;
        default:
          try {
            throw new DukeException("Unrecognised Command :(, type 'help' for available commands.");
          } catch (DukeException e) {
            System.out.println(duke + e.getMessage());
          }
      }
    }

    System.out.println(duke + "See you soon!");
  }


  public static void main(String[] args) {
    new Duke(filePath).run();
  }
  //END MAIN EXEC

}
