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
     * Iterates through all tasks in the task ArrayList and appends tasks with descriptions
     * containing the keyword to the returned result.
     * @param keyword to be used for the search
     * @return a string with all tasks that have descriptions containing the keyword.
     */
    public String searchWithKeyword(String keyword) {
        StringBuilder matches = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            String taskString = taskList.get(i).toString();
            if (taskString.contains(keyword)) {
                if (matches.length() == 0) {
                    matches.append(System.lineSeparator());
                }
                matches.append("  " + taskString + System.lineSeparator());
            }
        }
        return matches.toString();
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
