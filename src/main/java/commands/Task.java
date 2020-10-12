package commands;

import storage.Encodable;

/**
 * Represents a task to do and creates a parent class with child classes
 * Commands.Event, Commands.ToDo, Commands.Deadline
 */
public abstract class Task implements Encodable<Task> {


    protected String description;
    protected boolean isDone;

    /**
     * Constructor for making a Commands.Task Object
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * obtains the description from a task
     * @return description of the task
     */
    public String getDescription() {
        return description;
    }

    public boolean getIsDone() {
        return isDone;
    }

    /**
     * obtains a tick or cross from a Commands.Task
     *
     * @return String [✓] or [✗]
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * marks a task as completed by turning boolean isDone to true
     */
    public void markAsDone() {
        this.isDone = true;

    }

    /**
     * overrides Commands.Task String output to be formatted
     *
     * @return String of formatted Commands.Task
     */
    @Override
    public String toString() {
        String s = "[" + this.getStatusIcon() + "] " + this.description;
        return s;
    }


}
