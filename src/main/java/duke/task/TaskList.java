package duke.task;

import duke.userinterface.Printer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** Wrapper behind a List that performs operations on the List of Tasks. */
public class TaskList {
  List<Task> tasks;

  public TaskList() {
    this.tasks = new ArrayList<>();
  }

  public List<Task> getTaskList() {
    return tasks;
  }

  /**
   * Iterate through all Tasks.
   *
   * @return String containing all tasks.
   */
  public String iterate() {
    if (tasks.size() == 0) {
      return "Hooray! You have no tasks in the list";
    }
    StringBuilder output = new StringBuilder("Here are the tasks in your list: \n");
    for (int i = 0; i < tasks.size(); i++) {
      output.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
    }
    return output.toString();
  }

  /**
   * Given an array of words, return a List of all tasks that match the search word.
   *
   * @param search Array of words
   * @return List of all matching tasks
   */
  public List<Task> findMatchingTasks(String[] search) {
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
  public int size() {
    return tasks.size();
  }

  /**
   * Adds a duke.task.Task to the List.
   *
   * @param t duke.task.Task to be added
   * @return Whether operation succeeded
   */
  public boolean addTask(Task t) {
    tasks.add(t);
    return true;
  }

  /**
   * Removes a duke.task.Task from a given index
   *
   * @param index Index of duke.task.Task to be removed from
   * @return Optional<duke.task.Task> if the removal was successful, otherwise an empty Optional
   *     object.
   */
  public Optional<Task> removeTask(int index) {
    try {
      return Optional.of(tasks.remove(index));
    } catch (Exception e) {
      return Optional.empty();
    }
  }

  /**
   * Sets a duke.task.Task status to Done
   *
   * @param index Index of duke.task.Task to change
   * @return Optional<duke.task.Task> if the task was set as done, otherwise an empty Optional
   *     object.
   */
  public Optional<Task> setAsDone(int index) {
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
