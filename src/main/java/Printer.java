/** Handles printing of messages to the User. */
public class Printer {

  /**
   * Prints the message that is shown to user after a task is deleted.
   *
   * @param deletedTask Task that was deleted
   * @param lengthAfterDeletion Number of tasks left after deletion
   */
  static void printSuccessDeleteTask(Task deletedTask, int lengthAfterDeletion) {
    System.out.println("Noted. I've removed this task:\n" + deletedTask);
    String numberTasks =
        String.format(
            "Now you have %s %s in the list.",
            lengthAfterDeletion, lengthAfterDeletion == 1 ? "task" : "tasks");
    System.out.println(numberTasks);
  }

  /**
   * Prints the message that is shown to user after a task is successfully set as down.
   *
   * @param successDone Task that was set as done
   */
  static void printSuccessSetTaskAsDone(Task successDone) {
    System.out.println("Nice! I've marked this task as done: \n" + successDone);
  }

  /**
   * Prints the message that is shown to user after a task is successfully added.
   *
   * @param toDo Task that was added
   * @param size Number of tasks left after the task was added
   */
  static void printSuccessAddTask(Task toDo, int size) {
    System.out.println("Got it. I've added this task:");
    System.out.println(toDo);
    String numberTasks =
        String.format("Now you have %s %s in the list.", size, size == 1 ? "task" : "tasks");
    System.out.println(numberTasks);
  }

  /** Prints an error message when the Task to be operated on couldn't be found. */
  static void printTaskNotFound() {
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
