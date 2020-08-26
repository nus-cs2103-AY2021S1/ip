package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate date;
    protected String date2;

    // Constructor for Event
    public Event(String description, String date) {
        super(description);
        this.date = LocalDate.parse(date);
        this.date2 = date;
    }

    public LocalDate getDate() {
        return date;
    }

    // Get info to store in hard disk
    @Override
    public String[] getInfo() {
        return new String[] {"E", description, date2};
    }

    // Return string representation of Event
    @Override
    public String toString() {
        String dateString = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return " [E]" + super.toString() + " (at: " + dateString + ")";
    }
}
