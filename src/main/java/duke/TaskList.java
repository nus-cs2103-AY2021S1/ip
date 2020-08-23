package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of Tasks that uses an ArrayList
 */
public class TaskList {

    private List<Task> taskList;

    /**
     * Constructor for the TaskList Class
     * Creates a new ArrayList of Tasks
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructor for the TaskList Class
     * Takes in an existing lists of tasks
     *
     * @param taskList existing list of tasks
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds the task into the list
     *
     * @param task task to be added
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes the task from the list
     *
     * @param task task to be deleted
     */
    public void removeTask(Task task) {
        taskList.remove(task);
    }

    /**
     * Retrives the task from the list
     *
     * @param index index of the task
     * @return task
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Get the current list of tasks
     * @return list of tasks
     */
    public List<Task> getList() {
        return this.taskList;
    }
    
}
