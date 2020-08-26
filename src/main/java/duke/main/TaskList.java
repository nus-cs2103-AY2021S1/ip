package duke.main;

import duke.task.Task;

import java.util.ArrayList;

/**
 * TaskList is a container for Task. It has query, delete, and add function.
 */
public class TaskList {

    /** List to store the Tasks. */
    protected ArrayList<Task> taskList;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructs a TaskList based on the given data.
     *
     * @param data List of Task that wants to be added to this TaskList.
     */
    public TaskList(ArrayList<Task> data) {
        this.taskList = data;
    }

    /**
     * Adds a Task to this TaskList.
     *
     * @param t Task to be added.
     */
    public void add(Task t) {
        taskList.add(t);
    }

    /**
     * Deletes a Task in this TaskList based on the given index.
     *
     * @param i Index of Task that wants to be deleted.
     */
    public void delete(int i) {
        taskList.remove(i);
    }

    /**
     * Gets the Task based on the given index.
     *
     * @param i Index of the desired Task.
     * @return The desired Task based on the given index.
     */
    public Task get(int i) {
        return taskList.get(i);
    }

    /**
     * Returns the size of this TaskList.
     *
     * @return The size of this TaskList in integer form.
     */
    public int size() {
        return taskList.size();
    }
}
