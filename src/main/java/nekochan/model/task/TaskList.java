package nekochan.model.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import nekochan.exceptions.NekoDuplicateTaskException;
import nekochan.exceptions.NekoException;
import nekochan.exceptions.NekoSimilarTaskException;
import nekochan.exceptions.NekoTaskNotFoundException;
import nekochan.util.Messages;

/**
 * The {@code TaskList} class is an ordered collection of {@link Task}.
 * This class provides methods to add, delete and mark as complete {@code Task}.
 * This class also provides methods to list and remove all {@code Task}.
 * Implements the {@code Iterable} interface.
 */
public class TaskList {

    private List<Task> store;

    /**
     * Constructs an empty {@code TaskList} instance.
     */
    public TaskList() {
        store = new ArrayList<>();
    }

    /**
     * Constructs a deep copy of the supplied {@code TaskList} instance.
     *
     * @param copy the {@code TaskList} to copy.
     */
    public TaskList(TaskList copy) {
        store = new ArrayList<>();
        copy.store.forEach((task) -> store.add(task.deepCopy()));
    }

    /**
     * Constructs a {@code TaskList} containing {@code Task} of the specified {@code list}.
     *
     * @param list the list of {@code Task} whose contents are to be imported into this {@code TaskList}.
     */
    public TaskList(List<Task> list) {
        store = list;
    }

    /**
     * Adds the specified {@code Task} to the end of this {@code TaskList}.
     *
     * @param task the task to be inserted.
     * @return the task that was inserted.
     * @throws NekoSimilarTaskException if a similar copy of {@code task} exists.
     * @throws NekoDuplicateTaskException if a duplicate copy of {@code task} exists.
     */
    public TaskList addTask(Task task) throws NekoSimilarTaskException, NekoDuplicateTaskException {
        // Do not add task if there is an exact copy.
        boolean hasDuplicate = store.stream().anyMatch((x) -> x.equals(task));
        if (hasDuplicate) {
            throw new NekoDuplicateTaskException(Messages.DUPLICATE_TASK_ERROR);
        }

        // Add task but show an error if there is a similar task.
        List<Task> similars = store.stream().filter((x) -> x.isSimilar(task)).collect(Collectors.toList());
        boolean hasSimilars = similars.size() > 0;
        TaskList nextState = new TaskList(this);
        nextState.store.add(task);
        if (hasSimilars) {
            throw new NekoSimilarTaskException(Messages.SIMILAR_TASK_ERROR, similars);
        }
        return nextState;
    }

    /**
     * Marks the {@code Task} at the specified index in this {@code TaskList} as complete.
     *
     * @param index the index of the {@code Task} to mark as complete.
     * @return the {@code Task} that was marked as complete.
     * @throws NekoTaskNotFoundException if the index is out of range.
     */
    public TaskList markAsComplete(int index) throws NekoTaskNotFoundException {
        try {
            TaskList nextState = new TaskList(this);
            Task selected = store.get(index);
            nextState.store.set(index, selected.setCompleted());
            return nextState;
        } catch (IndexOutOfBoundsException e) {
            throw new NekoException(Messages.MISSING_TASK_ERROR);
        }
    }

    /**
     * Deletes the {@code Task} at the specified index in this {@code TaskList}.
     *
     * @param index the index of the {@code Task} to delete.
     * @return the {@code Task} that was deleted.
     * @throws NekoTaskNotFoundException if the index is out of range.
     */
    public TaskList deleteTask(int index) throws NekoTaskNotFoundException {
        try {
            TaskList nextState = new TaskList(this);
            nextState.store.remove(index);
            return nextState;
        } catch (IndexOutOfBoundsException e) {
            throw new NekoTaskNotFoundException(Messages.MISSING_TASK_ERROR);
        }
    }

    /**
     * Deletes all existing {@code Task} in this {@code TaskList}.
     */
    public TaskList clearList() {
        return new TaskList();
    }

    /**
     * Returns the number of {@code Task} in this {@code TaskList}.
     *
     * @return the number of {@code Task} in this {@code TaskList}.
     */
    public int getTaskCount() {
        return store.size();
    }

    /**
     * Returns a stream containing the tasks stored in this {@code TaskList}.
     *
     * @return a stream containing the tasks stored in this {@code TaskList}.
     */
    public Stream<Task> getStream() {
        return this.store.stream();
    }

    public Task getTask(int index) throws NekoTaskNotFoundException {
        try {
            return this.store.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new NekoTaskNotFoundException(Messages.MISSING_TASK_ERROR);
        }
    }
}
