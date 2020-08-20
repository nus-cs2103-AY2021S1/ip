package duke.duke;

import duke.task.Task;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * duke.runner.Duke is the singleton class we'll be using to manage the transactions. It will
 * encapsulate the task storage and retrieval system.
 */
public class Duke {

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

  public List<Task> addTask(String description) {
    Task task = new Task(description);
    tasks.add(task);
    return Collections.singletonList(task);
  }

  // TODO: FP. Method parameters is placeholder for now
  public List<Task> editTask(int id, boolean isDone) {
    Task task = tasks.get(id);
    task.setIsDone(isDone);
    return Collections.singletonList(task);
  }

  public List<Task> getTasks() {
    return tasks;
  }
}
