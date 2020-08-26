package duke;

import java.util.List;
import java.util.ArrayList;

import duke.task.Task;

/**
 * Represents a manager that deals with task operations.
 */
public class TaskManager {

    /**
     * The list of tasks that is stored.
     */
    private final List<Task> tasks;

    /**
     * Initializes a manager instance with an empty list of tasks.
     */
    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Initializes a manager instance with a non-empty list of tasks.
     * @param tasks the list of tasks to be managed
     */
    public TaskManager(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the list of tasks.
     * @return the list of tasks
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Adds a task to the list.
     * @param task the task to be added
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the list.
     * @param index the index of the task in the list
     * @return the deleted task
     */
    public Task deleteTask(int index) {
        return this.tasks.remove(index);
    }

    /**
     * Marks a task as done.
     * @param task the task to be marked as done
     */
    public void markTaskAsDone(Task task) {
        task.markAsDone();
    }
}