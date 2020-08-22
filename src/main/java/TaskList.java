package main.java;

import java.util.List;
import java.time.LocalDate;

public class TaskList {
    private List<Task> tasks;
    Storage storage;

    TaskList() {
        this.storage = new Storage();
        this.tasks = this.storage.readFromFile();
    }

    void add(Task task) {
        this.tasks.add(task);
        this.storage.appendToFile(task);
        System.out.println("New task added: " + task);
    }

    void markAsDone(int position) throws DukeException {
        if (position <= 0 || position > this.tasks.size()) {
            throw new DukeException("Invalid task.");
        } else {
            this.tasks.get(position - 1).markAsDone();
            this.storage.writeToFile(this.tasks);
        }
    }

    void delete(int position) throws DukeException {
        if (position <= 0 || position > this.tasks.size()) {
            throw new DukeException("Invalid task.");
        } else {
            Task removed = this.tasks.remove(position - 1);
            this.storage.writeToFile(this.tasks);
            System.out.println("Deleted: " + removed);
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

    void list(LocalDate date) {
        if (this.tasks.size() <= 0) {
            System.out.println("No tasks added.");
        } else {
            int position = 1;
            boolean hasTask = false;
            for (Task task : this.tasks) {
                if ((task instanceof Event || task instanceof Deadline)
                        && task.getDate().equals(date)) {
                    System.out.println(position + ". " + task);
                    hasTask = true;
                }
                position++;
            }
            if (!hasTask) {
                System.out.println("No tasks on that date.");
            }
        }
    }
}
