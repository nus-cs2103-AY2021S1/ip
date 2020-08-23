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
     * @return true if the task happens on the given date, ToDo does not happen on any date, so always false
     */
    public boolean isHappeningOn(LocalDate date) {
        return false;
    }

    /**
     * Checks whether the task happens today.
     * @return true if the task happens today, ToDo does not happen on any date, so always false
     */
    public boolean isHappeningToday() {
        return false;
    }

    /**
     * Checks whether the task happens before a designated date.
     * @param date the given date
     * @return true if the task happens before the given date, ToDo does not happen on any date, so always false
     */
    public boolean hasHappenedBefore(LocalDate date) {
        return false;
    }

    /**
     * Checks whether the task happens before today.
     * @return true if the task happens before today, ToDo does not happen on any date, so always false
     */
    public boolean hasHappenedBeforeToday() {
        return false;
    }

    /**
     * Checks whether the task happens after a designated date.
     * @param date the given date
     * @return true if the task happens after the given date, ToDo does not happen on any date, so always false
     */
    public boolean isHappeningAfter(LocalDate date) {
        return false;
    }

    /**
     * Checks whether the task happens after today.
     * @return true if the task happens after today, ToDo does not happen on any date, so always false
     */
    public boolean isHappeningAfterToday() {
        return false;
    }

    /**
     * Checks whether the task happens before the two given designated date.
     * @param date1 the starting date for the period
     * @param date2 the ending date for the period
     * @return true if the task happens before the two dates (inclusive), ToDo does not happen on any date, so always
     * false
     */
    public boolean isHappeningBetween(LocalDate date1, LocalDate date2) {
        return false;
    }

    /**
     * Check whether the task happens within the given days counting from today.
     * @param n the number of days to construct the period, 1 for tomorrow
     * @return true if the task happens within n days (inclusive), ToDo does not happen on any date, so always false
     */
    public boolean willHappenInDays(int n) {
        return false;
    }

    /**
     * Checks whether the task description contains a designated substring
     * @param s the substring to find
     * @return true if s is found in the task description
     */
    public boolean finds(String s) {
        return description.contains(s);
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
