package main.java;

import java.time.LocalDate;

public class Task {
    protected String description;
    protected boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }
    /*
    public Task(String description, boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }
    */
    public void markAsCompleted(){
        this.isCompleted = true;
    }

    public String getState() {
        if(this.isCompleted) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }

    @Override
    public String toString() {
        return "[" + getState() + "] " + description;
    }

    public String record() {
        return "";
    }

    public boolean isAt(LocalDate localDate) {
        return false;
    }

}
