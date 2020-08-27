package duke.task;

import java.time.LocalDate;

import duke.Ui;

/**
 * <code>Task</code> object represents a task containing a description. It contains a boolean <code>isDone</code> to
 * state whether a particular task has been completed.
 */
public class Task {
    protected static final Ui UI = new Ui();
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
     */
    public void markAsDone() {
        if (!isDone) {
            this.isDone = true;
            UI.markDoneSuccess("   [" + this.getStatusIcon() + "] " + this.description + "\n");
        } else {
            UI.markDoneRepeat();
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
