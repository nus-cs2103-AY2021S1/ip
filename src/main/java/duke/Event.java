package duke;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Event extends Task {
    protected LocalDate at;
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }
    
    public LocalDate getAtWhen() {
        return this.at;
    }

    public Event(String description, boolean isDone, LocalDate time) {
        super(description, isDone);
        this.at = time;
    }
    
    @Override
    public String toString() {
        String formattedTimeAt = at.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format("[E]" + super.toString() + " (at: %s)", formattedTimeAt);
    }
}
