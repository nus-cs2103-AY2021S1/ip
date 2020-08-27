package duke.task;

import java.util.ArrayList;
import java.util.List;

/**
 * The TaskList object keeps a List of Tasks for Duke.
 * Supports functions to retrieve, add, remove, and query Tasks.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * TaskList constructor called when first running Duke.
     */
    public TaskList() {
         tasks = new ArrayList<>(100);
    }

    /**
     * TaskList constructor called when reading data from Storage.
     * @param tasks A List of tasks retrieved from the hard disk.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the current number of Tasks in the list.
     */
    public int getNumOfTasks() {
        return tasks.size();
    }

    /**
     * Returns the Task associated with a task number.
     * @param i Task number provided.
     */
    public Task retrieve(int i) {
        return tasks.get(i - 1);
    }

    /**
     * Adds a given Task to the list.
     * @param t Given Task to be added.
     */
    public void add(Task t) {
        this.tasks.add(t);
    }

    /**
     * Removes the Task associated with a task number from the list.
     * @param i Task number provided.
     */
    public void remove(int i) {
        tasks.remove(i - 1);
    }
}
