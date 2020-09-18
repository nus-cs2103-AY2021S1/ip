package duke;

import java.util.ArrayList;
import java.util.List;

import duke.task.PriorityLevel;
import duke.task.Task;

/**
 * Supports actions involving adding, removing and storing tasks in a list.
 */
public class TaskList {

    /**
     * List of tasks.
     */
    private List<Task> tasks;

    /**
     * Creates an empty TaskList object.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Creates a TaskList object using an existing list of tasks.
     * @param taskArr List of tasks.
     */
    public TaskList(List<Task> taskArr) {
        this.tasks = taskArr;
    }

    /**
     * Generates a TaskList object by loading a saved list and generates
     * an empty TaskList object otherwise.
     * @param storage Object containing filepath to saved list of tasks.
     * @return TaskList object.
     */
    public static TaskList generateTaskList(Storage storage) {
        try {
            return new TaskList(storage.load());
        } catch (Exception e) {
            System.out.println("Could not load file. Generating blank duke.task.Task List.");
            return new TaskList();
        }
    }

    /**
     * Adds a task to the task list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task at a specified index from the task list.
     * @param i Index of task to be removed.
     */
    public void removeTask(int i) {
        tasks.remove(i);
    }

    /**
     * Returns the task at a specific index of the task list.
     * @param i Index of task.
     * @return Task at specified index.
     */
    public Task getTask(int i) {
        return tasks.get(i);
    }

    /**
     * Returns the last task on the task list.
     * @return Task at the end of the task list.
     */
    public Task getMostRecentTask() {
        return tasks.get(tasks.size() - 1);
    }

    /**
     * Returns the size of the task list.
     * @return Size of task list.
     */
    public int getTaskListSize() {
        return tasks.size();
    }

    /**
     * Checks if the task list is empty or not.
     * @return Either true or false.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Sets the priority level of a task.
     * @param i Index of task.
     * @param priorityLevel Priority level of task.
     */
    public void setTaskPriorityLevel(int i, PriorityLevel priorityLevel) {
        tasks.get(i).setPriorityLevel(priorityLevel);
    }
}
