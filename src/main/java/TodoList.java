package main.java;

import java.util.ArrayList;

public class TodoList {
    private ArrayList<Task> todoList;

    public TodoList() {
        this.todoList = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.todoList.add(task);
        System.out.println("     added: " + task.description);
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
            System.out.println("     " + listNumber + "." + taskStatus + " " + currentTask.description);
        }
    }

}
