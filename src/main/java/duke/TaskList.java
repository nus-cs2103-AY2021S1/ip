package duke;

import java.util.ArrayList;

/**
 * TaskList stores all the tasks entered by the user.
 */
public class TaskList {
    private ArrayList<Task> mainList;

    public TaskList(ArrayList<Task> mainList) {
        this.mainList = mainList;
    }

    public TaskList() {
        this.mainList = new ArrayList<Task>();
    }

    /**
     * Adds task to TaskList.
     *
     * @param task Task to add.
     */
    public void add(Task task) {
        this.mainList.add(task);
    }

    /**
     * Removes task from TaskList.
     *
     * @param index Index of task in TaskList to remove.
     */
    public void remove(int index) {
        this.mainList.remove(index);
    }

    /**
     * Removes task from TaskList
     *
     * @param task The specific task in TaskList to remove.
     */
    public void remove(Task task) {
        this.mainList.remove(task);
    }

    /**
     * Gets the task in the TaskList.
     *
     * @param index Index of task in TaskList to get.
     * @return Task.
     */
    public Task get(int index) {
        return this.mainList.get(index);
    }

    /**
     * Gets the size of TaskList.
     *
     * @return Size of TaskList.
     */
    public int size() {
        return this.mainList.size();
    }
}
