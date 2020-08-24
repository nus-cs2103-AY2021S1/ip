package tasks;

import timeformatter.TimeFormatter;

import java.time.LocalDate;

public class Event extends Task {

    protected LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + getStatusIcon() + super.toString() + "(at: "
                + TimeFormatter.prettyDate(at) + ")";
    }
}

