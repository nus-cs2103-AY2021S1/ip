package nekochan.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import nekochan.exceptions.NekoDuplicateTaskException;
import nekochan.exceptions.NekoException;
import nekochan.exceptions.NekoHistoryException;
import nekochan.exceptions.NekoSimilarTaskException;
import nekochan.exceptions.NekoStorageException;
import nekochan.model.task.Task;
import nekochan.model.task.TaskList;
import nekochan.storage.Storage;
import nekochan.util.Messages;

/**
 * The {@code NekoHistory} class records the state of the {@link TaskList} before changes are enacted on it.
 */
public class NekoHistory {

    private List<TaskList> histories;
    private int version;

    /**
     * Constructs a {@code NekoHistory} instance with the specified {@code TaskList} as the original state.
     *
     * @param original the original state of the {@code TaskList}.
     */
    public NekoHistory(TaskList original) {
        histories = new ArrayList<>();
        histories.add(original);
        version = 0;
    }

    private void saveState(TaskList nextState) {
        if (version != histories.size() - 1) {
            histories.subList(version + 1, histories.size()).clear();
        }
        histories.add(nextState);
        version++;
    }

    private void revertState() {
        histories.subList(version + 1, histories.size()).clear();
    }

    private TaskList getCurrent() {
        return histories.get(version);
    }

    /**
     * Saves the currently loaded state to file.
     *
     * @param storage the storage instance to use for saving the currently loaded {@code TaskList}.
     * @throws NekoStorageException if there was an exception when saving the file to disk.
     */
    public void save(Storage storage) throws NekoStorageException {
        storage.save(getCurrent());
    }

    /**
     * Creates a new state where the specified {@code task} has been added to the {@code TaskList}.
     *
     * @param task the {@code Task} to be added.
     * @return the added {@code Task}.
     * @throws NekoSimilarTaskException if there is a similar task in the currently loaded state.
     * @throws NekoDuplicateTaskException if there is a duplicate task in the currently loaded state.
     */
    public Task addTask(Task task) throws NekoSimilarTaskException, NekoDuplicateTaskException {
        try {
            TaskList nextState = getCurrent().addTask(task);
            saveState(nextState);
            return task;
        } catch (NekoDuplicateTaskException e) {
            revertState();
            throw e;
        }
    }

    /**
     * Creates a new state where the {@code Task} at the specified {@code index} has been marked as complete.
     *
     * @param index the index of the {@code Task} to mark as complete.
     * @return the {@code Task} that was marked as complete.
     */
    public Task markAsComplete(int index) {
        try {
            TaskList nextState = getCurrent().markAsComplete(index);
            saveState(nextState);
            return nextState.getTask(index);
        } catch (NekoException e) {
            revertState();
            throw e;
        }
    }

    /**
     * Creates a new state where the {@code Task} at the specified {@code index} has been deleted.
     *
     * @param index the index of the {@code Task} to delete.
     * @return the deleted {@code Task}.
     */
    public Task deleteTask(int index) {
        try {
            Task deletedTask = getCurrent().getTask(index);
            TaskList nextState = getCurrent().deleteTask(index);
            saveState(nextState);
            return deletedTask;
        } catch (NekoException e) {
            revertState();
            throw e;
        }
    }

    /**
     * Creates a new state where all {@code Task} have been removed from the {@code TaskList}.
     */
    public void clearAllTasks() {
        try {
            saveState(getCurrent().clearList());
        } catch (NekoException e) {
            revertState();
            throw e;
        }
    }

    /**
     * Returns a list of {@code Task} that matches the specified {@code searchParameter}.
     *
     * @param searchParameter the string for which to search for.
     * @return a list of {@code Task} that matches the specified {@code searchParameter}.
     */
    public List<Task> search(String searchParameter) {
        return getCurrent().getStream().filter((task) -> task.match(searchParameter)).collect(Collectors.toList());
    }

    /**
     * Returns a list of the string representations of all {@code Task} in the current state.
     *
     * @return a list of the string representations of all {@code Task} in the current state.
     */
    public List<String> listAll() {
        return getCurrent().getStream().map(Task::toString).collect(Collectors.toList());
    }

    /**
     * Returns the number of {@link Task} in the currently loaded {@code TaskList} state.
     *
     * @return the number of {@link Task} in the currently loaded {@code TaskList} state.
     */
    public int getCurrentTaskCount() {
        return getCurrent().getTaskCount();
    }

    /**
     * Sets the subsequent {@code TaskList} state as the currently loaded state.
     *
     * @throws NekoHistoryException if there are no subsequent states that can be loaded.
     */
    public void redo() throws NekoHistoryException {
        try {
            histories.get(++version);
        } catch (IndexOutOfBoundsException e) {
            version--;
            throw new NekoHistoryException(Messages.HISTORY_REDO_LIMIT_EXCEEDED);
        }
    }

    /**
     * Sets the previous {@code TaskList} state as the currently loaded state.
     *
     * @throws NekoHistoryException if there are no previous states that can be loaded.
     */
    public void undo() throws NekoHistoryException {
        try {
            histories.get(--version);
        } catch (IndexOutOfBoundsException e) {
            version++;
            throw new NekoHistoryException(Messages.HISTORY_UNDO_LIMIT_EXCEEDED);
        }
    }
}
