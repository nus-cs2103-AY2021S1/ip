import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** Wrapper behind a List that performs operations on the List of Tasks. */
public class TaskList {
  List<Task> tasks;

  TaskList() {
    this.tasks = new ArrayList<>();
  }

  List<Task> getTaskList() {
    return tasks;
  }

  /** Iterate through a list and prints its contents. */
  void iterate() {
    if (tasks.size() == 0) {
      System.out.println("Hooray! You have no tasks in the list");
      return;
    }
    System.out.println("Here are the tasks in your list:");
    for (int i = 0; i < tasks.size(); i++) {
      System.out.println((i + 1) + ". " + tasks.get(i));
    }
  }

  /**
   * Given an array of words, return a List of all tasks that match the search word.
   *
   * @param search Array of words
   * @return List of all matching tasks
   */
  List<Task> findMatchingTasks(String[] search) {
    List<Task> matchingTasks = new ArrayList<>();
    for (Task t : tasks) {
      if (t.matchesWordList(search)) {
        matchingTasks.add(t);
      }
    }
    return matchingTasks;
  }

  /**
   * Gets the size of the List.
   *
   * @return size of List
   */
  int size() {
    return tasks.size();
  }

  /**
   * Adds a Task to the List.
   *
   * @param t Task to be added
   * @return Whether operation succeeded
   */
  boolean addTask(Task t) {
    tasks.add(t);
    return true;
  }

  /**
   * Removes a Task from a given index
   * @param index Index of Task to be removed from
   * @return Optional<Task> if the removal was successful, otherwise an empty Optional object.
   */
  Optional<Task> removeTask(int index) {
    try {
      return Optional.of(tasks.remove(index));
    } catch (Exception e) {
      return Optional.empty();
    }
  }

  /**
   * Sets a Task status to Done
   * @param index Index of Task to change
   * @return Optional<Task> if the task was set as done, otherwise an empty Optional object.
   */
  Optional<Task> setAsDone(int index) {
    try {
      Task originalTask = tasks.get(index);
      Task newTask = originalTask.setTaskAsCompleted();
      return Optional.of(originalTask);
    } catch (IndexOutOfBoundsException e) {
      Printer.printTaskNotFound();
      return Optional.empty();
    }
  }
}
