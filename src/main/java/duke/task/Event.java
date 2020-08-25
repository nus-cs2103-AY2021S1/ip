package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected String at;
    protected LocalDate atDate;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        try {
            atDate = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            atDate = null;
        }
    }

    public Event(boolean isDone, String description, String at) {
        this(description, at);
        this.isDone = isDone;
    }

    @Override
    public String toStore() {
        String div = " | ";
        return "E" + div + (isDone ? "1" : "0") + div + description + div + at;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + (atDate == null ? at : atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")))
                + ")";
    }
}
