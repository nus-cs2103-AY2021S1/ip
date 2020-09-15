package duke;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Encapsulates a list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty task list.
     */
    TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a task list with the specified tasks.
     * @param tasks ArrayList of tasks.
     */
    TaskList(ArrayList<Task> tasks) {
        assert tasks != null : "ArrayList have not been initialised";
        this.tasks = tasks;
    }

    /**
     * Returns true if this TaskList contains no elements.
     * @return True if this TaskList contains no elements.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns number of elements in this TaskList.
     * @return Number of elements in this TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the element at the specified position in this TaskList.
     * @param index Index of the element to return.
     * @return The element at the specified position in this TaskList.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Removes the element at the specified position in this TaskList.
     * Shifts any subsequent elements to the left (subtracts one from their indices).
     * @param index The index of the element to be removed.
     */
    public void remove(int index) {
        tasks.remove(index);
    }

    /**
     * Appends the specified element to the end of this TaskList.
     * @param task Task to be appended to this list
     */
    public void add(Task task) {
        assert task != null : "task have not been initialised";
        tasks.add(task);
    }

    public Stream<Task> toStream() {
        return this.tasks.stream();
    }
}
