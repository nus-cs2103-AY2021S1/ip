package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Event extends Task {
    LocalDate at;

    public Event(String name, String at) {
        super(name);
        this.at = LocalDate.parse(at);
    }

    public String toData() {
        return "E | " + super.toData() + " | " + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
