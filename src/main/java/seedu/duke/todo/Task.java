package main.java.seedu.duke.todo;

import java.time.LocalDate;

/**
 * Represents the task. A Task consist of a description and a marker of whether it is done.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task.
     * @param description The task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Alternative constructor for Task.
     * @param description The task description.
     * @param isDone The marker whether the task is done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the corresponding status icon depending on whether the task is completed.
     * @return A tick if the task is completed and a cross if the task is incomplete.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns the date of the task.
     * @return null for task of type Task that has no date.
     */
    public LocalDate getDate() {
        return null;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public int getDataStatus() {
        return (isDone ? 1 : 0);
    }

    /**
     * Returns the data representation of the task.
     * @return the data representation of the task.
     */
    public String getData() {
        return "| " + getDataStatus() + " | " + this.description;
    }
}
