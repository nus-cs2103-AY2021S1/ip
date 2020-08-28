package tasks;

import java.util.ArrayList;

/**
 * Represents a data structure to contain an ArrayList of Task objects.
 */

public class TaskList {

    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Adds a task to the ArrayList of tasks
     * @param item The task to be added
     */
    public void addItem(Task item) {
        list.add(item);
    }

    public int size() {
        return list.size();
    }

    public ArrayList<Task> getTasks() {
        return list;
    }

    /**
     * Enables the TaskList object to be printed in a easily readable format for the user.
     * @return A representation of the TaskList in a form easily readable by a user.
     */
    public String toString() {
        StringBuilder ls = new StringBuilder();
        int counter = 1;
        for (Task task : list) {
            ls.append(counter == 1 ? "" : "\n").append(counter).append(".").append(task);
            counter++;
        }
        return ls.toString();
    }

    /**
     * Writes the TaskList object in a format easily readable by a Storage object.
     * @return A representation of the TaskList in a form easily readable by a Storage object.
     */
    public String writeString() {
        StringBuilder ls = new StringBuilder();
        int counter = 1;
        for (Task task : list) {
            ls.append(task.writeString()).append(counter != list.size() ? "\n" : "");
            counter++;
        }
        return ls.toString();
    }
}
