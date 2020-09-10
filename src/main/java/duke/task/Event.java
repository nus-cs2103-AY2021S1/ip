package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime at;

    /**
     * Creates an Event.
     *
     * @param description description of the Event
     * @param at date and time of the Event
     */
    public Event(String description, LocalDateTime at, String tag) {
        super(description, tag);
        this.at = at;
    }

    @Override
    public String toText() {
        String completionStatus = "0";
        if (this.isDone) {
            completionStatus = "1";
        }
        if (this.hasTag) {
            return "E" + " | " + completionStatus + " | " + this.description + " | "
                    + at.format(DateTimeFormatter.ofPattern("dd/MM/yyyy, HHmm"))
                    + tag.toString();
        } else {
            return "E" + " | " + completionStatus + " | " + this.description + " | "
                    + at.format(DateTimeFormatter.ofPattern("dd/MM/yyyy, HHmm"));
        }
    }

    @Override
    public String toString() {
        if (this.hasTag) {
            return "[E]" + this.getStatusIcon() + " " + super.toString() + " (at: "
                    + at.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HHmm")) + ")"
                    + this.tag.toString();
        } else {
            return "[E]" + this.getStatusIcon() + " " + super.toString() + " (at: "
                    + at.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HHmm")) + ")";
        }
    }
}
