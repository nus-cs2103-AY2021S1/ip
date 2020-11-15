package duke.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.StringJoiner;

import duke.task.Task;

/**
 * Models a list of tasks and the associated operations.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList from an ArrayList of Tasks.
     *
     * @param tasks The tasks to construct a TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Empty constructor for TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param task The task to add.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes and returns a task from the list of tasks.
     *
     * @param index The index of the task to remove
     * @return The task that was removed.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns the size of the list of tasks.
     *
     * @return The size of the list of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns a task from the list of tasks without removing it.
     *
     * @param index The index of the task to return.
     * @return The task corresponding to the given index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Returns a String representation of all the tasks in a save-friendly format.
     *
     * @return String representation of all the tasks in a save-friendly format.
     */
    public String getSaveFormat() {
        StringJoiner text = new StringJoiner("\n");
        for (Task task : tasks) {
            text.add(task.toSaveFormat());
        }
        return text.toString();
    }

    /**
     * Returns a String representation of all tasks which contain the parameter.
     *
     * @param toFind The string used to find tasks.
     * @return String representation of all tasks containing the parameter.
     */
    public String find(String toFind) {
        StringJoiner response = new StringJoiner("\n");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).contains(toFind)) {
                response.add(String.format("%d.%s", i + 1, tasks.get(i)));
            }
        }

        return response.toString();
    }

    /**
     * Sorts the task list by date.
     */
    public void sort() {
        Collections.sort(this.tasks);
    }
}
