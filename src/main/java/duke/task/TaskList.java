package duke.task;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<Task>();

    public TaskList() {}

    /** Adds a given duke.task to the taskList.
     *
     * @param t duke.task to be added.
     */
    public void addTask(Task t) {
        taskList.add(t);
    }

    /** Deletes a specified duke.task from the taskList.
     *
     * @param taskNumber index of the duke.task to be deleted in taskList.
     */
    public void deleteTask(int taskNumber) {
        taskList.remove(taskNumber);
    }

    /** Updates a specified duke.task to a new description.
     *
     * @param taskNumber index of the duke.task to be updated in taskList.
     * @param newTaskDesc new description to update the duke.task with.
     * @return the edited duke.task.
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

    public TaskList find(String keyword) {
        TaskList foundTasks = new TaskList(); // to be returned

        // process the keyword to make it easier to match
        String searchTerm = keyword.toLowerCase();

        for (Task t : taskList) {
            // process the task's string to make it easier to match
            String processedTaskString = t.toString().toLowerCase();

            if (processedTaskString.contains(searchTerm)) {
                foundTasks.addTask(t);
            }
        }

        return foundTasks;
    }
}
