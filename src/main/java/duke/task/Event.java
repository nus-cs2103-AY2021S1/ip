package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class the simulates the event task that user has inputted.
 */

public class Event extends Task {
    private final LocalDateTime at;
    private static final DateTimeFormatter FormatDateTime = DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy, h:mma");

    /**
     * Creates a event object the containing details of the task.
     * 
     * @param description Details of the task.
     * @param at LocalDateTime format of the time.
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Creates a event object the containing details of the task.
     * 
     * @param description Details of the task.
     * @param at String format of the time. Either YYYY-MM-DD HHMM or YYYY-MM-DD(Will be reformatted with 2359 as HHMM).
     * @param isDone Boolean value of whether a task is completed.
     */
    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = LocalDateTime.parse(at);
    }

    /**
     * Returns a proper styling to be recorded into CSV.
     * 
     * @return A format to be recorded into CSV.
     */
    public String formatStyling() {
        return String.format("event,%s%s", at, super.formatStyling());
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(FormatDateTime) + ")";
    }
}
