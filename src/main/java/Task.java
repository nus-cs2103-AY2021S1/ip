package main.java;

import java.util.ArrayList;
import java.util.List;

public class Task {
    private static List<Task> database = new ArrayList<>();

    protected String description;
    protected String time;
    protected boolean isDone = false;

    public Task(String description, String time) {
        this.description = description;
        this.time = time;
    }

    public static void addTask(Task task) {
        database.add(task);
    }

    public static Task getTask(int index) {
        if (index <= 0 || index > database.size()) {
            throw new IllegalArgumentException("Invalid argument for the LIST command.");
        }
        return database.get(index - 1);
    }

    private void markAsDone() {isDone = true;}

    public static void iDone(int index) {
        if (index <= 0 || index > database.size()) {
            throw new IllegalArgumentException("Invalid argument for the LIST command.");
        }
        database.get(index - 1).markAsDone();
    }

    public static Task iRemove(int index) {
        if (index <= 0 || index > database.size()) {
            throw new IllegalArgumentException("Invalid argument for the LIST command.");
        }
        return database.remove(index - 1);
    }

    public static int count() { return database.size();}

    @Override
    public String toString() {
        return String.format("[T][%s] %s", (isDone ? "\u2713" : "\u2718"), description);
    }

    public static void printTasks() {
        for (int i = 0; i < database.size(); i++) {
            System.out.println((i + 1) + "." + database.get(i));
        }
    }
}

class Todo extends Task {
    public Todo(String description) {
        super(description, null);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s",
                (super.isDone ? "\u2713" : "\u2718"),
                super.description);
    }
}

class Deadline extends Task {
    public Deadline(String description, String time) {
        super(description, time);
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)",
                (super.isDone ? "\u2713" : "\u2718"),
                super.description,
                super.time);
    }
}

class Event extends Task {
    public Event(String description, String time) {
        super(description, time);
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)",
                (super.isDone ? "\u2713" : "\u2718"),
                super.description,
                super.time);
    }
}