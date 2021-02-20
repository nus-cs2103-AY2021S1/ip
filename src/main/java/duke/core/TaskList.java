package duke.core;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

import duke.task.Task;

/**
 * The TaskList class stores the tasks and manages the tasks using corresponding methods.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Creates a task list with the list of tasks.
     *
     * @param tasks The list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        assert tasks != null : "the task list should not be null";
        tasks.add(task);
    }

    /**
     * Removes a task from the task list.
     *
     * @param n The count of the task to be removed.
     */
    public Task remove(int n) {
        assert tasks != null : "the task list should not be null";
        return tasks.remove(n);
    }

    /**
     * Marks a task as completed in the task list.
     *
     * @param n The count of the task to be mark as completed.
     */
    public void markAsCompleted(int n) {
        assert tasks != null : "the task list should not be null";
        tasks.get(n).markAsCompleted();
    }

    /**
     * Determines whether the task list has the task.
     *
     * @param n The count of the task.
     */
    public boolean has(int n) {
        assert tasks != null : "the task list should not be null";
        return n >= 0 && n < tasks.size();
    }

    /**
     * Finds the task on a specific date.
     *
     * @param localDate The specific date.
     * @return The task list with the corresponding tasks
     */
    public TaskList findTaskAt(LocalDate localDate) {
        assert tasks != null : "the task list should not be null";
        return new TaskList(
                new ArrayList<>(
                        tasks.stream()
                                .filter((task) -> task.isAt(localDate))
                                .collect(Collectors.toList())
                )
        );
    }

    /**
     * Finds the task with the description containing the search key.
     *
     * @param key The search key.
     * @return The task list with the corresponding tasks
     */
    public TaskList findTaskWithDescription(String key) {
        assert tasks != null : "the task list should not be null";
        return new TaskList(
                new ArrayList<>(
                        tasks.stream()
                                .filter((task) -> task.getDescription().toUpperCase().contains(key.toUpperCase()))
                                .collect(Collectors.toList())
                )
        );
    }

    /**
     * Returns the corresponding representation of the task list.
     *
     * @return The corresponding representation of the task list.
     */
    @Override
    public String toString() {
        assert tasks != null : "the task list should not be null";
        String result = "";

        for (int i = 0; i < tasks.size(); i = i + 1) {
            result = result + String.valueOf(i + 1) + "." + tasks.get(i).toString() + "\n";
        }
        return result;
    }

    /**
     * Gets the tasks in the task list.
     *
     * @return The tasks in the task list.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Gets the specific task in the task list.
     *
     * @param count The count of the task in the task list.
     * @return The task in the task list.
     */
    public Task getTask(int count) {
        return tasks.get(count);
    }
}
