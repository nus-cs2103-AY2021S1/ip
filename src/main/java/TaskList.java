package main.java;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public String listAllTasks() {
        String res = "";
        for (int i = 0; i < taskList.size(); i++) {
            res += String.format("%d. %s\n", i + 1, taskList.get(i));
        }
        return res;
    }

    public void addTask(Task newTask) {
        taskList.add(newTask);
    }

    public Task doneTask(int taskNum) throws DukeException {
        if (taskNum > 0 && taskNum <= taskList.size()) {
            Task task = taskList.get(taskNum - 1);
            task.doTask();
            return task;
        } else {
            throw new DukeException("What are you done with? Gotta specify a valid task number!");
        }
    }

    public Task removeTask(int taskNum) throws DukeException {
        if (taskNum > 0 && taskNum <= taskList.size()) {
            Task task = taskList.get(taskNum - 1);
            taskList.remove(taskNum - 1);
            return task;
        } else {
            throw new DukeException("What are you deleting? Gotta specify a valid task number!");
        }
    }

    public int getTaskCount() {
        return taskList.size();
    }


}
