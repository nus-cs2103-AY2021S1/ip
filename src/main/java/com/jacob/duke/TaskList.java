package com.jacob.duke;

import com.jacob.duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    public List<Task> taskList;
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        taskList = new ArrayList<>();
    }


}
