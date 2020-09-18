package seedu.duke;

import java.util.ArrayList;

/**
 * A list of tasks.
 */
public class TaskList {
    ArrayList<Task> taskList;

    /**
     * Constructor.
     * @param list A list of tasks to be managed.
     */
    public TaskList(ArrayList<Task> list) {
        this.taskList = list;
    }

    /**
     * Add a task to the task list.
     * @param task task to be added.
     */
    public void add(Task task) {
        taskList.add(task);
    }

}
