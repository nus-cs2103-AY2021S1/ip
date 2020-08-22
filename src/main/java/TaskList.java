package main.java;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public Task getTask(int taskNumber) {
        return taskList.get(taskNumber - 1);
    }

    public List<Task> getAllTasks() {
        return taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask(int taskNumber) {
        Task deleteTask = taskList.get(taskNumber - 1);
        taskList.remove(taskNumber - 1);
    }

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
