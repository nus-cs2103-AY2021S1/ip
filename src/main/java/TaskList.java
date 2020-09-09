package main.java;

import java.util.ArrayList;

/**
 * Represents a container that holds the tasks
 */
public class TaskList {
    ArrayList<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * gives the size of the arraylist
     * @return the size of the task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * returns a task corresponding to the index given
     * @param i index of the wanted task
     * @return the task corresponding to the index i
     */
    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * adds a new task to the task list
     * @param task a new task to be added to the task list
     */
    protected void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list
     * @param i index of the task to be removed
     */
    protected void remove(int i) {
        tasks.remove(i);
    }
}
