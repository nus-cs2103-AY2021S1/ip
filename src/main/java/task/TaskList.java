package main.java.duke.task;

import main.java.duke.task.Task;
import main.java.duke.Ui;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<Task>();

    public TaskList() {}

    /** Adds a given task to the taskList.
     *
     * @param t task to be added.
     */
    public void addTask(Task t) {
        taskList.add(t);
    }

    /** Deletes a specified task from the taskList.
     *
     * @param taskNumber index of the task to be deleted in taskList.
     */
    public void deleteTask(int taskNumber) {
        taskList.remove(taskNumber);
    }

    /** Updates a specified task to a new description.
     *
     * @param taskNumber index of the task to be updated in taskList.
     * @param newTaskDesc new description to update the task with.
     * @return the edited task.
     */
    public Task updateTaskDesc(int taskNumber, String newTaskDesc) {
        Task taskToBeEdited = taskList.get(taskNumber);
        taskToBeEdited.editDescription(newTaskDesc);
        return taskToBeEdited;
    }

    public void markAsDone(int taskNumber) {
        taskList.get(taskNumber).markAsDone();
    }

    public int getSize() {
        return taskList.size();
    }

    public Task getTask(int i) {
        return taskList.get(i);
    }
}
