package duke.tool;

import java.util.ArrayList;

import duke.tasks.Task;

/**
 * The class contains the task list.
 * It has operations to add/delete... tasks in the list.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> tl) {
        this.taskList = tl;
    }

    /**
     * Constructs an empty task list.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Adds a task to the list.
     *
     * @param t task waiting to be added.
     */
    public void add(Task t) {
        taskList.add(t);
    }

    /**
     * Deletes a task in the list.
     *
     * @param i position of the task.
     * @return the task being deleted.
     */
    public Task delete(int i) {
        return taskList.remove(i);
    }

    /**
     * Marks a task in the list as done.
     *
     * @param i position of the task.
     */
    public void markDone(int i) {
        taskList.get(i).markDone();
    }

    /**
     * Gets a task from the list.
     *
     * @param i position of the task.
     * @return the ith task.
     */
    public Task getTask(int i) {
        return taskList.get(i);
    }

    /**
     * Counts the number of tasks in the list.
     *
     * @return the number of tasks in the list.
     */
    public int getNumOfTasks() {
        return taskList.size();
    }
}
