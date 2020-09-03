package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class that simulates the event task that user has inputted.
 */

public class Event extends Task {
    private static final DateTimeFormatter FormatDateTime = DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy, h:mma");
    private final LocalDateTime eventTime;
    /**
     * Creates a event object the containing details of the task.
     *
     * @param description Details of the task.
     * @param eventTime LocalDateTime format of the time.
     */
    public Event(String description, LocalDateTime eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    /**
     * Creates a event object the containing details of the task.
     *
     * @param description Details of the task.
     * @param eventTime String format of the time. Either YYYY-MM-DD HHMM or
     *                  YYYY-MM-DD(Will be reformatted with 2359 as HHMM).
     * @param isDone Boolean value of whether a task is completed.
     */
    public Event(String description, String eventTime, boolean isDone) {
        super(description, isDone);
        this.eventTime = LocalDateTime.parse(eventTime);
    }

    /**
     * Returns a proper styling to be recorded into CSV.
     *
     * @return A format to be recorded into CSV.
     */
    public String formatStyling() {
        return String.format("event,%s%s", eventTime, super.formatStyling());
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventTime.format(FormatDateTime) + ")";
    }
}
