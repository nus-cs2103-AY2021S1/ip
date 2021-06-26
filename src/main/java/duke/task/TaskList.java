package duke.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * This class represents a list of Tasks.
 */
public class TaskList {
    private List<Task> list;
    private SaveFunction saveFunction;

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        list = new ArrayList<>();
        saveFunction = (list) -> {
        };
    }

    /**
     * Set the save function for this list. The save function will be run whenever the list changes.
     *
     * @param function Save function.
     */
    public void connectStorage(SaveFunction function) {
        saveFunction = function;
    }

    /**
     * Adds a task to this list.
     *
     * @param task Task to add.
     */
    public void add(Task task) {
        task.setOnChangeFunction(() -> saveFunction.save(this));
        list.add(task);
        saveFunction.save(this);
    }

    /**
     * Removes a task from this list.
     *
     * @param index Index of the task to be removed.
     * @return Task removed.
     */
    public Task delete(int index) {
        Task removed = list.remove(index);
        saveFunction.save(this);
        return removed;
    }

    /**
     * Retrieves a task from this list.
     *
     * @param index 0-based index of the task to be retrieved.
     * @return Task at the specified index.
     */
    public Task get(int index) {
        return list.get(index);
    }

    /**
     * Get the number of tasks in this list.
     *
     * @return Number of tasks.
     */
    public int size() {
        return list.size();
    }

    /**
     * Returns the Tasks in this TaskList as a Stream.
     *
     * @return Stream of Tasks.
     */
    public Stream<Task> toStream() {
        return IntStream.range(0, size()).mapToObj(list::get);
    }

    /**
     * Returns the Tasks in this TaskList as a List. 
     *
     * @return List of Tasks.
     */
    public List<Task> toList() {
        return new ArrayList<>(list);
    }
}
