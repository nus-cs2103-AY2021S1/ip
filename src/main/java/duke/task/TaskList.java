package duke.task;

import java.util.function.Consumer;

// Interface for any list-like data structure for storing a list of Tasks
public interface TaskList {
    /**
     * Adds the provided task to the task list.
     *
     * @param t Task task to add
     */
    void add(Task t);

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
     * Iterate and apply the action provided on each task in the list.
     *
     * @param action Consumer<Task> action to perform on each task
     */
    void forEach(Consumer<Task> action);
}
