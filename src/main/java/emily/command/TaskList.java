package main.java.emily.command;

import main.java.emily.task.Task;

import java.util.ArrayList;

/**
 * Modifies the arraylist of tasks and contains the most updated list
 * of tasks
 */
public class TaskList {
    ArrayList<Task> store = new ArrayList<>();

    TaskList(ArrayList<Task> store) {
        this.store = store;
    }

    public ArrayList<Task> retrieve() {
        return this.store;
    }

    public void add(Task t) {
        store.add(t);
    }

    public void delete(int index) {
        store.remove(index);

    }
}
