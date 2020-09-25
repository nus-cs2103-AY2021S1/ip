package duke.task;

import static duke.util.DateFormatter.FORMAT_DATE_TIME;

import java.time.LocalDateTime;

/**
 * Class that simulates the deadline task that user has inputted.
 */
public class Deadline extends Task {

    /**
     * Initialize a deadline object the containing details of the task.
     *
     * @param description Details of the task.
     * @param taskDeadline LocalDateTime format of the date and time.
     */
    public Deadline(String description, LocalDateTime taskDeadline) {
        super(description, 0, taskDeadline);
    }

    /**
     * Initialize a deadline object the containing details of the task.
     *
     * @param description Details of the task.
     * @param taskDeadline String format of the time.
     * @param isDone Boolean value of whether a task is completed.
     * @param isReminderOn Boolean value of whether this task needs a reminder.
     */
    public Deadline(String description, String taskDeadline, boolean isDone, boolean isReminderOn) {
        super(description, isDone, isReminderOn, 0, LocalDateTime.parse(taskDeadline));
    }

    /**
     * Return a proper styling to be recorded into CSV.
     *
     * @return A formatted string to be recorded into CSV.
     */
    public String formatStyling() {
        return String.format("deadline,%s%s", getDueDate(), super.formatStyling());
    }

    /**
     * A String representation of the deadline object.
     *
     * @return A String representation of the deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDueDate().format(FORMAT_DATE_TIME) + ")";
    }
}
