package sparkles.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represent a TaskList object containing a list
 * that stores the tasks user inputs.
 */
public class TaskList {

    protected List<Task> storage = new ArrayList<>();

    public TaskList(List<Task> storage) {
        this.storage = storage;
    }

    public TaskList() {
    }

    public List<Task> getStorage() {
        return storage;
    }

    /**
     * Replace the list with input list.
     *
     * @param taskList list to be replaced
     */
    public void updateList(List<Task> taskList) {
        storage = new ArrayList<>(taskList);
    }

    public void add(Task task) {
        storage.add(task);
    }

    public void remove(Task task) {
        storage.remove(task);
    }

    public int getListSize() {
        return storage.size();
    }

    public boolean isEmpty() {
        return storage.isEmpty();
    }

    public boolean hasTheSameTask(Task task) {
        return storage.contains(task);
    }
}
