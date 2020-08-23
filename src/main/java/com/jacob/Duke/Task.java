package com.jacob.Duke;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String type;
    protected String dateTime;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public Task(String description, String dateTime) {
        this.description = description;
        this.isDone = false;
        this.dateTime = dateTime;
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
        if (dateTime != null) {
            return "  ["+ type + "]"+ "[" + getStatusIcon() +"] " + getDescription()
                    + String.format(" (by: %s)", dateTime);
        }
        return "  ["+ type + "]"+ "[" + getStatusIcon() +"] " + getDescription();
    }

    public String convertToFile() {
        if (dateTime != null) {
            return String.format("%s,%s,%s,%s", type, isDone ? 1 : 0 , getDescription(), dateTime);
        }
        return String.format("%s,%s,%s", type, isDone ? 1 : 0, getDescription());
    }
}
