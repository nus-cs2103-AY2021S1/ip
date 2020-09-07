package ultron;

import java.util.ArrayList;

import ultron.tasks.Task;

public final class TaskList {

    /** Store the internal list of tasks. */
    private final ArrayList<Task> tasks;

    /**
     * Task list.
     * It stores all of the tasks present.
     *
     * @param taskArrayList Arraylist containing all the tasks.
     */
    public TaskList(final ArrayList<Task> taskArrayList) {
        tasks = taskArrayList;
    }

    /**
     * Returns the arraylist of tasks.
     *
     * @return ArrayList containing all of the tasks.
     */
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    /**
     * Gets the size of the tasklist.
     *
     * @return int size.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Mark a particular task as done.
     *
     * @param index The index of the task to be marked.
     */
    public void markDone(final int index) {
        if (index < this.tasks.size() && index >= 0) {
            this.get(index).markDone();
        }
    }

    /**
     * Remove a task from the tasklist.
     *
     * @param index Index of the task to be removed.
     */
    public void remove(final int index) {
        if (index < this.tasks.size() && index >= 0) {
            this.tasks.remove(index);
        }
    }

    /**
     * Get the task at index.
     *
     * @param index Index of the task.
     * @return Task at the index provided.
     */
    public Task get(final int index) {
        return this.tasks.get(index);
    }

    /**
     * Add the task to the task list.
     *
     * @param task Task to be added.
     */
    public void add(final Task task) {
        this.tasks.add(task);
    }
}
