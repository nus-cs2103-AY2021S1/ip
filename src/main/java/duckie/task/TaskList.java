package duckie.task;

import java.util.ArrayList;

import duckie.exception.*;
import duckie.Ui;

/**
 * Contains all the saved tasks in a List form
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Instantiate an empty list
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Instantiate a TaskList with pre-saved tasks
     * @param tasks ArrayList containing pre-saved tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Return the ArrayList containing the current tasks
     * @return ArrayList containing current tasks
     */
    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    /**
     * Return the actual Index of a task in the ArrayList
     * @param task Task required
     * @return Integer index value
     */
    public int getIndex(Task task) {
        return tasks.indexOf(task);
    }

    /**
     * Add a Task to TaskList
     * @param t1 Task to be added
     */
    public void addTask(Task t1) {
        tasks.add(t1);
    }

    /**
     * Delete a Task from the TaskList
     * @param ind Index of the Task in ArrayList
     */
    public void deleteTask(int ind) {
        tasks.remove(ind);
    }

    /**
     * Delete all Tasks in the TaskList
     */
    public void deleteAllTasks() {
        tasks.clear();
    }

    /**
     * Check the Task in the TaskList as done
     * @param ind Index of the Task in ArrayList
     */
    public void markTaskDone(int ind) {
        Task t1 = tasks.get(ind);
        t1.markDone();
    }

    /**
     * Display the current tasks in the TaskList
     * @throws DuckieException If list is empty
     */
    public void displayList() throws DuckieException {
        if (tasks.size() == 0) {
            throw new DuckieNoListException();
        }

        Ui.displayListReply(tasks);
    }

    public ArrayList<Task> filterList(String keyword) {
        ArrayList<Task> filteredList = new ArrayList<>();
        for (Task t1 : tasks) {
            String description = t1.getDescription();
            if (description.toLowerCase().indexOf(keyword) != -1) {
                filteredList.add(t1);
            }
        }
        return filteredList;
    }
}
