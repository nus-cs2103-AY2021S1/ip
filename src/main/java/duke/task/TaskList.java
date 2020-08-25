package duke.task;

import duke.task.Task;

import java.util.List;
import java.util.ArrayList;

/**
 * Encapsulates a list of tasks.
 */
public class TaskList {
    /**
     * The list of tasks.
     */
    private final List<Task> tasks;

    /**
     * Initializes a new task list object using an existing List.
     *
     * @param tasks existing list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Initializes a new task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Gets a given task in the task list.
     *
     * @param id the ID of the task to get.
     * @return Task with the given ID.
     */
    public Task getTask(int id) {
        return this.tasks.get(id - 1);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task the task to add.
     * @return <code>true</code> if task is added, <code>false</code> if not.
     */
    public boolean addTask(Task task) {
        return this.tasks.add(task);
    }

    /**
     * Deletes a task in a task list.
     *
     * @param id Task to be deleted.
     * @return Task that was deleted.
     */
    public Task deleteTask(int id) {
        return this.tasks.remove(id -1);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return number of tasks in the task list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns a task list of all the tasks containing the specified keyword.
     *
     * @param keyword User input keyword.
     * @return Task(s) that contain the keyword.
     */
    public TaskList findTask(String keyword) {
        TaskList filtered = new TaskList();
        this.tasks.forEach((task) -> {
            if (task.containsKeyword(keyword)) {
                filtered.addTask(task);
            }
        });
        return filtered;
    }

    /**
     * Returns a string representation of all the tasks in the task list.
     * Each task has a number of it's index + 1 and is separated by a newline.
     *
     * @return String comprising of all the tasks.
     */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < this.tasks.size(); i++) {
            out.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
        }

        return out.toString();
    }
}
