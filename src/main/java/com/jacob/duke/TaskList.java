package main.java.com.jacob.duke;

import main.java.com.jacob.duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList;
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Create a taskList object to encapsulate the task list
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Get arraylist of task objects
     * @return List containing the list of to do items
     */
    public List<Task> getTaskList() {
        return taskList;
    }
}
