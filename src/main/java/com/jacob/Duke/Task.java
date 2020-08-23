package com.jacob.Duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;
    protected LocalDateTime dueDateTime;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, String dateTime) {
        this.description = description;
        this.isDone = false;
        this.dueDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd kkmm"));
        // example command: deadline return book /by 2019-10-15 1800
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void setDone() {
        isDone = true;
    }

    public String getDescription() {
        return description;
    }

    public String getCurrentStatus() {
        if (dueDateTime != null) {
            return "  ["+ type + "]"+ "[" + getStatusIcon() +"] " + getDescription()
                    + " " +dueDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy ha"));
        }
        return "  ["+ type + "]"+ "[" + getStatusIcon() +"] " + getDescription();
    }

    public String convertToFile() {
        if (dueDateTime != null) {
            return String.format("%s,%s,%s,%s", type, isDone ? 1 : 0 , getDescription(),
                    dueDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd kkmm")));
        }
        return String.format("%s,%s,%s", type, isDone ? 1 : 0, getDescription());
    }

}
