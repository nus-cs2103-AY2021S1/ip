package duke.task;

import java.time.LocalDate;

/**
 * <code>Task</code> object represents a task containing a description. It contains a boolean <code>isDone</code> to
 * state whether a particular task has been completed.
 */
public class Task {
    protected final String description;
    protected boolean isDone;

    Task(String message) {
        this.description = message.stripLeading().stripTrailing();
        this.isDone = false;
    }

    Task(String message, boolean isDone) {
        this.description = message.stripLeading().stripTrailing();
        this.isDone = isDone;
    }

    /**
     * Returns the status icon of this <code>Task</code> depending on whether or not it has been completed.
     *
     * @return the status icon of this <code>Task</code> depending on whether or not it has been completed
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Marks this <code>Task</code> as completed.
     *
     * @return a string representation of the message informing user if the command has been successfully executed
     */
    public String markAsDone() {
        if (isDone) {
            return " You have already completed this task! *Woof woof*\n";
        } else {
            this.isDone = true;
            return " Good Job!!! You cleared this task:\n" + "   ["
                    + this.getStatusIcon() + "] " + this.description + "\n";
        }
    }

    /**
     * Marks this <code>Task</code> as incomplete.
     *
     * @return a string representation of the message informing user if the command has been successfully executed
     */
    public String undoDone() {
        if (isDone) {
            this.isDone = false;
            return " I've have unmarked this task:\n" + "   ["
                    + this.getStatusIcon() + "] " + this.description + "\n";
        } else {
            return " This task is not done! *Woof woof*\n";
        }
    }

    /**
     * Returns a string representation of this <code>Task</code> object for saving.
     *
     * @return a string representation of this <code>Task</code> object for saving
     */
    public String toSaveFormat() {
        return this.isDone ? "[T] " + this.description : "[F] " + this.description;
    }

    /**
     * Compare the date of this <code>Task</code> with the specified date.
     *
     * @param date the specified Date
     * @return returns true if the date of this <code>Task</code> is same as the specified date. Else, otherwise.
     */
    public boolean compareDate(LocalDate date) {
        return false;
    }

    /**
     * Returns a string representation of this <code>Task</code> object.
     *
     * @return a string representation of this <code>Task</code> object
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
