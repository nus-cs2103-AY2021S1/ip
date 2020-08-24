package duke.task;

import duke.exception.DukeException;
import duke.ui.Ui;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** TaskList that is 1-indexed list of tasks */
public class TaskList implements Iterable<Task> {
  private List<Task> tasks;

  public TaskList() {
    this.tasks = new ArrayList<>();
    this.tasks.add(null);
  }

  /**
   * Parses index to make sure it is within range
   *
   * @return valid index
   * @throws DukeException when index is out of bounds in tasklist
   */
  private int parseIndex(int index) throws DukeException {
    if (index == 0 || index > this.tasks.size() - 1) {
      throw Ui.taskIndexOutOfBoundsException(index, this);
    }

    return index;
  }

  /**
   * Returns length of the tasklist
   *
   * @return an integer that is the length of all tasklist
   */
  public int size() {
    return this.tasks.size() - 1;
  }

  /**
   * Adds a task into the tasklist
   *
   * @param task a task to be added to the tasklist
   */
  public void add(Task task) {
    this.tasks.add(task);
  }

  /**
   * Removes the task at the index from the tasklist
   *
   * @param index the index of the task in the tasklist to be removed
   * @return the task removed
   * @throws DukeException when index is out of bounds in tasklist
   */
  public Task remove(int index) throws DukeException {
    int validIndex = this.parseIndex(index);
    return this.tasks.remove(validIndex);
  }

  /**
   * Returns task at the specified index in the tasklist
   *
   * @param index of the task in the tasklist
   * @return the task at the specified index in the tasklist
   * @throws DukeException when index is out of bounds in tasklist
   */
  public Task get(int index) throws DukeException {
    int validIndex = this.parseIndex(index);
    return this.tasks.get(validIndex);
  }

  /**
   * Returns an iterator of the tasklist
   *
   * @return an iterator of the tasklist
   */
  @Override
  public Iterator<Task> iterator() {
    return new Iterator<>() {
      private int currentIndex = 1;

      @Override
      public boolean hasNext() {
        return currentIndex < TaskList.this.tasks.size();
      }

      @Override
      public Task next() {
        Task task = TaskList.this.tasks.get(currentIndex);
        this.currentIndex++;
        return task;
      }
    };
  }
}
