package duke;

import java.util.ArrayList;

/**
 * <h>duke.TaskList</h>
 * Contains the task list
 */
public class TaskList {
    ArrayList<Task> ls;

    /**
     * Constructor of duke.TaskList.
     * @param ls The array list of task to be converted.
     */
    public TaskList(ArrayList<Task> ls) {
        this.ls = ls;
    }

    /**
     * Getter for the list.
     * @return An array list of task.
     */
    public ArrayList<Task> getLs() {
        return ls;
    }

    /**
     * Check if the list is empty.
     * @return Boolean If the list is empty.
     */
    public boolean isEmpty() {
        return getLs().isEmpty();
    }

    /**
     * Get the size of the list.
     * @return int The number of task in the list.
     */
    public int size() {
        return getLs().size();
    }

    /**
     * Get the task at position i.
     * @param i int The position to be retrieved from.
     * @return duke.Task The task that is queried.
     */
    public Task get(int i) {
        return getLs().get(i);
    }

    /**
     * Set the task at position to be marked as done.
     * @param numToBeMarkedAsDone int Position of the task to be marked.
     * @param task The task to be marked.
     */
    public void set(int numToBeMarkedAsDone, Task task) {
        getLs().set(numToBeMarkedAsDone, task);
    }

    /**
     * Add a task to the list.
     * @param task The task to be added.
     */
    public void add(Task task) {
        getLs().add(task);
    }

    /**
     * Delete the task from the list.
     * @param numToBeDeleted int The task at the position to be deleted.
     */
    public void remove(int numToBeDeleted) {
        getLs().remove(numToBeDeleted);
    }
}
