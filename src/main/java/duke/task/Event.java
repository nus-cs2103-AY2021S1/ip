package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected LocalDate at;
    private String atString;

    public Event(String description, String at) {
        super(description);
        this.atString = at;
        try{
            this.atString = at;
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            this.at = null;
        }
    }

    @Override
    public String toDataString() {
        if (super.isDone) return "E | 1 | " + description + " | " + atString;
        else return "E | 0 | " + description + " | " + atString;
    }

    @Override
    public String toString() {
        if (this.at != null) return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        else return "[E]" + super.toString() + " (at: " + atString + ")";
    }
}
