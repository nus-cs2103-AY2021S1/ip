package main.java;

import duke.task.Task;

import java.util.ArrayList;

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
        this.tasks = tasks;
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
        this.tasks.add(task);
    }

    /**
     * Removes a Task from the TaskList.
     *
     * @param idx Index of Task to be removed from TaskList.
     */
    public void remove(int idx) {
        this.tasks.remove(idx);
    }

    /**
     * Retrieves a Task from the TaskList.
     *
     * @param idx Index of Task to be retrieved from TaskList.
     * @return Task retrieved from TaskList.
     */
    public Task get(int idx) {
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
