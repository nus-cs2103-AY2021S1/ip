package duke.duke;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * duke.runner.Duke is the singleton class we'll be using to manage the transactions. It will
 * encapsulate the task storage and retrieval system.
 */
public class Duke {

  private static Duke duke = null;
  private final List<String> tasks;

  private Duke() {
    tasks = new ArrayList<>();
  }

  public static Duke getInstance() {
    if (duke == null) {
      duke = new Duke();
    }

    return duke;
  }

  public List<String> addTask(String description) {
    tasks.add(description);
    return Collections.singletonList(description);
  }

  public List<String> getTasks() {
    return tasks;
  }
}
