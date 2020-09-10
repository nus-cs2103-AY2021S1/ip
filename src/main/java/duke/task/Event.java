package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime at;
    public Tag tag;

    /**
     * Creates an Event.
     *
     * @param description description of the Event
     * @param at date and time of the Event
     */
    public Event(String description, LocalDateTime at, boolean hasTag) {
        super(description, hasTag);
        this.at = at;
        if (hasTag) {
            this.tag = new Tag(description.substring(description.indexOf("@") + 1));
        }
    }

    @Override
    public String toText() {
        String completionStatus = "0";
        if (this.isDone) {
            completionStatus = "1";
        }
        return "E" + " | " + completionStatus + " | " + this.description + " | "
                + at.format(DateTimeFormatter.ofPattern("dd/MM/yyyy, HHmm"));
    }

    @Override
    public String toString() {
        return "[E]" + this.getStatusIcon() + " " + super.toString() + " (at: "
                + at.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HHmm")) + ")";
    }
}
