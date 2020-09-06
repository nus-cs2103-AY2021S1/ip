package ultron;

import java.util.ArrayList;

import ultron.tasks.Task;

public final class TaskList {

    /** Store the internal list of tasks. */
    private final ArrayList<Task> list;

    /**
     * Task list.
     * It stores all of the tasks present.
     *
     * @param taskArrayList Arraylist containing all the tasks.
     */
    public TaskList(final ArrayList<Task> taskArrayList) {
        list = taskArrayList;
    }

    /**
     * Returns the arraylist of tasks.
     *
     * @return ArrayList containing all of the tasks.
     */
    public ArrayList<Task> getList() {
        return new ArrayList<>(list);
    }

    /**
     * Gets the size of the tasklist.
     *
     * @return int size.
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Mark a particular task as done.
     *
     * @param index The index of the task to be marked.
     */
    public void markDone(final int index) {
        if (index < this.list.size() && index >= 0) {
            this.get(index).markDone();
        }
    }

    /**
     * Remove a task from the tasklist.
     *
     * @param index Index of the task to be removed.
     */
    public void remove(final int index) {
        if (index < this.list.size() && index >= 0) {
            this.list.remove(index);
        }
    }

    /**
     * Get the task at index.
     *
     * @param index Index of the task.
     * @return Task at the index provided.
     */
    public Task get(final int index) {
        return this.list.get(index);
    }

    /**
     * Add the task to the task list.
     *
     * @param task Task to be added.
     */
    public void add(final Task task) {
        this.list.add(task);
    }
}
