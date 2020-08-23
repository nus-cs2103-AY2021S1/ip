package com.duke.tasks;

public class Task {
    protected String task;
    protected boolean done;

    public Task() {
        this.task = null;
        this.done = false;
    }

    public Task(String task, boolean done) {
        this.task = task;
        this.done = done;
    }

    public Task(String task) {
        this.task = task;
        this.done = false;
    }

    public void setDone(boolean bool) {
        this.done = bool;
    }

    public String getTask() {
        return this.task;
    }

    public boolean getDone() { return this.done; }

    //format date from 'by Sunday' to '(by: Sunday)'
    public static String reformatDate(String input) {
        return "(" + input.substring(0, input.indexOf(" "))
                + ":"
                + input.substring(input.indexOf(" "))
                + ")";
    }

    // Task - 1 - read book
    public String parseToSaveFormat() {
        String isDoneStr = this.done ? "1" : "0";
        return "Task - " + isDoneStr + " - " + this.task;
    }
}
