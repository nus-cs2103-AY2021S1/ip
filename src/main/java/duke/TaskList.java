package duke;

import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    /** The tasks in this task list. */
    private List<Task> tasks;

    /**
     * Creates an empty task list with no tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a task list with the given tasks.
     * @param tasks The tasks to be added.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Gets the number of tasks in this task list.
     * @return The number of tasks in this task list.
     */
    public int getNumberOfTasks() {
        return this.tasks.size();
    }

    /**
     * Gets the task at the specified position in this task list.
     * @param taskNumber The position of the task in this task list.
     * @return The task at the specified position in this task list.
     */
    public Task getTask(int taskNumber) {
        return this.tasks.get(taskNumber - 1);
    }

    /**
     * Gets all the tasks in this task list as a list.
     * @return The list of all the tasks.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Gets all the tasks in this task list on the specified date as a task list.
     * @param date The date of all the tasks to get.
     * @return The task list with all the tasks on the specified date.
     */
    public TaskList getTaskListOnDate(LocalDate date) {
        TaskList result = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if ((task instanceof Deadline && ((Deadline) task).getBy().equals(date))
                    || task instanceof Event && ((Event) task).getAt().equals(date)) {
                result.addTask(task);
            }
        }
        return result;
    }

    /**
     * Gets all the tasks in this task list that contains the keyword as a task list.
     * @param keyword The string that the tasks you are finding should contain.
     * @return The task list with all the tasks that contain the keyword.
     */
    public TaskList getTasksWithKeyword(String keyword) {
        TaskList result = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.toString().contains(keyword)) {
                result.addTask(task);
            }
        }
        return result;
    }

    /**
     * Adds a task to this task list.
     * @param task The task to be added to this task list.
     * @return The task that was added.
     */
    public Task addTask(Task task) {
        this.tasks.add(task);
        return this.tasks.get(tasks.size() - 1);
    }

    /**
     * Deletes a task from this task list.
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
     * @param taskNumber The position of the task to be marked as done.
     */
    public void markTaskAsDone(int taskNumber) {
        this.tasks.get(taskNumber - 1).markAsDone();
    }

    /**
     * Returns a string representation of this task list.
     * @return A string representation of this task list.
     */
    @Override
    public String toString() {
        String numberedList = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            numberedList += "\n\t" + (i + 1) + "." + this.tasks.get(i);
        }
        return numberedList;
    }
}
