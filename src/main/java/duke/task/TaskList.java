package duke.task;

import duke.dukeException.DukeException;

import java.util.ArrayList;

/** Represents a Task List. */
public class TaskList {
  private ArrayList<Task> list;

  /**
   * Constructor of a TaskList object with an existing ArrayList, for e.g. save file.
   *
   * @param list ArrayList of Tasks.
   */
  public TaskList(ArrayList<Task> list) {
    this.list = list;
  }

  /** Constructor of a TaskList object without any existing ArrayList, for e.g. no save file. */
  public TaskList() {
    list = new ArrayList<>();
  }

  /**
   * A method to obtain a specified Task object from TaskList.
   *
   * @param id Specified task number from user input.
   * @return Task object.
   * @throws DukeException If task number is out of bounds in the ArrayList.
   */
  public Task getTask(int id) throws DukeException {
    try {
      return list.get(id - 1);
    } catch (IndexOutOfBoundsException e) {
      throw new DukeException(" This task #" + id + " does not exist.");
    }
  }

  /**
   * A method to add a new Task object into TaskList.
   *
   * @param t Task object.
   */
  public void addTask(Task t) {
    list.add(t);
  }

  /**
   * A method to remove a new Task object into TaskList.
   *
   * @param id Task number from user input.
   */
  public void removeTask(int id) {
    list.remove(id - 1);
  }

  /**
   * A method to indicate whether the TaskList is empty.
   *
   * @return Boolean 'true' or 'false'.
   */
  public boolean isEmpty() {
    return list.isEmpty();
  }

  /**
   * A method to return the size of the TaskList.
   *
   * @return Integer, size of list.
   */
  public int size() {
    return list.size();
  }

  /**
   * A method to retrieve the TaskList.
   *
   * @return ArrayList of TaskList.
   */
  public ArrayList<Task> getList() {
    return this.list;
  }
}
