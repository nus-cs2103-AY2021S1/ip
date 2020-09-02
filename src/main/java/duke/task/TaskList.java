package duke.task;

import java.util.ArrayList;


/**
 * Encapsulates a list of tasks.
 */
public class TaskList {

    private ArrayList<Task> al;

    /**
     * Initialises an empty task list.
     */
    protected TaskList() {
        al = new ArrayList<>();
    }

    /**
     * Initialises a task list using an existing ArrayList of tasks.
     * @param al ArrayList of tasks.
     */
    protected TaskList(ArrayList<Task> al) {
        this.al = al;
    }

    protected int length() {
        return al.size();
    }

    protected Task delete(int index) {
        return al.remove(index);
    }

    public void add(Task t) {
        al.add(t);
    }

    public Task get(int index) {
        return al.get(index);
    }

    @Override
    public String toString() {
        String s = "1. " + al.get(0);
        for (int i = 1; i < al.size(); i++) {
            s = s + "\n" + (i + 1) + ". " + al.get(i);
        }
        return s;
    }
}
