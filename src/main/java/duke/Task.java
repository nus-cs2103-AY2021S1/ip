package main.java.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String time;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, String time) {
        this.description = description;
        this.isDone = false;
        this.time = time;
                //LocalDate.parse(time);
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        isDone = true;
    }
    
    public String getNewFormatTime() {
        try {
            LocalDate newFormatTime = LocalDate.parse(time);
            return newFormatTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            return this.time;
        }
    }
    
    public LocalDate getLocalDate() {
        return LocalDate.parse(time);
    }
    
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
    
    public String getType() {
        return "Task";
    }
    
    public String toStringFile() {
        return "X";
    }
}
