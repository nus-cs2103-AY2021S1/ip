package task;

import java.util.ArrayList;
import java.util.List;

/**
 * TaskList class are used to store all the task specified
 * by user in an ArrayList of Task.
 */
public class TaskList {

    private List<Task> tasks;

    /**
     * Constructs a new TaskList with the tasks
     * initialize to a new ArrayList.
     */
    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    /**
     * Constructs a new TaskList with the
     * specified list of tasks.
     *
     * @param tasks List(Task) List of task
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets the list of tasks.
     *
     * @return List(Task) Returns list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Sets the current list of tasks to
     * the specified list of tasks.
     *
     * @param tasks List(Task) list of task.
     */
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds new Task to the list.
     *
     * @param task Task single task.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes the specified task from the list.
     *
     * @param index int list's index.
     * @return Task task
     */
    public Task remove(int index) {
        return this.tasks.remove(index);
    }

    /**
     * Gets the specified task.
     *
     * @param index int list's index.
     * @return Task Returns the specified task.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Gets the size of the list.
     *
     * @return int list's size.
     */
    public int size() {
        return this.tasks.size();
    }
}
