package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a {@code Tasklist} object to store tasks in memory
 */
public class TaskList {
    private List<Task> taskList;

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public Task add(Task task) {
        taskList.add(task);
        return task;
    }

    /**
     * Removes a specific task based on task number
     * 
     * @param taskNumber task index
     * @return removed task
     */
    public Task remove(int taskNumber) {
        return taskList.remove(taskNumber);
    }

    /**
     * Marks a task as done
     * 
     * @param taskNumber task index
     * @return updated task
     */
    public Task markAsDone(int taskNumber) {
        taskList.get(taskNumber).markAsDone();
        return taskList.get(taskNumber);
    }

    /**
     * Show task description
     * 
     * @param taskNumber task index
     * @return string description of task showing status, type and task description
     */
    public String show(int taskNumber) {
        return taskList.get(taskNumber).showTask();
    }

    /**
     * Get all tasks stored
     * 
     * @return list of tasks stored
     */
    public List<Task> getAll() {
        return this.taskList;
    }

    /**
     * Get number of tasks stored
     * 
     * @return size of taskList
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Returns a task list containing all tasks on the date
     * 
     * @param date date in ISO-8601 format
     * @return all tasks on 1 date
     */
    public TaskList viewAll(LocalDate date) {
        return new TaskList(
                this.taskList
                        .stream()
                        .filter(x ->
                                (x instanceof Event && ((Event) x).getDate().equals(date))
                                        || (x instanceof Deadline && ((Deadline) x).getDate().equals(date)))
                        .collect(Collectors.toList()));
    }

    /**
     * Find all tasks that contain a specific keyword
     * 
     * @param keyword user-inputted keyword
     * @return a Tasklist containing all tasks that contain a specific keyword
     */
    public TaskList find(String keyword) {
        return new TaskList(
                this.taskList
                        .stream()
                        .filter(x -> x.getDescription().contains(keyword))
                        .collect(Collectors.toList()));
    }
}
