package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task which needs to be done.
 */
abstract public class Task {

    /** Description of the Task to be done. */
    protected String description;

    /** True if the Task is already done otherwise False. */
    protected boolean isDone;

    /** Date format of which the Date of any Tasks will be displayed to the user. */
    protected final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

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
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks the Task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Formats the Task in the way it should be saved in Storage.
     *
     * @return String format of the Task for saving in Storage.
     */
    public String saveFormat() {
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
    abstract public boolean hasSameDate(LocalDate theDate);

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