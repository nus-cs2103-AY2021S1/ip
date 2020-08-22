package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

abstract public class Task {
    protected String description;
    protected boolean isDone;
    protected final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Task(String description) {
        this(description, false);
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String saveFormat() {
        return isDone + "~" + description;
    }

    abstract public boolean hasSameDate(LocalDate theDate);

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}