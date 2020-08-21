package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDate at;
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String record() {
        if(this.isCompleted) {
            return "E | 1 | " + description + " | " + at;
        } else {
            return "E | 0 | " + description + " | " + at;
        }
    }

    @Override
    public boolean isAt(LocalDate localDate) {
        return this.at.equals(localDate);
    }
}
