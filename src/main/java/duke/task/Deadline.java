package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class that simulates the deadline task that user has inputted.
 */

public class Deadline extends Task {
    private static final DateTimeFormatter Format_Date_Time = DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy, h:mma");
    /**
     * Creates a deadline object the containing details of the task.
     *
     * @param description Details of the task.
     * @param taskDeadline LocalDateTime format of the time.
     */
    public Deadline(String description, LocalDateTime taskDeadline) {
        super(description, 0, taskDeadline);
    }

    /**
     * Creates a deadline object the containing details of the task.
     *
     * @param description Details of the task.
     * @param taskDeadline String format of the time. Either YYYY-MM-DD HHMM or
     *                     YYYY-MM-DD(Will be reformatted with 2359 as HHMM).
     * @param isDone Boolean value of whether a task is completed.
     * @param isReminderOn Boolean value of whether this task needs a reminder.
     */
    public Deadline(String description, String taskDeadline, boolean isDone, boolean isReminderOn) {
        super(description, isDone, isReminderOn, 0, LocalDateTime.parse(taskDeadline));
    }
    /**
     * Returns a proper styling to be recorded into CSV.
     *
     * @return A format to be recorded into CSV.
     */
    public String formatStyling() {
        return String.format("deadline,%s%s", getDueDate(), super.formatStyling());
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDueDate().format(Format_Date_Time) + ")";
    }
}
