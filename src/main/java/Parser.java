import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Parser {
  TaskList taskList;
  Storage storage;

  /**
   * Constructor for Parser.
   *
   * @param taskList List of tasks that the Parser interacts with
   * @param storage Storage that the Parser interacts with
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
  boolean handleCommand(String command) {
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
      handleList();
      return true;
    } else if (command.startsWith("find")) {
      return handleSearchTask(command);
    } else if (command.startsWith("bye")) {
      return false;
    } else {
      return false;
    }
  }

  private boolean handleSearchTask(String command) {
    if (command.length() < 6) {
      Printer.printCustomStatement("Command must be in the form 'find <task name>'");
      return false;
    }
    String name = command.substring(5); // remove everything before "find"
    String[] words = name.split(" ");
    List<Task> tasksThatMatched = taskList.findMatchingTasks(words);
    if (tasksThatMatched.size() > 0) {
      Printer.printCustomStatement("Here's what you were looking for");
      for (Task t : tasksThatMatched) {
        Printer.printCustomStatement(t.toString());
      }
    } else {
      Printer.printCustomStatement("No matching tasks found! Try again?");
    }
    return true;
  }

  private boolean handleDelete(String command) {
    int index;
    try {
      index = Integer.parseInt(command.substring(7));
    } catch (IndexOutOfBoundsException e) {
      // no proper number given
      Printer.printTaskNotFound();
      return false;
    }
    Optional<Task> task = taskList.removeTask(index);
    if (task.isPresent()) {
      Printer.printSuccessDeleteTask(task.get(), taskList.size());
      updateStorage();
      return true;
    } else {
      return false;
    }
  }

  private boolean handleDone(String command) {
    int index = Character.getNumericValue(command.charAt(5));
    Optional<Task> task = taskList.setAsDone(index);
    if (task.isPresent()) {
      Printer.printSuccessSetTaskAsDone(task.get());
      updateStorage();
      return true;
    } else {
      Printer.printTaskNotFound();
      return false;
    }
  }

  private boolean handleAddTodo(String command) {
    int index = command.indexOf(" ");
    if (index == -1) {
      Printer.printEmptyArgument();
      return false;
    }
    command = command.substring(index + 1);
    Task toDo = new Todo(command);
    boolean isSuccess = taskList.addTask(toDo);
    if (isSuccess) {
      Printer.printSuccessAddTask(toDo, taskList.size());
      updateStorage();
      return true;
    } else {
      Printer.printEmptyArgument();
      return false;
    }
  }

  private boolean handleAddDeadline(String command) {
    int index = command.indexOf(' ');

    // get content after the space
    if (index == -1 || index == command.length() - 1) {
      Printer.printEmptyArgument();
    }
    // get stuff after the space if there are still characters after the space
    String content = command.substring(index + 1);
    index = content.indexOf('/');

    // get content after the slash
    if (index == -1 || index == content.length() - 1) {
      // nothing after the slash, or slash is not found
      Printer.printCustomStatement("No deadline given!");
      return false;
    }
    // for String formatting reasons, check if there is a space before the slash
    if (index == 0 || content.charAt(index - 1) != ' ') {
      Printer.printCustomStatement(
          "Command must be in the format 'deadline <task name> /by <yyyy-mm-dd>' ");
      return false;
    }
    String taskName = content.substring(0, index - 1);
    // get keyword, e.g. "by" or "at"
    String keyword = content.substring(index + 1, index + 3);
    boolean matches = keyword.equals("by");

    if (!matches) {
      Printer.printCustomStatement("Task name does not start with proper arguments");
      return false;
    }

    try {
      // get the deadline, e.g. deadline do homework /by tmr returns "tmr"
      String deadline = content.substring(index + 4);
      LocalDate dateTime = LocalDate.parse(deadline);
      Task newTask = new Deadline(taskName, dateTime);
      boolean isSuccessful = taskList.addTask(newTask);
      if (isSuccessful) {
        Printer.printSuccessAddTask(newTask, taskList.size());
        updateStorage();
      }
      return isSuccessful;
    } catch (IndexOutOfBoundsException e) {
      Printer.printEmptyArgument();
      return false;
    } catch (DateTimeException e) {
      Printer.printCustomStatement("Time given must be in the format yyyy-mm-dd");
      return false;
    }
  }

  private boolean handleAddEvent(String command) {
    int index = command.indexOf(' ');

    // get content after the space
    if (index == -1 || index == command.length() - 1) {
      Printer.printCustomStatement("No task name given");
      return false;
    }
    // get stuff after the space if there are still characters after the space
    String content = command.substring(index + 1);
    index = content.indexOf('/');

    // get content after the slash
    if (index == -1 || index == content.length() - 1) {
      // nothing after the slash, or slash is not found
      Printer.printCustomStatement("No deadline given!");
      return false;
    }

    // for String formatting reasons, check if there is a space before the slash
    if (index == 0 || content.charAt(index - 1) != ' ') {
      Printer.printCustomStatement(
          "Command must be in the format 'event <task name> /at <yyyy-mm-dd>' ");
      return false;
    }

    String taskName = content.substring(0, index - 1);
    // get keyword, e.g. "by" or "at"
    String keyword = content.substring(index + 1, index + 3);
    boolean matches = keyword.equals("at");

    if (!matches) {
      System.out.println(
          new EmptyArgumentException("Task name does not start with proper arguments"));
      return false;
    }

    try {
      // get the time, e.g. deadline do homework /by tmr returns "tmr"
      String time = content.substring(index + 4);
      LocalDate dateTime = LocalDate.parse(time);
      Task newTask = new Event(taskName, dateTime);
      boolean isSuccessful = taskList.addTask(newTask);
      if (isSuccessful) {
        updateStorage();
        Printer.printSuccessAddTask(newTask, taskList.size());
      }
      return isSuccessful;
    } catch (IndexOutOfBoundsException e) {
      System.out.println(new EmptyArgumentException("No task name given"));
      return false;
    } catch (DateTimeException e) {
      System.out.println(
          new InvalidArgumentException("Time given must be in the format yyyy-mm-dd"));
      return false;
    }
  }

  private void handleList() {
    taskList.iterate();
  }
}
