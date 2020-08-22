package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

abstract public class Task {
    protected String description;
    protected boolean isDone;
    protected final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    abstract public boolean hasSameDate(LocalDate theDate);

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}