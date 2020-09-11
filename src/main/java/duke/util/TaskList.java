package duke.util;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    /** Array list of tasks. */
    private final ArrayList<Task> tasks;

    /**
     * Creates a new and empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return Number of tasks in the list.
     */
    public int getNumOfTasks() {
        return this.tasks.size();
    }

    /**
     * Gets a task from the list.
     *
     * @param index Index of task stored in the list.
     * @return Task from index i of the list.
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Gets the task list.
     *
     * @return Task list.
     */
    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to add to the list.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the list.
     *
     * @param index Index of task stored in the list.
     */
    public void deleteTask(int index) {
        this.tasks.remove(index);
    }
}
