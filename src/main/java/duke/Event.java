package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    public Event(String description, boolean isDone, LocalDate at) {
        super(description, isDone);
        this.at = at;
    }

    public String getTime() {
        return at.toString();
    }

    @Override
    public String toString() {
        return "[E]" 
                + super.toString() 
                + " (at: " 
                + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) 
                + ")";
    }
}
