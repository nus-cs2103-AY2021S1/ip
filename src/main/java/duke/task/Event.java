package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime at;

    public Event(String description, String at) {
        super(description);
        this.at = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    public LocalDateTime getDateTime() {
        return this.at;
    }

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    @Override
    public String toFileString() {
        return "E | " + super.toFileString() + " | "
                + at.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + at.format(DateTimeFormatter.ofPattern("dd MMMM yyyy, hh:mm a")) + ")";
    }
}
