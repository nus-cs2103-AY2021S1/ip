package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    /**
     * TaskList constructor
     */
    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     * TaskList constructor
     *
     * @param list contents of the TaskList
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Returns TaskList's size.
     *
     * @return this TaskList's size.
     */
    public int size() {
        return list.size();
    }

    public Task get(int index) {
        return list.get(index);
    }

    public ArrayList<Task> getTasks() {
        return list;
    }

    public void addTask(Task task) {
        list.add(task);
    }

    public void removeTask(int index) {
        list.remove(index);
    }
}