package duke;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> mainList;

    public TaskList(ArrayList<Task> mainList) {
        this.mainList = mainList;
    }

    public TaskList() {
        this.mainList = new ArrayList<Task>();
    }

    /**
     * Adds task to TaskList.
     * @param task Task to add.
     */
    public void add(Task task) {
        assert task != null : "Task cannot be null";
        this.mainList.add(task);
    }

    /**
     * Removes task from TaskList.
     * @param index Index of task in TaskList to remove.
     */
    public void remove(int index) {
        assert index >= 0 : "Index of task cannot be smaller than 0";
        this.mainList.remove(index);
    }

    /**
     * Gets the task in the TaskList.
     * @param index Index of task in TaskList to get.
     * @return Task.
     */
    public Task get(int index) {
        assert index >= 0 : "Index of task cannot be smaller than 0";
        return this.mainList.get(index);
    }

    /**
     * Gets the size of TaskList.
     * @return Size of TaskList.
     */
    public int size() {
        return this.mainList.size();
    }
}
