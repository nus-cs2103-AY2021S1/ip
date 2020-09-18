package duke.userinterface;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Parser {
  TaskList taskList;
  Storage storage;

  /**
   * Constructor for duke.userinterface.Parser.
   *
   * @param taskList List of tasks that the duke.userinterface.Parser interacts with
   * @param storage duke.storage.Storage that the duke.userinterface.Parser interacts with
   */
  Parser(TaskList taskList, Storage storage) {
    this.storage = storage;
    this.taskList = taskList;
  }

  /** Update the storage after an operation is performed. */
  void updateStorage() {
    List<Task> tasks = taskList.getTaskList();
    List<String> taskStrings = tasks.stream().map(Task::toString).collect(Collectors.toList());
    storage.updateFile(taskStrings);
  }

  /**
   * Makes sense of a user's command.
   *
   * @param command Command given
   * @return Whether operation was successful
   */
  String handleCommand(String command) {
    if (command.startsWith("delete")) {
      return handleDelete(command);
    } else if (command.startsWith("done")) {
      return handleDone(command);
    } else if (command.startsWith("todo")) {
      return handleAddTodo(command);
    } else if (command.startsWith("deadline")) {
      return handleAddDeadline(command);
    } else if (command.startsWith("event")) {
      return handleAddEvent(command);
    } else if (command.startsWith("list")) {
      return handleList();
    } else if (command.startsWith("find")) {
      return handleSearchTask(command);
    } else if (command.startsWith("bye")) {
      return "";
    } else {
      Printer.printCustomStatement("Sorry, command not recognized!");
      return "";
    }
  }

  private String handleSearchTask(String command) {
    if (command.length() < 6) {
      Printer.printCustomStatement("Command must be in the form 'find <task name>'");
      return "Command must be in the form 'find <task name>'";
    }
    String name = command.substring(5); // remove everything before "find"
    String[] words = name.split(" ");
    List<Task> tasksThatMatched = taskList.findMatchingTasks(words);
    String output = "";
    if (tasksThatMatched.size() > 0) {
      Printer.printCustomStatement("Here's what you were looking for");
      output += "Here's what you were looking for";
      for (Task t : tasksThatMatched) {
        output += String.format("\n %s", t.toString());
      }
    } else {
      output = "No matching tasks found! Try again?";
    }
    return output;
  }

  private String handleDelete(String command) {
    int index;
    try {
      index = Integer.parseInt(command.substring(7)) - 1;
    } catch (IndexOutOfBoundsException e) {
      // no proper number given
      Printer.printTaskNotFound();
      return "No task name given";
    }
    Optional<Task> task = taskList.removeTask(index);
    if (task.isPresent()) {
      String output = Printer.printSuccessDeleteTask(task.get(), taskList.size());
      updateStorage();
      return output;
    } else {
      return "Task deletion failed! Try again?";
    }
  }

  private String handleDone(String command) {
    int index = Character.getNumericValue(command.charAt(5));
    Optional<Task> task = taskList.setAsDone(index);
    if (task.isPresent()) {
      String output = Printer.printSuccessSetTaskAsDone(task.get());
      updateStorage();
      return output;
    } else {
      return "Sorry! Task not found";
    }
  }

  private String handleAddTodo(String command) {
    int index = command.indexOf(" ");
    if (index == -1) {
      return "No task name given";
    }
    command = command.substring(index + 1);
    Task toDo = new Todo(command);
    boolean isSuccess = taskList.addTask(toDo);
    if (isSuccess) {
      String output = Printer.printSuccessAddTask(toDo, taskList.size());
      updateStorage();
      return output;
    } else {
      return "No task name given";
    }
  }

  private String handleAddDeadline(String command) {
    int index = command.indexOf(' ');

    // get content after the space
    if (index == -1 || index == command.length() - 1) {
      return "No task name given";
    }
    // get stuff after the space if there are still characters after the space
    String content = command.substring(index + 1);
    index = content.indexOf('/');

    // get content after the slash
    if (index == -1 || index == content.length() - 1) {
      // nothing after the slash, or slash is not found
      return "No deadline given!";
    }
    // for String formatting reasons, check if there is a space before the slash
    if (index == 0 || content.charAt(index - 1) != ' ') {
      return "Command must be in the format 'deadline <task name> /by <yyyy-mm-dd>' ";
    }
    String taskName = content.substring(0, index - 1);
    // get keyword, e.g. "by" or "at"
    String keyword = content.substring(index + 1, index + 3);
    boolean matches = keyword.equals("by");

    if (!matches) {
      Printer.printCustomStatement("duke.task.Task name does not start with proper arguments");
      return "Task name does not start with proper arguments";
    }

    try {
      // get the deadline, e.g. deadline do homework /by tmr returns "tmr"
      String deadline = content.substring(index + 4);
      LocalDate dateTime = LocalDate.parse(deadline);
      Task newTask = new Deadline(taskName, dateTime);
      boolean isSuccessful = taskList.addTask(newTask);
      if (isSuccessful) {
        String output = Printer.printSuccessAddTask(newTask, taskList.size());
        updateStorage();
        return output;
      } else {
        return "Sorry! Addition failed. Try again?";
      }
    } catch (IndexOutOfBoundsException e) {
      return "No task name given";
    } catch (DateTimeException e) {
      return "Time given must be in the format yyyy-mm-dd";
    }
  }

  private String handleAddEvent(String command) {
    int index = command.indexOf(' ');

    // get content after the space
    if (index == -1 || index == command.length() - 1) {
      return "No task name given";
    }
    // get stuff after the space if there are still characters after the space
    String content = command.substring(index + 1);
    index = content.indexOf('/');

    // get content after the slash
    if (index == -1 || index == content.length() - 1) {
      return "No event time given!";
    }

    // for String formatting reasons, check if there is a space before the slash
    if (index == 0 || content.charAt(index - 1) != ' ') {
      return "Command must be in the format 'event <task name> /at <yyyy-mm-dd>' ";
    }

    String taskName = content.substring(0, index - 1);
    // get keyword, e.g. "by" or "at"
    String keyword = content.substring(index + 1, index + 3);
    boolean matches = keyword.equals("at");

    if (!matches) {
      return "Task name does not start with proper arguments";
    }

    try {
      // get the time, e.g. deadline do homework /by tmr returns "tmr"
      String time = content.substring(index + 4);
      LocalDate dateTime = LocalDate.parse(time);
      Task newTask = new Event(taskName, dateTime);
      boolean isSuccessful = taskList.addTask(newTask);
      if (isSuccessful) {
        updateStorage();
        String output = Printer.printSuccessAddTask(newTask, taskList.size());
        return output;
      } else {
        return "Sorry! Adding task failed";
      }
    } catch (IndexOutOfBoundsException e) {
      String output = "No task name given";
      return output;
    } catch (DateTimeException e) {
      return "Time given must be in the format yyyy-mm-dd";
    }
  }

  private String handleList() {
    return taskList.iterate();
  }
}
