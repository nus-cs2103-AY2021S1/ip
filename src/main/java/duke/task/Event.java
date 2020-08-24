package main.java.duke.task;

import java.time.LocalDate;

public class Event extends Task {

    public Event(String description, boolean isComplete, LocalDate date) {
        super(description, isComplete, date);
    }

    @Override
    public String[] getDataString() {
        return new String[] {"deadline", String.valueOf(isComplete), this.description, this.date.toString()};
    }

    @Override
    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.description + " (at: " + super.getDateString() + ")";
    }
}
