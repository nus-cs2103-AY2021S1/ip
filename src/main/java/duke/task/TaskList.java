package duke.task;

import java.util.function.Consumer;

// Interface for any list-like data structure for storing a list of Tasks
public interface TaskList {
    /**
     * Adds the provided task to the task list.
     *
     * @param t Task task to add
     * @param shouldUpdateStorage whether the new task should be saved
     */
    void add(Task t, boolean shouldUpdateStorage);

    /**
     * Returns the task at the specified index.
     *
     * @param i int index of task to fetch
     * @return Task task at specified index
     */
    Task get(int i);

    /**
     * Removes the task at the specified index.
     *
     * @param i int index of task
     * @return Task task that was removed
     */
    Task remove(int i);

    /**
     * Returns the number of tasks in the list.
     *
     * @return int number of tasks in list
     */
    int size();

    /**
     * Triggers the Storage object to update a specific line.
     *
     * @param index int index of the task to update
     */
    void update(int index);
}
