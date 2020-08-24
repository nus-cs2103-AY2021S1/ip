package com.duke.tasks;

public class Task {
    protected String task;
    protected boolean isDone;

    public Task() {
        this.task = null;
        this.isDone = false;
    }

    public Task(String task, boolean isDone) {
        this.task = task;
        this.isDone = isDone;
    }

    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    /**
     * Sets a task as done.
     *
     * @param bool boolean on whether task is done or not.
     */
    public void setDone(boolean bool) {
        this.isDone = bool;
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
    public boolean getDone() {
        return this.isDone;
    }

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
        String isDoneStr = this.isDone ? "1" : "0";
        return "Task - " + isDoneStr + " - " + this.task;
    }
}
