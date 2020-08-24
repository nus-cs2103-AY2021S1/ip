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

    /**
     * Sets a task as done.
     *
     * @param bool boolean on whether task is done or not.
     */
    public void setDone(boolean bool) {
        this.done = bool;
    }

    /**
     * Getter for details of task.
     *
     * @return String returns task attribute of Task object.
     */
    public String getTask() {
        return this.task;
    }

    /**
     * Getter for whether task is done.
     *
     * @return boolean returns done attribute of Task object.
     */
    public boolean getDone() { return this.done; }

    /**
     * Reformats date into format displayable.
     * Transform an example input "by Sunday" to "(by: Sunday)".
     *
     * @return String returns String in displayable format.
     */
    //format date from 'by Sunday' to '(by: Sunday)'
    public static String reformatDate(String input) {
        return "(" + input.substring(0, input.indexOf(" "))
                + ":"
                + input.substring(input.indexOf(" "))
                + ")";
    }

    /**
     * Returns a string representation of the Deadline object to be saved in persistent file.
     *
     * @return String Returns a string representation of the Task object to be saved in persistent file.
     */
    // Task - 1 - read book
    public String parseToSaveFormat() {
        String isDoneStr = this.done ? "1" : "0";
        return "Task - " + isDoneStr + " - " + this.task;
    }
}
