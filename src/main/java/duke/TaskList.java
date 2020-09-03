package duke;

import java.util.ArrayList;

/**
 *  TaskList Contains the task list e.g. it has operations to add/delete tasks in the list
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor of a new TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor of an existing Tasklist.
     * 
     * @param tasks Takes in an existing TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds tasks in list of tasks.
     * 
     * @param task Takes in a task object.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Gets the size of the list of tasks.
     * 
     * @return the size of the list.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Gets the task object according to the taskNumber.
     * 
     * @param taskNumber The task number is according to the user input.
     * @return a Task object.
     */
    public Task get(int taskNumber) {
        return tasks.get(taskNumber);
    }

    /**
     * Returns the list of tasks.
     * 
     * @return the list of tasks.
     */
    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * Deletes the task from the list of tasks.
     * 
     * @param taskNumber The task number is according to the user input.
     */
    public void deleteTask(int taskNumber) {
        tasks.remove(taskNumber);
    }
    
}
