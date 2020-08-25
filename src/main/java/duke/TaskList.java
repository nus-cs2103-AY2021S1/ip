package main.java.duke;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList = new ArrayList<>();

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public boolean addTask(Task task) {
        return this.taskList.add(task);
    }

    public Task removeTask(int taskIndex) {
        return this.taskList.remove(taskIndex);
    }

    public Task getTask(int taskIndex) {
        return this.taskList.get(taskIndex);
    }

    /**
     * Returns the size of the arraylist task list.
     * @return Returns the size of the task list
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Lists all tasks within the task list
     * @return
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            if (i == taskList.size() - 1) {
                result.append("  " + (i+1) + "." + taskList.get(i));
            } else {
                result.append("  " + (i+1) + "." + taskList.get(i) + System.lineSeparator());
            }
        }
        return result.toString();
    }

}
