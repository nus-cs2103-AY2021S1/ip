package src.main.java.duke.data.task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * A list of tasks that does not allow null elements or duplicates.
 *
 */
public class TaskList implements Iterable<Task> {

    private final List<Task> internalList = new ArrayList<>();

    /**
     * Constructs empty person list.
     */
    public TaskList() {}

    /**
     * Constructs a list from the items in the given collection.
     * @param tasks a collection of persons
     */
    public TaskList(Collection<Task> tasks) {
        internalList.addAll(tasks);
    }

    /**
     * Constructs a shallow copy of the list.
     * @param source the source of the task list
     */
    public TaskList(TaskList source) {
        internalList.addAll(source.internalList);
    }

    /**
     * Returns an unmodifiable java List view with elements cast as immutable {@link Task}s.
     * For use with other methods/libraries.
     * Any changes to the internal list/elements are immediately visible in the returned list.
     * @return a list of task that is immmutable.
     */
    public List<Task> immutableListView() {
        return Collections.unmodifiableList(internalList);
    }

    /**
     * Returns an unmodifiable java List view with elements cast as immutable {@link Task}s.
     * For use with other methods/libraries.
     * Any changes to the internal list/elements are immediately visible in the returned list.
     * @param searchString String to be searched in the task
     * @return a list of tasks that is filtered
     */
    public List<Task> filteredView(String searchString) {
        List<Task> filteredList = internalList
                .stream()
                .filter(task -> task.description.contains(searchString)).collect(Collectors.toList());
        return Collections.unmodifiableList(filteredList);
    }

    /**
     * Checks if the list contains an equivalent person as the given argument.
     * defines a weaker notion of equality.
     * @param toCheck the task to be check
     * @return return true if the list contains the task
     */
    public boolean contains(Task toCheck) {
        for (Task p : internalList) {
            if (p == toCheck) {
                return true;
            }
        }
        return false;
    }


    /**
     * Adds a task to the list.
     * @param task task to be added
     */
    public void add(Task task) {
        internalList.add(task);
    }

    /**
     * Removes the equivalent task from the list.
     * @param index index to remove the task
     * @throws TaskNotFoundException if no such task could be found in the list.
     */
    public void remove(int index) throws TaskNotFoundException {
        final Task taskNotFound = internalList.remove(index);
        if (taskNotFound == null) {
            throw new TaskNotFoundException();
        }
    }

    /**
     * Gets the task at a certain index from the list.
     * @param index index of the task
     * @return return a task
     */
    public Task getTask(int index) {
        return internalList.get(index - 1);
    }

    /**
     * Gets the task list iterator.
     * @return an iterator that represents the task list.
     */
    @Override
    public Iterator<Task> iterator() {
        return internalList.iterator();
    }

    /**
     * Gets the task list iterator.
     * @return an iterator that represents the task list.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TaskList tasks = (TaskList) o;
        return Objects.equals(internalList, tasks.internalList);
    }

    /**
     * Gets the hashcode of taskList.
     * @return the hashcode of the tasklist
     */
    @Override
    public int hashCode() {
        return Objects.hash(internalList);
    }

    /**
     * Signals that an operation targeting a specified task in the list would fail because
     * there is no such matching task in the list.
     */
    public static class TaskNotFoundException extends Exception {}
}
