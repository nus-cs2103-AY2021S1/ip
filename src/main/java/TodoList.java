package main.java;

import java.util.ArrayList;

public class TodoList {
    ArrayList<Task> tasks;

    TodoList() {
        this.tasks = new ArrayList<>();
    }

    void add(String task) {
        this.tasks.add(new Task(task));
        System.out.println("New task added: " + task);
    }

    void list() {
        if (this.tasks.size() <= 0) {
            System.out.println("No tasks added.");
        } else {
            int position = 1;
            for (Task task : this.tasks) {
                System.out.println(position + ". " + task);
                position++;
            }
        }
    }
}
