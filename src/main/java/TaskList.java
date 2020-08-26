package main.java;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents the task list contains the user's tasks.
 */
public class TaskList {
    List<Task> taskList = new ArrayList<>();

    /**
     * Adds a task to the list.
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Gets a task corresponding to its index in the list.
     * @param index The index of the task in the list.
     * @return The corresponding task.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Gets the number of tasks in the list.
     * @return The number of tasks in the user's task list.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Gets the task list.
     * @return The user's task list.
     */
    public List<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Removes a task from the task list.
     * @param index Index of the task to be deleted.
     */
    public void deleteTask(int index) {
        Task taskToBeDeleted = taskList.get(index);
        taskList.remove(taskToBeDeleted);
    }

    @Override
    public String toString() {
        String result = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            int taskNumber = i + 1;
            result = result + taskNumber + "." + taskList.get(i).toString()
                    + (i == taskList.size() - 1 ? "" : "\n");
        }
        return result;
    }
}
