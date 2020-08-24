package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    private String parseDateTime(String at) {
        LocalDate d = LocalDate.parse(at);
        return d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + parseDateTime(at) + ")";
    }

    @Override
    public String toFileString() {
        return "E\n" + super.getDone() + "\n" + super.toFileString() + "\n" + at + "\n\n";
    }
}