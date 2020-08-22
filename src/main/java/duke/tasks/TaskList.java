package duke.tasks;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents a list to store the tasks.
 */
public class TaskList {
    List<Task> result;

    /**
     * Constructs a TaskList object by passing in a list of tasks.
     *
     * @param list a list of tasks that needs to be stored.
     */
    public TaskList(List<Task> list) {
        this.result = list;
    }

    /**
     * Constructs a TaskList object by passing in no parameters.
     */
    public TaskList() {
        this.result = new ArrayList<>();
    }

    /**
     * Adds a task to the taskList.
     *
     * @param task the task object that needs to be added to the list.
     */
    public void add(Task task) {
        result.add(task);
    }

    /**
     * Sets the task at an index of the TaskList.
     *
     * @param index the index in the list that needs to be set.
     * @param task the task object that needs to be set at the spot.
     */
    public void set(Integer index, Task task) {
        result.set(index, task);
    }

    /**
     * Returns a task object at an index.
     *
     * @param index the index in the list that needs to be retrieved.
     * @return a task object at the index in the list.
     */
    public Task get(Integer index) {
        return result.get(index);
    }

    /**
     * Returns the size of the TaskList.
     *
     * @return an int value representing the size of the TaskList.
     */
    public int getSize() {
        return result.size();
    }

    /**
     * Deletes the task object at the index.
     *
     * @param index the index in the list that needs to be deleted.
     */
    public void remove(int index) {
        result.remove(index);
    }
}
