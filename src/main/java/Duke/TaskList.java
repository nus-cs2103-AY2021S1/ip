package duke;

import java.util.ArrayList;

import task.Task;

/**
 * Represents a TaskList which is a list that stores tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates an instance of a TaskList using the ArrayList of Tasks given.
     *
     * @param tasks ArrayList of Tasks used for initiating TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Creates an instance of a TaskList containing no Tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a Task into the TaskList.
     *
     * @param task Task to be added into TaskList.
     */
    public void add(Task task) {
        assert task != null : "task should not be null";
        this.tasks.add(task);
    }

    /**
     * Removes a Task from the TaskList.
     *
     * @param idx Index of Task to be removed from TaskList.
     */
    public void remove(int idx) {
        boolean isPositiveIndex = idx >= 0;
        boolean isIndexWithinBounds = idx < this.tasks.size();
        assert isPositiveIndex && isIndexWithinBounds : "Invalid index";

        this.tasks.remove(idx);
    }

    /**
     * Retrieves a Task from the TaskList.
     *
     * @param idx Index of Task to be retrieved from TaskList.
     * @return Task retrieved from TaskList.
     */
    public Task get(int idx) {
        boolean isPositiveIndex = idx >= 0;
        boolean isIndexWithinBounds = idx < this.tasks.size();
        assert isPositiveIndex && isIndexWithinBounds : "Invalid index";

        return tasks.get(idx);
    }

    /**
     * Gets the number of Tasks in the TaskList.
     *
     * @return The number of Tasks in the TaskList.
     */
    public int size() {
        return tasks.size();
    }
}
