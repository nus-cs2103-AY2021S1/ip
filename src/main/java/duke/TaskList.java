package duke;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import duke.task.Task;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    /** The tasks in this task list. */
    private final List<Task> tasks;

    /**
     * Creates an empty task list with no tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a task list with the given tasks.
     *
     * @param tasks The tasks to be added.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Gets the number of tasks in this task list.
     *
     * @return The number of tasks in this task list.
     */
    public int getNumberOfTasks() {
        return this.tasks.size();
    }

    /**
     * Gets the task at the specified position in this task list.
     *
     * @param taskNumber The position of the task in this task list.
     * @return The task at the specified position in this task list.
     */
    public Task getTask(int taskNumber) {
        return this.tasks.get(taskNumber - 1);
    }

    /**
     * Gets all the tasks in this task list as a list.
     *
     * @return The list of all the tasks.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Gets all the tasks in this task list on the specified date as a task list.
     *
     * @param date The date of all the tasks to get.
     * @return The task list with all the tasks on the specified date.
     * @throws DukeException If there were some problems with getting the tasks on the date.
     */
    public TaskList getTaskListOnDate(LocalDate date) throws DukeException {
        TaskList result = new TaskList();
        for (Task task : tasks) {
            if (task.hasDate() && task.getDate().equals(date)) {
                result.addTask(task);
            }
        }
        return result;
    }

    /**
     * Gets all the tasks in this task list that contains the keyword as a task list.
     *
     * @param keyword The string that the tasks you are finding should contain.
     * @return The task list with all the tasks that contain the keyword.
     */
    public TaskList getTasksWithKeyword(String keyword) {
        TaskList result = new TaskList();
        for (Task task : tasks) {
            if (task.toString().contains(keyword)) {
                result.addTask(task);
            }
        }
        return result;
    }

    /**
     * Adds a task to this task list.
     *
     * @param task The task to be added to this task list.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task from this task list.
     *
     * @param taskNumber The position of the task to be deleted.
     * @return The task that was deleted.
     */
    public Task deleteTask(int taskNumber) {
        Task removedTask = this.tasks.get(taskNumber - 1);
        this.tasks.remove(taskNumber - 1);
        return removedTask;
    }

    /**
     * Marks the task with the specified position in this task list as done.
     *
     * @param taskNumber The position of the task to be marked as done.
     */
    public void markTaskAsDone(int taskNumber) {
        this.tasks.get(taskNumber - 1).markAsDone();
    }

    /**
     * Returns a string representation of this task list.
     *
     * @return A string representation of this task list.
     */
    @Override
    public String toString() {
        StringBuilder numberedList = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            // e.g.     1.[D]✘ Finish project (by: Jan 23 2012)
            numberedList.append(String.format("\t%d.%s\n", i + 1, this.tasks.get(i)));
        }
        return numberedList.toString();
    }
}
