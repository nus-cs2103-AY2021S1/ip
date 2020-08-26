package duke.task;

import java.util.List;

/**
 * Encapsulates information and methods that relate to a list of tasks being tracked
 * for the user by DukeBot.
 */
public class TaskList {

    private final List<Task> taskList;

    /**
     * Creates and initialises a new TaskList object.
     *
     * @param taskList List of tasks that has been created by the user.
     */
    public TaskList(List<Task> taskList) {
        this.taskList =  taskList;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Number of tasks in the list.
     */
    public int getListSize() {
        return this.taskList.size();
    }

    /**
     * Retrieves the correct task in the list of tasks at the given index.
     *
     * @param index Zero based index of the desired task in the list.
     *
     * @return Task at the given index in the list.
     */
    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    /**
     * Returns the list of tasks created by the user.
     *
     * @return List of tasks.
     */
    public List<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Determines if the list of tasks is empty.
     *
     * @return True if the list of tasks is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.taskList.isEmpty();
    }

    /**
     * Adds a new task into the list of tasks.
     *
     * @param toAdd Task to be added.
     */
    public void addTask(Task toAdd) {
        this.taskList.add(toAdd);
    }

    /**
     * Removes a task from the list of tasks at the given index.
     *
     * @param index Zero based index of the task to be deleted.
     */
    public void removeTask(int index) {
        this.taskList.remove(index);
    }

}
