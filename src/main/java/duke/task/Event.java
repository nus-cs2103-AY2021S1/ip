package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    /** Event time of a task */
    protected LocalDateTime at;

    /**
     * Creates an events with given time.
     *
     * @param description Description of the event.
     * @param at Time for the event.
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Creates an events with status, description and time.
     *
     * @param isDone Status of the event.
     * @param description Description of the event.
     * @param at Time for the event.
     */
    public Event(boolean isDone, String description, LocalDateTime at) {
        super(description);
        this.at = at;
        this.isDone = isDone;
    }

    @Override
    public String toFileStringFormat() {
        return String.format("E / %d / %s / %s", isDone ? 1 : 0, this.desciption, this.at);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

        return "[E]" + super.toString() + " (at: " + at.format(formatter) + ")";
    }

}
