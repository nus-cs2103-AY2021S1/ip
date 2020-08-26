package sparkles.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represent a TaskList object containing a list
 * that stores the tasks user inputs.
 */
public class TaskList {

    protected List<Task> storage = new ArrayList<>();

    public TaskList() {
    }

    public List<Task> getStorage() {
        return storage;
    }

    public TaskList(List<Task> storage) {
        this.storage = storage;
    }

    /**
     * Replace the list with input list.
     * @param taskList
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

    public int listSize() {
        return storage.size();
    }
}
