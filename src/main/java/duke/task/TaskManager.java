package duke.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
     * @param task The task to be deleted.
     */
    public void deleteTask(Task task) {
        tasks.remove(task);
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
     * Finds all matching tasks with the specified keyword(s).
     *
     * @param keywords The keyword(s) used to find matching tasks.
     * @return The list of tasks that match the keyword(s).
     */
    public List<Task> findTasks(String... keywords) {
        List<Task> matchingTasks = new ArrayList<>(tasks);
        return matchingTasks
                .stream()
                .filter(task -> Arrays.stream(keywords)
                        .anyMatch(keyword -> task.getDescription().contains(keyword)))
                .collect(Collectors.toList());
    }

    /**
     * Sorts task according to their date-time and their task type.
     */
    public void sortTasks() {
        tasks.sort(new TaskDateTimeComparator());
        tasks.sort(new TaskComparator());
    }
}
