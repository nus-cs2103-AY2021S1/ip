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
     * @param function the save function.
     */
    public void connectStorage(SaveFunction function) {
        saveFunction = function;
    }

    /**
     * Adds a task to this list.
     *
     * @param task the task to add.
     */
    public void add(Task task) {
        task.setOnChangeFunction(() -> saveFunction.save(this));
        list.add(task);
        saveFunction.save(this);
    }

    /**
     * Removes a task from this list.
     *
     * @param index the index of the task to be removed.
     * @return the task removed.
     */
    public Task delete(int index) {
        Task removed = list.remove(index);
        saveFunction.save(this);
        return removed;
    }

    /**
     * Retrieves a task from this list.
     *
     * @param index the index of the task to be retrieved.
     * @return a task.
     */
    public Task get(int index) {
        return list.get(index);
    }

    /**
     * Get the number of tasks in this list.
     *
     * @return the number of tasks.
     */
    public int size() {
        return list.size();
    }

    /**
     * Returns the Tasks in this TaskList as a Stream.
     *
     * @return a Stream of Tasks.
     */
    public Stream<Task> toStream() {
        return IntStream.range(0, size()).mapToObj(list::get);
    }

    /**
     * Returns the Tasks in this TaskList as a List. It is not possible to modify the Tasks in the returned list,
     * and changes in the returned list will also not be reflected in this TaskList.
     *
     * @return a List of Tasks.
     */
    public List<Task> toList() {
        return new ArrayList<>(list);
    }
}
