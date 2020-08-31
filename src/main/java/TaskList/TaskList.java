package TaskList;

import java.util.ArrayList;
import Tasks.*;

/**
 * TaskList.TaskList is the list of tasks and handles the list functions (get, add, remove, size) in the program and the storage.
 */
public class TaskList {
    private Storage storage;
    private ArrayList<Task> taskList;

    /**
     * The constructor that takes in TaskList.Storage for saving and loading data.
     */
    public TaskList(Storage storage) {
        this.storage = storage;
        taskList = storage.load();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Adds a task to list and updates saved data in storage.
     * @param task task to be added
     */
    public void addTask(Task task) {
        taskList.add(task);
        storage.addTask(task);
    }

    /**
     * Returns the number of tasks in list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Gets the task at the list index.
     * @param index list index
     * @return the task at list index
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Removes the task at the list index.
     * @param index list index
     * @return the task removed at list index
     */
    public Task remove(int index) {
        Task temp = taskList.remove(index);
        storage.reset();
        storage.addAll(this);
        return temp;
    }

    public void setCompleted(int whichTask) {
        taskList.get(whichTask).setCompleted();
        storage.reset();
        storage.addAll(this);
    }

    /**
     * Finds tasks containing search text.
     * @param searchText phrase to be searched for
     * @return the list of tasks containing the search text
     */
    public ArrayList<Task> find(String searchText) {
        ArrayList<Task> list = new ArrayList<>();
        for (Task t : taskList) {
            if (t.getName().contains(searchText)) {
                list.add(t);
            }
        }
        return list;
    }
}
