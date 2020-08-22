package main.java.duke.core;

import java.time.LocalDate;
import java.util.ArrayList;
import main.java.duke.task.Task;

/**
 * The TaskList class stores the tasks and manages the tasks using corresponding methods.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Creates a task list with the list of tasks.
     *
     * @param tasks The list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the task list.
     *
     * @param n The count of the task to be removed.
     */
    public Task remove(int n) {
        return tasks.remove(n);
    }

    /**
     * Marks a task as completed in the task list.
     *
     * @param n The count of the task to be mark as completed.
     */
    public void markAsCompleted(int n) {
        tasks.get(n).markAsCompleted();
    }

    /**
     * Determines whether the task list has the task.
     *
     * @param n The count of the task.
     */
    public boolean has(int n) {
        return n >= 0 && n < tasks.size();
    }

    /**
     * Finds the task on a specific date.
     *
     * @param localDate The specific date.
     * @return The task list with the corresponding tasks
     */
    public TaskList findTaskAt(LocalDate localDate) {
        ArrayList<Task> list = new ArrayList<>();
        for(int i = 0; i < tasks.size(); i = i + 1) {
            if(tasks.get(i).isAt(localDate)) {
                list.add(tasks.get(i));
            }
        }
        return new TaskList(list);
    }


    /**
     * Finds the task with the description containing the search key.
     *
     * @param key The search key.
     * @return The task list with the corresponding tasks
     */
    public TaskList findTaskWithDescription(String key) {
        ArrayList<Task> list = new ArrayList<>();
        for(int i = 0; i < tasks.size(); i = i + 1) {
            if(tasks.get(i).getDescription().contains(key)) {
                list.add(tasks.get(i));
            }
        }
        return new TaskList(list);
    }

    /**
     * Returns the corresponding representation of the task list.
     *
     * @return The corresponding representation of the task list.
     */
    @Override
    public String toString() {
        String result = "";
        for(int i = 0; i < tasks.size(); i = i + 1) {
            result = result + String.valueOf(i + 1) + "." + tasks.get(i).toString() + "\n";
        }
        return result;
    }

    /**
     * Gets the tasks in the task list.
     *
     * @return The tasks in the task list.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Gets the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Gets the specific task in the task list.
     *
     * @param count The count of the task in the task list.
     * @return The task in the task list.
     */
    public Task getTask(int count) {
        return tasks.get(count);
    }
}
