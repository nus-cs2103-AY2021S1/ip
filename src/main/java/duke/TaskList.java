package duke;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a list of Tasks for the Duke program.
 */
public class TaskList implements Serializable {
    protected ArrayList<Task> list;

    /**
     * Instantiates a TaskList with a specific List.
     * @param list the list to instantiate the TaskList with.
     */
    public TaskList(List<Task> list) {
        this.list = new ArrayList<>(list);
    }

    /**
     * Instantiates a TaskList with no existing tasks.
     */
    public TaskList() {
        this(new ArrayList<>());
    }

    /**
     * Adds a task to the TaskList.
     * @param task the task to be added
     */
    public void add(Task task) {
        list.add(task);
    }

    /**
     * Marks a certain task in the TaskList as done.
     * @param index the 0-based index of the task to be marked as done
     * @return the task that was marked as done
     */
    public Task markTaskAsDone(int index) {
        Task task = list.get(index);
        task.setDone(true);
        return task;
    }

    /**
     * Returns the task at a certain 0-based index of the TaskList.
     * @param index the 0-based index of the task to be returned
     * @return the task at the 0-based index
     */
    public Task get(int index) {
        return list.get(index);
    }

    /**
     * Removes a task at the 0-based index of the TaskList
     * @param index the 0-based index of the task to be removed
     * @return the task that was removed
     */
    public Task remove(int index) {
        return list.remove(index);
    }

    /**
     * Returns the size of the TaskList.
     * @return the size of the TaskList
     */
    public int size() {
        return list.size();
    }

    /**
     * Returns true if the TaskList is empty.
     * @return true if the TaskList is empty
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TaskList taskList = (TaskList) o;
        return Objects.equals(list, taskList.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }
}
