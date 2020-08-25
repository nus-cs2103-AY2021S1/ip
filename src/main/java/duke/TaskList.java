package duke;

import java.util.ArrayList;

/**
 * Encapsulates the duke.TaskList containing the tasks.
 * @param <Task> the duke.Task objects
 */
public class TaskList<Task> {
    ArrayList<Task> taskList;

    /**
     * Instantiates new duke.TaskList object to store Tasks.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Instantiates duke.TaskList Object to store existing Tasks.
     * @param taskList
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Check if duke.TaskList is empty.
     * @return if taskList is empty or not
     */
    boolean isEmpty() {
        return taskList.isEmpty();
    }

    /**
     * Checks duke.TaskList size.
     * @return the size of the duke.TaskList
     */
    int size() {
        return taskList.size();
    }

    /**
     * Gets a task at the given position.
     * @param position the position of the task
     * @return the task at the given position
     */
    Task get(int position) {
        return taskList.get(position);
    }

    /**
     * Replaces a new duke.Task at given position in line with immutability.
     * @param position the position of the task
     * @param newTask the new task to input
     */
    void set(int position, Task newTask) {
        taskList.set(position, newTask);
    }

    /**
     * Adds a duke.Task to duke.TaskList.
     * @param taskToDo the task to be added
     */
    void add(Task taskToDo) {
        taskList.add(taskToDo);
    }

    /**
     * Removes a duke.Task from the duke.TaskList.
     * @param taskToRemove the task to be removed
     */
    void remove(Task taskToRemove) {
        taskList.remove(taskToRemove);
    }

    /**
     * Exports the duke.TaskList as an arrayList for easier processing.
     * @return the arrayList containing the Tasks
     */
    ArrayList<Task> exportList() {
        return taskList;
    }

    /**
     * Clears the entire TaskList
     */
    void clearList() {
        taskList = new ArrayList<>();
    }
}
