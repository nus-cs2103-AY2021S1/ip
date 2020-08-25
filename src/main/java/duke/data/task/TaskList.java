package src.main.java.duke.data.task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


/**
 * A list of persons. Does not allow null elements or duplicates.
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
     */
    public TaskList(TaskList source) {
        internalList.addAll(source.internalList);
    }

    /**
     * Returns an unmodifiable java List view with elements cast as immutable {@link Task}s.
     * For use with other methods/libraries.
     * Any changes to the internal list/elements are immediately visible in the returned list.
     */
    public List<Task> immutableListView() {
        return Collections.unmodifiableList(internalList);
    }

    /**
     * Checks if the list contains an equivalent person as the given argument.
     * defines a weaker notion of equality.
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
     * Adds a person to the list.
     */
    public void add(Task toAdd) {
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent person from the list.
     *
     * @throws TaskNotFoundException if no such person could be found in the list.
     */
    public void remove(int toRemove) throws TaskNotFoundException {
        final Task taskNotFound = internalList.remove(toRemove);
        if (taskNotFound == null) {
            throw new TaskNotFoundException();
        }
    }

    public Task getTask(int index) {
        return internalList.get(index - 1);
    }

    @Override
    public Iterator<Task> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskList // instanceof handles nulls
                && this.internalList.equals(((TaskList) other).internalList));
    }

    /**
     * Signals that an operation targeting a specified task in the list would fail because
     * there is no such matching task in the list.
     */
    public static class TaskNotFoundException extends Exception {}
}
