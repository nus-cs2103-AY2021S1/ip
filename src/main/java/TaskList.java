import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** 1-indexed task list */
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
   * @throws DukeException that index is out of bounds in task list
   */
  private int parseIndex(int index) throws DukeException {
    if (index == 0 || index > this.tasks.size() - 1) {
      throw Ui.taskIndexOutOfBoundsException(index, this);
    }

    return index;
  }

  public int size() {
    return this.tasks.size() - 1;
  }

  public void add(Task task) {
    this.tasks.add(task);
  }

  public Task remove(int index) throws DukeException {
    int validIndex = this.parseIndex(index);
    return this.tasks.remove(validIndex);
  }

  public Task get(int index) throws DukeException {
    int validIndex = this.parseIndex(index);
    return this.tasks.get(validIndex);
  }

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
