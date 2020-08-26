package duke.io;

import duke.task.Task;
import java.util.ArrayList;

/**
 * Tasklist class define rules the list of tasks.
 *
 * @author Galvin Leow Wen Yuan
 * @author A0200204J
 * @version v1.0
 */
public class TaskList {
  public ArrayList<Task> taskArrayList;

  /** Class constructor. Initialise taskArraylist will be empty. */
  public TaskList() {
    this.taskArrayList = new ArrayList<>();
  }

  /**
   * Overloaded method. Class constructor. Initialise taskArrayList with data from filepath.
   *
   * @param taskArrayList task array list from file.
   */
  public TaskList(ArrayList<Task> taskArrayList) {
    this.taskArrayList = taskArrayList;
  }

  /**
   * Add task into the taskArrayList.
   *
   * @param task task to be added.
   */
  public void addTask(Task task) {
    this.taskArrayList.add(task);
  }

  /**
   * Return number of task in list.
   *
   * @return size of arraylist.
   */
  public int sizeOfList() {
    return this.taskArrayList.size();
  }

  /**
   * Get task by index.
   *
   * @param index int number to retrieve task.
   * @return task based on the index.
   */
  public Task retrieveTask(int index) {
    return this.taskArrayList.get(index);
  }

  /**
   * Delete task by index.
   *
   * @param index task index that is to be deleted.
   */
  public void deleteTask(int index) {
    this.taskArrayList.remove(index);
  }
}
