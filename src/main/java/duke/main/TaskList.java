package duke.main;

import java.util.ArrayList;

import duke.task.Task;

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
     * @param task Task to be added.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes a Task in this TaskList based on the given index.
     *
     * @param taskNumber The desired task number to be deleted.
     */
    public void delete(int taskNumber) {
        taskList.remove(taskNumber - 1);
    }

    /**
     * Gets the Task based on the given index.
     *
     * @param taskNumber The desired task number.
     * @return The desired Task based on the given task number.
     */
    public Task get(int taskNumber) {
        return taskList.get(taskNumber - 1);
    }

    /**
     * Returns the size of this TaskList.
     *
     * @return The size of this TaskList in integer form.
     */
    public int getSize() {
        return taskList.size();
    }
}
