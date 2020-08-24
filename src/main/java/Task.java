package main.java;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Task {
    protected String type;
    protected String description;
    protected LocalDateTime time;
    protected boolean isDone = false;

    public Task(String description, LocalDateTime time) {
        type = "Task";
        this.description = description;
        this.time = time;
    }

    public void markAsDone() {isDone = true;}

    public String serialize() {
        String datetimeString = time.getDayOfMonth() + "/" +
                time.getMonthValue() + "/" +
                time.getYear() + " " +
                (time.getHour() * 100 + time.getMinute());
        if (time.equals(LocalDateTime.MIN)) datetimeString = "null";
        return type + "%%%" + description + "%%%" + datetimeString + "%%%" + (isDone ? 1 : 0);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", (isDone ? "\u2713" : "\u2718"), description);
    }
}

class Todo extends Task {
    public Todo(String description) {
        super(description, LocalDateTime.MIN);
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
    public Deadline(String description, LocalDateTime time) {
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
    public Event(String description, LocalDateTime time) {
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
