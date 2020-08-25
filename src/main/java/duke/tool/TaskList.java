package duke.tool;

import duke.tasks.Task;

import java.util.ArrayList;

/**
 * The class contains the task list.
 * It has operations to add/delete... tasks in the list.
 */
public class TaskList {
     public ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> tl) {
        this.taskList = tl;
    }

    /**
     * Construct an empty task list.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Add a task to the list.
     * @param t task waiting to be added.
     */
    public void add(Task t) {
        taskList.add(t);
    }

    /**
     * Delete a task in the list.
     * @param i position of the task.
     * @return
     */
    public Task delete(int i) {
        return taskList.remove(i);
    }

    /**
     * Mark a task in the list as done.
     * @param i position of the task.
     */
    public void markDone(int i) {
        taskList.get(i).markDone();
    }

    /**
     * Get a task from the list.
     * @param i position of the task.
     * @return
     */
    public Task getTask(int i) {
        return taskList.get(i);
    }

    /**
     * Count the number of tasks in the list.
     * @return
     */
    public int getNumOfTasks() {
        return taskList.size();
    }
}
