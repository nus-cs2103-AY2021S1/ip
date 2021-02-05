package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * <h1>Represents an Deadline</h1>
 * * Contains the details of the deadline, whether it is completed,
 * * and the due date.
 */
public class Deadline extends Task {
    private String date;
    private LocalDate localDate;

    /**
     * Creates a Deadline object.
     *
     * @param task Deadline details.
     * @param date Date of Deadline.
     * @param isCompleted True if Deadline is completed, false otherwise.
     * @param priority Priority level of the Deadline, if any.
     */
    public Deadline(String task, String date, boolean isCompleted, int priority) {
        super(task, isCompleted, priority);
        this.date = date;
        localDate = LocalDate.parse(date);
    }

    public String getDate() {
        return date;
    }

    /**
     * An overridden method that returns a String with the Task type,
     * deadline details, due date formatted in MM dd yyyy format,
     * and priority level of deadline, if any.
     *
     * @return String with all details of the deadline.
     */
    @Override
    public String toString() {
        String text = "[D]" + super.toString() + " (by: "
                + localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        if (getPriority() != 0) {
            return text + " (priority: " + getPriority() + ")";
        } else {
            return text;
        }
    }
}
