package duke;

import duke.task.Task;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents the part of Duke that stores the list of tasks.
 * Tasks can be added into, marked as done, or deleted from the list.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a task list containing tasks from the given list.
     *
     * @param tasks the list whose tasks are to be placed into this list.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Adds a task into the task list.
     *
     * @param task the task to be added into the task list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskNum the number of the task to be deleted from the task list.
     * @return the deleted task.
     */
    public Task deleteTask(int taskNum) {
        int index = taskNum - 1;
        return tasks.remove(index);
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return the list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Retrieves the number of tasks in the task list.
     *
     * @return the number of tasks in the task list.
     */
    public int getNumOfTasks() {
        return tasks.size();
    }

    /**
     * Retrieves a task from the task list.
     *
     * @param taskNum the number of the task to be retrieved from the task list.
     * @return the retrieved task.
     */
    public Task getTask(int taskNum) {
        int index = taskNum - 1;
        return tasks.get(index);
    }

    /**
     * Marks a task as done.
     *
     * @param taskNum the number of the task to be marked as done.
     */
    public void markAsDone(int taskNum) {
        int index = taskNum - 1;
        tasks.get(index).markAsDone();
    }

    /**
     * Retrieves the list of tasks in the task list which contain the keyword.
     *
     * @param keyword the keyword used to find the matching tasks.
     * @return the list of matching tasks.
     */
    public TaskList getMatchingTasks(String keyword) {
        TaskList matchingTasks = new TaskList();
        for (Task task : tasks) {
            if (task.toString().contains(keyword)) {
                matchingTasks.addTask(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Returns a string representation of the task list.
     *
     * @return a string representation of the task list.
     */
    @Override
    public String toString() {
        StringBuilder allTasks = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            int index = i - 1;
            allTasks.append("\n").append(i).append(".").append(tasks.get(index));
        }
        return allTasks.toString();
    }
}