package bob;

import bob.exception.BobIndexOutOfBoundsException;
import bob.task.Task;

import java.util.ArrayList;

/** Encapsulates a list of tasks */
public class TaskList {

    /** An array list consisting of tasks. */
    private ArrayList<Task> list;

    /**
     * Constructs a TaskList by assigning the list parameter to a newly constructed
     * empty Task ArrayList.
     */
    public TaskList() {
        list = new ArrayList<Task>();
    }

    /**
     * Deletes a task from list with at index minus one.
     * @param index the index (plus one) of the task to be removed from the list.
     * @throws BobIndexOutOfBoundsException if a task on the list does not have the provided index.
     */
    public void delete(int index) throws BobIndexOutOfBoundsException {
        try {
            list.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new BobIndexOutOfBoundsException();
        }
    }

    /**
     * Adds a task to list.
     *
     * @param task the task to be added to the list.
     */
    public void add(Task task) {
        list.add(task);
    }

    /**
     * Retrieves the task at a provided index minus one from list.
     *
     * @param index the index (plus one) of the task to be retrieved from list.
     * @return
     * @throws BobIndexOutOfBoundsException
     */
    public Task get(int index) throws BobIndexOutOfBoundsException {
        try {
            return list.get(index - 1);
        } catch(IndexOutOfBoundsException e) {
            throw new BobIndexOutOfBoundsException();
        }
    }

    /**
     * Returns the size of TaskList.
     *
     * @return the size of list.
     */
    public int size() {
        return list.size();
    }

    /**
     * Returns a boolean value indicating if the TaskList is empty.
     *
     * @return true or false if the list is empty or not empty respectively.
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Returns a TaskList of tasks containing the provided key words or phrases.
     *
     * @param keyWord the key words or phrases that are being searched for in the TaskList.
     * @return a TaskList of tasks containing the provided key words or phrases.
     * @throws BobIndexOutOfBoundsException if trying to access an index that does not exist on TaskList.
     */
    public TaskList contains(String keyWord) throws BobIndexOutOfBoundsException {
        TaskList keyWordTasks = new TaskList();
        for (int i = 1; i < this.size(); i++) {
            if (this.get(i).toString().contains(keyWord)) {
                keyWordTasks.add(this.get(i));
            }
        }
        return keyWordTasks;
    }

    public void deleteAll() {
        list.clear();
    }

    public void markAllDone() {
        for (Task task: list) {
            task.markAsDone();
        }
    }
}
