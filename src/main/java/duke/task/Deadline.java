package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class the simulates the deadline task that user has inputted.
 */

public class Deadline extends Task {
    private final LocalDateTime by;
    private static final DateTimeFormatter FormatDateTime = DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy, h:mma");

    /**
     * Creates a deadline object the containing details of the task.
     * 
     * @param description Details of the task.
     * @param by LocalDateTime format of the time.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Creates a deadline object the containing details of the task.
     * 
     * @param description Details of the task.
     * @param by String format of the time. Either YYYY-MM-DD HHMM or YYYY-MM-DD(Will be reformatted with 2359 as HHMM).
     * @param isDone Boolean value of whether a task is completed.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = LocalDateTime.parse(by);
    }

    /**
     * Returns a proper styling to be recorded into CSV.
     * 
     * @return A format to be recorded into CSV.
     */
    public String formatStyling() {
        return String.format("deadline,%s%s", by, super.formatStyling());
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(FormatDateTime) + ")";
    }
}
