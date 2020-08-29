package duke.tasks;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents a list to store the tasks.
 */
public class TaskList {
    List<Task> tasks;

    /**
     * Constructs a TaskList object by passing in a list of tasks.
     *
     * @param list a list of tasks that needs to be stored.
     */
    public TaskList(List<Task> list) {
        this.tasks = list;
    }

    /**
     * Constructs a TaskList object by passing in no parameters.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the taskList.
     *
     * @param task the task object that needs to be added to the list.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Sets the task at an index of the TaskList.
     *
     * @param index the index in the list that needs to be set.
     * @param task the task object that needs to be set at the spot.
     */
    public void set(Integer index, Task task) {
        tasks.set(index, task);
    }

    /**
     * Returns a task object at an index.
     *
     * @param index the index in the list that needs to be retrieved.
     * @return a task object at the index in the list.
     */
    public Task get(Integer index) {
        return tasks.get(index);
    }

    /**
     * Returns the size of the TaskList.
     *
     * @return an int value representing the size of the TaskList.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Deletes the task object at the index.
     *
     * @param index the index in the list that needs to be deleted.
     */
    public void remove(int index) {
        tasks.remove(index);
    }
}
