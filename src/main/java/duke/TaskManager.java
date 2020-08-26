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
        tasks = new ArrayList<>();
    }

    /**
     * Initializes a manager instance with a non-empty list of tasks.
     *
     * @param tasks The list of tasks to be managed.
     */
    public TaskManager(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the list.
     *
     * @param index The index of the task in the list.
     * @return The deleted task.
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Marks a task as done.
     *
     * @param task The task to be marked as done.
     */
    public void markTaskAsDone(Task task) {
        task.markAsDone();
    }

    /**
     * Finds all matching tasks with the specified keyword.
     *
     * @param keyword The keyword used to find matching tasks.
     * @return The list of tasks that match the keyword.
     */
    public List<Task> findTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}