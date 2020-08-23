package duke.task;

import duke.Ui;

import java.time.LocalDate;

/**
 * <code>Task</code> object represents a task containing a description. It contains a boolean <code>isDone</code> to
 * state whether a particular task has been completed.
 */
public class Task{
    protected final String description;
    protected boolean isDone;
    protected final static Ui ui = new Ui();

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
     */
    public void markAsDone() {
        if (!isDone) {
            this.isDone = true;
            ui.markDoneSuccess("   [" + this.getStatusIcon() + "] " + this.description + "\n");
        } else {
            ui.markDoneRepeat();
        }
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
