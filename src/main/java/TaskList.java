/**
 * Encapsulates a list that stores Tasks
 */

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> list;

    /**
     * Constructor
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Adds a Task into the TaskList
     *
     * @param task is the task to be added
     */
    protected void add(Task task) {
        this.list.add(task);
    }

    /**
     * Returns the total number of Tasks in the TaskList
     */
    protected int length() {
        return this.list.size();
    }

    /**
     * Returns the Task at a specific index
     *
     * @param index is the index of the Task to be retrieved
     * @return the Task
     */
    protected Task get(int index) {
        return this.list.get(index);
    }

    /**
     * Returns the index of a specific Task
     *
     * @param task is the Task of the index to be retrieved
     * @return the index as an int
     */
    protected int getIndex(Task task) {
        return this.list.indexOf(task);
    }

    /**
     * Checks if the TaskList is empty
     *
     * @return true if the TaskList is empty
     */
    protected boolean isEmpty() {
        return this.list.isEmpty();
    }

    /**
     * Removes a specific Task from the TaskList
     *
     * @param task is the Task to be removed
     */
    protected void remove(Task task) {
        this.list.remove(task);
    }

    /**
     * Provides the actual list that is storing the Tasks
     *
     * @return the list
     */
    protected ArrayList<Task> get() {
        return this.list;
    }
}
