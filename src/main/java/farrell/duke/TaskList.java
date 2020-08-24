package main.java.farrell.duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks and actions that can be performed on the list.
 */
public class TaskList {
    /** The list of tasks */
    private List<Task> taskList;

    TaskList() {
        this.taskList = new ArrayList<>();
    }

    TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns the task at the specified index
     *
     * @param taskNumber Index of the task to get.
     * @return The task at the index.
     */
    public Task getTask(int taskNumber) {
        return taskList.get(taskNumber - 1);
    }

    /**
     * Returns a list of all tasks being tracked.
     *
     * @return A list of Tasks.
     */
    public List<Task> getAllTasks() {
        return taskList;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param taskNumber Index of the task to delete.
     */
    public void deleteTask(int taskNumber) {
        Task deleteTask = taskList.get(taskNumber - 1);
        taskList.remove(taskNumber - 1);
    }

    /**
     * Updates the completion status of the task at the specified index.
     *
     * @param taskNumber Index of the task to update.
     */
    public void updateDone(int taskNumber) {
        Task task = taskList.get(taskNumber - 1);
        task.markAsDone(true);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < taskList.size(); i++) {
            sb.append(i + 1)
                    .append(". ")
                    .append(taskList.get(i).toString());
            if(i < taskList.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
