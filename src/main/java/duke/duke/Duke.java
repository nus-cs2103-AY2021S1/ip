package duke.duke;

import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TodoTask;
import duke.utils.Storage;
import duke.view.cli.CLI;
import java.util.ArrayList;
import java.util.List;

/**
 * duke.runner.Duke is the singleton class we'll be using to manage the transactions. It will
 * encapsulate the task storage and retrieval system.
 */
public class Duke {

  private CLI observer;
  private final List<Task> tasks;
  private Storage storage;

  public Duke(Storage storage) {
    this.storage = storage;
    try {
      tasks = new ArrayList<>(this.storage.load());
    } catch (Exception e) {
      tasks = new ArrayList<>();
    }
  }

  // TODO: observer list
  public void addObserver(CLI observer) {
    this.observer = observer;
  }

  public void notifyObservers(String s) {
    observer.update(s);
  }

  public void updateStorage() throws Exception {
    storage.store(tasks);
  }

  public void addTodoTask(String description) throws Exception {
    Task task = new TodoTask(description);
    tasks.add(task);
    notifyObservers("Added TODO: " + task.toString());
    updateStorage();
  }

  public void addEventTask(String description, String at) throws Exception {
    Task task = new EventTask(description, at);
    tasks.add(task);
    notifyObservers("Added EVENT: " + task.toString());
    updateStorage();
  }

  public void addDeadlineTask(String description, String by) throws Exception {
    Task task = new DeadlineTask(description, by);
    tasks.add(task);
    notifyObservers("Added DEADLINE: " + task.toString());
    updateStorage();
  }

  public void markTaskDone(int id) throws Exception {
    Task task = tasks.get(id);
    task.markDone();
    notifyObservers("Marked DONE:" + task.toString());
    updateStorage();
  }

  public void deleteTask(int id) throws Exception {
    Task task = tasks.remove(id);
    notifyObservers("DELETED:" + task.toString());
    notifyObservers(String.format("You now have %d tasks.", tasks.size()));
    updateStorage();
  }

  public List<Task> getTasks() {
    notifyObservers(tasks.toString());
    return tasks;
  }
}
