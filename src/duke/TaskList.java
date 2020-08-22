package duke;

import exception.NoSuchTaskException;

import task.Task;

import java.util.List;

/**
 * Represents a <code>TaskList</code> object that contains the task list.
 */
public class TaskList {
    List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> all() {
        return tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the task based on the index provided.
     *
     * @return the task based on the index provided.
     * @throws NoSuchTaskException If the index not available in the list.
     */
    public Task get(int zeroBasedIndex) throws NoSuchTaskException {
        try {
            return tasks.get(zeroBasedIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchTaskException();
        }
    }

    /**
     * Returns the task based on the index provided and removes it from the list.
     *
     * @return the task based on the index provided.
     * @throws NoSuchTaskException If the index not available in the list.
     */
    public Task remove(int zeroBasedIndex) throws NoSuchTaskException {
        try {
            return tasks.remove(zeroBasedIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchTaskException();
        }
    }

    public int size() {
        return tasks.size();
    }
}
