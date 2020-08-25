package main.java.com.jacob.duke;

import java.util.ArrayList;
import java.util.List;

import main.java.com.jacob.duke.task.Task;

public class TaskList {
    private List<Task> taskList;
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public List<Task> getTaskList() {
        return taskList;
    }
}
