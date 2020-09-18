package task;

import exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task which needs to be done.
 */
public abstract class Task {

    /** Date format of which the Date of any Tasks will be displayed to the user. */
    protected static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /** Description of the Task to be done. */
    protected String description;

    /** True if the Task is already done otherwise False. */
    protected boolean isDone;

    /**
     * Creates a Task instance containing a description.
     *
     * @param description Description of Task to be done.
     */
    public Task(String description) {
        this(description, false);
    }

    /**
     * Creates a Task instance containing a description and a Date.
     *
     * @param description Description of Task to be done.
     * @param isDone True if the Task is already done otherwise false.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the appropriate status icon of the Task to be displayed.
     *
     * @return A tick if the task is already done otherwise A cross.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); // return tick or X symbols
    }

    /**
     * Marks the Task as done.
     *
     * @throws DukeException Thrown when task is already marked as done.
     */
    public void markAsDone() throws DukeException {
        if (this.isDone) {
            throw new DukeException("The specified task is already marked as done");
        }
        this.isDone = true;
    }

    /**
     * Formats the Task in the way it should be saved in Storage.
     *
     * @return String format of the Task for saving in Storage.
     */
    public String getSavingFormat() {
        return isDone + "~" + description;
    }

    /**
     * Checks if the description of the Task has the specified keyword.
     *
     * @param keyword String to check if present in the desciption of the Task.
     * @return True if the description of the Task has the keyword otherwise false.
     */
    public boolean hasKeyword(String keyword) {
        return this.description.toLowerCase().contains(keyword.toLowerCase());
    }

    /**
     * Checks whether the Task has the same Date as specified.
     *
     * @param theDate Date to check whether the Task has occurred on.
     * @return True if the Task occurred on the specified date otherwise false.
     */
    public abstract boolean hasSameDate(LocalDate theDate);

    /**
     * Returns the String representation of the Task to be displayed to the user.
     *
     * @return String representation of Task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
