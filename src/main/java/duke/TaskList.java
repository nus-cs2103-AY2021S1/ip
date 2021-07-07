package duke;

import java.util.ArrayList;
import java.util.List;

import duke.task.Task;

/**
 * Represents a list which stores tasks.
 */
public class TaskList {
    /** Represents the tasks in the specified task list. */
    private final List<Task> tasks;

    /**
     * Generates an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a task list with the specified tasks.
     *
     * @param tasks represents the tasks to be stored in the task list.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Adds a task into the task list.
     *
     * @param task is the task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param taskNum is the index of the task in the task list.
     * @return is the deleted task.
     */
    public Task deleteTask(int taskNum) {
        assert (taskNum > 0 && taskNum <= this.tasks.size()) : "taskNum cannot be less than 1 or more than task size.";
        Task deletedTask = this.tasks.get(taskNum - 1);
        this.tasks.remove(taskNum - 1);
        return deletedTask;
    }

    /**
     * Marks a specified task from the task list as done.
     *
     * @param taskNum is the index of the task to be marked as completed.
     */
    public void markAsDone(int taskNum) {
        assert (taskNum > 0 && taskNum <= this.tasks.size()) : "taskNum cannot be less than 1 or more than task size.";
        this.tasks.get(taskNum - 1).markAsDone();
    }

    /**
     * Retrieves a specified task from the task list.
     *
     * @param taskNum is the index of the task to be retrieved.
     * @return the specified task from the list.
     */
    public Task getTask(int taskNum) {
        return this.tasks.get(taskNum - 1);
    }

    /**
     * Makes a list of all the tasks from the task list.
     *
     * @return a list of all the tasks in the task list.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Gets the number of tasks in the task list.
     *
     * @return the number of tasks.
     */
    public int getNumOfTasks() {
        return this.tasks.size();
    }

    /**
     * Gets a task list of tasks containing the keyword.
     *
     * @param keyword is the keyword used to find the tasks.
     * @return a task list of tasks containing the keyword.
     */
    public TaskList getMatchingTasks(String keyword) {
        TaskList taskList = new TaskList();
        for (Task task : this.tasks) {
            if (task.toString().contains(keyword)) {
                taskList.addTask(task);
            }
        }
        return taskList;
    }

    /**
     * Checks for any duplicates when adding task to task list.
     *
     * @param task is the task to check duplicates for in the task list.
     * @return true if duplicate is detected in task list and false otherwise.
     */
    public boolean isDuplicate(Task task) {
        for (Task t : tasks) {
            if (t.equals(task)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Creates a string representation of the task list.
     *
     * @return a string representation of the task list.
     */
    @Override
    public String toString() {
        StringBuilder orderedList = new StringBuilder();
        for (int i = 1; i <= this.tasks.size(); i++) {
            orderedList.append("\n  ").append(i).append(". ").append(this.tasks.get(i - 1));
        }
        return orderedList.toString();
    }
}
