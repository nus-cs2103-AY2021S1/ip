package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class that simulates the deadline task that user has inputted.
 */

public class Deadline extends Task {
    private static final DateTimeFormatter FormatDateTime = DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy, h:mma");
    private final LocalDateTime taskDeadline;
    /**
     * Creates a deadline object the containing details of the task.
     *
     * @param description Details of the task.
     * @param taskDeadline LocalDateTime format of the time.
     */
    public Deadline(String description, LocalDateTime taskDeadline) {
        super(description);
        this.taskDeadline = taskDeadline;
    }

    /**
     * Creates a deadline object the containing details of the task.
     *
     * @param description Details of the task.
     * @param taskDeadline String format of the time. Either YYYY-MM-DD HHMM or
     *                     YYYY-MM-DD(Will be reformatted with 2359 as HHMM).
     * @param isDone Boolean value of whether a task is completed.
     */
    public Deadline(String description, String taskDeadline, boolean isDone) {
        super(description, isDone);
        this.taskDeadline = LocalDateTime.parse(taskDeadline);
    }

    /**
     * Returns a proper styling to be recorded into CSV.
     *
     * @return A format to be recorded into CSV.
     */
    public String formatStyling() {
        return String.format("deadline,%s%s", taskDeadline, super.formatStyling());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + taskDeadline.format(FormatDateTime) + ")";
    }
}
