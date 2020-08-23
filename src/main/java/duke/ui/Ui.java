package duke.ui;

import duke.command.CommandType;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import java.io.IOException;
import java.time.LocalDate;

public class Ui {
  public static String getListString(TaskList tasks) {
    StringBuilder output = new StringBuilder();
    int index = 1;
    for (Task task : tasks) {
      output.append(String.format("%d. %s%n", index, task));
      index++;
    }
    return output.toString();
  }

  public static String getListDate(TaskList tasks, LocalDate date) {
    StringBuilder output = new StringBuilder();
    int index = 1;
    for (Task task : tasks) {
      if (task.getDate().isPresent() && task.getDate().get().isEqual(date)) {
        output.append(String.format("%d. %s%n", index, task));
        index++;
      }
    }
    return output.toString();
  }

  public static void showList(TaskList tasks) {
    System.out.println(Ui.getListString(tasks));
  }

  public static void showListDate(TaskList tasks, LocalDate date) {
    System.out.println(Ui.getListDate(tasks, date));
  }

  public static void showGreet() {
    System.out.println("Hello! I'm Duke\nWhat can I do for you?");
  }

  public static void showBye() {
    System.out.println("Bye! Hope to never see you again!");
  }

  public static void showDone(Task task) {
    System.out.printf("Good job. This task is marked as done:\n %s%n", task);
  }

  public static void showDelete(Task task) {
    System.out.printf("Noted. I've remove this task:\n %s%n", task);
  }

  public static void showAddTask(Task task) {
    System.out.printf("Got it. I've added this task:%n %s%n", task);
  }

  public static void showError(String message) {
    System.out.println(message);
  }

  public static void showUnexpectedError(String message) {
    System.out.println("Unexpected Error:\n" + message);
  }

  public static DukeException commandInvalidException() {
    return new DukeException("This command is invalid.");
  }

  public static DukeException taskDescriptionEmptyException(CommandType type) {
    return new DukeException(String.format("The description of a %s cannot be empty.", type));
  }

  public static DukeException taskDateEmptyException(CommandType type) {
    return new DukeException(
        String.format("Error! The description of a %s is missing a date.", type));
  }

  public static DukeException taskIndexFormatException(CommandType type) {
    return new DukeException(
        String.format("Input format is wrong. Please make sure it is `%s <task-index>`", type));
  }

  public static DukeException taskDateFormatException() {
    return new DukeException(
        "Date format is wrong. It should be yyyy-mm-dd and within possible date ranges");
  }

  public static DukeException taskIndexOutOfBoundsException(int index, TaskList tasks) {
    StringBuilder message =
        new StringBuilder(String.format("Task at index %d doesn't exist%n", index));
    if (tasks.size() == 0) {
      message.append("\nThere are no tasks currently.");
    } else {
      message.append(
          String.format(
              "There are %d tasks currently. Please a number between 1 and %d inclusive.",
              tasks.size(), tasks.size()));
    }

    return new DukeException(message.toString());
  }

  public static DukeException ioException(IOException e) {
    return new DukeException("Something is wrong with the input:\n" + e.getMessage());
  }
}
