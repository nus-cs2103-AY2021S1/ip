package task;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>TaskList class</h1>
 * List of all the current tasks that the user has inputted.
 */
public class TaskList {
    private List<Task> list;
    private int totalTasks;

    /**
     * Creates a TaskList object.
     */
    public TaskList() {
        list = new ArrayList<>();
        totalTasks = 0;
    }

    /**
     * Overloaded method that takes in a list of tasks retrieved from storage.
     *
     * @param list List of tasks that the user inputted previously.
     */
    public TaskList(List<Task> list) {
        this.list = list;
        totalTasks = list.size();
    }

    public List<Task> getList() {
        return list;
    }

    public int getTotalTasks() {
        return totalTasks;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public Task get(int x) {
        return list.get(x);
    }

    /**
     * Add task to current list of tasks.
     *
     * @param t Task to be added.
     */
    public void add(Task t) {
        int tP = t.getPriority();
        if (tP == 0) {
            list.add(t);
        } else {
            for (int i = 0; i < totalTasks; i++) {
                if (list.get(i).getPriority() == 0) {
                    list.add(i, t);
                    break;
                } else if (list.get(i).getPriority() > tP) {
                    list.add(i, t);
                    break;
                }
            }
        }
        list.add(t);
        totalTasks++;
    }

    /**
     * Remove task from current list of tasks.
     *
     * @param x Index of task to be removed.
     */
    public void remove(int x) {
        list.remove(x);
        totalTasks--;
    }

    @Override
    public String toString() {
        return "Now you have " + totalTasks + " tasks in your list.";
    }
}
