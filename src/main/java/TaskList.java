package main.java;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> todoList;

    public TaskList() {
        this.todoList = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.todoList.add(task);
        System.out.println("     Got it. I've added this task:" + "\n" + "       " + task.toString());
        System.out.println("     Now you have " + this.todoList.size() + " tasks in the list.");
    }

    public Task get(int index) {
        return this.todoList.get(index);
    }

    public void listTasks() {
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < this.todoList.size(); i++) {
            int listNumber = i + 1;
            Task currentTask = todoList.get(i);
            String taskStatus = currentTask.getStatusIcon();
            System.out.println("     " + listNumber + "." + currentTask.toString());
        }
    }

}
