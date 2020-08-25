package duke.tasks;

import java.util.ArrayList;
import java.util.List;

/**
 * List that contains all the task.
 */
public class TaskList {
    private List<Task> list;

    /**
     * Constructor to create TaskList.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Overloaded Constructor to create TaskList object when a list is already saved in local storage.
     *
     * @param tasks list saved in user's local storage.
     */
    public TaskList(List<Task> tasks) {
        this.list = tasks;
    }

    /**
     * Adds a new task to TaskList.
     *
     * @param task task to be added into list of tasks.
     */
    public void add(Task task) {
        this.list.add(task);
    }

    /**
     * Removes task from TaskList.
     *
     * @param index position of task being deleted in the TaskList.
     */
    public void delete(int index) {
        this.list.remove(index);
    }

    /**
     * Get task from TaskList.
     *
     * @param index position of task needed in the TaskList.
     * @return task at the index position in TaskList.
     */
    public Task get(int index) {
        return this.list.get(index);
    }

    /**
     * Gets the size of the TaskList.
     *
     * @return the number of tasks in the TaskList.
     */
    public int getSize() {
        return this.list.size();
    }

    /**
     * Gets the entire list of tasks.
     *
     * @return the entire lists of tasks.
     */
    public List<Task> getList() {
        return this.list;
    }
}
