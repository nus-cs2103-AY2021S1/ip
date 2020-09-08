package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Represents a Task that is accompanied by a specific deadline.
 */
public class Deadline extends Task {

    /** Date of the deadline of the Task */
    protected LocalDate date;

    /** Time of the deadline of the Task */
    protected LocalTime time;

    /**
     * Creates a Task with a deadline which has not been marked as done.
     *
     * @param description Details of what has to be done in the Task.
     * @param dateTime Date and time of the deadline of the Task.
     */
    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.date = dateTime.toLocalDate();
        this.time = dateTime.toLocalTime();
    }

    /**
     * Creates a Task with a deadline.
     *
     * @param description Details of what has to be done in the Task.
     * @param isDone Boolean value that indicates if the Task has been completed.
     * @param dateTime Date and time of the deadline of the Task.
     */
    public Deadline(String description, boolean isDone, LocalDateTime dateTime) {
        super(description, isDone);
        this.date = dateTime.toLocalDate();
        this.time = dateTime.toLocalTime();
    }

    /**
     * Returns the String representation of the Deadline
     * to be displayed on the UI.
     *
     * @return String representation of the Deadline to
     * be displayed on the UI.
     */
    @Override
    public String toDisplayString() {
        return "[D]" + super.toDisplayString() + " (by: "
                + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " "
                + time.format(DateTimeFormatter.ofPattern("h:mm a")) + ")";
    }

    /**
     * Returns the String representation of the Deadline
     * to be displayed in the storage of the task list.
     *
     * @return String representation of the String representation
     * of the Deadline to be displayed in the storage of the task list.
     */
    @Override
    public String toSavedString() {
        return "D" + " | " + super.toSavedString() + " | "
                + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " "
                + time.format(DateTimeFormatter.ofPattern("h:mm a"));
    }

}