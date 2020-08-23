package main.java;

import java.util.ArrayList;
import java.util.List;

public class Task {
    protected String type;
    protected String description;
    protected String time;
    protected boolean isDone = false;

    public Task(String description, String time) {
        type = "Task";
        this.description = description;
        this.time = time;
    }

    public void markAsDone() {isDone = true;}

    public String serialize() {
        return type + "%%%" + description + "%%%" + time + "%%%" + (isDone ? 1 : 0);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", (isDone ? "\u2713" : "\u2718"), description);
    }
}

class Todo extends Task {
    public Todo(String description) {
        super(description, null);
        super.type = "todo";
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
        super.type = "deadline";
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
        super.type = "event";
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)",
                (super.isDone ? "\u2713" : "\u2718"),
                super.description,
                super.time);
    }
}
