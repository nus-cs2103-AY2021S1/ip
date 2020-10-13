package duke;

import java.util.List;

/**
 * This class provides an simple functionality to store and operate the List of task in Duke.
 * It is essentially a wrapper class around the List of Task
 */
public class TaskList {

    private List<Task> lstOfTask;

    /**
     * The constructor for the TaskList object
     *
     * @param lstOfTask the list of task that is used to store tasks from duke
     */
    public TaskList(List<Task> lstOfTask) {
        this.lstOfTask = lstOfTask;
    }

    /**
     * Thie method gives the number of tasks in the list.
     *
     * @return returns size of task list.
     */
    public int getNumOfTask() {
        return lstOfTask.size();
    }

    public List<Task> getLstOfTask() {
        return this.lstOfTask;
    }

    /**
     * This method adds task to the list.
     *
     * @param task The task to be added to the list.
     */
    public void add(Task task) {
        this.lstOfTask.add(task);
    }

    /** This method returns a task from the list based on the index
     *
     * @param index the index of the task in the list
     * @return Task that correspond to the index
     */
    public Task getTask(int index) {
        return this.lstOfTask.get(index);
    }

}
