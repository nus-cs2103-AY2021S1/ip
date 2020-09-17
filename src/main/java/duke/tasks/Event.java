package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that is an event with a description and timing
 */
public class Event extends Task {
    private static final String TEXTFILE_DATE_FORMAT = "yyyy-MM-dd HH:mm";
    private static final String OUTPUT_DATE_FORMAT = "MMM d yyyy hh:mm a";
    private LocalDateTime timing;

    /**
     * Creates an event task with description and timing of event, that is not done.
     *
     * @param description Description of the event task.
     * @param timing Timing event will take place.
     */
    public Event(String description, LocalDateTime timing) {
        super(description);
        this.timing = timing;
    }

    /**
     * Creates an event task with description and timing of event, specifying if it is done.
     *
     * @param description Description of the event task.
     * @param timing Timing event will take place.
     * @param isDone True to show task is done, False to show task is not done.
     */
    public Event(String description, LocalDateTime timing, boolean isDone) {
        super(description, isDone);
        this.timing = timing;
    }

    public LocalDateTime getTiming() {
        return this.timing;
    }

    /**
     * Marks the event task as done.
     *
     * @return duke.tasks.Event task that is marked as done.
     */
    @Override
    public Event markAsDone() {
        Event doneEvent = new Event(this.getDescription(), this.timing, true);
        return doneEvent;
    }

    @Override
    public String toTxtFileFormat() {
        return "E" + super.toTxtFileFormat() + " | "
                + this.timing.format(DateTimeFormatter.ofPattern(TEXTFILE_DATE_FORMAT));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.timing.format(DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT)) + ")";
    }
}
