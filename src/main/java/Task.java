package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String time;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.time = "";
    }

    public Task(String description, String time) {
        this.description = description;
        this.isDone = false;
        this.time = time;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        isDone = true;
    }
    
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
    
    public String toStringFile() {
        return "X";
    }
}
