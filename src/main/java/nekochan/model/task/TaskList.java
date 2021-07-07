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
 * The {@code TaskList} class is an ordered and immutable collection of {@link Task}.
 * This class provides methods to add, delete and mark as complete {@code Task}.
 * This class also provides methods to list and remove all {@code Task}.
 */
public class TaskList {

    private final List<Task> tasks;

    /**
     * Constructs an empty {@code TaskList} instance.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a new copy of the supplied {@code TaskList} instance.
     *
     * @param copy the {@code TaskList} to copy.
     */
    public TaskList(TaskList copy) {
        tasks = new ArrayList<>();
        copy.tasks.forEach((task) -> tasks.add(task.deepCopy()));
    }

    /**
     * Constructs a {@code TaskList} containing {@code Task} of the specified {@code list}.
     *
     * @param list the list of {@code Task} whose contents are to be imported into this {@code TaskList}.
     */
    public TaskList(List<Task> list) {
        tasks = list;
    }

    /**
     * Adds the specified {@code task} to the end of this {@code TaskList}.
     * Returns a new immutable {@code TaskList} instance containing the added {@code task}.
     *
     * @param task the task to be added.
     * @return a new immutable {@code TaskList} instance containing the added {@code task}.
     * @throws NekoSimilarTaskException if a similar copy of {@code task} exists.
     * @throws NekoDuplicateTaskException if a duplicate copy of {@code task} exists.
     */
    public TaskList addTask(Task task) throws NekoSimilarTaskException, NekoDuplicateTaskException {
        // Do not add task if there is an exact copy.
        boolean hasDuplicate = tasks.stream().anyMatch((x) -> x.isDuplicate(task));
        if (hasDuplicate) {
            throw new NekoDuplicateTaskException(Messages.DUPLICATE_TASK_ERROR);
        }

        // Add task but throw an error if there is a similar task.
        List<Task> similars = tasks.stream().filter((x) -> x.isSimilar(task)).collect(Collectors.toList());
        boolean hasSimilars = similars.size() > 0;

        TaskList nextState = new TaskList(this);
        nextState.tasks.add(task);

        if (hasSimilars) {
            throw new NekoSimilarTaskException(Messages.SIMILAR_TASK_ERROR, similars, nextState);
        }
        return nextState;
    }

    /**
     * Returns a new immutable {@code TaskList} instance containing the {@code Task} at the specified {@code index}
     * marked as completed.
     *
     * @param index the index of the {@code Task} to mark as complete.
     * @return a new immutable {@code TaskList} instance containing the {@code Task} at the specified {@code index}
     *         marked as completed.
     * @throws NekoTaskNotFoundException if the index is out of range.
     */
    public TaskList markAsComplete(int index) throws NekoTaskNotFoundException {
        try {
            TaskList nextState = new TaskList(this);
            Task selected = tasks.get(index);
            nextState.tasks.set(index, selected.setCompleted());
            return nextState;
        } catch (IndexOutOfBoundsException e) {
            throw new NekoException(Messages.MISSING_TASK_ERROR);
        }
    }

    /**
     * Returns a new immutable {@code TaskList} instance where the {@code Task} at the specified {@code index} has been
     * deleted.
     *
     * @param index the index of the {@code Task} to delete.
     * @return a new immutable {@code TaskList} instance where the {@code Task} at the specified {@code index} has been
     *         deleted.
     * @throws NekoTaskNotFoundException if the index is out of range.
     */
    public TaskList deleteTask(int index) throws NekoTaskNotFoundException {
        try {
            TaskList nextState = new TaskList(this);
            nextState.tasks.remove(index);
            return nextState;
        } catch (IndexOutOfBoundsException e) {
            throw new NekoTaskNotFoundException(Messages.MISSING_TASK_ERROR);
        }
    }

    /**
     * Returns a new empty {@code TaskList} instance.
     *
     * @return a new empty {@code TaskList} instance.
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
        return tasks.size();
    }

    /**
     * Returns a stream containing the tasks stored in this {@code TaskList}.
     *
     * @return a stream containing the tasks stored in this {@code TaskList}.
     */
    public Stream<Task> getStream() {
        return this.tasks.stream();
    }

    /**
     * Returns the {@code Task} at the specified {@code index} in this {@code TaskList}.
     *
     * @param index the index of the {@code Task}.
     * @return the {@code Task} at the specified {@code index}.
     * @throws NekoTaskNotFoundException if the {@code index} is out of range.
     */
    public Task getTask(int index) throws NekoTaskNotFoundException {
        try {
            return this.tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new NekoTaskNotFoundException(Messages.MISSING_TASK_ERROR);
        }
    }
}
