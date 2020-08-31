package main.java.emily.command;

import main.java.emily.task.Task;

import java.util.ArrayList;

/**
 * Modifies the arraylist of tasks and contains the most updated list
 * of tasks
 */
public class TaskList {
    private ArrayList<Task> store = new ArrayList<>();

    TaskList(ArrayList<Task> store) {
        this.store = store;
    }

    public ArrayList<Task> getStore() {
        return this.store;
    }

    public void add(Task t) {
        store.add(t);
    }

    /**
     * Removes the corresponding task based on the index
     *
     * @param index provided by the user
     */
    public void delete(int index) {
        store.remove(index);
    }

    /**
     * Looks through the list of tasks.
     * Provides a list of tasks containing the given keyword.
     *
     * @param keyword matches the task description
     * @return a list of task the user is finding
     */
    public ArrayList<Task> finder(String keyword) {
        ArrayList<Task> ls = new ArrayList<>();
        for (Task t : this.store) {
            String d = t.getDescription();
            if (d.contains(keyword)) {
                ls.add(t);
            }
        }
        return ls;
    }
}
