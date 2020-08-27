package duckie.task;

import java.util.ArrayList;

import duckie.exception.*;
import duckie.Ui;

/**
 * Contains all the saved tasks in a List form
 */
public class TaskList {
    private ArrayList<Task> lst;

    /**
     * Instantiate an empty list
     */
    public TaskList() {
        this.lst = new ArrayList<>();
    }

    /**
     * Instantiate a TaskList with pre-saved tasks
     * @param lst ArrayList containing pre-saved tasks
     */
    public TaskList(ArrayList<Task> lst) {
        this.lst = lst;
    }

    /**
     * Return the ArrayList containing the current tasks
     * @return ArrayList containing current tasks
     */
    public ArrayList<Task> getTaskList() {
        return lst;
    }

    /**
     * Return the actual Index of a task in the ArrayList
     * @param task Task required
     * @return Integer index value
     */
    public int getIndex(Task task) {
        return lst.indexOf(task);
    }

    /**
     * Add a Task to TaskList
     * @param t1 Task to be added
     */
    public void addTask(Task t1) {
        lst.add(t1);
    }

    /**
     * Delete a Task from the TaskList
     * @param ind Index of the Task in ArrayList
     */
    public void deleteTask(int ind) {
        lst.remove(ind);
    }

    /**
     * Delete all Tasks in the TaskList
     */
    public void deleteAllTasks() {
        lst.clear();
    }

    /**
     * Check the Task in the TaskList as done
     * @param ind Index of the Task in ArrayList
     */
    public void markTaskDone(int ind) {
        Task t1 = lst.get(ind);
        t1.markDone();
    }

    /**
     * Display the current tasks in the TaskList
     * @throws DuckieException If list is empty
     */
    public void displayList() throws DuckieException {
        if (lst.size() == 0) {
            throw new DuckieNoListException();
        }

        Ui.displayListReply(lst);
    }
}
