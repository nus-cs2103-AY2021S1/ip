package com.duke.tasks;

/**
 * Represents a task item.
 */

public abstract class Task {
    protected static final String COMPLETED_INDICATOR = "[\u2713]";
    protected static final String INCOMPLETE_INDICATOR = "[\u2718]";

    protected String task;
    protected boolean isDone;

    /**
     * Constructor for Tasks.
     */
    public Task() {
        this.task = null;
        this.isDone = false;
    }

    /**
     * Constructor for Tasks.
     *
     * @param task Task description.
     * @param isDone whether task is done.
     */
    public Task(String task, boolean isDone) {
        this.task = task;
        this.isDone = isDone;
    }

    /**
     * Constructor for Tasks.
     *
     * @param task Task description.
     */
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

    protected String getDoneIndicator() {
        return this.isDone ? COMPLETED_INDICATOR : INCOMPLETE_INDICATOR;
    }

    /**
     * Returns a string representation of the Deadline object to be saved in persistent file.
     *
     * @return String Returns a string representation of the Task object to be saved in persistent file.
     */
    // Task - 1 - read book
    public abstract String parseToSaveFormat();

    public abstract String toString();
}
