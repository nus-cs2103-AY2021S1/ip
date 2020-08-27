package duke;

import task.Task;

import java.util.ArrayList;

/**
 * TaskList contains the task list.
 *
 * @author Joshua
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Creates TaskList with the task list.
     *
     * @param taskList the task list that is given.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Creates TaskList with a new empty task list.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task the task to be added.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Returns the current task list.
     *
     * @return the task list as an array list.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }
}
