package main.java;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<>();
    }

    void add(String task) {
        this.tasks.add(new Task(task));
        System.out.println("New task added: " + task);
    }

    void markAsDone(int position) {
        if (position <= 0 || position > this.tasks.size()) {
            System.out.println("Invalid task.");
        } else {
            this.tasks.get(position - 1).markAsDone();
        }
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
