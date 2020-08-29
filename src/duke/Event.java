package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    public Event(String description, String at) throws DateTimeParseException {
        super(description);
        this.deadline = LocalDateTime.parse(at);
        this.type = "E";
    }

    @Override
    public String toString() {
    return "[E]" + super.toString() + " (at: " + this.formatDeadline() + ")";
    }

}
