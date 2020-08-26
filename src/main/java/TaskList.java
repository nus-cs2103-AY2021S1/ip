import java.util.ArrayList;

/**
 * TaskList class Used to store an ArrayList of Tasks Handles the various type of commands for
 * modifying the list
 */
public class TaskList {
  private final ArrayList<Task> tasks;

  /**
   * Constructor to wrap an ArrayList<Task>
   *
   * @param tasks ArrayList of Tasks
   */
  public TaskList(ArrayList<Task> tasks) {
    this.tasks = tasks;
  }

  /**
   * Handles the addition of an event
   *
   * @param inputs Userinput split into tokens
   */
  public void handleEvent(String[] inputs) {
    String[] tokens = inputs[1].split(" /at ");
    tasks.add(new Event(tokens[0], tokens[1]));
    System.out.println("Got it. I've added this task:");
    System.out.println(tasks.get(tasks.size() - 1));
    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
  }

  /**
   * Handles the addition of a deadline
   *
   * @param inputs Userinput split into tokens
   */
  public void handleDeadline(String[] inputs) {
    String[] tokens = inputs[1].split(" /by ");
    tasks.add(new Deadline(tokens[0], tokens[1]));
    System.out.println("Got it. I've added this task:");
    System.out.println(tasks.get(tasks.size() - 1));
    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
  }

  /**
   * Handles the addition of a todo
   *
   * @param inputs Userinput split into tokens
   */
  public void handleToDo(String[] inputs) throws DukeException {
    if (inputs.length < 2) {
      throw new DukeException("The description of a todo cannot be empty.");
    }
    tasks.add(new ToDo(inputs[1]));
    System.out.println("Got it. I've added this task:");
    System.out.println(tasks.get(tasks.size() - 1));
    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
  }

  /**
   * Marks a given task as Done
   *
   * @param inputs Userinput split into tokens
   */
  public void handleDone(String[] inputs) throws DukeException {
    int index = Integer.parseInt(inputs[1]) - 1;
    if (index >= tasks.size() || index < 0) {
      throw new DukeException("This task does not exist");
    }
    tasks.get(index).markDone();
    System.out.println("Nice! I've marked this task as done: ");
    System.out.println(tasks.get(index));
  }

  /**
   * Deletes a specified task
   *
   * @param inputs Userinput split into tokens
   */
  public void handleDelete(String[] inputs) throws DukeException {
    int index = Integer.parseInt(inputs[1]) - 1;
    if (index >= tasks.size() || index < 0) {
      throw new DukeException("This task does not exist");
    }
    System.out.println("Noted. I've removed this task: ");
    System.out.println(tasks.get(index));
    tasks.remove(index);
    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
  }

  /** Prints out the TaskList */
  public void list() {
    System.out.println("Task list");
    int i = 1;
    for (Task task : tasks) {
      System.out.println(i++ + ". " + task);
    }
  }

  /** Returns the ArrayList of tasks */
  public ArrayList<Task> getList() {
    return this.tasks;
  }
}
