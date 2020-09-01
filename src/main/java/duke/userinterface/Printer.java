package duke.userinterface;

import duke.exception.EmptyArgumentException;
import duke.task.Task;

/** Handles printing of messages to the User. */
public class Printer {

  /**
   * Prints the message that is shown to user after a task is deleted.
   *
   * @param deletedTask duke.task.Task that was deleted
   * @param lengthAfterDeletion Number of tasks left after deletion
   * @return Output of operation
   */
  static String printSuccessDeleteTask(Task deletedTask, int lengthAfterDeletion) {
    String output = String.format("Noted. I've removed this task:\n" + deletedTask + "\n");
    output +=
        String.format(
            "Now you have %s %s in the list.",
            lengthAfterDeletion, lengthAfterDeletion == 1 ? "task" : "tasks");
    return output;
  }

  /**
   * Prints the message that is shown to user after a task is successfully set as down.
   *
   * @param successDone duke.task.Task that was set as done
   * @return Success message
   */
  static String printSuccessSetTaskAsDone(Task successDone) {
    String output = String.format("Nice! I've marked this task as done: \n" + successDone);
    return output;
  }

  /**
   * Prints the message that is shown to user after a task is successfully added.
   * @param toDo duke.task.Task that was added
   * @param size Number of tasks left after the task was added
   * @return Success message
   */
  static String printSuccessAddTask(Task toDo, int size) {
    String output = "Got it. I've added this task: " + toDo.toString() + "\n";
    String numberTasks =
        String.format("Now you have %s %s in the list.", size, size == 1 ? "task" : "tasks");
    output += numberTasks;
    return output;
  }

  /** Prints an error message when the duke.task.Task to be operated on couldn't be found. */
  public static void printTaskNotFound() {
    System.out.println(new EmptyArgumentException("Sorry! Duke could not find the task"));
  }

  /** Prints an error message when the input given by user is invalid. */
  static void printEmptyArgument() {
    System.out.println(new EmptyArgumentException("No task name given"));
  }

  /**
   * Prints a custom String.
   *
   * @param s String to be printed
   */
  static void printCustomStatement(String s) {
    System.out.println(s);
  }
}
