package duke.storage;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Represents an ArrayList of Tasks
 */
public class TaskList {

    protected final ArrayList<Task> tasks;

    /**
     * Constructor for a TaskList object.
     */
    TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Removes the object from the ArrayList at the given index.
     *
     * @param index the index to delete from
     * @return the Task that was removed
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Adds the Task to the ArrayList.
     *
     * @param task the task to be added
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the ArrayList of tasks
     *
     * @return the ArrayList of all the tasks stored
     */
    public ArrayList<Task> list() {
        return tasks;
    }

    /**
     * Returns whether or not the list is currently empty.
     *
     * @return true if tasks is empty, false otherwise
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Marks the Task in the ArrayList at the given index as completed.
     *
     * @param index the index to mark as complete
     * @return the Task that was completed.
     */
    public Task complete(int index) {
        Task toComplete = tasks.get(index);
        toComplete.setCompleted();
        return toComplete;
    }

    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return "The list is empty.";
        } else {
            String result = "";
            for (Task t : tasks) {
                result = result.concat(t.toString()).concat("\n");
            }
            return result;
        }
    }
}
