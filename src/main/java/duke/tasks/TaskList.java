package main.java.duke.tasks;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(TaskList list) {
        this.taskList = list.getTaskList();
    }

    public void addTask(Task t) {
        taskList.add(t);
    }

    public void markAsDone(int index) {
        taskList.get(index).markAsDone();
    }

    public void removeTask(int index) {
        taskList.remove(index);
    }

    public int taskListSize() {
        return taskList.size();
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void showAllTasks() {
        String allTasks = "";
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + "." + taskList.get(i));
        }
    }

}
