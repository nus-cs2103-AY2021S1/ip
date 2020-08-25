package core;

import tasks.Task;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Controls the current list of tasks.
 */
public class TaskList {

    private static final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds a {@code Task}.
     * @param t the {@code Task} to be added.
     */
    public static void add(Task t) {
        tasks.add(t);
    }

    /**
     * Retrieves the {@code Task} at the given index.
     * @param id the index of the {@code Task} to find
     * @return the {@code Task} at index {@code id}
     */
    public static Task get(int id) {
        return tasks.get(id);
    }

    /**
     * Removes the {@code Task} at the given index.
     * @param id the index of the {@code Task} to remove
     * @return the {@code Task} at index {@code id} that was removed
     */
    public static Task remove(int id) {
        return tasks.remove(id);
    }

    /**
     * Retrieves the size of the task list.
     * @return the size of the task list
     */
    public static int size() {
        return tasks.size();
    }

    /**
     * Retrieves a {@code Stream} representation of the task list for iteration purposes.
     * @return a {@code Stream} whose elements are {@code Task}s in the task list
     */
    public static Stream<Task> stream() {
        return tasks.stream();
    }

}
