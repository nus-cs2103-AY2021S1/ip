package duke.duke;

import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TodoTask;
import duke.view.cli.CLI;
import java.util.ArrayList;
import java.util.List;

/**
 * duke.runner.Duke is the singleton class we'll be using to manage the transactions. It will
 * encapsulate the task storage and retrieval system.
 */
public class Duke {

  private CLI observer;
  private static Duke duke = null;
  private final List<Task> tasks;

  private Duke() {
    tasks = new ArrayList<>();
  }

  public static Duke getInstance() {
    if (duke == null) {
      duke = new Duke();
    }

    return duke;
  }

  // TODO: observer list
  public void addObserver(CLI observer) {
    this.observer = observer;
  }

  public void notifyObservers(String s) {
    observer.update(s);
  }

  public void addTodoTask(String description) {
    Task task = new TodoTask(description);
    tasks.add(task);
    notifyObservers(task.toString());
  }

  public void addEventTask(String description, String at) {
    Task task = new EventTask(description, at);
    tasks.add(task);
    notifyObservers(task.toString());
  }

  public void addDeadlineTask(String description, String by) {
    Task task = new DeadlineTask(description, by);
    tasks.add(task);
    notifyObservers(task.toString());
  }

  public void markTaskDone(int id) {
    Task task = tasks.get(id);
    task.markDone();
    notifyObservers(task.toString());
  }

  public List<Task> getTasks() {
    notifyObservers(tasks.toString());
    return tasks;
  }
}
