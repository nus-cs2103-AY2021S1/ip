package main.java;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event extends Task {

    protected LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    @Override
    public boolean hasSameDate(LocalDate theDate) {
        return at.toLocalDate().equals(theDate);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(this.DATE_FORMAT) + ")";
    }
}