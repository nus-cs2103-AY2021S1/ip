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
   * @param inputs User input split into tokens
   */
  public String handleEvent(String[] inputs) {
    String[] tokens = inputs[1].split(" /at ");
    tasks.add(new Event(tokens[0], tokens[1]));
    return getResponse();
  }

  /**
   * Handles the addition of a deadline
   *
   * @param inputs User input split into tokens
   */
  public String handleDeadline(String[] inputs) {
    String[] tokens = inputs[1].split(" /by ");
    tasks.add(new Deadline(tokens[0], tokens[1]));
    return getResponse();
  }

  private String getResponse() {
    System.out.println("Got it. I've added this task:");
    System.out.println(tasks.get(tasks.size() - 1));
    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    return "Got it. I've added this task:\n" + tasks.get(tasks.size() - 1) + "\nNow you have "
            + tasks.size() + " tasks in the list.";
  }

  /**
   * Handles the addition of a todo
   *
   * @param inputs User input split into tokens
   */
  public String handleToDo(String[] inputs) throws DukeException {
    if (inputs.length < 2) {
      throw new DukeException("The description of a todo cannot be empty.");
    }
    tasks.add(new ToDo(inputs[1]));
    return getResponse();
  }

  /**
   * Marks a given task as Done
   *
   * @param inputs User input split into tokens
   */
  public String handleDone(String[] inputs) throws DukeException {
    int index = Integer.parseInt(inputs[1]) - 1;
    if (index >= tasks.size() || index < 0) {
      throw new DukeException("This task does not exist");
    }
    tasks.get(index).markDone();
    System.out.println("Nice! I've marked this task as done: ");
    System.out.println(tasks.get(index));
    return "Nice! I've marked this task as done: \n" + tasks.get(index);
  }

  /**
   * Deletes a specified task at indicated index
   *
   * @param inputs User input split into tokens
   */
  public String handleDelete(String[] inputs) throws DukeException {
    int index = Integer.parseInt(inputs[1]) - 1;
    if (index >= tasks.size() || index < 0) {
      throw new DukeException("This task does not exist");
    }
    System.out.println("Noted. I've removed this task: ");
    System.out.println(tasks.get(index));
    String oldIndex = tasks.get(index).toString();
    tasks.remove(index);
    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    return "Noted. I've removed this task: \n" + oldIndex + "\nNow you have "
            + tasks.size() + " tasks in the list.";
  }

  public String handleUpdate(String[] inputs) {
    inputs = inputs[1].split(" ",2);
    int index = Integer.parseInt(inputs[0]) - 1;
    Task current = tasks.get(index);
    if (current.getType().equals("E")) {
      try {
        ((Event) current).changeStartTime(inputs[1]);
        return "I have updated the task as follows: \n" + current.toString();
      } catch (DukeException e) {
        return e.getMessage();
      }
    }
    else if (current.getType().equals("D")) {
      try {
        ((Deadline) current).changeDeadline(inputs[1]);
        return "I have updated the task as follows: \n" + current.toString();
      } catch (DukeException e) {
        return e.getMessage();
      }
    }
    return "Unable to update task";
  }

  /** Prints out the TaskList */
  public String list() {
    StringBuilder out = new StringBuilder("Meow list\n");
    System.out.println("Meow list");
    int i = 0;
    for (Task task : tasks) {
      System.out.println(++i+ ". " + task);
      out.append(i).append(". ").append(task).append("\n");
    }
    return out.toString();
  }

  /**
   * Searches for a task with the specified keyword Prints it out
   *
   * @param inputs User input split into tokens
   */
  public String handleFind(String[] inputs) {
    StringBuilder out = new StringBuilder("Here are the matching tasks in your list:\n");
    System.out.println("Here are the matching tasks in your list:");
    int i = 0;
    for (Task task : tasks) {
      if (task.getDescription().contains(inputs[1])) {
        System.out.println(++i + ". " + task);
        out.append(i).append(". ").append(task).append("\n");
      }
    }
    return out.toString();
  }

  /** Returns the ArrayList of tasks */
  public ArrayList<Task> getList() {
    return this.tasks;
  }
}
