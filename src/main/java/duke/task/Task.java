package duke.task;

import duke.command.InvalidCommandException;

import java.time.LocalDate;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task and sets it as not done.
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon for this task.
     * @return cross if not done and tick if done
     */
    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Marks this task as done.
     * @throws InvalidCommandException if the task is already done
     */
    public void markAsDone() throws InvalidCommandException {
        if (isDone) {
            throw new InvalidCommandException("The task " + this + " has already been done.");
        }
        isDone = true;
    }

    /**
     * Gets the string to write in the storage file.
     * @return the string to write in the storage file
     */
    public String output() {
        int done = isDone ? 1 : 0;
        return " | " + done + " | " + description;
    }

    /**
     * Checks whether the task happens on a designated date.
     * @param date the given date
     * @return true if the task happens on the given date, ToDo does not happen on any date
     */
    public boolean happenOnDate(LocalDate date) {
        return false;
    }

    /**
     * Checks whether the task happens today.
     * @return true if the task happens today, ToDo does not happen on any date
     */
    public boolean happenToday() {
        return false;
    }

    /**
     * Checks whether the task happens before a designated date.
     * @param date the given date
     * @return true if the task happens before the given date, ToDo does not happen on any date
     */
    public boolean happenBeforeDate(LocalDate date) {
        return false;
    }

    public boolean happenBeforeToday() {
        return false;
    }

    /**
     * Checks whether the task happens after a designated date.
     * @param date the given date
     * @return true if the task happens after the given date, ToDo does not happen on any date
     */
    public boolean happenAfterDate(LocalDate date) {
        return false;
    }

    public boolean happenAfterToday() {
        return false;
    }

    public boolean happenBetween(LocalDate date1, LocalDate date2) {
        return false;
    }

    public boolean happenIn(int n) {
        return false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
