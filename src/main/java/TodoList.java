package main.java;

import java.util.ArrayList;
import java.util.List;

public class TodoList {
    private ArrayList<String> todoList;

    public TodoList() {
        this.todoList = new ArrayList<>();
    }

    public void addTask(String task) {
        this.todoList.add(task);
        System.out.println("     added: " + task);
    }

    public void listTasks() {
        for (int i = 0; i < this.todoList.size(); i++) {
            int listNumber = i + 1;
            System.out.println("     " + listNumber + ": " + todoList.get(i));
        }

    }
}
