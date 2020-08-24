package main.java;

import java.time.format.DateTimeFormatter;

public abstract class Task {
    private final String name;
    private boolean isDone;
    protected static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    protected static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void setDone() throws TaskDoneException{
        if (isDone) {
            throw new TaskDoneException();
        }
        this.isDone = true;
    }

    public String toSaveFormat() {
        String status = isDone ? "1" : "0";
        return " | " + status + " | " + name ;
    }

    @Override
    public String toString() {
        //String icon = isDone ? "\u2713" : "\u2718";
        String icon = isDone ? "X" : " ";
        return "[" + icon + "] " + name;
    }
}
