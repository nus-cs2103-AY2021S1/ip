package seedu.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Creates deadlines that are a subtype of Task and stores the name of the deadline and its deadline.
 */
public class Deadline extends Task {
    private String dueDate;

    /**
     * Initializes an instance of Deadline.
     *
     * @param description Name of the task.
     * @param due Date that the task will be due by.
     */
    public Deadline(String description, String due) {
        super(description);
        LocalDate formatDate = LocalDate.parse(due);
        String convertedDate = formatDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        dueDate = convertedDate;
    }

    /**
     * Initializes an instance of Deadline for local storage.
     *
     * @param task Name of the task.
     * @param isDone Status of the task.
     * @param due Date that the task will be due by.
     */
    public Deadline(String task, boolean isDone, String due) {
        super(task, isDone);
        dueDate = due;
    }

    /**
     * Gets access to the formatted date.
     *
     * @return Formatted version of date.
     */
    public String getFormattedDate() {
        return dueDate;
    }

    /**
     * Provides a string describing the Deadline class.
     *
     * @return description of Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate + ")";
    }
}
