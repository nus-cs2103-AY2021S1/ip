package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate date;

    // Constructor for Event
    public Event(String description, String date) {
        super(description);
        this.date = LocalDate.parse(date);
    }

    // Return string representation of Event
    @Override
    public String toString() {
        String dateString = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return " [E]" + super.toString() + " (at: " + dateString + ")";
    }
}
