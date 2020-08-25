import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;

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
      ui.showErrorMsg(e);
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
            ui.showListEmptyMsg();
          } else {
            ui.showListMsg();
            for (Task t : list.getList()) {
              System.out.println(++idx + ". " + t.toString());
            }
          }
          break;
        case "done":
          if (list.isEmpty()) {
            ui.showListEmptyMsg();
          } else {
            ui.showListMsg();
            for (Task t : list.getList()) {
              System.out.println(++idx + ". " + t.toString());
            }
            do {
              ui.showListDoneAskMsg();
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
                    ui.showErrorMsg(e);
                  }
                }
                if (!doneTasks.isEmpty()) {
                  ui.showListDoneMsg();
                  for (Task t : doneTasks) {
                    System.out.println(t.toString());
                  }
                }
                validInput = true;
              } catch (DukeException e) {
                ui.showErrorMsg(e);
              }
            } while (!validInput);
          }

          try {
            storeFile.saveFile(list);
          } catch (DukeException e) {
            ui.showErrorMsg(e);
          }

          break;
        case "todo":
          do {
            ui.showTaskAddAskMsg();
            try {
              userInput = ui.readInput();
              if (userInput.equals("")) {
                validInput = false;
                throw new DukeException("Yo! Task details are missing.");
              }
              Task toDo = new Todo(userInput);
              list.addTask(toDo);
              ui.showTaskAddedMsg(toDo);
              validInput = true;
            } catch (DukeException e) {
              ui.showErrorMsg(e);
            }
          } while (!validInput);

          try {
            storeFile.saveFile(list);
          } catch (DukeException e) {
            ui.showErrorMsg(e);
          }

          break;
        case "deadline":
          do {
            ui.showTaskAddAskMsg();
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
              ui.showTaskAddedMsg(deadLine);
              validInput = true;

            } catch (DukeException e) {
              ui.showErrorMsg(e);
            }
          } while (!validInput);

          try {
            storeFile.saveFile(list);
          } catch (DukeException e) {
            ui.showErrorMsg(e);
          }

          break;
        case "event":
          do {
            ui.showTaskAddAskMsg();
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
              ui.showTaskAddedMsg(event);
              validInput = true;

            } catch (DukeException e) {
              ui.showErrorMsg(e);
            }
          } while (!validInput);

          try {
            storeFile.saveFile(list);
          } catch (DukeException e) {
            ui.showErrorMsg(e);
          }

          break;
        case "delete":
          if (list.isEmpty()) {
            ui.showListEmptyMsg();
          } else {
            ui.showListMsg();
            for (Task t : list.getList()) {
              System.out.println(++idx + ". " + t.toString());
            }
            do {
              ui.showTaskDeleteAskMsg();
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
                    ui.showErrorMsg(e);
                  }
                }
                if (!deletedTasks.isEmpty()) {
                  ui.showTaskDeleteMsg();
                  for (Task t : deletedTasks) {
                    System.out.println(t.toString());
                  }
                }
                validInput = true;
              } catch (DukeException e) {
                ui.showErrorMsg(e);
              }
            } while (!validInput);
          }

          try {
            storeFile.saveFile(list);
          } catch (DukeException e) {
            ui.showErrorMsg(e);
          }

          break;
        case "help":
          ui.showHelp();
          break;
        default:
          try {
            throw new DukeException("Unrecognised Command :(, type 'help' for available commands.");
          } catch (DukeException e) {
            ui.showErrorMsg(e);
          }
      }
    }

    ui.showByeMsg();
  }


  public static void main(String[] args) {
    new Duke(filePath).run();
  }
}
