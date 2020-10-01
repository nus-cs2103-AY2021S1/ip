package util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import task.Task;

/**
 * Data structure to store tasks.
 */
public class TaskList {

    private List<Task> tasks;

    /**
     * Creates new task list object with empty list of tasks.
     */
    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    /**
     * Creates new task list object with the given list of tasks.
     *
     * @param tasks List of tasks to be stored.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns all tasks in a list format.
     *
     * @return List of all tasks.
     */
    public List<Task> getList() {
        return tasks;
    }

    /**
     * Prints all tasks sequentially.
     */
    public String printList() {
        return printList(task -> true);
    }

    /**
     * Prints all tasks sequentially that satisfied filter.
     *
     * @param filter Filters which tasks will be printed.
     */
    public String printList(Predicate<Task> filter) {

        String output = "";
        for (int i = 0; i < tasks.size(); i++) {
            if (filter.test(tasks.get(i))) {
                output += (i + 1) + ". " + tasks.get(i) + "\n";
            }
        }

        if (output.length() == 0) {
            output += "\n";
        }

        return output;
    }

    /**
     * Prints task stored at index.
     *
     * @param index Index of task to be printed.
     * @throws DukeException If index is invalid.
     */
    public String printTask(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Invalid index given");
        }
        return (index + 1) + ". " + tasks.get(index) + "\n";
    }

    /**
     * Add task to the end of the list.
     *
     * @param task Task to be added.
     * @return Index of duplicate task if any, else -1.
     */
    public int addTask(Task task) {
        assert tasks != null;

        if (!tasks.contains(task)) {
            tasks.add(task);
            return -1;
        }
        return tasks.indexOf(task);
    }

    /**
     * Removes task from the list at index.
     *
     * @param index Index of task to be removed.
     * @return Removed task.
     * @throws DukeException If index is invalid.
     */
    public Task removeTask(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Invalid index given");
        }
        return tasks.remove(index);
    }

    /**
     * Removes task from the list at index.
     *
     * @param index Index of task to be removed.
     * @return Completed task.
     * @throws DukeException If index is invalid.
     */
    public Task completeTask(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Invalid index given");
        }
        tasks.get(index).completeTask();
        assert tasks.get(index).isCompleted() == true;
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks.
     *
     * @return The number of tasks.
     */
    public int getSize() {
        assert tasks != null;
        return tasks.size();
    }
}
